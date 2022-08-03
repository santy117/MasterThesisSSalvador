import requests
import json
import random

versiones = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
usuario = 'scenario3'
weights_number = [ 3, 3, 3, 1, 1, 1, 1, 1, 1, 1]
numPartidas = 1500

for i in range(numPartidas):
    idVersion = random.choices(versiones, weights=weights_number, k=1)
    print('Leyendo partida numero '+ str(i)+ " para la version "+ str(idVersion))
    url = 'http://localhost:8080/partidas/version/' + str(idVersion[0]) + '?user='+ usuario
    session = requests.Session()
    session.auth = ("admin", "password")
    r = session.get(url)

    print(r.text)