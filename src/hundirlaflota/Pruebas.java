package hundirlaflota;

import java.util.Scanner;

public class Pruebas {

    public static void main(String[] args) {

        ////////////////////////////////////////////////////////
        //Test para ver que las coordenadas son formato [y][x]//
        ////////////////////////////////////////////////////////
//        int[] prueba = new int[] {0,0};
//        int[] prueba1 = new int[] {0,1};
//        int[] prueba2 = new int[] {0,2};
//        Tablero test = new Tablero(false);
//        test.rellenarBarcos();
//        Tablero.mostrarTablero(test);
//        System.out.println("");
//        test.actualizarTablero(prueba, 'A');
//        test.actualizarTablero(prueba1, 'B');
//        test.actualizarTablero(prueba2, 'C');
//        Tablero.mostrarTableroHackMaquina(test);
//        System.out.println(test.getCasilla(prueba));
//        System.out.println(test.getCasilla(prueba1));
//        System.out.println(test.getCasilla(prueba2));
//
        ////////////////////////////////////////////////
        //Test generacion de 100 tablero de la maquina//
        ////////////////////////////////////////////////
        for (int i = 0; i < 100; i++) {
            Tablero test = new Tablero(false);
            test.rellenarBarcos();
            System.out.println("");
            Tablero.mostrarTableroHackMaquina(test);
        }
//
        ////////////////////////////////////////////
        //Test generacion de tablero de la maquina//
        ////////////////////////////////////////////
//        Tablero test = new Tablero(false);
//        test.rellenarBarcos();
//        Tablero.mostrarTablero(test);
//        System.out.println("");
//        Tablero.mostrarTableroHackMaquina(test);
//
        ////////////////////////////////////////////////////////
        //Test de la generacion de coordenadas para la maquina//
        ////////////////////////////////////////////////////////
//        for (int i = 0; i < 100; i++) {
//            int [] aux= Tablero.generarCoordenadas();
//            char coord = Tablero.generarHorientacion();
//            System.out.println(aux[0] + " " + aux[1] + " " + coord);
//        }
//        
        ////////////////////////////////////////////////////
        //Test para crear y rellenar un tablero de jugador//
        ////////////////////////////////////////////////////
//        Tablero test = new Tablero(true);
//        Tablero.mostrarTablero(test);
//        test.rellenarBarcos();
//        Tablero.mostrarTablero(test);
//
        /////////////////////////////////////
        //Test muchos metodos clase tablero//
        /////////////////////////////////////
//        Tablero.mostrarTablero(test);
//        System.out.println(Tablero.comprobarTablero(test));
//        while(true){
//            try {
//                System.out.println("");
//                System.out.println("Introduce una coordenada para colocar un barco");
//                test.colocarBarco('B', Tablero.introducirCoordenadas(), Tablero.pedirHorientacion());
//                break;
//            } catch (ErrorFlota e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        System.out.println(Tablero.comprobarTablero(test));
//        Tablero.mostrarTablero(test);
//        
        ////////////////////////////////////////////////////////
        //Test del metodo mostrar tablero y actualizar tablero//
        ////////////////////////////////////////////////////////
//        Tablero test = new Tablero(true);
//        Tablero.mostrarTablero(test);
//        System.out.println(Tablero.comprobarTablero(test));
//        int[] a = new int[]{0,6};
//        test.actualizarTablero(a, 'V');
//        System.out.println(Tablero.comprobarTablero(test));
//        
//        Scanner datos = new Scanner(System.in);
//        
//        for (int i=0 ; i< 100 ; i++){
//        
//        System.out.println("Introduce una coordenada en el formato letraNumero");
//        String coordenada = datos.nextLine();
//        
//        System.out.println(Tablero.comprobarCoordenadas(coordenada));
//        System.out.println("");
//        }
//
        ////////////////////////////////////
        //Generador de ramdoms entra 0 y 9//
        ////////////////////////////////////
//        int contador0 = 0;
//        int contador1 = 0;
//        int contador2 = 0;
//        int contador3 = 0;
//        int contador4 = 0;
//        int contador5 = 0;
//        int contador6 = 0;
//        int contador7 = 0;
//        int contador8 = 0;
//        int contador9 = 0;
//        int errores = 0;
//
//        for (int i = 0; i < 1000000; i++) {
////            int numero = (int) (Math.random() * 10);
////            int numero = Math.round((float) (Math.random() * 9));
//            int numero = (int) Math.round(Math.random());
//
//            if (numero == 0) {
//                contador0++;
//            } else if (numero == 1) {
//                contador1++;
//            } else if (numero == 2) {
//                contador2++;
//            } else if (numero == 3) {
//                contador3++;
//            } else if (numero == 4) {
//                contador4++;
//            } else if (numero == 5) {
//                contador5++;
//            } else if (numero == 6) {
//                contador6++;
//            } else if (numero == 7) {
//                contador7++;
//            } else if (numero == 8) {
//                contador8++;
//            } else if (numero == 9) {
//                contador9++;
//            } else {
//                errores++;
//            }
//        }
//        
//        System.out.println("Numero 0 = " + contador0);
//        System.out.println("Numero 1 = " + contador1);
//        System.out.println("Numero 2 = " + contador2);
//        System.out.println("Numero 3 = " + contador3);
//        System.out.println("Numero 4 = " + contador4);
//        System.out.println("Numero 5 = " + contador5);
//        System.out.println("Numero 6 = " + contador6);
//        System.out.println("Numero 7 = " + contador7);
//        System.out.println("Numero 8 = " + contador8);
//        System.out.println("Numero 9 = " + contador9);
//        System.out.println(errores);

        ///////////////////////////////////
        //Generador de numero entro 0 y 1//
        ///////////////////////////////////
//        for (int i = 0; i < 100; i++){
//            System.out.println(Math.round(Math.random()));
//        }
//
        //////////////////////////////////////////////////
        //Generador de coordenadas formato (letraNumero)//
        //////////////////////////////////////////////////
//        char[] letrasPosibles = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
//        String coordenadas = "";
//        coordenadas += letrasPosibles[Math.round((float) (Math.random()*9))];
//        coordenadas += (int) Math.round(Math.random()*9);
        /////////////////////////////////////////
        //Test del metodo comprobar coordenadas//
        /////////////////////////////////////////
//        char[] letrasPosibles = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
//        
//        for (int i=0; i < 100; i++){
//            String coordenadas = "";
//            coordenadas += letrasPosibles[Math.round((float) (Math.random()*9))];
//            coordenadas += (int) Math.round(Math.random()*9);
//            System.out.println(Tablero.comprobarCoordenadas(coordenadas));
//            System.out.println(coordenadas);
//        }
//
        ////////////////////////////////////////
        //Test del metodo traducir coordenadas//
        ////////////////////////////////////////
//        for (int i = 0; i < 100; i++) {
//            
//            char[] letrasPosibles = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
//            String coordenadas = "";
//            coordenadas += letrasPosibles[Math.round((float) (Math.random()*9))];
//            coordenadas += (int) Math.round(Math.random()*9);
//            System.out.println(coordenadas);
//            int[] a = Tablero.traducirCoordenadas(coordenadas);
//            System.out.println(a[0] + " " + a[1]);
//            System.out.println("-------------------");
//        }
//        
    }
}
