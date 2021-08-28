package trampas;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author Felipe
 */
public class dificiles {

    int trampa1 = 0;
    int posicionT1 = 0;
    int trampa2 = 0;
    int posicionT2 = 0;
    int errorT1 = 0;
    int[] posicionErrorT1 = new int[2];
    int errorT2 = 0;
    int[] posicionErrorT2 = new int[2];

	public int seleccionarProblema(int posicion){        
        int trampa = (int) (Math.random() * 3) + 1;
        while (trampa1 == trampa || trampa2 == trampa){
            trampa = (int) (Math.random() * 3) + 1;
        }

        if(trampa1 == 0) {trampa1 = trampa; posicionT1 = posicion;}
        else {trampa2 = trampa; posicionT2 = posicion;}
        
        return trampa;
    }
    
    public boolean problema(int trampa){
        boolean retorno = false;
        
        switch (trampa) {
            case 1:
                retorno = trampaUno();
                break;
            case 2:
                retorno = trampaDos();
                break;
            case 3:
                retorno = trampaTres();
                break;
            default:
                System.out.print("Manipulacion de datos detectada, borrando partida");
                System.exit(0);
                break;
        }
        
        return retorno;
    }

	private boolean trampaUno(){
		return true;
	}
	
	private boolean trampaDos(){
		return true;
	}
	
	private boolean trampaTres(){
		return true;
	}

	/////// Operaciones
    
    private double encontrarDeterminante(double[][] matriz){
        double determinante = 0.000;
        double[][] temporal;

		//matriz de 1x1
    	if (matriz.length == 1) {
			determinante = matriz[0][0];
			return (determinante);
		}

		//matriz de 2x2
		if (matriz.length == 2) {
			determinante = ((matriz[0][0] * matriz[1][1]) - (matriz[0][1] * matriz[1][0]));
			return (determinante);
		}

		//matriz de nxn (lanza error en matrices inferiores a 3x3)
        for (int i = 0; i < matriz[0].length; i++) {

			//temporal == matriz de tamaño n-1xn-1 (matriz cuadrada) referente a la matriz enviada a esta funcion
			//de temporal se busca una matriz mas pequeña para 
            temporal = new double[matriz.length - 1][matriz[0].length - 1];

			//metodo por adjuntos (fila 1), la nueva matriz no llevara la fila 0 de la matriz original, j inicia en 1
            for (int j = 1; j < matriz.length; j++) {
                for (int k = 0; k < matriz[0].length; k++) {
					//k != i, sera la fila y columna a eliminar en esta repeticion
                    if (k < i) {
                        temporal[j - 1][k] = matriz[j][k];
                    } else if (k > i) {
                        temporal[j - 1][k - 1] = matriz[j][k];
                    }
                }
            }

			//determinante en metodo de los adjuntos  =
			// A_1(i+1) * (-1)^(1+(i+1)) * (determinante de matriz menor)
            determinante += matriz[0][i] * Math.pow (-1, (double) i) * encontrarDeterminante(temporal);
        }
        return (determinante);
    }

	private double[][] inversaGaussJordan(double[][] matriz){
		double[][] matrizRetorno, matrizGJ;
		double operador = 0;
		int columnas = 0;

		for (int i = 0; i < matriz[0].length; i++) columnas++;

		//inicializar matriz y dar valor
		matrizGJ = new double[matriz.length][columnas*2];
		matrizRetorno = new double[matriz.length][columnas];

		for (int i = 0; i < matrizGJ.length; i++) {
			for (int j = 0; j < matrizGJ[0].length; j++) {
				if(j < columnas) matrizGJ[i][j] = matriz[i][j];
				else if((columnas + i) == j) matrizGJ[i][j] = 1;
				else matrizGJ[i][j] = 0;
			}
		}

		//resolver inversa
		double pivote = 0;
		for (int i = 0; i < matrizGJ.length; i++) {
			//hacer un pivote en la fila i				
			pivote = matrizGJ[i][i];

			//verificar para evitar pivote 0, verifica cuantas filas han sido valuadas con i
			while (pivote == 0){
				matrizGJ = cambioMatriz(matrizGJ, i);
				pivote = matrizGJ[i][i];
			}

			//finalmente si el pivote no cambia de 0, devuelve la matriz
			if(pivote == 0) break;

			for (int j = i; j < matrizGJ[0].length; j++) {
				//matriz con ixj = 1, el pivote
				matrizGJ[i][j] = (matrizGJ[i][j]/pivote);
			}
			
			//recorrer las demas filas para que no queden numeros diferentes a 0 en la posicion i
			for (int j = 0; j < matrizGJ.length; j++) {                
                //operador para hacer que la columna del pivote tenga 0
				operador = matrizGJ[j][i];
				for (int k = 0; k < matrizGJ[0].length; k++) {
					// evita valuar filas innecesarias
					if( j == i || operador == 0 ) continue;
					matrizGJ[j][k] = matrizGJ[j][k] - (operador * matrizGJ[i][k]);
				}
			}
		}

		//ahora separamos I de la matriz inversa
		for (int i = 0; i < matrizGJ.length; i++) {
			for (int j = columnas; j < matrizGJ[0].length; j++) {
				matrizRetorno[i][j-columnas] = redondear3decimales(matrizGJ[i][j]);
			}
		}

		return matrizRetorno;
	}

	private double[][] cambioMatriz(double[][] matriz, int filasValuadas){
		double[] valorAux = new double[matriz[0].length];
		for (int i = filasValuadas; i < matriz.length ; i++) {
			if(i == filasValuadas) valorAux = matriz[i];
			else matriz[i-1] = matriz[i];
			if(i == matriz.length - 1) matriz[i] = valorAux;
		}

		return matriz;
	}

	private void dibujarMatriz(int[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if(j == 0) System.out.print("| ");
                System.out.printf("%8d", matriz[i][j]);
                if(j == matriz[i].length - 1) System.out.println(" |");
            }
        }
    }

    private double redondear3decimales(double numero){
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.HALF_EVEN);
        double resultado = Double.parseDouble(df.format(numero));
        return resultado;
    }
}
