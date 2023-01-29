package hundirlaflota;

public class Maquina {

    //Tiene un atributo tablero el cual tiene jugador como false
    public static boolean disparoInteligente;
    public static boolean comprobadoArriba;
    public static boolean comprobadoDerecha;
    public static boolean comprobadoAbajo;
    public static boolean comprobadoIzquierda;
    public static boolean objetivoVertical;
    public static boolean objetivoHorizontal;
    private static int[] coordenadasUltimoDisparo;
    private static int[] primerAcierto;
    public Tablero tableroMaquina;

    public Maquina() {
        this.tableroMaquina = new Tablero(false);
        this.tableroMaquina.rellenarBarcos();
        Tablero.mostrarTablero(tableroMaquina);
        System.out.println("==============================================================================\n"
                + "Este es el tablero de la maquina, ahora tendras que disparar en contra de ella\n"
                + "e intentar hundir sus barcos, tiene los mismos barcos que tu. \n\n"
                + "La partida acaba cuando uno de los dos se quede sin barcos buena suerte\n"
                + "==============================================================================");
        this.disparoInteligente = false;
        this.comprobadoArriba = false;
        this.comprobadoDerecha = false;
        this.comprobadoAbajo = false;
        this.comprobadoIzquierda = false;
        this.objetivoHorizontal = false;
        this.objetivoVertical = false;

    }

    //DISPARO NO INTELIGENTE
    public static boolean disparoNormal(Tablero humanoTablero) {

        int[] coordenadas = new int[]{(int) (Math.random() * 10), (int) (Math.random() * 10)};

        char objetivo = humanoTablero.getCasilla(coordenadas);

        if (objetivo == '¤' || objetivo == 'ø') {
            return false;
        } else if (objetivo == '.') {
            humanoTablero.actualizarTablero(coordenadas, 'ø');
            Tablero.mostrarTablero(humanoTablero);
            System.out.println("La maquina ha dado Aguas");
            return true;
        } else if (objetivo == 'V') {
            humanoTablero.actualizarTablero(coordenadas, '¤');
            Tablero.mostrarTablero(humanoTablero);
            System.out.println("La maquina ha acertado un disparo");
            System.out.println("La maquina ha hundido un velero");
            return false; //Devuelve false para que el metodo se vuelva a llamar a si mismo y asi dispara otra vez
        } else if (objetivo == 'B' || objetivo == 'F' || objetivo == 'P') {
            humanoTablero.actualizarTablero(coordenadas, '¤');
            Tablero.mostrarTablero(humanoTablero);
            System.out.println("La maquina ha acertado un disparo");
            coordenadasUltimoDisparo = coordenadas;
            //Llamo al metodo detectar vida pasandoles coordenadasUltimoAcierto
            if (!detectarVida(coordenadasUltimoDisparo, humanoTablero)) {
                System.out.println("La maquina ha hundio un barco");
            }
            return false; //devuelvo false para que vuelva a dispara
        }

        System.out.println("ALGO SALIO MAL EN DISPARO NOrMAL MAQUINA");
        return true;
    }

    //ESTE ES COMO EL DISPARO NORMAL PERO CUANDO ACIERTA INICIA EL DISPARO INTELIGENTE 
    //ADEMAS NO DISPARA ALREDEDOR DE BARCOS YA HUNDIDOS
    public static boolean disparoMaquinaInicioInteligente(Tablero humanoTablero) {

        if (disparoInteligente) {
            try {
                return disparoInteligenteIniciado(humanoTablero); //En caso de que el metodo haya fallado el disparo, devuelve un true, sino devuelve false para que partida dispare otra vez
            } catch (Exception e) {
                throw e;
            }
        }

        int[] coordenadas;
        //Este while se encarga que las coordenadas sean validas
        //Para que las coordenadas sean validas no se puede haber disparado ahi
        //Y no pueden estar adyacentes a un barco ya disparado
        while (true) {
            coordenadas = new int[]{Math.round((float) (Math.random() * 9)), Math.round((float) (Math.random() * 9))};

            int y = coordenadas[0];
            int x = coordenadas[1];

            //El siguiente bloque se encarga de que las coordenadas no sean adyacentes a un acierto ya que es imposible que haya un barco ahi
            if (humanoTablero.getCasilla(coordenadas) == '¤') {
                continue; //Vuelvo a intentar otras coordenadas
            }
            //Miro a la comprobadoIzquierda
            if (x > 0) {
                int[] coordenadasComprobadas = new int[]{y, x - 1};
                if (humanoTablero.getCasilla(coordenadasComprobadas) == '¤') {
                    continue; //Vuelvo a intentar otras coordenadas
                }
            }
            //Miro a la comprobadoDerecha
            if (x < 9) {
                int[] coordenadasComprobadas = new int[]{y, x + 1};
                if (humanoTablero.getCasilla(coordenadasComprobadas) == '¤') {
                    continue; //Vuelvo a intentar otras coordenadas
                }
            }
            //Miro comprobadoArriba
            if (y > 0) {
                int[] coordenadasComprobadas = new int[]{y - 1, x};
                if (humanoTablero.getCasilla(coordenadasComprobadas) == '¤') {
                    continue; //Vuelvo a intentar otras coordenadas
                }
            }
            //Miro comprobadoAbajo
            if (y < 9) {
                int[] coordenadasComprobadas = new int[]{y + 1, x};
                if (humanoTablero.getCasilla(coordenadasComprobadas) == '¤') {
                    continue; //Vuelvo a intentar otras coordenadas
                }
            }

            //Si las coordenadas han pasado todos los filtros se ejecuta el break para continuar el codigo
            break;
        }

        char objetivo = humanoTablero.getCasilla(coordenadas);

        if (objetivo == '.') {
            humanoTablero.actualizarTablero(coordenadas, 'ø');
            Tablero.mostrarTablero(humanoTablero);
            System.out.println("La maquina ha dado Aguas");

            return true;

        } else if (objetivo == 'V') {
            humanoTablero.actualizarTablero(coordenadas, '¤');
            Tablero.mostrarTablero(humanoTablero);
            System.out.println("La maquina ha acertado un disparo");
            System.out.println("La maquina ha hundido un velero");
            return false; //Devuelve false para que el metodo se vuelva a llamar a si mismo y asi dispara otra vez

        } else if (objetivo == 'B' || objetivo == 'F' || objetivo == 'P') {
            humanoTablero.actualizarTablero(coordenadas, '¤');
            Tablero.mostrarTablero(humanoTablero);
            System.out.println("La maquina ha acertado un disparo");
            disparoInteligente = true;
            coordenadasUltimoDisparo = coordenadas; //Asi el metodo disparo inteligente sabe 
            primerAcierto = coordenadas;
            return false; //Devuelve false para que el metodo se vuelva a llamar a si mismo y de el comienzo empieze el disparo Inteligente
        }
        return false;//En caso de que la maquina haya disparado en un lugar no valido (un barco hundido o algo asi)
    }

    public static boolean disparoInteligenteIniciado(Tablero humanoTablero) {

        //Mensaje para saber que el metodo se inicio
        System.out.println("La maquina esta pensando su siguiente jugada");

        //Guardo las coordenadas en variables auxiliares
        int[] coordenadas = coordenadasUltimoDisparo;
        int x = coordenadas[1];
        int y = coordenadas[0];

        //Primero elimino lados en base a mi posicion
        if (x == 0) {
            comprobadoIzquierda = true;
        }
        if (x == 9) {
            comprobadoDerecha = true;
        }
        if (y == 0) {
            comprobadoArriba = true;
        }
        if (y == 9) {
            comprobadoAbajo = true;
        }

        //Ahora compruebo lados y si el lado cumple los requisitos para dispara disparo
        if (!objetivoVertical) {
            //Miro a la izquierda del ultimo disparo
            if (!comprobadoIzquierda) {
                int[] coordenadasComprobar = {y, x - 1};
                //Si hay un acierto a la izquierda significa que el barco es horizontal
                if (humanoTablero.getCasilla(coordenadasComprobar) == '¤') {
                    objetivoHorizontal = true;
                    comprobadoAbajo = true;
                    comprobadoArriba = true;
                    //Basicamente si la izquierda es un disparo acertado miro mas a la izquierda si es posible
                    if (x > 1) {
                        coordenadasUltimoDisparo = coordenadasComprobar;
                        return false;
                    }

                    //Si la casilla de la izquierda no es un acierto a un barco sino un disparo de aguas
                } else if (humanoTablero.getCasilla(coordenadasComprobar) == 'ø') {
                    comprobadoIzquierda = true;

                } else {
                    //Caso aguas
                    if (humanoTablero.getCasilla(coordenadasComprobar) == '.') {
                        humanoTablero.actualizarTablero(coordenadasComprobar, 'ø');
                        Tablero.mostrarTablero(humanoTablero);
                        System.out.println("La maquina ha dado Aguas");
                        comprobadoIzquierda = true; //Ya se que no hay un barco por la izquierda
                        return true; //Paro el metodo

                        //Caso acierto
                    } else if (humanoTablero.getCasilla(coordenadasComprobar) == 'B' || humanoTablero.getCasilla(coordenadasComprobar) == 'F' || humanoTablero.getCasilla(coordenadasComprobar) == 'P') {
                        humanoTablero.actualizarTablero(coordenadasComprobar, '¤');
                        Tablero.mostrarTablero(humanoTablero);
                        objetivoHorizontal = true;
                        System.out.println("La maquina ha acertado un disparo");
                        coordenadasUltimoDisparo = coordenadasComprobar; //Asi el metodo disparo inteligente sabe donde disparar

                        //Si el barco esta hundido
                        if (!detectarVida(coordenadasUltimoDisparo, humanoTablero)) {
                            System.out.println("La maquina ha hundio un barco");
                            finDisparoInteligente(); //Coloco todo a false
                        }

                        return false; //Termino el metodo devolviendo false para que vuelva a disparar

                    }
                }
            }
            //Miro a la derecha
            if (!comprobadoDerecha) {
                int[] coordenadasComprobar = {y, x + 1};
                //Si hay un acierto a la derecha significa que el barco es horizontal
                if (humanoTablero.getCasilla(coordenadasComprobar) == '¤') {
                    objetivoHorizontal = true;
                    comprobadoAbajo = true;
                    comprobadoArriba = true;
                    //Basicamente si la derecha es un disparo acertado miro mas a la derecha si es posible
                    if (x < 8) {
                        coordenadasUltimoDisparo = coordenadasComprobar;
                        return false;
                    }

                    //Si la casilla de la derecha no es un acierto a un barco sino un disparo de aguas
                } else if (humanoTablero.getCasilla(coordenadasComprobar) == 'ø') {
                    comprobadoDerecha = true;

                    //Si la casilla no es disparo fallido, ni acierto, disparo!!
                } else {
                    //Caso aguas
                    if (humanoTablero.getCasilla(coordenadasComprobar) == '.') {
                        humanoTablero.actualizarTablero(coordenadasComprobar, 'ø');
                        Tablero.mostrarTablero(humanoTablero);
                        System.out.println("La maquina ha dado Aguas");
                        comprobadoDerecha = true; //Ya se que no hay un barco por la derecha
                        return true; //Paro el metodo

                        //Caso acierto
                    } else if (humanoTablero.getCasilla(coordenadasComprobar) == 'B' || humanoTablero.getCasilla(coordenadasComprobar) == 'F' || humanoTablero.getCasilla(coordenadasComprobar) == 'P') {
                        humanoTablero.actualizarTablero(coordenadasComprobar, '¤');
                        Tablero.mostrarTablero(humanoTablero);
                        objetivoHorizontal = true;
                        System.out.println("La maquina ha acertado un disparo");
                        coordenadasUltimoDisparo = coordenadasComprobar; //Asi el metodo disparo inteligente sabe donde disparar

                        //Si el barco esta hundido
                        if (!detectarVida(coordenadasUltimoDisparo, humanoTablero)) {
                            System.out.println("La maquina ha hundio un barco");
                            finDisparoInteligente(); //Coloco todo a false
                        }

                        return false; //Termino el metodo devolviendo false para que vuelva a disparar

                    }
                }
            }
        }

        if (!objetivoHorizontal) {
            //Miro a la arriba
            if (!comprobadoArriba) {
                int[] coordenadasComprobar = {y + 1, x};
                //Si hay un acierto arriba significa que el barco es vertical
                if (humanoTablero.getCasilla(coordenadasComprobar) == '¤') {
                    objetivoVertical = true;
                    comprobadoIzquierda = true;
                    comprobadoDerecha = true;
                    //Basicamente si arriba es un disparo acertado miro mas arriba si es posible
                    if (y < 8) {
                        coordenadasUltimoDisparo = coordenadasComprobar;
                        return false;
                    }

                    //Si la casilla de arriba no es un acierto a un barco sino un disparo de aguas
                } else if (humanoTablero.getCasilla(coordenadasComprobar) == 'ø') {
                    comprobadoArriba = true;

                    //Si la casilla no es disparo fallido, ni acierto, disparo!!
                } else {
                    //Caso aguas
                    if (humanoTablero.getCasilla(coordenadasComprobar) == '.') {
                        humanoTablero.actualizarTablero(coordenadasComprobar, 'ø');
                        Tablero.mostrarTablero(humanoTablero);
                        System.out.println("La maquina ha dado Aguas");
                        comprobadoDerecha = true; //Ya se que no hay un barco por la arriba
                        return true; //Paro el metodo

                        //Caso acierto
                    } else if (humanoTablero.getCasilla(coordenadasComprobar) == 'B' || humanoTablero.getCasilla(coordenadasComprobar) == 'F' || humanoTablero.getCasilla(coordenadasComprobar) == 'P') {
                        humanoTablero.actualizarTablero(coordenadasComprobar, '¤');
                        Tablero.mostrarTablero(humanoTablero);
                        objetivoVertical = true;
                        System.out.println("La maquina ha acertado un disparo");
                        coordenadasUltimoDisparo = coordenadasComprobar; //Asi el metodo disparo inteligente sabe donde disparar

                        //Si el barco esta hundido
                        if (!detectarVida(coordenadasUltimoDisparo, humanoTablero)) {
                            System.out.println("La maquina ha hundio un barco");
                            finDisparoInteligente(); //Coloco todo a false
                        }

                        return false; //Termino el metodo devolviendo false para que vuelva a disparar

                    }
                }
            }

            //Miro a la abajo
            if (!comprobadoAbajo) {
                int[] coordenadasComprobar = {y - 1, x};
                //Si hay un acierto abajo significa que el barco es vertical, sigo mirando mas abajo
                if (humanoTablero.getCasilla(coordenadasComprobar) == '¤') {
                    objetivoVertical = true;
                    comprobadoIzquierda = true;
                    comprobadoDerecha = true;
                    //Basicamente si abajo es un disparo acertado miro mas abajo si es posible
                    if (y > 1) {
                        coordenadasUltimoDisparo = coordenadasComprobar;
                        return false;
                    }

                    //Si la casilla de abajo no es un acierto a un barco sino un disparo de aguas
                } else if (humanoTablero.getCasilla(coordenadasComprobar) == 'ø') {
                    comprobadoAbajo = true;

                    //Si la casilla no es disparo fallido, ni acierto, disparo!!
                } else {
                    //Caso aguas
                    if (humanoTablero.getCasilla(coordenadasComprobar) == '.') {
                        humanoTablero.actualizarTablero(coordenadasComprobar, 'ø');
                        Tablero.mostrarTablero(humanoTablero);
                        System.out.println("La maquina ha dado Aguas");
                        comprobadoAbajo = true; //Ya se que no hay un barco por abajo
                        return true; //Paro el metodo

                        //Caso acierto
                    } else if (humanoTablero.getCasilla(coordenadasComprobar) == 'B' || humanoTablero.getCasilla(coordenadasComprobar) == 'F' || humanoTablero.getCasilla(coordenadasComprobar) == 'P') {
                        humanoTablero.actualizarTablero(coordenadasComprobar, '¤');
                        Tablero.mostrarTablero(humanoTablero);
                        objetivoVertical = true;
                        System.out.println("La maquina ha acertado un disparo");
                        coordenadasUltimoDisparo = coordenadasComprobar; //Asi el metodo disparo inteligente sabe donde disparar

                        //Si el barco esta hundido
                        if (!detectarVida(coordenadasUltimoDisparo, humanoTablero)) {
                            System.out.println("La maquina ha hundio un barco");
                            finDisparoInteligente(); //Coloco todo a false
                        }

                        return false; //Termino el metodo devolviendo false para que vuelva a disparar

                    }
                }
            }
        }

//        //Si he llegado al final del metodo significa que el disparo empezo desde el medio de algun barco y no uno de sus extremos,
//        //como solucion sencilla hago false a los comprobar devuelvo false para que el metodo empieze desde el principio
//        comprobadoArriba = false;
//        comprobadoDerecha = false;
//        comprobadoAbajo = false;
//        comprobadoIzquierda = false;
//        objetivoHorizontal = false;
//        objetivoVertical = false;
//        coordenadasUltimoDisparo = primerAcierto;
        return false; //Este false se encarga de que el metodo de la vuelta, esta accion es necesaria en el caso de el primer disparo no haya sido en el medio del barco

    }

    //Este metodo simplemente hace que todos los booleanos del dispro inteligente vuelvan a false
    public static void finDisparoInteligente() {
        disparoInteligente = false;
        comprobadoArriba = false;
        comprobadoDerecha = false;
        comprobadoAbajo = false;
        comprobadoIzquierda = false;
        objetivoHorizontal = false;
        objetivoVertical = false;
    }

    //Este metodo recibe una coordenada y busca en los arededores a ver si hay algun barco
    //Devuelve true si encuentra un barco y false si no lo encuentra
    public static boolean detectarVida(int[] coordenadasUltimoAcierto, Tablero tableroComprobado) {
        boolean vivo = false;

        int y = coordenadasUltimoAcierto[0];
        int x = coordenadasUltimoAcierto[1];

        //Miro si hay un barco por la comprobadoIzquierda
        if (x > 0) {
            for (int i = 1; i < 4; i++) {

                int[] coordenadasComprobar = new int[]{y, x - i};

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
        //Miro si hay un barco por la comprobadoDerecha
        if (x < 9) {
            for (int i = 1; i < 4; i++) {

                int[] coordenadasComprobar = new int[]{y, x + i};

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

        //Miro comprobadoAbajo
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

        //Miro comprobadoArriba
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
        return vivo;
    }

    //devuelve true en caso de que no queden barcos en el tablero que recibe
    //caso contrario devuelve un false
    //TESTEADO
    public static boolean comprobarTableroMaquina(Tablero tableroCompobando) {

        //Basicamente recorro el tablero buscando por barcos
        for (int i = 0; i < tableroCompobando.getTablero().length; i++) {
            for (int j = 0; j < tableroCompobando.getTablero()[0].length; j++) {

                int[] coordenadaComprobada = new int[]{i, j};
                if (tableroCompobando.getCasilla(coordenadaComprobada) == 'V' || tableroCompobando.getCasilla(coordenadaComprobada) == 'F' || tableroCompobando.getCasilla(coordenadaComprobada) == 'B' || tableroCompobando.getCasilla(coordenadaComprobada) == 'P') {
                    return false; //Si encontre un barco devuelvo false
                }
            }
        }
        //si no encontre barco devuelvo un false
        return true;
    }

    public Tablero getTableroMaquina() {
        return tableroMaquina;
    }

}
