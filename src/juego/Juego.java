package juego;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import trampas.*;
import reportes.*;

/**
 *
 * @author Felipe
 */
public class Juego {
    Tablero tab = new Tablero();
    reportes reportes = new reportes();

    public static void main(String[] args) {
        try{
            Juego obj = new Juego();
            obj.run(args);
        }catch (Exception e){
        e.printStackTrace ();
        }
    }
    
    public void run (String[] args) throws Exception
    {
        menu();
    }
    
    public void menu(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion = 0;
        System.out.println("Ingrese una opcion");
        System.out.println("1. Iniciar Juego");
        System.out.println("2. Retomar Juego");
        System.out.println("3. Generar Reportes");
        System.out.println("4. Salir");
        
        try{
            opcion = Integer.parseInt(br.readLine());
            
            switch(opcion){
                case 1:
                    tab.mostrarTableroInicial();
                    break;
                case 2:
                    System.out.println("Ningun juego en curso");                    
                    menu();
                    break;
                case 3:
                    System.out.println("Ningun juego en curso, imposible generar reporte");                    
                    menu();
                    break;
                case 4:
                    System.exit(0);
                    break;
                case 5:
                    System.out.println("Este apartado es de pruebas");
                    break;
                default:                    
                    System.out.println("Opcion invalida");
                    menu();
                    break;
            }
        }catch(IOException | NumberFormatException ex){
            System.out.println("Opcion invalida");
            menu();
        }
    }

    public void menu(
        Jugador jugador,
        int[] trampas,
        faciles tFaciles,
        medias tMedias,
        dificiles tDificiles,
        boolean juegoFinalizado
    ){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion = 0;
        System.out.println("Ingrese una opcion");
        System.out.println("1. Iniciar Juego");
        System.out.println("2. Retomar Juego");
        System.out.println("3. Generar Reportes");
        System.out.println("4. Salir");
        
        try{
            opcion = Integer.parseInt(br.readLine());
            
            switch(opcion){
                case 1:
                    tab.mostrarTableroInicial();
                    break;
                case 2:
                    if(!juegoFinalizado)
                    tab.reanudarJuego(
                        jugador,
                        trampas,
                        tFaciles,
                        tMedias,
                        tDificiles
                    );
                    else System.out.println("Partida terminada");
                    menu();
                    break;
                case 3:
                    try {
                        System.out.println("Ingrese una opcion");
                        System.out.println("1. Trampas");
                        System.out.println("2. Bitacora");
                        System.out.println("3. Regresar");
                        opcion = Integer.parseInt(br.readLine());
                        switch(opcion){
                            case 1:                                
                                reportes.generarReporte1(tFaciles,tMedias,tDificiles,jugador);
                                menu(jugador,trampas,tFaciles,tMedias,tDificiles,juegoFinalizado);
                                break;
                            case 2:
                                if(!juegoFinalizado)System.out.println("Juego en curso, aun no disponible");
                                else reportes.generarReporte2(tFaciles, tMedias, tDificiles, jugador);
                                menu(jugador,trampas,tFaciles,tMedias,tDificiles,juegoFinalizado);
                                break;
                            case 3:
                                menu(jugador,trampas,tFaciles,tMedias,tDificiles,juegoFinalizado);
                                break;
                        }
                    } catch (IOException | NumberFormatException ex) {
                        System.out.println("Opcion invalida, regresando al menu");
                        menu(jugador,trampas,tFaciles,tMedias,tDificiles,juegoFinalizado);
                    }
                    menu();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:                    
                    System.out.println("Opcion invalida");
                    menu();
                    break;
            }
        }catch(IOException | NumberFormatException ex){
            System.out.println("Opcion invalida");
            menu(jugador,trampas,tFaciles,tMedias,tDificiles,juegoFinalizado);
        }
    }
}
