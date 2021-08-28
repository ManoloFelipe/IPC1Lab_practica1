package trampas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Felipe
 */
public class medias {

    public int[] trampas = new int[2];
    public int[] posiciones = new int[2];
    public boolean[] resultados = new boolean[2];

    public int[] error = new int[2];
    public int[][] posicionError = new int[2][2];
    public int[][][] respuestas = new int[2][0][0];

    public void iniciarDatosMedios() {
        trampas[0] = 0;
        trampas[1] = 0;
        posiciones[1] = 0;
        posiciones[1] = 0;
        resultados[0] = true;
        resultados[1] = true;
    }
    
    public int seleccionarProblema(int posicion){        
        int trampa = (int) (Math.random() * 3) + 1;
        while (trampas[0] == trampa || trampas[1] == trampa){
            trampa = (int) (Math.random() * 3) + 1;
        }

        if(trampas[0] == 0) {trampas[0] = trampa; posiciones[0] = posicion;}
        else {trampas[1] = trampa; posiciones[1] = posicion;}
        
        return trampa;
    }
    
    public void problema(int trampa){
        switch (trampa) {
            case 1:
                trampaUno();
                break;
            case 2:
                trampaDos();
                break;
            case 3:
                trampaTres();
                break;
            default:
                System.out.print("Manipulacion de datos detectada, borrando partida");
                System.exit(0);
                break;
        }
    }
    
    private int[][] sumaMatrices(int[][] matrizA, int[][] matrizB){
        int[][] resultado = new int[5][5];
        
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[i].length; j++) {
                resultado[i][j] = (matrizA[i][j] + matrizB[i][j]);
            }
        }
        
        return resultado;
    }
    
    private int[][] datosIngresadosUsuarios(){
        System.out.println("Ingrese los numeros de las posiciones indicadas:");
        int[][] data = new int[5][5];        
        
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print("Ingrese el numero de la posicion "+(i+1)+","+(1+j)+": ");
                data[i][j] = ingresoUsuario(i,j);
            }
        }
        
        return data;
    }
    
    private int ingresoUsuario(int i, int j){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int data = 0;
        try{
            data = Integer.parseInt(br.readLine());
        }
        catch(IOException | NumberFormatException ex){
            System.out.print("Valor invalido, Ingrese el numero de la posicion "+(i+1)+","+(j+1)+": ");
            ingresoUsuario(i,j);
        }
        return data;
    }

    private void trampaUno() {
        int[][] matrizA = new int[5][5];
        int[][] matrizB = new int[5][5];
        
        matrizA[0][0] = 7; matrizA[0][1] = 48; matrizA[0][2] = 5; matrizA[0][3] = 0; matrizA[0][4] = 1;
        matrizA[1][0] = 57; matrizA[1][1] = 8; matrizA[1][2] = 4; matrizA[1][3] = 6; matrizA[1][4] = 14;
        matrizA[2][0] = 0; matrizA[2][1] = 5; matrizA[2][2] = 6; matrizA[2][3] = 78; matrizA[2][4] = 15;
        matrizA[3][0] = 21; matrizA[3][1] = 14; matrizA[3][2] = 8; matrizA[3][3] = 19; matrizA[3][4] = 54;
        matrizA[4][0] = 32; matrizA[4][1] = 20; matrizA[4][2] = 26; matrizA[4][3] = 47; matrizA[4][4] = 12;
        
        matrizB[0][0] = 9; matrizB[0][1] = 5; matrizB[0][2] = 2; matrizB[0][3] = 1; matrizB[0][4] = 8;
        matrizB[1][0] = 4; matrizB[1][1] = 2; matrizB[1][2] = 3; matrizB[1][3] = 47; matrizB[1][4] = 8;
        matrizB[2][0] = 48; matrizB[2][1] = 55; matrizB[2][2] = 32; matrizB[2][3] = 19; matrizB[2][4] = 6;
        matrizB[3][0] = 7; matrizB[3][1] = 56; matrizB[3][2] = 32; matrizB[3][3] = 14; matrizB[3][4] = 8;
        matrizB[4][0] = 32; matrizB[4][1] = 87; matrizB[4][2] = 0; matrizB[4][3] = 1; matrizB[4][4] = 7;
        
        System.out.println("¡¡Suma de matrices!!");
        System.out.println("suma las siguientes matricez:");
        System.out.println("Matriz A:");
        dibujarMatriz(matrizA);
        System.out.println("");
        System.out.println("Matriz B:");
        dibujarMatriz(matrizB);
        System.out.println("");
                
        int[][] matrizR = sumaMatrices(matrizA, matrizB);
        int[][] matrizUsuario = datosIngresadosUsuarios();

        boolean resultado = true;

        for (int i = 0; i < matrizUsuario.length; i++) {
            for (int j = 0; j < matrizUsuario[i].length; j++) {
                if(matrizR[i][j] != matrizUsuario[i][j]){
                    resultado = false;
                    if(trampas[0] == 1) {
                        error[0] = matrizUsuario[i][j];
                        posicionError[0][0] = i;
                        posicionError[0][1] = j;
                        respuestas[0] = matrizR;
                        resultados[0] = false;
                    }else {
                        error[1] = matrizUsuario[i][j];
                        posicionError[1][0] = i;
                        posicionError[1][1] = j;
                        respuestas[1] = matrizR;
                        resultados[1] = false;
                    }
                    break;
                }
            }
        }
        
        if(resultado){            
            System.out.println("");
            System.out.println("Correcto!!");
            System.out.println("");
            System.out.println("Matriz Respuesta:");
            dibujarMatriz(matrizR);
            System.out.println("");
        }else{
            System.out.println("");
            System.out.println("Fallaste, intentalo la proxima.");
            System.out.println("");
        }
    }

    private boolean trampaDos() {
        int[][] matrizA = new int[5][5];
        int[][] matrizB = new int[5][5];
        
        matrizA[0][0] = 4; matrizA[0][1] = 9; matrizA[0][2] = 7; matrizA[0][3] = 45; matrizA[0][4] = 18;
        matrizA[1][0] = 7; matrizA[1][1] = 51; matrizA[1][2] = 26; matrizA[1][3] = 8; matrizA[1][4] = 38;
        matrizA[2][0] = 48; matrizA[2][1] = 26; matrizA[2][2] = 37; matrizA[2][3] = 21; matrizA[2][4] = 19;
        matrizA[3][0] = 1; matrizA[3][1] = 0; matrizA[3][2] = 6; matrizA[3][3] = 8; matrizA[3][4] = 1;
        matrizA[4][0] = 2; matrizA[4][1] = 19; matrizA[4][2] = 55; matrizA[4][3] = 25; matrizA[4][4] = 15;
        
        matrizB[0][0] = 0; matrizB[0][1] = 2; matrizB[0][2] = 15; matrizB[0][3] = 1; matrizB[0][4] = 66;
        matrizB[1][0] = 21; matrizB[1][1] = 48; matrizB[1][2] = 62; matrizB[1][3] = 7; matrizB[1][4] = 33;
        matrizB[2][0] = 4; matrizB[2][1] = 88; matrizB[2][2] = 0; matrizB[2][3] = 68; matrizB[2][4] = 4;
        matrizB[3][0] = 25; matrizB[3][1] = 18; matrizB[3][2] = 24; matrizB[3][3] = 7; matrizB[3][4] = 55;
        matrizB[4][0] = 24; matrizB[4][1] = 15; matrizB[4][2] = 36; matrizB[4][3] = 5; matrizB[4][4] = 98;
        
        System.out.println("¡¡Suma de matrices!!");
        System.out.println("suma las siguientes matricez:");
        System.out.println("Matriz A:");
        dibujarMatriz(matrizA);
        System.out.println("");
        System.out.println("Matriz B:");
        dibujarMatriz(matrizB);
        System.out.println("");
                
        int[][] matrizR = sumaMatrices(matrizA, matrizB);
        int[][] matrizUsuario = datosIngresadosUsuarios();

        boolean resultado = true;

        for (int i = 0; i < matrizUsuario.length; i++) {
            for (int j = 0; j < matrizUsuario[i].length; j++) {
                if(matrizR[i][j] != matrizUsuario[i][j]){
                    resultado = false;
                    if(trampas[0] == 2) {
                        error[0] = matrizUsuario[i][j];
                        posicionError[0][0] = i;
                        posicionError[0][1] = j;
                        respuestas[0] = matrizR;
                        resultados[0] = false;
                    }else {
                        error[1] = matrizUsuario[i][j];
                        posicionError[1][0] = i;
                        posicionError[1][1] = j;
                        respuestas[1] = matrizR;
                        resultados[1] = false;
                    }
                    break;
                }
            }
        }
        
        if(resultado){            
            System.out.println("");
            System.out.println("Correcto!!");
            System.out.println("");
            System.out.println("Matriz Respuesta:");
            dibujarMatriz(matrizR);
            System.out.println("");
            return true;
        }else{
            System.out.println("");
            System.out.println("Fallaste, intentalo la proxima.");
            System.out.println("");
            return false;
        }
    }

    private boolean trampaTres() {
        int[][] matrizA = new int[5][5];
        int[][] matrizB = new int[5][5];
        
        matrizA[0][0] = 0; matrizA[0][1] = 1; matrizA[0][2] = 15; matrizA[0][3] = 5; matrizA[0][4] = 36;
        matrizA[1][0] = 1; matrizA[1][1] = 78; matrizA[1][2] = 65; matrizA[1][3] = 32; matrizA[1][4] = 4;
        matrizA[2][0] = 48; matrizA[2][1] = 66; matrizA[2][2] = 39; matrizA[2][3] = 0; matrizA[2][4] = 55;
        matrizA[3][0] = 14; matrizA[3][1] = 98; matrizA[3][2] = 63; matrizA[3][3] = 20; matrizA[3][4] = 15;
        matrizA[4][0] = 11; matrizA[4][1] = 39; matrizA[4][2] = 84; matrizA[4][3] = 7; matrizA[4][4] = 1;
        
        matrizB[0][0] = 78; matrizB[0][1] = 25; matrizB[0][2] = 66; matrizB[0][3] = 48; matrizB[0][4] = 98;
        matrizB[1][0] = 0; matrizB[1][1] = 45; matrizB[1][2] = 2; matrizB[1][3] = 3; matrizB[1][4] = 1;
        matrizB[2][0] = 2; matrizB[2][1] = 9; matrizB[2][2] = 14; matrizB[2][3] = 10; matrizB[2][4] = 20;
        matrizB[3][0] = 35; matrizB[3][1] = 87; matrizB[3][2] = 65; matrizB[3][3] = 2; matrizB[3][4] = 32;
        matrizB[4][0] = 25; matrizB[4][1] = 8; matrizB[4][2] = 4; matrizB[4][3] = 9; matrizB[4][4] = 39;
        
        System.out.println("¡¡Suma de matrices!!");
        System.out.println("suma las siguientes matricez:");
        System.out.println("Matriz A:");
        dibujarMatriz(matrizA);
        System.out.println("");
        System.out.println("Matriz B:");
        dibujarMatriz(matrizB);
        System.out.println("");
                
        int[][] matrizR = sumaMatrices(matrizA, matrizB);
        int[][] matrizUsuario = datosIngresadosUsuarios();

        boolean resultado = true;

        for (int i = 0; i < matrizUsuario.length; i++) {
            for (int j = 0; j < matrizUsuario[i].length; j++) {
                if(matrizR[i][j] != matrizUsuario[i][j]){
                    resultado = false;
                    if(trampas[0] == 3) {
                        error[0] = matrizUsuario[i][j];
                        posicionError[0][0] = i;
                        posicionError[0][1] = j;
                        respuestas[0] = matrizR;
                        resultados[0] = false;
                    }else {
                        error[1] = matrizUsuario[i][j];
                        posicionError[1][0] = i;
                        posicionError[1][1] = j;
                        respuestas[1] = matrizR;
                        resultados[1] = false;
                    }
                    break;
                }
            }
        }
        
        if(resultado){            
            System.out.println("");
            System.out.println("Correcto!!");
            System.out.println("");
            System.out.println("Matriz Respuesta:");
            dibujarMatriz(matrizR);
            System.out.println("");
            return true;
        }else{
            System.out.println("");
            System.out.println("Fallaste, intentalo la proxima.");
            System.out.println("");
            return false;
        }
    }
    
    private void dibujarMatriz(int[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if(j == 0) System.out.print("| ");
                System.out.printf("%4d", matriz[i][j]);
                if(j == matriz[i].length - 1) System.out.println(" |");
            }
        }
    }
}
