# hundir-la-flota


### Descripcion

Hundir la flota es un juego creado desde cero con el lenguage java, esta inspirado en el famoso juego hundir la flota, en este juego jugaras en contra de una maquina inteligente la cual es capaz de aprovechar las reglas del juego para optimizar sus disparos.


### Como jugar

#### Metodo 1: 

Para jugar este juego hace falta tener java instalado, ir a la seccion de release y descargar el jar, para ejecutar el jar hay que abrir la consola de comandos y usar el comando: java -jar "[ubicacion de el archivo ]"

#### Metodo 2: 
Clonar el repositorio en netbeans (o cualquier otro IDE) y ejecutar el archivo main "HundirLaFlota.java"


### Caracteristicas y vista previa


#### Inicio de el Juego y fase de colocacion de los barcos

En el juego el jugador empezara rellenando su tablero eligiendo coordenadas para sus barcos (4 veleros de tamaño 1, 3 fragatas de tamaño 2, 2 buques de tamaño 3 y un portaviones de tamaño 4) y decidiendo la orientacion de estos.

[Captura de la pantalla de inicio ](https://imgur.com/mDJkndj) <br />
[Captura de pantalla de la fase de colocacion de barcos](https://imgur.com/qAWdUiS)


#### Fase de disparos

Una vez el jugador crea su tablero tendra que selecionar casillas a las cuales disparar, a su vez cada vez que el jugador dispara la maquina disparara, ganara el primero en hundir todos los barcos de el otro.

[Captura de pantalla "disparo jugador"](https://imgur.com/Z0UxUiy) <br />
[Captura de pantalla "disparo maquina"](https://imgur.com/RQin7lc) 


#### Explicacion de el disparo Inteligente de la maquina

A su vez la maquina dispara de forma inteligente, al principio dispara a casillas aleatorias hasta encontrar un barco, una vez encuentra el barco disparar a casillas cercanas hasta hundirlo.

El juego no permite que dos barcos esten adyacentes por lo que la maquina nunca disparar cerca de un barco ya hundido.

Capturas de el dipsaro inteligente de la maquina: <br />
https://imgur.com/1KV5j4U (Encuentra un barco) <br />
https://imgur.com/TojHQXO (Busca cerca) <br />
https://imgur.com/R9xG491 (Encuentra la horientacion de el barco por lo que no para de disparar en la misma direccion) <br />
https://imgur.com/yEO6lUB (Lo hunde asi que ya no dispara mas cerca) <br />


#### Control de errores de el programa

El juego controla todos los posibles errores de el usuario, no deja barcos adyacentes, no deja posicionar barcos fuera de los limites, no deja poner un barco sobre otro no deja disparar dos veces en el mismo lugar etc.

Capturas de control de errores: <br />
https://imgur.com/hqAgGDe <br />
https://imgur.com/RQbjMnG <br />
https://imgur.com/tkS5aPD <br />
