import requests
import json
import random

versiones = ''
usuario = 'santi'
numPartidas = 1
menu_options = {
    1: 'Seleccionar rango versiones a generar aleatorios(x - y)',
    2: 'Seleccionar usuario (santi por defecto)',
    3: 'Seleccionar numero de partidas a leer (1 por defecto)',
    4: 'Ejecutar',
    5: 'Salir',
}

def print_menu():
    for key in menu_options.keys():
        print (key, '--', menu_options[key] )

def option1():
     value = input("Introduce el rango de versiones:\n")
     versiones = value
     return versiones

def option2():
     value = input("Introduce el usuario:\n")
     usuario = value 
     return usuario

def option3():
     value = input("Introduce el numero de partidas a leer:\n")
     numPartidas = int(value) 
     return numPartidas

def option4():
     for i in range(numPartidas):
        versionMinima = int(versiones.split("-")[0])
        versionMaxima = int(versiones.split("-")[1])
        idVersion = random.randrange(versionMinima, versionMaxima, 1)
        print('Leyendo partida numero '+ str(i)+ " para la version "+ str(idVersion))
        url = 'http://localhost:8080/partidas/version/' + str(idVersion) + '?user='+ usuario
        session = requests.Session()
        session.auth = ("admin", "password")
        r = session.get(url)

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
           versiones = option1()
        elif option == 2:
           usuario = option2()
        elif option == 3:
           numPartidas = option3()
        elif option == 4:
            option4()
        elif option == 5:
            print('Bye!')
            exit()
        else:
            print('Introduce un numero entre 1 y 4')
