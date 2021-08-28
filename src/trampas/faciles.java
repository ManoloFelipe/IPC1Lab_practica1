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
public class faciles {
    public int[] trampas = new int[2];
    public int[] posiciones = new int[2];    
    public boolean[] resultado = new boolean[2];
    public double[] error = new double[6];
    public double[] respuestas = new double[6];
    public double[] datos = new double[6];

    public void iniciarDatosFaciles() {
        trampas[0] = 0;
        trampas[1] = 0;
        posiciones[1] = 0;
        posiciones[1] = 0;
        resultado[0] = true;
        resultado[1] = true;
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
    
    private double descubrirLado(double lado1, double lado2, double angulo){
        double resultado = 0.000;        
        double cos = Math.cos(Math.toRadians(angulo));        
        resultado = Math.sqrt(Math.pow(lado1,2)+ Math.pow(lado2,2) - (2 * lado1 * lado2 * cos));
        return resultado;
    }
    
    private double descubrirAngulo1(double lado1, double lado2, double ladoDescubierto){
        double resultado = 0.000;        
        resultado = Math.toDegrees(Math.acos((Math.pow(lado1,2) - Math.pow(ladoDescubierto,2) - Math.pow(lado2,2))/(-2*ladoDescubierto*lado2)));        
        return resultado;
    }
    
    private double descubrirAngulo2(double angulo1, double angulo2){
        return (180-angulo1-angulo2);
    }
    
    private double redondear3decimales(double numero){
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.HALF_EVEN);
        double resultado = Double.parseDouble(df.format(numero));
        return resultado;
    }
    
    void mostrarTrampa(){
        System.out.println("");
        System.out.println("imagen de referencia: https://i.imgur.com/8cOAUmX.png");
        System.out.println("");
    }
    
    void trampaUno(){
        mostrarTrampa();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        double ladoB = 0.000, ladoA = 15, ladoC = 20;
        double anguloGamma = 0.000, anguloAlfa = 25, anguloBeta = 0.000;
        
        double resLadoB = descubrirLado(ladoC, ladoA, anguloAlfa);
        double resBeta = descubrirAngulo1(ladoA, ladoC, resLadoB);
        
        resLadoB = redondear3decimales(resLadoB);
        resBeta = redondear3decimales(resBeta);
        
        double resGamma = redondear3decimales(descubrirAngulo2(anguloAlfa, resBeta));
        
        System.out.println("Encontrar el lado B y los angulos Beta y Gamma; aproxime a 3 decimales.");
        System.out.println("Datos:");
        System.out.println("Lado A = "+ladoA);
        System.out.println("Lado C = "+ladoC);
        System.out.println("angulo alfa = "+anguloAlfa);        
        System.out.println("");                
        
        try{
            System.out.println("Ingrese el lado B");
            ladoB = Double.parseDouble(br.readLine());
            System.out.println("Ingrese el angulo Gamma");
            anguloGamma = Double.parseDouble(br.readLine());
            System.out.println("Ingrese el angulo Beta");
            anguloBeta = Double.parseDouble(br.readLine());
        }catch (IOException | NumberFormatException ex){
            System.out.println("Opcion invalida");
            trampaUno();
        }

        if(trampas[0] == 1){            
            datos[0] = ladoA;
            datos[1] = ladoC;
            datos[2] = anguloAlfa;
        }else{
            datos[3] = ladoA;
            datos[4] = ladoC;
            datos[5] = anguloAlfa;
        }
        
        if(ladoB == resLadoB && anguloBeta == resBeta && anguloGamma == resGamma) {
            System.out.println("Correcto!!");
            System.out.println("Lado B = "+resLadoB);
            System.out.println("Angulo Beta = "+resBeta);
            System.out.println("Angulo Gamma = "+resGamma);
        }
        else {
            if(trampas[0] == 1) {
                respuestas[0] = resLadoB;
                respuestas[1] = resBeta;
                respuestas[2] = resGamma;
                resultado[0] = false;
                error[0] = ladoB;
                error[1] = anguloBeta;
                error[2] = anguloGamma;
            }else {
                respuestas[3] = resLadoB;
                respuestas[4] = resBeta;
                respuestas[5] = resGamma;
                resultado[1] = false;
                error[3] = ladoB;
                error[4] = anguloBeta;
                error[5] = anguloGamma;
            }
            System.out.println("Fallaste, intentalo la proxima.");
        }
    }
    
    void trampaDos(){
        mostrarTrampa();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
               
        double ladoB = 10, ladoA = 0.000, ladoC = 25;
        double anguloGamma = 0.000, anguloAlfa = 0.000, anguloBeta = 30;
        
        double resLadoA = descubrirLado(ladoB, ladoC, anguloBeta);
        double resAlfa = descubrirAngulo1(ladoB, ladoC, resLadoA);
        
        resLadoA = redondear3decimales(resLadoA);
        resAlfa = redondear3decimales(resAlfa);
        
        double resGamma = redondear3decimales(descubrirAngulo2(anguloBeta, resAlfa));
        
        System.out.println("Encontrar el lado A y los angulos Alfa y Gamma; aproxime a 3 decimales.");
        System.out.println("Datos:");
        System.out.println("Lado B = 10");
        System.out.println("Lado C = 25");
        System.out.println("angulo beta = 30");        
        System.out.println("");                
        
        try{
            System.out.println("Ingrese el lado A");
            ladoA = Double.parseDouble(br.readLine());
            System.out.println("Ingrese el angulo Gamma");
            anguloGamma = Double.parseDouble(br.readLine());
            System.out.println("Ingrese el angulo Alfa");
            anguloAlfa = Double.parseDouble(br.readLine());
        }catch (IOException | NumberFormatException ex){
            System.out.println("Opcion invalida");
            trampaUno();
        }

        if(trampas[0] == 2){
            datos[0] = ladoB;
            datos[1] = ladoC;
            datos[2] = anguloBeta;
        }else{
            datos[3] = ladoB;
            datos[4] = ladoC;
            datos[5] = anguloBeta;
        }
        
        if(ladoA == resLadoA && anguloAlfa == resAlfa && anguloGamma == resGamma) {
            System.out.println("Correcto!!");
            System.out.println("Lado A = "+ resLadoA);
            System.out.println("Angulo Alfa = "+resAlfa);
            System.out.println("Angulo Gamma = "+resGamma);
        }
        else {
            if(trampas[0] == 2) {
                respuestas[0] = resLadoA;
                respuestas[1] = resAlfa;
                respuestas[2] = resGamma;
                resultado[0] = false;                
                error[0] = ladoA;
                error[1] = anguloAlfa;
                error[2] = anguloGamma;
            }else {
                respuestas[3] = resLadoA;
                respuestas[4] = resAlfa;
                respuestas[5] = resGamma;
                resultado[1] = false;
                error[3] = ladoA;
                error[4] = anguloAlfa;
                error[5] = anguloGamma;
            }
            System.out.println("Fallaste, intentalo la proxima.");
        }
    }
    
    void trampaTres(){
        mostrarTrampa();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        double ladoB = 25, ladoA = 18, ladoC = 0.000;
        double anguloGamma = 30, anguloAlfa = 0.000, anguloBeta = 0.000;
        
        double resLadoC = descubrirLado(ladoB, ladoA, anguloGamma);
        double resAlfa = descubrirAngulo1(ladoB, ladoA, resLadoC);
        
        resLadoC = redondear3decimales(resLadoC);
        resAlfa = redondear3decimales(resAlfa);
        
        double resBeta = redondear3decimales(descubrirAngulo2(anguloGamma, resAlfa));
        
        System.out.println("Encontrar el lado C y los angulos Beta y Alfa; aproxime a 2 decimales.");
        System.out.println("Datos:");
        System.out.println("Lado A = 18");
        System.out.println("Lado B = 25");
        System.out.println("angulo gamma = 30");        
        System.out.println("");                
        
        try{
            System.out.println("Ingrese el lado C");
            ladoC = Double.parseDouble(br.readLine());
            System.out.println("Ingrese el angulo Alfa");
            anguloAlfa = Double.parseDouble(br.readLine());
            System.out.println("Ingrese el angulo Beta");
            anguloBeta = Double.parseDouble(br.readLine());
        }catch (IOException | NumberFormatException ex){
            System.out.println("Opcion invalida");
            trampaTres();
        }

        if(trampas[0] == 3){
            datos[0] = ladoA;
            datos[1] = ladoB;
            datos[2] = anguloGamma;
        }else{
            datos[3] = ladoA;
            datos[4] = ladoB;
            datos[5] = anguloGamma;
        }
        
        if(ladoC == resLadoC && anguloBeta == resBeta && anguloAlfa == resAlfa) {
            System.out.println("Correcto!!");
            System.out.println("Lado C = "+resLadoC);
            System.out.println("Angulo Beta = "+resBeta);
            System.out.println("Angulo Alfa = "+resAlfa);
        }
        else {
            if(trampas[0] == 3) {
                respuestas[0] = resLadoC;
                respuestas[1] = resAlfa;
                respuestas[2] = resBeta;
                resultado[0] = false;
                error[0] = ladoC;
                error[1] = anguloAlfa;
                error[2] = anguloBeta;
            }else {
                respuestas[3] = resLadoC;
                respuestas[4] = resAlfa;
                respuestas[5] = resBeta;
                resultado[1] = false;
                error[3] = ladoC;
                error[4] = anguloAlfa;
                error[5] = anguloBeta;
            }
            System.out.println("Fallaste, intentalo la proxima.");
        }
    }
}
