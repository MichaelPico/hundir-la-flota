package hundirlaflota;

import java.util.Scanner;

public class Tablero { //El array de coordenadas esta en formato [y][x]

    //Atributo char Array el cual contendra todo el juego
    private char[][] tablero;
    private boolean jugador; //Si el dueño es el jugador sera true sino sera false

    //Constructor el cual por defecto inicia el Array de tamaño 10x10 y llama un metodo
    //para que llene el array de mar
    //TESTEADO
    public Tablero(boolean jugador) {
        this.tablero = new char[10][10];
        iniciarTablero();
        this.jugador = jugador; //Si es false el jugador sera la maquina
    }

    //Metodo prepararTablero/iniciarTablero el cual es llamado en el constructor y llena
    //el tablero de mar
    //TESTEADO
    public void iniciarTablero() {
        //Uso un doble bucle para llenar el tablero de mar
        for (int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero[i].length; j++) {
                this.tablero[i][j] = '.';
            }
        }
    }

    //Metodo mostrar tablero el cual muestra el tablero en el formato adecuado
    //(los barcos de la maquina no los muestra, si hay un barco simplemente muestra 
    //mar aunque el tablero si que guarda sus posiciones)
    //TESTEADO
    public static void mostrarTablero(Tablero tablero) {

        //Guardo el tablero en un char[][] para que sea mas facil trabajar con el y declaro
        //un char[] con las letras para mostrarlas en las coordenadas
        char[][] mostrando = tablero.getTablero();
        char[] letrasPosibles = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        //Muestro el titulo del tablero
        if (tablero.getJugador()) {
            System.out.println(ANSI_CYAN + ANSI_BLACK_BACKGROUND +"===================================   MAR DEL JUGADOR   ==================================" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + ANSI_BLACK_BACKGROUND + "===================================  MAR DE LA MAQUINA  ==================================" + ANSI_RESET);
        }

        //Muestro la primera fila de numeros para coordenadas
        System.out.println(" \t0\t1\t2\t3\t4\t5\t6\t7\t8\t9\t||");

        //Bucle que recorre la primera dimension
        for (int i = 0; i < mostrando.length; i++) {

            //Muestro una letra por columna para orientar al jugador
            System.out.print(letrasPosibles[i] + "\t");

            //Bucle que recorre la segunda dimension del array
            for (int j = 0; j < mostrando[i].length; j++) {

                //Aqui solo entra cuando es el tablero del jugador, al ser un jugador
                //le muestro todo
                if (tablero.getJugador()) {
                    
                    if (mostrando[i][j] == '¤'){
                        System.out.print(ANSI_RED + mostrando[i][j] + ANSI_RESET + "\t");
                    } else {
                        System.out.print(mostrando[i][j] + "\t");
                    }

                    //Este else se ejecuta en el caso de que el tablero sea de la maquina, 
                    //al ser la maquina uso un if para no mostrar la posicion de sus barcos
                } else {
                    if (mostrando[i][j] == 'V' || mostrando[i][j] == 'F' || mostrando[i][j] == 'B' || mostrando[i][j] == 'P') {
                        System.out.print(".\t");
                    } else if (mostrando[i][j] == '¤'){
                        System.out.print(ANSI_CYAN + mostrando[i][j] + ANSI_RESET + "\t");
                    } else {
                        System.out.print(mostrando[i][j] + "\t");
                    }
                }
            }
            System.out.print("||");
            System.out.println("");
        }
        System.out.println("==========================================================================================");
    }

    //Metodo comprobar tablero el cual comprueba que hay algun barco vivo
    //Este metodo recibe un tablero y devuelve un boolean si al recorrerlo encuentra un barco
    //lo recorre todo con un doble bucle y atravez de un if mira si hay un barco
    //TESTEADO
    public static boolean comprobarTablero(Tablero tablero) {

        //Guardo el tablero en un char[][] para que sea mas facil trabajar con el
        char[][] comprobando = tablero.getTablero();

        //Doble bucle e if que comprueban que queden barcos vivos
        for (int i = 0; i < comprobando.length; i++) {
            for (int j = 0; j < comprobando[i].length; j++) {
                if (comprobando[i][j] == 'V' || comprobando[i][j] == 'F' || comprobando[i][j] == 'B' || comprobando[i][j] == 'P') {
                    return true;
                }
            }
        }

        return false;
    }

    //Metodo actualizar tablero el cual recibe coordenadas y un char
    //(en caso de estar colocando el barco, este metodo es llamada varias veces segun el 
    //tamaño del barco, ejemplo: barco tamaño 3 se llama el metodo 3 veces con el mismo char
    //pero 3 coordenanas)
    //TESTEADO
    public void actualizarTablero(int[] coordenadas, char caracter) {
        this.tablero[coordenadas[0]][coordenadas[1]] = caracter;
    }

    //Metodo colocar barco el cual recibe el tipo del barco, la coordenada, la orientacion
    //y comprueba que la coordenana es valida (no se sale de los limites y cumple las 
    //condiciones) y llama al metodo actualizar tablero las veces necesarias
    //TESTEADO
    public void colocarBarco(char tipoBarco, int[] coordenadas, char orientacion) throws ErrorFlota {

        //Primero determino el tamaño del barco segun su tipo
        int tamaño = 0;

        switch (tipoBarco) {
            case 'V':
            case 'v':
                tamaño = 1;
                break;
            case 'F':
            case 'f':
                tamaño = 2;
                break;
            case 'B':
            case 'b':
                tamaño = 3;
                break;
            case 'P':
            case 'p':
                tamaño = 4;
                break;
            default:
                //Si el tipo de barco recibido por el metodo no es valido tiro error
                throw new ErrorFlota("EL TIPO DE BARCO NO ES VALIDO!!");
        }

        //Ahora chequeo que el barco no se salga de los limites y lo coloco
        if (orientacion == 'v' || orientacion == 'V') {

            //Chequeo que su posicion sea valida
            try {
                posicionValida(coordenadas, orientacion, tamaño);
            } catch (ErrorFlota e) {
                throw e;
            }

            //Si el tipo de barco y la orientacion son validas coloco el barco y termino el metodo
            for (int i = 0; i < tamaño; i++) {
                int y = coordenadas[0] + i; //La coordenada y aumenta 1 cada vuelta del bucle, el bucle da numero de vueltas el tamaño del barco
                int[] posicionBarco = new int[]{y, coordenadas[1]}; //este array como auxiliar
                actualizarTablero(posicionBarco, tipoBarco);
            }

        } else if (orientacion == 'h' || orientacion == 'H') {

            try {
                posicionValida(coordenadas, orientacion, tamaño);
            } catch (ErrorFlota e) {
                throw e;
            }

            //Si el tipo de barco y la orientacion son validas coloco el barco y termino el metodo
            for (int i = 0; i < tamaño; i++) {
                int x = coordenadas[1] + i; //La coordenada x aumenta 1 cada vuelta del bucle, el bucle da numero de vueltas el tamaño del barco
                int[] posicionBarco = new int[]{coordenadas[0], x}; //este array como auxiliar ya que el metodo actualizar tablero necesita recibir un int[] con las coordenadas
                actualizarTablero(posicionBarco, tipoBarco);
            }
        }
    }

    //Metodo el cual mira que el barco este siendo colocado en una posicion valida, sin ninguno adyacente
    //y sin salirse de los limites
    //TESTEADO PARA MAQUINA
    public void posicionValida(int[] coordenadas, char orientacion, int tamaño) throws ErrorFlota {

        //Variable que uso para ayudarme
        int extremo;
        //Entra a este if cuando la orientacion es vertical
        if (orientacion == 'v' || orientacion == 'V') {
            extremo = tamaño + coordenadas[0];
            //Ahora chequeo que el barco no se salga del limite superior
            if (extremo > 10) { //Si el barco se sale del limite tiro un error
                throw new ErrorFlota("EL BARCO SE SALE DEL LIMITE INFERIOR!!");
            } else {

                //Este bloque de codigo evita que el siguiente buecle for mire casillas de arriba fuera del limite
                int a = -1;
                if (coordenadas[0] == 0) {
                    a = 0;
                }
                
                //Este bucle for comprueba que no hay ningun barco en las casillas que voy a usar para colocar el barco y en sus adyacentes verticales
                //Ya que estoy comprobando las adyacentes miro desde coordenadas[0] - 1 hasta tamaño+1 (miro todo lo que ocupa el barco+1 casilla de arriba y otra de abajo)
                for (int i = a; i < tamaño + 1; i++) {
                    int y = coordenadas[0] + i;
                    int[] coordenadaComprobada = new int[]{y, coordenadas[1]};

                    //Basicamente aqui estoy comprobando que no haya un barco en las posiciones, ignoro los casos donde hay numeros o letras ya que ya comprobe que no se salia del tablero
                    if (getCasilla(coordenadaComprobada) == 'V' || getCasilla(coordenadaComprobada) == 'F' || getCasilla(coordenadaComprobada) == 'B' || getCasilla(coordenadaComprobada) == 'P') {
                        throw new ErrorFlota("ERROR el barco no puede estar adyacente a otro o sobre otro");
                    }
                    //Con este if evito mirar las casillas que estan fuera
                    if (y == 9) {
                        break;
                    }
                }

                //Este if se encarga de evitoar mirar las casillas de la izquierda si estas estan fuera del limite
                if (coordenadas[1] > 0) {
                //Este bucle comprobara las casillas del lado izquierdo del barco para ver que no haya ningun barco adyacente por la izquierda
                    for (int i = 0; i < tamaño; i++) {
                        int y = coordenadas[0] + i;
                        int x = coordenadas[1] - 1; //Menos 1 para mirar por la izquierda de donde quiero colocar el barco
                        int[] coordenadaComprobada = new int[]{y, x};

                        //Basicamente aqui estoy comprobando que no haya un barco en las posiciones, ignoro los casos donde hay numeros o letras ya que ya comprobe que no se salia del tablero
                        if (getCasilla(coordenadaComprobada) == 'V' || getCasilla(coordenadaComprobada) == 'F' || getCasilla(coordenadaComprobada) == 'B' || getCasilla(coordenadaComprobada) == 'P') {
                            throw new ErrorFlota("ERROR el barco no puede estar adyacente a otro");
                        }
                    }
                }

                //Este if se encarga de evitoar mirar las casillas de la izquierda si estas estan fuera del limite
                if (coordenadas[1] < 9) {
                //Este bucle comprobara las casillas del lado derecho del barco para ver que no haya ningun barco adyacente por la derecha
                    for (int i = 0; i < tamaño; i++) {
                        int y = coordenadas[0] + i;
                        int x = coordenadas[1] + 1; //mas 1 para mirar a la derecha de donde quiero colocar el barco
                        int[] coordenadaComprobada = new int[]{y, x};

                        //Basicamente aqui estoy comprobando que no haya un barco en las posiciones, ignoro los casos donde hay numeros o letras ya que ya comprobe que no se salia del tablero
                        if (getCasilla(coordenadaComprobada) == 'V' || getCasilla(coordenadaComprobada) == 'F' || getCasilla(coordenadaComprobada) == 'B' || getCasilla(coordenadaComprobada) == 'P') {
                            throw new ErrorFlota("ERROR el barco no puede estar adyacente a otro");
                        }
                    }
                }

            }
            // Entra aqui cuando la orientacion es horizontal
        } else {
            extremo = tamaño + coordenadas[1];
            //Ahora chequeo que el barco no se salga del limite deerecho
            if (extremo > 10) { //Si el barco se sale del limite tiro un error
                throw new ErrorFlota("EL BARCO SE SALE DEL LIMITE DERECHO!!");
            } else {

                //Este bloque de codigo evita que el siguiente buecle for mire casillas por fuera de la izquierda
                int a = -1;
                if (coordenadas[1] == 0) {
                    a = 0;
                }
                
                //Este bucle for comprueba que no hay ningun barco en las casillas que voy a usar para colocar el barco y en sus adyacentes horizontales
                //Ya que estoy comprobando las adyacentes miro desde coordenadas[0] - 1 hasta tamaño+1 (miro todo lo que ocupa el barco+1 casilla de la izquierda y otra de la derecha)
                for (int i = a; i < tamaño + 1; i++) {
                    int x = coordenadas[1] + i;
                    int[] coordenadaComprobada = new int[]{coordenadas[0], x};

                    //Basicamente aqui estoy comprobando que no haya un barco en las posiciones, ignoro los casos donde hay numeros o letras ya que ya comprobe que no se salia del tablero
                    if (getCasilla(coordenadaComprobada) == 'V' || getCasilla(coordenadaComprobada) == 'F' || getCasilla(coordenadaComprobada) == 'B' || getCasilla(coordenadaComprobada) == 'P') {
                        throw new ErrorFlota("ERROR el barco no puede estar adyacente a otro o sobre otro");
                    }
                    //Con este if evito mirar las casillas que estan fuera por la derecha
                    if (x == 9) {
                        break;
                    }
                }

                //Este if se encarga de evitar mirar las casillas fuera del limite superior
                if (coordenadas[0] > 0) {
                    //Este bucle comprobara las casillas del lado superior del barco para ver que no haya ningun barco adyacente por arriba
                    for (int i = 0; i < tamaño; i++) {
                        int y = coordenadas[0] - 1; //Menos 1 para mirar por arriba de donde quiero colocar el barco
                        int x = coordenadas[1] + i;
                        int[] coordenadaComprobada = new int[]{y, x};

                        //Basicamente aqui estoy comprobando que no haya un barco en las posiciones, ignoro los casos donde hay numeros o letras ya que ya comprobe que no se salia del tablero
                        if (getCasilla(coordenadaComprobada) == 'V' || getCasilla(coordenadaComprobada) == 'F' || getCasilla(coordenadaComprobada) == 'B' || getCasilla(coordenadaComprobada) == 'P') {
                            throw new ErrorFlota("ERROR el barco no puede estar adyacente a otro");
                        }
                    }
                }
                
                //El if se encarga de evitar mirar las casillas fuera del limite de abajo
                if (coordenadas[0] < 9) {
                //Este bucle comprobara las casillas de abajo del barco para ver que no haya ningun barco adyacente por abajo
                    for (int i = 0; i < tamaño; i++) {
                        int y = coordenadas[0] + 1; //Mas 1 para mirar por debajo de donde quiero colocar el barco
                        int x = coordenadas[1] + i;
                        int[] coordenadaComprobada = new int[]{y, x};

                        //Basicamente aqui estoy comprobando que no haya un barco en las posiciones, ignoro los casos donde hay numeros o letras ya que ya comprobe que no se salia del tablero
                        if (getCasilla(coordenadaComprobada) == 'V' || getCasilla(coordenadaComprobada) == 'F' || getCasilla(coordenadaComprobada) == 'B' || getCasilla(coordenadaComprobada) == 'P') {
                            throw new ErrorFlota("ERROR el barco no puede estar adyacente a otro");
                        }
                    }
                }
            }
        }
    }

    //Metodo rellenarBarcos el cual coloca todos los barcos en el tablero
    //TESTEADO
    public void rellenarBarcos() {

        //Este if solo si el tablero pertenece al jugador
        if (jugador) {
            for (int i = 0; i < 4; i++) {
                while (true) {
                    try {
                        System.out.println("");
                        System.out.println("Introduce una coordenada para colocar un Velero");
                        colocarBarco('V', Tablero.introducirCoordenadas(), 'h');
                        break;
                    } catch (ErrorFlota e) {
                        System.out.println(e.getMessage());
                    }
                }
                mostrarTablero(this);
            }
            for (int i = 0; i < 3; i++) {
                while (true) {
                    try {
                        System.out.println("");
                        System.out.println("Introduce una coordenada para colocar una Fragata");
                        colocarBarco('F', Tablero.introducirCoordenadas(), Tablero.pedirOrientacion());
                        break;
                    } catch (ErrorFlota e) {
                        System.out.println(e.getMessage());
                    }
                }
                mostrarTablero(this);
            }
            for (int i = 0; i < 2; i++) {
                while (true) {
                    try {
                        System.out.println("");
                        System.out.println("Introduce una coordenada para colocar un Buque");
                        colocarBarco('B', Tablero.introducirCoordenadas(), Tablero.pedirOrientacion());
                        break;
                    } catch (ErrorFlota e) {
                        System.out.println(e.getMessage());
                    }
                }
                mostrarTablero(this);
            }

            while (true) {
                try {
                    System.out.println("");
                    System.out.println("Introduce una coordenada para colocar un Portaviones");
                    colocarBarco('P', Tablero.introducirCoordenadas(), Tablero.pedirOrientacion());
                    mostrarTablero(this);
                    break;
                } catch (ErrorFlota e) {
                    System.out.println(e.getMessage());
                }
            }
            
            System.out.println("\n========================================================================");
            System.out.println("Bravo ya rellenaste tu tablero, el siguiente tablero es el de la maquina");
            System.out.println("========================================================================\n");

            //En caso de ser el tablero de una maquina se ejecuta este, los catch no muestran el error ya que no quiero que el jugador lo sepa
        } else {
            for (int i = 0; i < 4; i++) {
                while (true) {
                    try {
                        colocarBarco('V', Tablero.generarCoordenadas(), Tablero.generarOrientacion());
                        break;
                    } catch (Exception e) {
                        //no hago nada, simplemente sigo intentando
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                while (true) {
                    try {
                        colocarBarco('F', Tablero.generarCoordenadas(), Tablero.generarOrientacion());
                        break;
                    } catch (Exception e) {
                        //no hago nada, simplemente sigo intentando
                    }
                }
            }
            for (int i = 0; i < 2; i++) {
                while (true) {
                    try {
                        colocarBarco('B', Tablero.generarCoordenadas(), Tablero.generarOrientacion());
                        break;
                    } catch (Exception e) {
                        //no hago nada, simplemente sigo intentando
                    }
                }
            }
            while (true) {
                try {
                    colocarBarco('P', Tablero.generarCoordenadas(), Tablero.generarOrientacion());
                    break;
                } catch (Exception e) {
                    //no hago nada, simplemente sigo intentando
                }
            }
        }
    }

    //Metodo que genera coordenadas para colocar los barcos de la maquina
    //TESTEADO
    public static int[] generarCoordenadas() {

        int[] coordenadas = new int[2];

        coordenadas[0] = (int) (Math.random() * 10);
        coordenadas[1] = (int) (Math.random() * 10);

        return coordenadas;
    }

    //Metodo que genera una orientacion para colocar el barco de la maquina
    //TESTEADO
    public static char generarOrientacion() {

        int auxiliar = (int) Math.round(Math.random());
        char orientacion = 0;

        if (auxiliar == 0) {
            orientacion = 'v';
        } else if (auxiliar == 1) {
            orientacion = 'h';
        }

        return orientacion;
    }

    //Metodo que pregunta al jugador en que orientacion quiere colocar el barco
    //comprueba que la orientacion es valida y la devuelve
    //TESTEADO
    public static char pedirOrientacion() throws ErrorFlota {
        Scanner datos = new Scanner(System.in);
        System.out.println("¿En que orientacion quieres que este el barco? (h/v)");
        String respuesta = datos.next();

        //Compruebo que el jugador solo coloque un caracter
        if (respuesta.length() > 1) {
            throw new ErrorFlota("ORIENTACION NO VALIDA");
        }

        char orientacion = respuesta.toLowerCase().charAt(0);

        //Compruebo que el caracter introducido sea valido
        if (orientacion != 'v' && orientacion != 'h') {
            throw new ErrorFlota("ORIENTACION NO VALIDA");
        }

        return orientacion;

    }

    //Metodo Introducir coordenadas que pide coordenadas al usuario, las comprueba, las 
    //traduce y devuelve su traduccion
    //TESTEADO
    public static int[] introducirCoordenadas() throws ErrorFlota {
        boolean valida = false;
        String coordenadas = null;

        while (!valida) {
            Scanner datos = new Scanner(System.in);
            coordenadas = datos.nextLine();

            if (comprobarCoordenadas(coordenadas)) {
                valida = true;
            } else {
                throw new ErrorFlota("La coordenada introducida no es valida por favor introduzca una coordenada en el formato (letraNumero) ejemplo (A2)");
            }
        }
        return traducirCoordenadas(coordenadas);
    }

    //Metodo comprobarCoordenadas que recibe un String (por ejemplo C8) y verifica
    //que cumpla las condiciones, luego llama al metodo traducirCoordenadas
    //Este metodo devuelve true si la cordenada es valida y false si no lo es
    //TESTEADO
    public static boolean comprobarCoordenadas(String coordenadas) {

        //Primero compruebo que la cadena coordenada tiene el tamaño correcto
        if (coordenadas.length() != 2) { //testeado
            return false;
        }

        //Guardo las dos partes de la coordenada en variables
        String coordenada1 = "";
        coordenada1 += coordenadas.charAt(0);
        int coordenada2 = Integer.valueOf(coordenadas.charAt(1));
        coordenada2 -= 48;

        String letras1 = "abcdefghij";
        //Compruebo que la coordenada 1 es valida
        if (!letras1.contains(coordenada1.toLowerCase())) {
            return false;
        }

        if (coordenada2 >= 0 && coordenada2 <= 9) {
            return true;
        }

        return false;

    }

    //Metodo traducirCoordenadas que recibe un String (por ejemplo C8) y devuelve un int[]
    //con las coordenadas traducidas
    //TESTEADO
    public static int[] traducirCoordenadas(String coordenadas) {

        int[] coordenadasInt = new int[2];

        char coordenada1 = 0;
        coordenada1 += coordenadas.toLowerCase().charAt(0);
        int coordenada2 = Integer.valueOf(coordenadas.charAt(1));
        coordenada2 -= 48;

        switch (coordenada1) {
            case 'a':
                coordenadasInt[0] = 0;
                break;
            case 'b':
                coordenadasInt[0] = 1;
                break;
            case 'c':
                coordenadasInt[0] = 2;
                break;
            case 'd':
                coordenadasInt[0] = 3;
                break;
            case 'e':
                coordenadasInt[0] = 4;
                break;
            case 'f':
                coordenadasInt[0] = 5;
                break;
            case 'g':
                coordenadasInt[0] = 6;
                break;
            case 'h':
                coordenadasInt[0] = 7;
                break;
            case 'i':
                coordenadasInt[0] = 8;
                break;
            case 'j':
                coordenadasInt[0] = 9;
                break;
        }

        coordenadasInt[1] = coordenada2;

        return coordenadasInt;
    }

    public char[][] getTablero() {
        return tablero;
    }

    public boolean getJugador() {
        return jugador;
    }

    public char getCasilla(int[] coordenadas) {
        return tablero[coordenadas[0]][coordenadas[1]];
    }

    //Metodo para hacer test y poder ver el tablero de la maquina
    //TESTEADO
    public static void mostrarTableroHackMaquina(Tablero tablero) {

        char[][] mostrando = tablero.getTablero();
        System.out.println("====HACK========HACK===============  MAR DE LA MAQUINA  ==================================");
        char[] letrasPosibles = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_WHITE + " \t0\t1\t2\t3\t4\t5\t6\t7\t8\t9\t||");

        for (int i = 0; i < mostrando.length; i++) {

            System.out.print(ANSI_BLACK_BACKGROUND + ANSI_WHITE + letrasPosibles[i] + ANSI_RESET + "\t");

            for (int j = 0; j < mostrando[i].length; j++) {

                System.out.print(ANSI_BLACK_BACKGROUND + ANSI_WHITE + mostrando[i][j] + "\t" + ANSI_RESET);

            }
            System.out.print("||");
            System.out.println("");
        }
        System.out.println("==========================================================================================");

    }

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";


    //https://www.campusmvp.es/recursos/post/como-cambiar-los-colores-de-la-consola-con-java-y-system-out-println.aspx#:~:text=As%C3%AD%2C%20podemos%20escribir%20lo%20siguiente,de%20color%20rojo%22%20%2B%20ANSI_RESET)%3B
    //Link para dar formato al output
}
