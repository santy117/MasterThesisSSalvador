import mysql.connector
from time import sleep
import json
from bson import json_util
from kafka import KafkaProducer
from pymongo import MongoClient
import random
from scipy import stats 

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from collections import defaultdict
plt.style.use(style='seaborn')

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="password",
  database="tfm"
)

#IA PARAMETERS weighted random choices
#random.choices(population, weights=None, *, cum_weights=None, k=1)
#population: It is is sequence or data structure from which you want to choose data.
#weights or cum_weights: Define the selection probability for each element.
#weights: If a weights sequence is specified, random selections are made according to the relative weights.
#cum_weights: Alternatively, if a cum_weights sequence is given, the random selections are made according to the cumulative weights.
#k: The number of samples you want from a population.
#alpha: wight of the element with most probabilities
#Probability = element_weight/ sum of all weights
alpha = 0.8


#IA PARAMETERS Probability based on number of occurences
#probability = number of occurences of the pattern / total number of occurences 



#method to generate n-grams:
#params:
#text-the text for which we have to generate n-grams
#ngram-number of grams to be generated from the text(1,2,3,4 etc., default value=1)

def generate_N_grams(text,ngram=1):
  words=[word for word in text.split(" ") if word != ""]  
  #print("Numbers: ",words)
  temp=zip(*[words[i:] for i in range(0,ngram)])
  ans=[' '.join(ngram) for ngram in temp]
  print(ans)
  return ans


def send_kafka_message(user, prediction):
  producer = KafkaProducer(bootstrap_servers=['localhost:9092'],
                          value_serializer=lambda x: 
                          json.dumps(x).encode('utf-8'))
  data = { 'usuario' : user,
  'versiones': prediction
  }
  mensajeIA =  "com.master.demo.Entities.MensajeIA"
  mensajeIABytes = mensajeIA.encode('utf-8')
  lista = [("__TypeId__",mensajeIABytes)]
  producer.send('my_topic3', headers = lista, value=data)
  producer.flush()

def plot_ngrama(x, y, tipo, usuario):
  plt.figure(1,figsize=(16,4))
  plt.bar(x,y, color ='green',
          width = 0.4)
  plt.xlabel("Object versions reviewed by the user "+ usuario)
  plt.ylabel("Count")
  plt.title("Top 10 object versions reviewed by user "+usuario+"-"+tipo+" analysis")
  plt.savefig(tipo+".png")
  plt.show()

mycursor = mydb.cursor(dictionary=True)
name = 'LECTURA'
mycursor.execute("SELECT * FROM registro_peticion rp WHERE rp.tipo_peticion=%s", (name,))

myresult = mycursor.fetchall()
data = json.dumps(myresult)
#print(f"json: {json.dumps(myresult)}")
list_users = []
for item in myresult:
  #print(item)
  if item['usuario'] not in list_users:
    list_users.append(item['usuario'])
print("list of users: ", list_users)
with open('data.json', 'w', encoding='utf-8') as f:
    json.dump(data, f, ensure_ascii=False, indent=4)

client = MongoClient('localhost:27017')
collection = client.tfm.registrosPeticion
if len(myresult) > 0:
        y = collection.delete_many({})
        x = collection.insert_many(myresult) #myresult comes from mysql cursor
        #print(len(x.inserted_ids))

#Aqui comienza la parte de procesado para cada usuario y envio de mensaje a cola con el resultado de cada usuario
for usuario in list_users:
  texto = ""
  for item in myresult:
    if item['usuario'] == usuario:
      texto = texto + str(item['id_version']) + " "

  #NGRAMAS
  conteoNgrama=defaultdict(int)
  for word in generate_N_grams(texto, 3):
      numeros = word.split(' ')
      #solo guardamos el ngrama si no tiene valores repetidos
      if (len(numeros) - len(set(numeros)) == 0):
        conteoNgrama[word]+=1
      

  #focus on more frequently occuring numbers
  df_conteo=pd.DataFrame(sorted(conteoNgrama.items(),key=lambda x:x[1],reverse=True))
  print("ngrama limpio: ")
  print(df_conteo.values.tolist())
  pd1=df_conteo[0][:10]
  pd2=df_conteo[1][:10]

  #plot_ngrama(pd1,pd2, "trigrama", usuario)

#Probability based on alpha parameter
#GEOMETRIC DISTRIBUTION p(r;p)=p(1−p)r−1
  geometrica = stats.geom(alpha) # Distribución
  x = np.arange(geometrica.ppf(0.0001),
              geometrica.ppf(0.9999))
  fmp = geometrica.pmf(x) # Función de Masa de Probabilidad
  weights_p = []
  for i in range(pd1.size):
    if(0 <= i < fmp.size):
      weights_p.append(fmp[i])
    else:
      weights_p.append(0)

  print(str(weights_p))
  version_mas_probable_alpha = random.choices(pd1, weights=weights_p, k=1)

#Probability based on number of occurences
  weights_number = []
  total_weight = 0
  for i in range(pd1.size):
    total_weight = total_weight + int(df_conteo[1][i])
  
  print("total weight " + str(total_weight))
  for j in range(pd1.size):
    weights_number.append(df_conteo[1][j] / total_weight)
  print("pesos basados en numero de apariciones: " + str(weights_number))
  version_mas_probable_number = random.choices(pd1, weights=weights_number, k=1)
  print("Version mas probable de trigrama con parametro alpha: "+ str(version_mas_probable_alpha))
  print("Version mas probable de trigrama con pesos basados en numero de apariciones: "+ str(version_mas_probable_number))
  #send_kafka_message(usuario, str(version_mas_probable_alpha))
