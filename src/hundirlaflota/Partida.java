package hundirlaflota;

import java.util.Scanner;

public class Partida {
    
    //Atributo booleano del cual dependera que la partida continue o se acabe el juego
    private boolean continuar;
    private boolean perdido;
    private boolean ganado;
    
    //Constructor que deja continuar como true y los otros como false por defecto
    public Partida() {
        this.continuar=true;
        this.perdido=false;
        this.ganado=false;
    
    }
 
    Scanner datos = new Scanner(System.in);
    
    //Metodo juego/iniciarPartida el cual crea un Jugador, una Maquina y un tablero para cada uno
    
    //Hay varios bucles anidados en este modo, uno que controla el juego entero y que solo acaba   -----> while (continuar)
    //si el jugador al final de una partida responde que no quiere jugar.
    public void juego() {
                
        //MENSAJE DE BIENEVENIDO AL JUEGO
        bienvenida();
        System.out.println("");
        System.out.println("DALE AL ENTER PARA EMPEZAR A JUGAR");
        datos.nextLine();
        
        
        //Dentro del primer bucle lo primero que hago es crear mi objeto jugador, maquina, los
        //tableros y colocar los barcos en los tableros
        while(this.continuar) {
            
            //INICIO HUMANO
            Jugador j1 = new Jugador();
            //INICIO MAQUINA
            Maquina m1 = new Maquina();
            

            //Otro que es "la partida" en este caso el bucle solo acaba cuando todos los barcos
            //de un tablero se hayan hundido ----> while (!perdido && !ganado)
            while(!perdido && !ganado) {
                boolean disparoHumano = false;
                boolean disparoMaquina = false;
                //Dentro de este bucle sucede: disparoHumano -> comprobarGanado -> disparoMaquina -> comprobarPerdido *vuelta*
                
                while (!disparoHumano && !perdido && !ganado) {
                    disparoHumano = Jugador.disparoHumano(m1.getTableroMaquina());
                    perdido = Maquina.comprobarTableroMaquina(m1.getTableroMaquina());
                }
                
                while (!disparoMaquina && !ganado && !perdido) {
                    disparoMaquina = Maquina.disparoMaquinaInicioInteligente(j1.getTableroJugador());
//                    disparoMaquina = Maquina.disparoNormal(j1.getTableroJugador());
                    ganado = Jugador.comprobarTablaroHumano(j1.getTableroJugador());

                }
                
                
            }
            
            if (perdido) {
                System.out.println("");
                System.out.println("Felicidades has ganado la partida, has destruido la maquina");
            } else {
                System.out.println("La maquina ha hundido todos tus barcos");
                System.out.println("");
                System.out.println("Oh NO!! has perdido la partida, seguro que la proxima vez lo lograras");
            }
            System.out.println("");
            
            //Llamo al metodo continuarPartida donde pregunto si quiere continuar 
            //y jugar otra vez o quiere terminar de jugar
            while (true) {
                try {
                    continuar = continuarJuego();
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            
        }
        
    }
    
    //Metodo continuar partida que pregunta al jugador si quiere jugar otra vez y devuelve
    //un booleano con la respuesta
    
    public boolean continuarJuego() throws ErrorFlota {
        
        System.out.println("¿Quieres jugar otra vez? s/n");
        String respuesta = datos.nextLine();
        char res = respuesta.toLowerCase().charAt(0);
        if (respuesta.length()>1) {
            throw new ErrorFlota("La respuesta no es valida");
        } else if (res == 's') {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            return true;
        } else if (res == 'n') {
            return false;
        } else throw new ErrorFlota("La respuesta no es valida");
    }
    
    //Metodo que muestra la bienvenida
    public void bienvenida() {
        System.out.println( " /$$   /$$                           /$$ /$$                 /$$                 /$$$$$$$$ /$$             /$$              \n" +
                            "| $$  | $$                          | $$|__/                | $$                | $$_____/| $$            | $$              \n" +
                            "| $$  | $$ /$$   /$$ /$$$$$$$   /$$$$$$$ /$$  /$$$$$$       | $$  /$$$$$$       | $$      | $$  /$$$$$$  /$$$$$$    /$$$$$$ \n" +
                            "| $$$$$$$$| $$  | $$| $$__  $$ /$$__  $$| $$ /$$__  $$      | $$ |____  $$      | $$$$$   | $$ /$$__  $$|_  $$_/   |____  $$\n" +
                            "| $$__  $$| $$  | $$| $$  \\ $$| $$  | $$| $$| $$  \\__/      | $$  /$$$$$$$      | $$__/   | $$| $$  \\ $$  | $$      /$$$$$$$\n" +
                            "| $$  | $$| $$  | $$| $$  | $$| $$  | $$| $$| $$            | $$ /$$__  $$      | $$      | $$| $$  | $$  | $$ /$$ /$$__  $$\n" +
                            "| $$  | $$|  $$$$$$/| $$  | $$|  $$$$$$$| $$| $$            | $$|  $$$$$$$      | $$      | $$|  $$$$$$/  |  $$$$/|  $$$$$$$\n" +
                            "|__/  |__/ \\______/ |__/  |__/ \\_______/|__/|__/            |__/ \\_______/      |__/      |__/ \\______/    \\___/   \\_______/\n" +
                            "                                                                                                                            \n" +
                            "                                                                                                                            \n" +
                            "                                                                                                                            ");
        
        
        
        
        System.out.println( "\t\t\t====================================================================\n" +
                            "\t\t\t||      .-\"\"-.                                                    ||\n" +
                            "\t\t\t||   ) (     )                                                    ||\n" +
                            "\t\t\t||  (   )   (                                                     ||\n" +
                            "\t\t\t||     /     )                                                    ||\n" +
                            "\t\t\t||    (_    _)                     0_,-.__                        ||\n" +
                            "\t\t\t||      (_  )_                     |_.-._/                        ||\n" +
                            "\t\t\t||       (    )                    |_--..\\                        ||\n" +
                            "\t\t\t||        (__)                     |__--_/                        ||\n" +
                            "\t\t\t||     |''   ``\\                   |                              ||\n" +
                            "\t\t\t||     |        \\                  |      /b.                     ||\n" +
                            "\t\t\t||     |         \\  ,,,---=====`\\  |  ,==y'                       ||\n" +
                            "\t\t\t||______,,,,,---==\"\"\\        |h| \\ | ;|\\ |>                       ||\n" +
                            "\t\t\t||           _   _   \\   ___,|H|,---==\"\"\"\"====                    ||\n" +
                            "\t\t\t|| o  o  O  (_) (_)   \\ /          _     /////                    ||\n" +
                            "\t\t\t||                     /         _(+)_  ////                      ||\n" +
                            "\t\t\t||      \\@_,,,,,,---==\"   \\      \\\\|//  ///                       ||\n" +
                            "\t\t\t||--''''\"                         ===  //                         ||\n" +
                            "\t\t\t||                                    //                          ||\n" +
                            "\t\t\t||                                    ,'__________________________||\n" +
                            "\t\t\t||   \\    \\    \\     \\               ,/~~~~~~~~~~~~~~~~~~~~~~~~~~~||\n" +
                            "\t\t\t||                         _____    ,'  ~~~   .-\"\"-.~~~~~~  .-\"\"-.||\n" +
                            "\t\t\t||      .-\"\"-.           ///==---   /`-._ ..-'      -.__..-'      ||\n" +
                            "\t\t\t||__..-'      `-.__..-' =====\\\\\\\\\\\\ V/  .---\\.                    ||\n" +
                            "\t\t\t||                      ~~~~~~~~~~~~, _',--/_.\\  .-\"\"-.           ||\n" +
                            "\t\t\t||                            .-\"\"-.___` --  \\|         -.__..-   ||\n" +
                            "\t\t\t====================================================================\n");
        
        
        System.out.println("");
        
        
        
        
        
        
        
        
        
        
        
        System.out.println( "                                                                                                                          \n" +
                            " ,,                                                  MM                        db                                         \n" +
                            "*MM                     `7MMM.     ,MMF' db         7MM                       7MM      `7MM\"\"\"Mq.   db                    \n" +
                            " MM                       MMMb    dPMM               MM                        MM        MM   `MM.                        \n" +
                            " MM,dMMb.`7M'   `MF'      M YM   ,M MM  7MM  ,p6\"bo  MMpMMMb.  ,6\"Yb.  .gP\"Ya  MM        MM   ,M9  7MM  ,p6\"bo   ,pW\"Wq.  \n" +
                            " MM    `Mb VA   ,V        M  Mb  M' MM   MM 6M'  OO  MM    MM 8)   MM ,M'   Yb MM        MMmmdM9    MM 6M'  OO  6W'   `Wb \n" +
                            " MM     M8  VA ,V         M  YM.P'  MM   MM 8M       MM    MM  ,pm9MM 8M\"\"\"\"\"\" MM        MM         MM 8M       8M     M8 \n" +
                            " MM.   ,M9   VVV          M  `YM'   MM   MM YM.    , MM    MM 8M   MM YM.    , MM        MM         MM YM.    , YA.   ,A9 \n" +
                            " P^YbmdP'    ,V         .JML. `'  .JMML.JMML.YMbmd'.JMML  JMML`Moo9^Yo.`Mbmmd.JMML.    .JMML.     .JMML.YMbmd'   `Ybmd9'  \n" +
                            "            ,V                                                                                                            \n" +
                            "         OOb\"                                                                                                             ");

        
    }
    
     
    
    

    
    
    
    
    
    //Una vez "la partida" acabo, se llama al metodo finDePartida el cual informa del 
    //resultado y pregunta al jugador si quiere vovler a jugar, segun la respuesta 
    //cambia le booleano continuar y la partida volvera a empezar o terminara.

    
    


}
