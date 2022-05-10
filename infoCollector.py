import mysql.connector
from time import sleep
import json
from bson import json_util
from kafka import KafkaProducer
from pymongo import MongoClient

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="password",
  database="tfm"
)

mycursor = mydb.cursor(dictionary=True)

name = "santi"
mycursor.execute("SELECT * FROM registro_peticion rp WHERE rp.usuario=%s", (name,))

myresult = mycursor.fetchall()
data = json.dumps(myresult)
print(f"json: {json.dumps(myresult)}")
with open('data.json', 'w', encoding='utf-8') as f:
    json.dump(data, f, ensure_ascii=False, indent=4)


producer = KafkaProducer(bootstrap_servers=['localhost:9092'],
                         value_serializer=lambda x: 
                         json.dumps(x).encode('utf-8'))

client = MongoClient('localhost:27017')
collection = client.tfm.registrosPeticion
if len(myresult) > 0:
        y = collection.delete_many({})
        x = collection.insert_many(myresult) #myresult comes from mysql cursor
        print(len(x.inserted_ids))
for e in range(1):
    data = { 'idVersion' : 2,
    'gastos': 'kafkaNotificacion',
    'informacion': 'kafkaNotificacion'
  }
    header = {"__TypeId__":"com.master.demo.Entities.MensajeIA"}
    mensajeIA =  "com.master.demo.Entities.MensajeIA"
    mensajeIABytes = mensajeIA.encode('utf-8')
    lista = [("__TypeId__",mensajeIABytes)]
    producer.send('my_topic3', headers = lista, value=data)
    producer.flush()
    sleep(1)
