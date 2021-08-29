package juego;

/**
 *
 * @author Felipe
 */
public class Jugador {
    int fila;   //numero de fila en la que se encuentra el Jugador [0-7]
    // int columna;    //numero de columna donde se encuentra el jugado [0-7], sin uso
    int posicion;   //posicion actual del Jugador [1-64]
    int trampasFaciles;  //numero de veces que ah caido en trampas [0-2]
    int trampasMedias;  //numero de veces que ah caido en trampas [0-2]
    int trampasDificiles;  //numero de veces que ah caido en trampas [0-2]
    
    void datosJugador(){
        this.fila = 1;
        //this.columna = columna;
        this.posicion = 1;
        this.trampasFaciles = 0;
        this.trampasMedias = 0;
        this.trampasDificiles = 0;
    }
    
    boolean caidaEnTrampa(){
        boolean retorno = false;
        switch(fila){
            case 1: case 2:
                retorno = (trampasFaciles < 2);
                if(trampasFaciles < 2)trampasFaciles++;
                break;
            case 3: case 4: case 5:
                retorno = (trampasMedias < 2);
                if(trampasMedias < 2)trampasMedias++;
                break;
            case 6: case 7: case 8:
                retorno = (trampasDificiles < 2);
                if(trampasDificiles < 2) trampasDificiles++;
                break;
            default:
                System.out.println("Manipulacion de datos detectada, borrando partida");
                System.exit(0);
                break;
        }   
        return retorno;
    }
        
    public boolean cambioDePosicion(int avanza){
        posicion += avanza;
        if (posicion > 64) {
            ganar();
            return true;
        }
        
        fila = (int) Math.ceil((double)posicion/(double)8);
        return false;
    }
    
    void ganar(){
        System.out.println("Felicidades, Haz ganado");
        System.out.println("");
        System.out.println("Indica que deseas realizar:");
        System.out.println("1. Generar Bitacora.");
        System.out.println("2. Nueva partida.");
        System.out.println("3. Salir");

    }
}
