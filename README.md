# Hundir la flota es un proyecto en java creado desde cero el cual consta de una imitacion del famoso juego hundir la flota.

#Captura de la pantalla de inicio: https://imgur.com/mDJkndj

#En el juego el jugador empezara rellenando su tablero eligiendo coordenadas para sus barcos (4 veleros de tamaño 1, 3 fragatas de tamaño 2,
#2 buques de tamaño 3 y un portaviones de tamaño 4) y decidiendo la orientacion de estos.

#Captura de pantalla "colocando barcos": https://imgur.com/qAWdUiS

#Una vez el jugador crea su tablero tendra que selecionar casillas a las cuales 
#disparar, a su vez cada vez que el jugador dispara la maquina disparara, ganara 
#el primero en hundir todos los barcos de el otro.

#Captura de pantalla "disparo jugador": https://imgur.com/Z0UxUiy
#Captura de pantalla "disparo maquina": https://imgur.com/RQin7lc

#A su vez la maquina dispara de forma inteligente, al principio
#dispara a casillas aleatorias hasta encontrar un barco

#Una vez encuentra el barco disparar a casillas cercanas hasta hundirlo

#El juego no permite que dos barcos esten adyacentes por lo que la maquina
#nunca disparar cerca de un barco ya hundido

#Captura de el dipsaro inteligente de la maquina:
https://imgur.com/1KV5j4U (Encuentra un barco)
https://imgur.com/TojHQXO (Busca cerca)
https://imgur.com/R9xG491 (Encuentra la horientacion de el barco por lo que no para de disparar en la misma direccion)
https://imgur.com/yEO6lUB (Lo hunde asi que ya no dispara mas cerca)

#El juego controla todos los posibles errores de el usuario, no deja barcos adyacentes, 
#no deja posicionar barcos fuera de los limites, no deja poner un barco sobre otro
#no deja disparar dos veces en el mismo lugar etc.

#Capturas de control de errores:
https://imgur.com/hqAgGDe
https://imgur.com/RQbjMnG
https://imgur.com/tkS5aPD
