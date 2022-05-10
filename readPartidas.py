import requests
import json

idVersion = 0
numPartidas = 1
menu_options = {
    1: 'Seleccionar idVersion',
    2: 'Seleccionar numero de partidas a leer (1 por defecto)',
    3: 'Ejecutar',
    4: 'Salir',
}

def print_menu():
    for key in menu_options.keys():
        print (key, '--', menu_options[key] )

def option1():
     value = input("Introduce el id de version:\n")
     idVersion = int(value) 
     return idVersion
def option2():
     value = input("Introduce el numero de partidas a leer:\n")
     numPartidas = int(value) 
     return numPartidas
def option3():
     for i in range(numPartidas):
        print('Leyendo partida numero '+ str(i))
        url = 'http://localhost:8080/partidas/version/' + str(idVersion)
        r = requests.get(url)

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
           idVersion = option1()
        elif option == 2:
           numPartidas = option2()
        elif option == 3:
            option3()
        elif option == 4:
            print('Bye!')
            exit()
        else:
            print('Introduce un numero entre 1 y 4')
