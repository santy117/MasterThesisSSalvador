import requests
import json

pload =  '{"idVersion": 1, "gastos": "gastos python script", "informacion": "informacion python script" }'
data = json.loads(pload)
numPartidas = 1
menu_options = {
    1: 'Seleccionar idVersion',
    2: 'Seleccionar numero de partidas a insertar (1 por defecto)',
    3: 'Ejecutar',
    4: 'Salir',
}

def print_menu():
    for key in menu_options.keys():
        print (key, '--', menu_options[key] )

def option1():
     value = input("Introduce el id de version:\n")
     data['idVersion'] = int(value)
def option2():
     value = input("Introduce el numero de partidas a insertar:\n")
     numPartidas = int(value) 
     return numPartidas
def option3():
     headers = {'Content-type': 'application/json', 'Accept': '*/*'}
     for i in range(numPartidas):
        print('Insertando partida numero '+ str(i) +': ' + json.dumps(data))
        session = requests.Session()
        session.auth = ("admin", "password")
        r = session.post('http://localhost:8080/partidas',data = json.dumps(data), headers=headers)

        print(r.text)

if __name__=='__main__':
    while(True):
        print_menu()
        option = ''
        try:
            option = int(input('Elige una opcion: '))
        except:
            print('Por favor elige un numero')
        #Check what choice was entered and act accordingly
        if option == 1:
           option1()
        elif option == 2:
           numPartidas = option2()
        elif option == 3:
            option3()
        elif option == 4:
            print('Bye!')
            exit()
        else:
            print('Introduce un numero entre 1 y 4')
