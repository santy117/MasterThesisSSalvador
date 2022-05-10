from kafka import KafkaConsumer
from json import loads
from pymongo import MongoClient

consumer = KafkaConsumer(
    'my_topic3',
     bootstrap_servers=['localhost:9092'],
     auto_offset_reset='earliest',
     enable_auto_commit=True,
     group_id='my_group_id',
     value_deserializer=lambda x: loads(x.decode('utf-8')))


for message in consumer:
    message = message.value
   
    print('{} added'.format(message))