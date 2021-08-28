package juego;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import trampas.*;

public class Tablero {
    
    int[][] tablero = new int[8][8];
    int[] trampas = new int[0];
    int[] cambioDePosicion = new int[0];
    
    Jugador jugador = new Jugador();
    faciles tFaciles = new faciles();
    medias tMedias = new medias();
    // dificiles tDificiles = new dificiles();
    
    void separacion(){
        System.out.println("................................................");
    }
    
    public void mostrarTableroInicial(){
        jugador.datosJugador();
        tFaciles.iniciarDatosFaciles();
        generarTrampas();
        crearTablero();
        redibujarTablero();
    }
    
    public void reanudarJuego(
        Jugador jugador,
        int[] trampas,
        faciles tFaciles,
        medias tMedias
    ){
        this.trampas = trampas;
        this.jugador = jugador;
        this.tFaciles = tFaciles;
        this.tMedias = tMedias;
        crearTablero();
        redibujarTablero();
    }
    
    private void generarTrampas() {
        int pos;

        //aprovecho este comentario para quejarme de la restriccion del uso de ArrayList :)

        for(int i = 0; i < 8; i++){
            int noPenalizaciones = (int) ((Math.random() * 3) + 2);
            for (int j = 0; j < noPenalizaciones; j++) {
                int max = 8*(8-i), min = max - 7;
                pos = (int) ((Math.random() * (max - min + 1)) + min);
                for (int k = 0; k < trampas.length; k++) {
                    while(trampas[k] == pos || pos == 1) {
                        pos = (int) ((Math.random() * (max - min + 1)) + min);
                    }                    
                }
                trampas = agregarElementoArray(trampas, pos);
            }
        }
    }


    
    private void turno() {
        int opcion = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Ingrese una opcion:");
        System.out.println("1. Lanzar Dado");
        System.out.println("2. Regresar al menu principal");
        
        try{
            opcion = Integer.parseInt(br.readLine());
            
            switch(opcion){
                case 1:
                    tirarDado();
                    break;
                case 2:
                    Juego juego = new Juego();
                    juego.menu(
                        jugador,
                        trampas,
                        tFaciles,
                        tMedias
                    );
                    break;
                default:                    
                    System.out.println("Opcion invalida");
                    turno();
                    break;
            }
        }catch(IOException | NumberFormatException ex){
            System.out.println("Opcion invalida");
            turno();
        }
        separacion();
    }
    
    private void tirarDado(){
        System.out.println("");
        
        int avanza = (int) (Math.random() * 6) + 1;
        
        jugador.cambioDePosicion(avanza);
        
        System.out.println("El jugador avanza "+ avanza +" espacios");
        for(int i : trampas){
            if(i == jugador.posicion){verificarPosicion(); break;}
        }
        System.out.println("Redibujando tablero...");
        redibujarTablero();
    }
    
    private void verificarPosicion(){
        if(jugador.caidaEnTrampa()){
            System.out.println("");
            System.out.println("Has caido en una penalizacion!");
            int trampa = 0;
            switch(jugador.fila){
                case 1: case 2:
                    trampa = tFaciles.seleccionarProblema(jugador.posicion);
                    tFaciles.problema(trampa);
                    break;
                case 3: case 4: case 5:
                    trampa = tMedias.seleccionarProblema(jugador.posicion);
                    tMedias.problema(trampa);
                    break;
                case 6: case 7: case 8:
                    
                    break;
                default:
                    System.out.print("Manipulacion de datos detectada, borrando partida");
                    System.exit(0);
                    break;
            } 
            separacion();
        }
        else{
            System.out.println("Ya has caido en suficientes trampas de este nivel, continua con tu viaje!!");
            separacion();
        }
    }
    
    private void redibujarTablero(){
        for (int i = 0; i < 8; i++) {
            separacion();
            for (int j = 0; j < 8; j++) { 
                if (!verificarTrampas(tablero[i][j])) {
                    if (j == 0 && i == 6) System.out.print("|   " + tablero[i][j] + "|");
                    else if (j < 7 && i != 7) System.out.print("|  " + tablero[i][j] + "|");
                    else if (i == 7 && j < 7) System.out.print("|   " + tablero[i][j] + "|");
                    if (j == 7  && i != 7) System.out.println("|  " + tablero[i][j] + "|");
                    else if (j == 7 && i == 7) System.out.println("|   " + tablero[i][j] + "|");                
                }else {
                    if (j == 0 && i == 6) System.out.print("|#  " + tablero[i][j] + "|");
                    else if (j < 7 && i != 7) System.out.print("|# " + tablero[i][j] + "|");
                    else if (i == 7 && j < 7) System.out.print("|#  " + tablero[i][j] + "|");
                    if (j == 7  && i != 7) System.out.println("|# " + tablero[i][j] + "|");
                    else if (j == 7 && i == 7) System.out.println("|#  " + tablero[i][j] + "|"); 
                }
            }
            for(int j = 0; j<8; j++){
                if(jugador.posicion != tablero[i][j])System.out.print("|    |");
                else System.out.print("|   @|");
                if(j==7) System.out.println("");
            }
            if(i == 7) separacion();
        }
        turno();
    }

    private boolean verificarTrampas(int posicion){
        boolean retorno = false;
        
        for (int i = 0; i < trampas.length; i++) {
            if(trampas[i] == posicion) {retorno = true; break;}
        }

        return retorno;
    }

    private int[] agregarElementoArray(int[] array, int elemento){
        int retorno[] = new int[array.length+1];

        for (int i = 0; i < array.length; i++) {
            if(array[i] != 0)retorno[i] = array[i];
        }

        retorno[array.length] = elemento;
 
        return retorno; 
    }

    private void crearTablero(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i%2 == 0)tablero[i][j] = (8*(8-i)) - (7-j);
                if (i%2 != 0)tablero[i][j] = (8*(8-i)) - j;
            }
        }
    }
}
