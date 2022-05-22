import mysql.connector
from time import sleep
import json
from bson import json_util
from kafka import KafkaProducer
from pymongo import MongoClient

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


#method to generate n-grams:
#params:
#text-the text for which we have to generate n-grams
#ngram-number of grams to be generated from the text(1,2,3,4 etc., default value=1)

def generate_N_grams(text,ngram=1):
  words=[word for word in text.split(" ") if word != ""]  
  print("Numbers: ",words)
  temp=zip(*[words[i:] for i in range(0,ngram)])
  ans=[' '.join(ngram) for ngram in temp]
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
  print(item)
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
        print(len(x.inserted_ids))

#Aqui comienza la parte de procesado para cada usuario y envio de mensaje a cola con el resultado de cada usuario
for usuario in list_users:
  texto = ""
  for item in myresult:
    if item['usuario'] == usuario:
      texto = texto + str(item['id_version']) + " "

  #NGRAMAS
  conteoNgrama=defaultdict(int)
  for word in generate_N_grams(texto, 3):
      conteoNgrama[word]+=1

  #focus on more frequently occuring numbers
  df_conteo=pd.DataFrame(sorted(conteoNgrama.items(),key=lambda x:x[1],reverse=True))

  pd1=df_conteo[0][:10]
  pd2=df_conteo[1][:10]

  plot_ngrama(pd1,pd2, "trigrama", usuario)

  version_mas_probable = df_conteo[0][0]
  print("Version mas probable de trigrama: "+ version_mas_probable)
  send_kafka_message(usuario, version_mas_probable)
