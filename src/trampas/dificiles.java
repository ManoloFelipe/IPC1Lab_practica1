package trampas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author Felipe
 */
public class dificiles {

    public int[] trampas = new int[2];
    public int[] posiciones = new int[2];
    public double[] errorTipo1 = new double[2]; //error en determinante
    public double[][][] errorTipo2 = new double[2][0][0]; //error en matriz
	public boolean[] resultados = new boolean[2];
	public double[] determinantesRespuesta = new double[2];
    public double[][][] matricesRespuesta = new double[2][0][0];
    public double[][][] matrices = new double[4][0][0];
    public double[][][] matricesGaussJordan = new double[2][0][0];

	public void iniciarDatosDificiles() {
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
        else {trampas[1] = trampa; posiciones[0] = posicion;}
        
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

	private void trampaUno(){
		double[][] matrizA = new double[4][4];
        double[][] matrizB = new double[4][4];
        double[][] matrizBInversa = new double[4][4];
		double[][] matrizR = new double[0][0];
		double determinanteUsuario = 0.000;
        
        matrizA[0][0] = 5; matrizA[0][1] = 10; matrizA[0][2] = 1; matrizA[0][3] = 3;
        matrizA[1][0] = 9; matrizA[1][1] = 14; matrizA[1][2] = 2; matrizA[1][3] = 6;
        matrizA[2][0] = 7; matrizA[2][1] = 8; matrizA[2][2] = 15; matrizA[2][3] = 3;
        matrizA[3][0] = 6; matrizA[3][1] = 8; matrizA[3][2] = 9; matrizA[3][3] = 2;
        
        matrizB[0][0] = 5; matrizB[0][1] = 13; matrizB[0][2] = 9; matrizB[0][3] = 4;
        matrizB[1][0] = 1; matrizB[1][1] = 9; matrizB[1][2] = 6; matrizB[1][3] = 3;
        matrizB[2][0] = 8; matrizB[2][1] = 11; matrizB[2][2] = 69; matrizB[2][3] = 33;
        matrizB[3][0] = 25; matrizB[3][1] = 6; matrizB[3][2] = 7; matrizB[3][3] = 4;

		double determinanteR = redondear3decimales(encontrarDeterminante(matrizB));
		System.out.println(determinanteR);
		if(determinanteR == 0) matrizR = new double[1][1];
		else {
			matrizBInversa = inversaGaussJordan(matrizB);
			matrizR = multiplicacion(matrizA, matrizBInversa);
		}
        
        System.out.println("Division de matrices!!");
        System.out.println("Realiza la division A/B:");
        System.out.println("Matriz A:");
        dibujarMatriz(matrizA);
        System.out.println("");
        System.out.println("Matriz B:");
        dibujarMatriz(matrizB);
        System.out.println("");
        System.out.println("Para dividir matrices, la matriz divisora debe tener determinante diferente a 0.");
        System.out.println("¿cual es la determinante de la matrizB?, recuerde redondear a 3 decimales:");

		determinanteUsuario = ingresoUsuarioDeterminante();
		System.out.println("Ingreso:" + determinanteUsuario + " res. correcta:"+ determinanteR);

		//datos para el reporte
		if(trampas[0] == 1){
			determinantesRespuesta[0] = determinanteR;
			matricesRespuesta[0] = matrizR;
			matrices[0] = matrizA;
			matrices[1] = matrizB;
			matricesGaussJordan[0] = matrizBInversa;
		}else{
			determinantesRespuesta[1] = determinanteR;
			matricesRespuesta[1] = matrizR;
			matrices[2] = matrizA;
			matrices[3] = matrizB;
			matricesGaussJordan[1] = matrizBInversa;
		}

		if(determinanteUsuario == 0){
			if(determinanteR == 0){
				System.out.println("");
				System.out.println("Correcto!!");
				System.out.println("");
				System.out.println("Determinante de Matriz B: " + determinanteR);
				System.out.println("Imposible Realizar Division");
				System.out.println("");
			}
			else{
				System.out.println("");
				System.out.println("Fallaste, intentalo la proxima.");
				System.out.println("");
				//datos para el reporte en error
				if(trampas[0] == 1){
					errorTipo1[0] = determinanteUsuario;
					resultados[0] = false;
				}else{
					errorTipo1[1] = determinanteUsuario;
					resultados[1] = false;
				}
			}
		}else{
			if(determinanteR ==  determinanteUsuario){
				System.out.println("Correcto!!");
				System.out.println("");
				boolean resultado = true;
				double[][] matrizUsuario = ingresoUsuarioMatriz();
				for (int i = 0; i < matrizUsuario.length; i++) {
					for (int j = 0; j < matrizUsuario[i].length; j++) {
						if(matrizR[i][j] != matrizUsuario[i][j]){
							resultado = false;
							if(trampas[0] == 1) {
								errorTipo2[0] = matrizUsuario;
								matricesRespuesta[0] = matrizR;
								resultados[0] = false;
							}else {
								errorTipo2[0] = matrizUsuario;
								matricesRespuesta[0] = matrizR;
								resultados[0] = false;
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
			}else{
				System.out.println("");
				System.out.println("Fallaste, intentalo la proxima.");
				System.out.println("");
				//datos para el reporte en error
				if(trampas[0] == 1){
					errorTipo1[0] = determinanteUsuario;
					resultados[0] = false;
				}else{
					errorTipo1[1] = determinanteUsuario;
					resultados[1] = false;
				}
			}
		}
	}
	
	private void trampaDos(){
		double[][] matrizA = new double[4][4];
        double[][] matrizB = new double[4][4];
        double[][] matrizBInversa = new double[4][4];
		double[][] matrizR = new double[0][0];
		double determinanteUsuario = 0.000;
        
        matrizA[0][0] = 1; matrizA[0][1] = 12; matrizA[0][2] = 9; matrizA[0][3] = 8;
        matrizA[1][0] = 7; matrizA[1][1] = 6; matrizA[1][2] = 3; matrizA[1][3] = 2;
        matrizA[2][0] = 0; matrizA[2][1] = 5; matrizA[2][2] = 6; matrizA[2][3] = 14;
        matrizA[3][0] = 6; matrizA[3][1] = 9; matrizA[3][2] = 6; matrizA[3][3] = 10;
        
        matrizB[0][0] = 8; matrizB[0][1] = 19; matrizB[0][2] = 20; matrizB[0][3] = 4;
        matrizB[1][0] = 12; matrizB[1][1] = 33; matrizB[1][2] = 6; matrizB[1][3] = 8;
        matrizB[2][0] = 4; matrizB[2][1] = 5; matrizB[2][2] = 9; matrizB[2][3] = 7;
        matrizB[3][0] = 8; matrizB[3][1] = 22; matrizB[3][2] = 14; matrizB[3][3] = 6;

		double determinanteR = redondear3decimales(encontrarDeterminante(matrizB));
		if(determinanteR == 0) matrizR = new double[1][1];
		else {
			matrizBInversa = inversaGaussJordan(matrizB);
			matrizR = multiplicacion(matrizA, matrizBInversa);
		}
        
        System.out.println("Division de matrices!!");
        System.out.println("Realiza la division A/B:");
        System.out.println("Matriz A:");
        dibujarMatriz(matrizA);
        System.out.println("");
        System.out.println("Matriz B:");
        dibujarMatriz(matrizB);
        System.out.println("");
        System.out.println("Para dividir matrices, la matriz divisora debe tener determinante diferente a 0.");
        System.out.println("¿cual es la determinante de la matrizB?, recuerde redondear a 3 decimales:");

		determinanteUsuario = ingresoUsuarioDeterminante();

		//datos para el reporte
		if(trampas[0] == 2){
			determinantesRespuesta[0] = determinanteR;
			matricesRespuesta[0] = matrizR;
			matrices[0] = matrizA;
			matrices[1] = matrizB;
			matricesGaussJordan[0] = matrizBInversa;
		}else{
			determinantesRespuesta[1] = determinanteR;
			matricesRespuesta[1] = matrizR;
			matrices[2] = matrizA;
			matrices[3] = matrizB;
			matricesGaussJordan[1] = matrizBInversa;
		}

		if(determinanteUsuario == 0){
			if(determinanteR == 0){
				System.out.println("");
				System.out.println("Correcto!!");
				System.out.println("");
				System.out.println("Determinante de Matriz B: " + determinanteR);
				System.out.println("Imposible Realizar Division");
				System.out.println("");
			}
			else{
				System.out.println("");
				System.out.println("Fallaste, intentalo la proxima.");
				System.out.println("");
				//datos para el reporte en error
				if(trampas[0] == 2){
					errorTipo1[0] = determinanteUsuario;
					resultados[0] = false;
				}else{
					errorTipo1[1] = determinanteUsuario;
					resultados[1] = false;
				}
			}
		}else{
			if(determinanteR == determinanteUsuario){
				System.out.println("Correcto!!");
				System.out.println("");
				boolean resultado = true;
				double[][] matrizUsuario = ingresoUsuarioMatriz();
				for (int i = 0; i < matrizUsuario.length; i++) {
					for (int j = 0; j < matrizUsuario[i].length; j++) {
						if(matrizR[i][j] != matrizUsuario[i][j]){
							resultado = false;
							if(trampas[0] == 2) {
								errorTipo2[0] = matrizUsuario;
								matricesRespuesta[0] = matrizR;
								resultados[0] = false;
							}else {
								errorTipo2[0] = matrizUsuario;
								matricesRespuesta[0] = matrizR;
								resultados[0] = false;
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
			}else{
				System.out.println("");
				System.out.println("Fallaste, intentalo la proxima.");
				System.out.println("");
				//datos para el reporte en error
				if(trampas[0] == 2){
					errorTipo1[0] = determinanteUsuario;
					resultados[0] = false;
				}else{
					errorTipo1[1] = determinanteUsuario;
					resultados[1] = false;
				}
			}
		}
	}
	
	private void trampaTres(){
		double[][] matrizA = new double[4][4];
        double[][] matrizB = new double[4][4];
        double[][] matrizBInversa = new double[4][4];
		double[][] matrizR = new double[0][0];
		double determinanteUsuario = 0.000;
        
        matrizA[0][0] = 5; matrizA[0][1] = 9; matrizA[0][2] = 14; matrizA[0][3] = 5;
        matrizA[1][0] = 6; matrizA[1][1] = 0; matrizA[1][2] = 5; matrizA[1][3] = 3;
        matrizA[2][0] = 1; matrizA[2][1] = 14; matrizA[2][2] = 68; matrizA[2][3] = 8;
        matrizA[3][0] = 7; matrizA[3][1] = 5; matrizA[3][2] = 3; matrizA[3][3] = 9;
        
        matrizB[0][0] = 0; matrizB[0][1] = 9; matrizB[0][2] = 7; matrizB[0][3] = 19;
        matrizB[1][0] = 2; matrizB[1][1] = 30; matrizB[1][2] = 5; matrizB[1][3] = 48;
        matrizB[2][0] = 1; matrizB[2][1] = 31; matrizB[2][2] = 2; matrizB[2][3] = 5;
        matrizB[3][0] = 15; matrizB[3][1] = 8; matrizB[3][2] = 4; matrizB[3][3] = 3;

		double determinanteR = redondear3decimales(encontrarDeterminante(matrizB));
		if(determinanteR == 0) matrizR = new double[1][1];
		else {
			matrizBInversa = inversaGaussJordan(matrizB);
			matrizR = multiplicacion(matrizA, matrizBInversa);
		}
        
        System.out.println("Division de matrices!!");
        System.out.println("Realiza la division A/B:");
        System.out.println("Matriz A:");
        dibujarMatriz(matrizA);
        System.out.println("");
        System.out.println("Matriz B:");
        dibujarMatriz(matrizB);
        System.out.println("");
        System.out.println("Para dividir matrices, la matriz divisora debe tener determinante diferente a 0.");
        System.out.println("¿cual es la determinante de la matrizB?, recuerde redondear a 3 decimales:");

		determinanteUsuario = ingresoUsuarioDeterminante();

		//datos para el reporte
		if(trampas[0] == 3){
			determinantesRespuesta[0] = determinanteR;
			matricesRespuesta[0] = matrizR;
			matrices[0] = matrizA;
			matrices[1] = matrizB;
			matricesGaussJordan[0] = matrizBInversa;
		}else{
			determinantesRespuesta[1] = determinanteR;
			matricesRespuesta[1] = matrizR;
			matrices[2] = matrizA;
			matrices[3] = matrizB;
			matricesGaussJordan[1] = matrizBInversa;
		}

		if(determinanteUsuario == 0){
			if(determinanteR == 0){
				System.out.println("");
				System.out.println("Correcto!!");
				System.out.println("");
				System.out.println("Determinante de Matriz B: " + determinanteR);
				System.out.println("Imposible Realizar Division");
				System.out.println("");
			}
			else{
				System.out.println("");
				System.out.println("Fallaste, intentalo la proxima.");
				System.out.println("");
				//datos para el reporte en error
				if(trampas[0] == 3){
					errorTipo1[0] = determinanteUsuario;
					resultados[0] = false;
				}else{
					errorTipo1[1] = determinanteUsuario;
					resultados[1] = false;
				}
			}
		}else{
			if(determinanteR == determinanteUsuario){
				System.out.println("Correcto!!");
				System.out.println("");
				boolean resultado = true;
				double[][] matrizUsuario = ingresoUsuarioMatriz();
				for (int i = 0; i < matrizUsuario.length; i++) {
					for (int j = 0; j < matrizUsuario[i].length; j++) {
						if(matrizR[i][j] != matrizUsuario[i][j]){
							resultado = false;
							if(trampas[0] == 3) {
								errorTipo2[0] = matrizUsuario;
								matricesRespuesta[0] = matrizR;
								resultados[0] = false;
							}else {
								errorTipo2[0] = matrizUsuario;
								matricesRespuesta[0] = matrizR;
								resultados[0] = false;
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
			}else{
				System.out.println("");
				System.out.println("Fallaste, intentalo la proxima.");
				System.out.println("");
				//datos para el reporte en error
				if(trampas[0] == 3){
					errorTipo1[0] = determinanteUsuario;
					resultados[0] = false;
				}else{
					errorTipo1[1] = determinanteUsuario;
					resultados[1] = false;
				}
			}
		}		
	}

	private double[][] ingresoUsuarioMatriz(){
		System.out.println("Ingrese los numeros de las posiciones indicadas:");
        double[][] data = new double[4][4];        
        
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print("Ingrese el numero de la posicion "+(i+1)+","+(1+j)+": ");
                data[i][j] = ingresoUsuario(i,j);
            }
        }
        
        return data;
	}

	private double ingresoUsuario(int i, int j){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double data = 0;
        try{
            data = Double.parseDouble(br.readLine());
        }
        catch(IOException | NumberFormatException ex){
            System.out.print("Valor invalido, Ingrese el numero de la posicion "+(i+1)+","+(j+1)+": ");
            ingresoUsuario(i,j);
        }
        return data;
    }
	
	private double ingresoUsuarioDeterminante(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double retorno = 0.000;
		try{
			retorno = Double.parseDouble(br.readLine());
		}
		catch(IOException | NumberFormatException ex){
			System.out.println("Valor invalido, Ingrese la determinante de la matrizB : ");
			ingresoUsuarioDeterminante();
		}
		return retorno;
	}

	/////// Operaciones

	private double[][] multiplicacion(double[][] matrizA, double[][] matrizB){
		double[][] retorno;
		int filas, columnas;

		filas = matrizA.length;
		columnas = matrizB[0].length;
		retorno = new double[filas][columnas];

		for (int i = 0; i < retorno.length; i++) {
			for (int j = 0; j < retorno[0].length; j++) {
				for (int k = 0; k < matrizA[0].length; k++) {
					retorno[i][j] += (matrizA[i][k] * matrizB[k][j]); 
				}
			}
		}

		return retorno;
	}
    
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
            determinante += matriz[0][i] * Math.pow (-1, (i+2)) * encontrarDeterminante(temporal);
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
			//ERROR!! cuando llega a la fila final con pivote 0, bucle infinito, revisar
			while (pivote == 0){
				matrizGJ = cambioMatriz(matrizGJ, i);
				pivote = matrizGJ[i][i];
			}

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
				matrizRetorno[i][j-columnas] = matrizGJ[i][j];
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

	private void dibujarMatriz(double[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if(j == 0) System.out.print("| ");
                System.out.printf("%10.3f", matriz[i][j]);
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
