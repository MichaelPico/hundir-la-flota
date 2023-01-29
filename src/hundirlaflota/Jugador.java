package hundirlaflota;

import java.util.Scanner;

public class Jugador {

    //Tiene un atributo tableroJugador el cual tiene jugador como true
    private Tablero tableroJugador;
    private static int[] coordenadasUltimoDisparoAcertado;

    //Tiene un constructor el cual inica su tableroJugador y llama al metodo rellenar tableroJugador
    public Jugador() {
        this.tableroJugador = new Tablero(true);
        Tablero.mostrarTablero(tableroJugador);
        this.mensajeRellenar();
        this.tableroJugador.rellenarBarcos();
    }

    //Metodo disparo del humano
    public static boolean disparoHumano(Tablero maquinaTablero) {

        //Declaro variable auxiliar
        int[] coordenadas;

        System.out.println("");
        System.out.println("Dime en que cordenada quieres disparar");

        //Pido coordenadas al usuario hasta que introduce unas coordenadas validas
        while (true) {
            try {
                //El metodo introducir coordenadas, traduce las coordenadas y comprueba que no esta fuera del tablero
                coordenadas = Tablero.introducirCoordenadas();

                //Compruebo que el jugador no esta intentando disparar en un lugar donde ya ha disparado antes
                if (maquinaTablero.getCasilla(coordenadas) == '¤' || maquinaTablero.getCasilla(coordenadas) == 'ø') {
                    throw new ErrorFlota("Ya has disparado aqui antes, por favor intenta otras coordenadas");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        //Guardo la casilla donde el usuario dispara para comprobarla
        char objetivo = maquinaTablero.getCasilla(coordenadas);

        //Si dispara en el agua
        if (objetivo == '.') {
            maquinaTablero.actualizarTablero(coordenadas, 'ø');
            Tablero.mostrarTablero(maquinaTablero);
            System.out.println("Has dado Aguas");
            return true;

            //Si dispara en un velero
        } else if (objetivo == 'V') {
            maquinaTablero.actualizarTablero(coordenadas, '¤');
            Tablero.mostrarTablero(maquinaTablero);
            System.out.println("Has acertado un disparo");
            System.out.println("Has hundido un velero");
            return false; //Devuelve false para que el metodo se vuelva a llamar a si mismo y asi dispara otra vez
            //Si dispara a un barco que no sea un velero
        } else if (objetivo == 'B' || objetivo == 'F' || objetivo == 'P') {
            maquinaTablero.actualizarTablero(coordenadas, '¤');
            Tablero.mostrarTablero(maquinaTablero);
            System.out.println("Has acertado un disparo");
            coordenadasUltimoDisparoAcertado = coordenadas;
            //Llamo al metodo detectar vida pasandoles coordenadasUltimoAcierto
            if (!detectarVida(coordenadasUltimoDisparoAcertado, maquinaTablero)) {
                System.out.println("Has hundio un barco");
            }
            return false; //devuelvo false para que vuelva a dispara
        }

        System.out.println("ALGO SALIO MAL EN DISPARO NOrMAL HUMANO");
        return true;
    }

    //Metodo que recorre todo el tableroJugador del jugador y devuelve un false si hay algun barco y devuelve un true si no hay ningun barco
    public static boolean comprobarTablaroHumano(Tablero tableroCompobando) {

        for (int i = 0; i < tableroCompobando.getTablero().length; i++) {
            for (int j = 0; j < tableroCompobando.getTablero()[0].length; j++) {

                int[] coordenadaComprobada = new int[]{i, j};
                if (tableroCompobando.getCasilla(coordenadaComprobada) == 'V' || tableroCompobando.getCasilla(coordenadaComprobada) == 'F' || tableroCompobando.getCasilla(coordenadaComprobada) == 'B' || tableroCompobando.getCasilla(coordenadaComprobada) == 'P') {
                    return false;
                }
            }
        }
        return true;
    }

    //Este metodo recibe una coordenada y busca en los arededores a ver si hay algun barco
    //Devuelve true si encuentra un barco y false si no lo encuentra
    public static boolean detectarVida(int[] coordenadasUltimoAcierto, Tablero tableroComprobado) {
        
        int y = coordenadasUltimoAcierto[0];
        int x = coordenadasUltimoAcierto[1];

        //Miro si hay un barco por la izquierda
        if (x > 0) {
            for (int i = 1; i < 4; i++) {

                int[] coordenadasComprobar = new int[]{y, (x-i)};

                //Si hay mar break al bucle
                if (tableroComprobado.getCasilla(coordenadasComprobar) == '.' || tableroComprobado.getCasilla(coordenadasComprobar) == 'ø') {
                    break;
                }

                //Si detecto un barco detecto true
                if (tableroComprobado.getCasilla(coordenadasComprobar) == 'B' || tableroComprobado.getCasilla(coordenadasComprobar) == 'F' || tableroComprobado.getCasilla(coordenadasComprobar) == 'P') {
                    return true;
                }

                //Para que no se salga del limite
                if (x - i == 0) {
                    break;
                }
            }
        }
        //Miro si hay un barco por la derecha
        if (x < 9) {
            for (int i = 1; i < 4; i++) {

                int[] coordenadasComprobar = new int[]{y, x +i};

                //Si hay mar break al bucle
                if (tableroComprobado.getCasilla(coordenadasComprobar) == '.' || tableroComprobado.getCasilla(coordenadasComprobar) == 'ø') {
                    break;
                }

                //Si detecto un barco detecto true
                if (tableroComprobado.getCasilla(coordenadasComprobar) == 'B' || tableroComprobado.getCasilla(coordenadasComprobar) == 'F' || tableroComprobado.getCasilla(coordenadasComprobar) == 'P') {
                    return true;
                }

                //Para que no se salga del limite
                if (x + i == 9) {
                    break;
                }
            }
        }

        //Miro abajo
        if (y < 9) {
            for (int i = 1; i < 4; i++) {

                int[] coordenadasComprobar = new int[]{y + i, x};

                //Si hay mar break al bucle
                if (tableroComprobado.getCasilla(coordenadasComprobar) == '.' || tableroComprobado.getCasilla(coordenadasComprobar) == 'ø') {
                    break;
                }

                //Si detecto un barco detecto true
                if (tableroComprobado.getCasilla(coordenadasComprobar) == 'B' || tableroComprobado.getCasilla(coordenadasComprobar) == 'F' || tableroComprobado.getCasilla(coordenadasComprobar) == 'P') {
                    return true;
                }

                //Para que no se salga del limite
                if (y + i == 9) {
                    break;
                }
            }
        }

        //Miro arriba
        if (y > 0) {
            for (int i = 1; i < 4; i++) {

                int[] coordenadasComprobar = new int[]{y - i, x};

                //Si hay mar break al bucle
                if (tableroComprobado.getCasilla(coordenadasComprobar) == '.' || tableroComprobado.getCasilla(coordenadasComprobar) == 'ø') {
                    break;
                }

                //Si detecto un barco detecto true
                if (tableroComprobado.getCasilla(coordenadasComprobar) == 'B' || tableroComprobado.getCasilla(coordenadasComprobar) == 'F' || tableroComprobado.getCasilla(coordenadasComprobar) == 'P') {
                    return true;
                }

                //Para que no se salga del limite
                if (y - i == 0) {
                    break;
                }
            }
        }
        return false;
    }

    //Mensaje que muestro como especie de tutorial
    public void mensajeRellenar() {

        System.out.println("Este es tu tablero, para empezar a jugar primero tendras que rellenarlo de barcos.\n\n"
                + "Para llenarlo de barcos tendras que introducir coordenadas (por ejemplo A4).      \n\n"
                + "Tendras 4 veleros de tamaño 1, 3 Buques de tamaño 2, 2 Fragatas de tamaño 3 y un Portaviones de tamaño 4.\n\n"
                + "Para cada barco distinto del velero tendras que especificar su horientacion.\n"
                + "Buena suerte :D\n"
                + "=========================================================================================================");
    }

    public Tablero getTableroJugador() {
        return tableroJugador;
    }

    public int[] getCoordenadasUltimoDisparoAcertado() {
        return coordenadasUltimoDisparoAcertado;
    }

    public void setCoordenadasUltimoDisparoAcertado(int[] coordenadasUltimoDisparoAcertado) {
        this.coordenadasUltimoDisparoAcertado = coordenadasUltimoDisparoAcertado;
    }

}
