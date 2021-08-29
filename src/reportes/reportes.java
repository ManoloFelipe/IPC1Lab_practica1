package reportes;

import java.io.*;
import java.util.Scanner;

import juego.Jugador;
import trampas.*;

/**
 *
 * @author Felipe
 */
public class reportes {
    
    public void generarReporte1(faciles tFaciles, medias tMedias, dificiles tDificiles, Jugador jugador){
        System.out.println("Ingrese la ruta donde desea guardar su reporte: ");
        String rutaReporte = new Scanner(System.in).nextLine();

        //Generación del reporte en java:
        FileWriter fichero = null;
        PrintWriter pw = null;

        int trampasFaciles = 0;
        int trampasMedias = 0;
        int trampasDificiles = 0;

        for (int i = 0; i < 2; i++) {
            if(tFaciles.trampas[i]!= 0 )trampasFaciles++;
            if(tMedias.trampas[i]!= 0 )trampasMedias++;
            if(tDificiles.trampas[i]!= 0 )trampasDificiles++;
        }

        String archivo = "<!DOCTYPE html>\n" +
        "<html lang=\"es\">\n" +
        "<head>\n" +
        "    <meta charset=\"UTF-8\">\n" +
        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\n" +
        "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js\"></script>\n" +
        "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\n" +
        "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\n" +
        "\n" +
        "    <title>Trampas</title>\n" +
        "</head>\n" +
        "<body class=\"bg-light\">\n" +
        "        <nav class=\"navbar navbar-dark justify-content-md-center bg-dark\" aria-label=\"\">\n" +
        "            <div class=\"container-xl justify-content-md-center\">\n" +
        "                <a class=\"navbar-brand\" href=\"#\">Trampas del juego</a>                \n" +
        "            </div>\n" +
        "        </nav>\n" +
        "\n" +
        "        <div class=\"mx-auto py-4\">\n" +
        "            <h2 class=\"text-center\">\n" +
        "                Trampas faciles: Ley de cosenos                \n" +
        "            </h2>\n" +
        "        </div>\n";

        //#region faciles
        if(trampasFaciles != 0){            
            archivo += "        <div class=\"album py-3 bg-light\">\n" +
            "            <div class=\"container\">\n" +
            "    \n" +
            "              <div class=\"row\">    \n";
            for (int i = 0; i < trampasFaciles; i++) {
                String respuesta = "";
                if(tFaciles.resultado[i]) respuesta = "Haz respondido este problema correctamente";
                else respuesta = "Haz respondido este problema incorrectamente";
                archivo +="                <div class=\"col-md\">\n" +
                "                    <div class=\"card mb-4 box-shadow\">\n" +
                "                      <img class=\"card-img-top mx-auto\" src=\"https://i.imgur.com/8cOAUmX.png\" data-holder-rendered=\"true\" style=\"height: 50%; width: 50%; display: inline;\">\n" +
                "                      <div class=\"card-body\">\n" +
                "                          <h5 class=\"card-title\">"+(i+1)+"° Problema</h5>\n" +
                "                        <p class=\"card-text\">"+respuesta+"</p>\n" +
                "                        <div class=\"d-flex justify-content-between align-items-center\">\n" +
                "                           <div class=\"btn-group\" role=\"group\" aria-label=\"Basic example\">" +
                "                            <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#modal2"+i+"\">\n" +
                "                                Mostrar pasos para resolver\n" +
                "                              </button>\n";
                if(!tFaciles.resultado[i]) archivo +="                            <button type=\"button\" class=\"btn btn-danger\" data-toggle=\"modal\" data-target=\"#modal3"+i+"\">\n" +
                "                                Mostrar Error\n" +
                "                              </button>\n";
                archivo +="                          </div>" +
                "<small class=\"text-muted\">problema "+tFaciles.trampas[i]+"</small>\n"+
                "                        </div>\n" +
                "                      </div>\n" +
                "                    </div>\n" +
                "                  </div>\n" ;
            }
            archivo+= "              </div>\n" +
"            </div>\n" +
"        </div>";
        }else{
            archivo+= "<div class=\"mx-auto py-4\">\n" +
            "          <h3 class=\"text-center\">\n" +
            "              ¡¡¡No haz pasado por trampas!!!\n" +
            "          </h3>\n" +
            "      </div>\n";
        }
        //#endregion

        archivo+="        <div class=\"mx-auto py-4\">\n" +
        "            <h2 class=\"text-center\">\n" +
        "                Trampas Medias: Suma de matrices                \n" +
        "            </h2>\n" +
        "        </div>\n";

        //#region medias
        if(trampasMedias != 0){            
            archivo += "        <div class=\"album py-3 bg-light\">\n" +
            "            <div class=\"container\">\n" +
            "    \n" +
            "              <div class=\"row\">    \n";
            for (int i = 0; i < trampasMedias; i++) {
                String respuesta = "";
                String matrizA = dibujarMatriz(tMedias.matrices[(i*2) + 0]);
                String matrizB = dibujarMatriz(tMedias.matrices[(i*2) + 1]);
                if(tMedias.resultados[i]) respuesta = "Haz respondido este problema correctamente";
                else respuesta = "Haz respondido este problema incorrectamente";
                archivo +="                <div class=\"col-md\">\n" +
                "                    <div class=\"card mb-4 box-shadow\">\n" +
                "                      <div class=\"card-img-top mx-auto text-center\" style=\"display: block;\">Matriz A:<br>"+matrizA+"<d><b>+</b></d><br>Matriz B:<br>"+matrizB+"</div>\n" +
                "                      <div class=\"card-body\">\n" +
                "                          <h5 class=\"card-title\">"+(i+1)+"° Problema</h5>\n" +
                "                        <p class=\"card-text\">"+respuesta+"</p>\n" +
                "                        <div class=\"d-flex justify-content-between align-items-center\">\n"+
                "                           <div class=\"btn-group\" role=\"group\" aria-label=\"Basic example\">" +
                "                            <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#modal2"+i+"\">\n" +
                "                                Mostrar pasos para resolver\n" +
                "                              </button>\n";
                if(!tMedias.resultados[i]) archivo +="                            <button type=\"button\" class=\"btn btn-danger\" data-toggle=\"modal\" data-target=\"#modal3"+i+"\">\n" +
                "                                Mostrar Error\n" +
                "                              </button>\n";
                archivo +="                          </div>"
                        + "<small class=\"text-muted\">problema "+tMedias.trampas[i]+"</small>\n"+
                "                        </div>\n" +
                "                      </div>\n" +
                "                    </div>\n" +
                "                  </div>\n" ;
            }
            archivo+= "              </div>\n" +
"            </div>\n" +
"        </div>";
        }else{
            archivo+= "<div class=\"mx-auto py-4\">\n" +
            "          <h3 class=\"text-center\">\n" +
            "              ¡¡¡No haz pasado por trampas!!!\n" +
            "          </h3>\n" +
            "      </div>\n";
        }
        //#endregion

        archivo +="        <div class=\"mx-auto py-4\">\n" +
        "            <h2 class=\"text-center\">\n" +
        "                Trampas Dificiles: Division de Matrices                \n" +
        "            </h2>\n" +
        "        </div>\n";

        //#region dificiles
        if(trampasDificiles != 0){            
            archivo += "        <div class=\"album py-3 bg-light\">\n" +
            "            <div class=\"container\">\n" +
            "    \n" +
            "              <div class=\"row\">    \n";
            for (int i = 0; i < trampasDificiles; i++) {
                String respuesta = "";
                String matrizA = dibujarMatriz(tDificiles.matrices[(i*2) + 0]);
                String matrizB = dibujarMatriz(tDificiles.matrices[(i*2) + 1]);
                if(tDificiles.resultados[i]) respuesta = "Haz respondido este problema correctamente";
                else respuesta = "Haz respondido este problema incorrectamente";
                archivo +="                <div class=\"col-md\">\n" +
                "                    <div class=\"card mb-4 box-shadow\">\n" +
                "                      <div class=\"card-img-top mx-auto text-center\" style=\"display: block;\">Matriz A:<br>"+matrizA+"<d><b>-dividido-</b></d><br>Matriz B:<br>"+matrizB+"</div>\n" +
                "                      <div class=\"card-body\">\n" +
                "                          <h5 class=\"card-title\">"+(i+1)+"° Problema</h5>\n" +
                "                        <p class=\"card-text\">"+respuesta+"</p>\n" +
                "                        <div class=\"d-flex justify-content-between align-items-center\">\n"+
                "                           <div class=\"btn-group\" role=\"group\" aria-label=\"Basic example\">" +
                "                            <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#modal4"+i+"\">\n" +
                "                                Mostrar pasos para resolver\n" +
                "                              </button>\n";
                if(!tDificiles.resultados[i]) archivo +="                            <button type=\"button\" class=\"btn btn-danger\" data-toggle=\"modal\" data-target=\"#modal5"+i+"\">\n" +
                "                                Mostrar Error\n" +
                "                              </button>\n";
                archivo +="                          </div>"
                        + "<small class=\"text-muted\">problema "+tDificiles.trampas[i]+"</small>\n"+
                "                        </div>\n" +
                "                      </div>\n" +
                "                    </div>\n" +
                "                  </div>\n" ;
            }
            archivo+= "              </div>\n" +
"            </div>\n" +
"        </div>";
        }else{
            archivo+= "<div class=\"mx-auto py-4\">\n" +
            "          <h3 class=\"text-center\">\n" +
            "              ¡¡¡No haz pasado por trampas!!!\n" +
            "          </h3>\n" +
            "      </div>\n";
        }
        //#endregion

        //#region Procedimientos y errores
        if(trampasFaciles != 0){
            for (int i = 0; i < trampasFaciles; i++) {
                archivo +="<div class=\"modal\" id=\"modal0"+i+"\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLong1\" aria-hidden=\"true\">\n" +
"            <div class=\"modal-dialog\" role=\"document\">\n" +
"              <div class=\"modal-content\">\n" +
"                <div class=\"modal-header\">\n" +
"                  <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Procedimiento</h5>\n" +
"                  <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n" +
"                    <span aria-hidden=\"true\">&times;</span>\n" +
"                  </button>\n" +
"                </div>\n" +
"                <div class=\"modal-body\">\n" +
"                 para resolver este problema primero buscamos el lado desconocido con la siguiente formula: \n"
                        + "<p>ladoDesc. = <span>&#8730;</span>("+tFaciles.datos[(i*3) + 0]+"^2 + "+tFaciles.datos[(i*3) + 1]+"^2 - 2("+tFaciles.datos[(i*3) + 0]+")("+tFaciles.datos[(i*3) + 1]+")cos("+tFaciles.datos[(i*3) + 2]+"))</p>" +
                        "<b>ladoDesc. = "+tFaciles.respuestas[(i*3)+0]+"</b><p></p>"
                        + "Ahora con este nuevo dato podemos encontrar el angulo menor con la siguiente formula:"
                        + "<p>AnguloMenor = cos^-1(("+tFaciles.datos[(i*3) + 0]+"-"+tFaciles.datos[(i*3) + 1]+"-"+tFaciles.respuestas[(i*3) + 0]+")/(-2("+tFaciles.datos[(i*3) + 1]+")("+tFaciles.respuestas[(i*3) + 0]+")))</p>"
                        + "<b>AnguloMenor = "+tFaciles.respuestas[(i*3) + 1]+"</b><p></p>"
                        + "Finalmente con la siguiente formula obtenemos el ultimo angulo"
                        + "<p>AnguloRestante = 180 -"+tFaciles.datos[(i*3) + 2]+"-"+tFaciles.respuestas[(i*3) + 1]+"</p>"
                        + "<b>AnguloRestante = "+tFaciles.respuestas[(i*3) + 2]+"</b><p></p>"+
"                </div>\n" +
"                <div class=\"modal-footer\">\n" +
"                  <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n" +
"                </div>\n" +
"              </div>\n" +
"            </div>\n" +
"          </div>";

                if(!tFaciles.resultado[i]){
                    archivo +="\n" +
                "\n" +
                "        <div class=\"modal\" id=\"modal1"+i+"\" tabindex=\"-1\" role=\"dialog\" aria-hidden=\"true\">\n" +
                "            <div class=\"modal-dialog\" role=\"document\">\n" +
                "              <div class=\"modal-content\">\n" +
                "                <div class=\"modal-header\">\n" +
                "                  <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Errores</h5>\n" +
                "                  <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n" +
                "                    <span aria-hidden=\"true\">&times;</span>\n" +
                "                  </button>\n" +
                "                </div>\n" +
                "                <div class=\"modal-body\">\n" +
                "                  El error se encuentra en la(s) siguiente(s) respuesta(s):<p></p>";

                for (int j = 0; j < 3; j++) {
                    if(tFaciles.respuestas[(i*3) + j] != tFaciles.error[(i*3) + j]) {
                        archivo += "<d>";
                            if(j == 0) archivo += "<b>Tu respuesta del lado desconocido ("+tFaciles.error[(i*3) + j]+") no es igual a la correcta ("+tFaciles.respuestas[(i*3) + j]+") </b>";
                            if(j == 1) archivo += "<b>Tu respuesta del angulo menor("+tFaciles.error[(i*3) + j]+") no es igual a la correcta ("+tFaciles.respuestas[(i*3) + j]+") </b>";
                            if(j == 2) archivo += "<b>Tu respuesta del angulo mayor("+tFaciles.error[(i*3) + j]+") no es igual a la correcta ("+tFaciles.respuestas[(i*3) + j]+") </b>";
                        archivo += "</d><p></p>";
                    }
                }

                    archivo +="                </div>\n" +
                "                <div class=\"modal-footer\">\n" +
                "                  <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          </div>\n" ;
                }
            }
        }
        
        if(trampasDificiles != 0){
            for (int i = 0; i < trampasDificiles; i++) {
                archivo +="<div class=\"modal\" id=\"modal4"+i+"\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLong1\" aria-hidden=\"true\">\n" +
"            <div class=\"modal-dialog\" role=\"document\">\n" +
"              <div class=\"modal-content\">\n" +
"                <div class=\"modal-header\">\n" +
"                  <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Procedimiento</h5>\n" +
"                  <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n" +
"                    <span aria-hidden=\"true\">&times;</span>\n" +
"                  </button>\n" +
"                </div>\n" +
"                <div class=\"modal-body\">\n" +
"                 para resolver la division de matrices, la matriz divisora tiene restricciones, ser cuadrada y tener determinante diferente de 0,<br> \n" +
"                 primero que nada, hacer la verificacion del determinante de la matriz, para ello, en este reporte, utilizamos el metodo por determinantes \n"+
"                 que nos indica eliminar una fila y columna, para obtener un menor complementario (determinante de matriz menor denominada m_ij obtenida de eliminar la fila i y columna j)\n" +
"                 multiplicado por -1^(suma de fila y columna) multiplicado por el punto donde la fila y columna eliminada convergen:<br>" +
                        "<p>Matriz B = "+ dibujarMatriz(tDificiles.matrices[(i*2) + 1]) +"</p>" 
                        + "<br><p>Para este ejemplo usaremos la fila 1, asi que primero tomaremos la posicion 1,1 de la sig. forma:</p><br>" 
                        + "<p>(posicion i,j) * (-1)^(i+j) * det(m_ij)</p>"
                        + "<p>"+tDificiles.matrices[(i*2)+1][0][0]+"*(-1)^(2)*det("+dibujarMatrizMenor(tDificiles.matrices[(i*2)+1],0)+")</p>"
                        + "<br>repetiremos esto hasta obtener una matriz facil de obtener determinante, un 2x2 o 1x1.<br>"
                        + "Se repite el procedimiento en cada uno de los elementos de la fila seleccionada, todos estos resultados se sumaran y obtendremos el determinante de la matriz<br>"
                        + "<b>"+tDificiles.determinantesRespuesta[i]+"</b><p></p>";
                    if(tDificiles.determinantesRespuesta[i] == 0){
                        archivo += "<p>En este caso, el determinante es 0, asi que concluimos que <b>es imposible realizar la Division<b></p>"; 
                    }else{
                        archivo += "<p>ahora sabemos que la division es posible, asi que avanzamos, ahora tenemos que obtener la inversa de la siguiente forma:</p>" 
                        + "Lo haremos por el metodo de Gauss-Jordan, que nos indica ingresar la matriz identidad al lado de la matriz base de esta manera:<br>"
                        + dibujarMatrizGaussJordan(tDificiles.matrices[(i*2)+1])
                        + "<br>ahora con esta matriz nueva obtenemos el primer pivote, que es dividir toda la fila 1 segun el primer elemento de la fila 1:";
                        if(tDificiles.matrices[(i*2)+1][0][0] == 0){
                            archivo += "<br>En este caso el primer digito es 0, por lo que cambiaremos el orden de filas para tener un numero diferente de 0, obteniendo"
                            + "<br><b>"+ tDificiles.matrices[(i*2)+1][1][0] +"</b> , y dividiremos toda la fila con este numero, obteniendo:<br>";
                            for (int k = 0; k < tDificiles.matrices[(i*2)+1][1].length; k++) {                                
                                archivo += tDificiles.matrices[(i*2)+1][1][k]/tDificiles.matrices[(i*2)+1][1][0] +",  ";
                                if(k == (tDificiles.matrices[(i*2)+1][1].length-1)) archivo += "0,  " +(1/tDificiles.matrices[(i*2)+1][1][0]) + ",  0,  0<br>";
                            }
                        }else{
                            archivo += "<br><b>"+ tDificiles.matrices[(i*2)+1][0][0] +"</b> sera nuestro pivote, y dividiremos toda la fila con este numero<br>";
                            for (int k = 0; k < tDificiles.matrices[(i*2)+1][0].length; k++) {                                
                                archivo += tDificiles.matrices[(i*2)+1][0][k]/tDificiles.matrices[(i*2)+1][0][0] +",  ";
                                if(k == (tDificiles.matrices[(i*2)+1][1].length-1)) archivo += (1/tDificiles.matrices[(i*2)+1][0][0]) + ",  0,  0,  0<br>";
                            }
                        }
                        archivo += "<p>Ahora hacemos una multiplicacion por el numero de la misma columna en las demas filas y el resultado lo restamos a dicha fila, ejemplo:</p>";
                        if(tDificiles.matrices[(i*2)+1][0][0] == 0){
                            archivo += "En este caso cambiamos el orden de filas, y el primer numero de la fila cambiada es 0, por lo que la fila no tendra cambios";
                        }else{
                            archivo += "<b>"+ (tDificiles.matrices[(i*2)+1][1][0]) +"</b> sera nuestro valor, ahora multiplicaremos en la fila 1 este valor, y dividiremos toda la fila con este numero<br>";
                            for (int k = 0; k < tDificiles.matrices[(i*2)+1][1].length; k++) {                                
                                archivo += tDificiles.matrices[(i*2)+1][1][k]/tDificiles.matrices[(i*2)+1][1][0] +",  ";
                                if(k == (tDificiles.matrices[(i*2)+1][1].length-1)) archivo += ("0,  ")+ (1/tDificiles.matrices[(i*2)+1][0][0]) + ",  0,  0<br>";
                            }
                        }
                        archivo += "<p>Realizamos este proceso de pivote en diferentes columnas y filas B_nn, y luego de division en hasta obtener la matriz identidad del lado contrario donde empezo:</p>"+
                        dibujarMatrizGaussJordanInversa(tDificiles.matricesGaussJordan[i])+"<br>" +
                        "quitando la matriz identidad obtenemos la inversa del divisor; ahora operamos como una multiplicacion, la matriz resultado de esta multiplicacion tendra la cantidad de filas del multiplicando y"+
                        " la cantidad de columnas del multiplicador; el elemento R_ij se obtiene multiplicando el elemento A_ij por cada columna de B y sumando los resultados, ejemplo:<br><br>" +
                        "Matriz A, posicion 1,1: "+tDificiles.matrices[(i*2)+0][0][0]+"<br>"+
                        "Elementos de la columna 1 de B^-1:"+(tDificiles.matrices[(i*2)+1][0][1])+",  "+(tDificiles.matrices[(i*2)+1][1][1])+",  "+(tDificiles.matrices[(i*2)+1][2][1])+",  "+(tDificiles.matrices[(i*2)+1][3][1])+"<br><br>" +
                        "Se mutiplica A_11 * los elementos de la columna B_1 y se suman los resultados para obtener la posicion R_11:<br><br>"+
                        "R_11 = "+(tDificiles.matrices[(i*2)+1][0][1]*tDificiles.matrices[(i*2)+0][0][0])+"+"+(tDificiles.matrices[(i*2)+1][1][1]*tDificiles.matrices[(i*2)+0][0][0])+"+"+(tDificiles.matrices[(i*2)+1][2][1]*tDificiles.matrices[(i*2)+0][0][0])+"+"+(tDificiles.matrices[(i*2)+1][3][1]*tDificiles.matrices[(i*2)+0][0][0]) + "<br>" +
                        "<b>R_11 = "+tDificiles.matricesRespuesta[i][0][0]+"</b><br>"+
                        "Repetimos para cada posicion de R y obtenemos la matriz final:<br><br>"+
                        "<b> Matriz R:<br>"+
                        dibujarMatriz(tDificiles.matricesRespuesta[i])+
                        "</b>";
                    }
                    archivo +="                </div>\n" +
"                <div class=\"modal-footer\">\n" +
"                  <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n" +
"                </div>\n" +
"              </div>\n" +
"            </div>\n" +
"          </div>";

                if(!tDificiles.resultados[i]){
                    archivo +="\n" +
                "\n" +
                "        <div class=\"modal\" id=\"modal5"+i+"\" tabindex=\"-1\" role=\"dialog\" aria-hidden=\"true\">\n" +
                "            <div class=\"modal-dialog\" role=\"document\">\n" +
                "              <div class=\"modal-content\">\n" +
                "                <div class=\"modal-header\">\n" +
                "                  <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Errores</h5>\n" +
                "                  <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n" +
                "                    <span aria-hidden=\"true\">&times;</span>\n" +
                "                  </button>\n" +
                "                </div>\n";
                    if(tDificiles.determinantesRespuesta[i] == tDificiles.errorTipo1[i]){
                    archivo +="                <div class=\"modal-body\">\n" +
                    "                  El error se encuentra en la(s) siguiente(s) posicion(es):<p></p>"+
                    "                   <d>"+dibujarMatrizErrores(tDificiles.matricesRespuesta[i], tDificiles.errorTipo2[i])+"</d>"+
                    "                  Matriz Ingresada:<p></p>"+
                    "                   <d>"+dibujarMatriz(tDificiles.errorTipo2[i])+"</d>"+
                    "                  Matriz Correcta:<p></p>"+
                    "                   <d>"+dibujarMatriz(tDificiles.matricesRespuesta[i])+"</d>"+
                    "                </div>\n" ;
                    }else{
                    archivo += "                <div class=\"modal-body\">\n" +
                    "                  El error se encuentra en el determinante:<p></p>"+
                    "                  Determinante Ingresada:<p></p>"+
                    "                   <d>"+tDificiles.errorTipo1[i]+"</d>"+
                    "                  Determinante Correcta:<p></p>"+
                    "                   <d>"+tDificiles.determinantesRespuesta[i]+"</d>"+
                    "                </div>\n" ;
                    }
                archivo += "                <div class=\"modal-footer\">\n" +
                "                  <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          </div>\n" ;
                }
            }
        }

        if(trampasMedias != 0){
            for (int i = 0; i < trampasMedias; i++) {
                archivo +="<div class=\"modal\" id=\"modal2"+i+"\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLong1\" aria-hidden=\"true\">\n" +
"            <div class=\"modal-dialog\" role=\"document\">\n" +
"              <div class=\"modal-content\">\n" +
"                <div class=\"modal-header\">\n" +
"                  <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Procedimiento</h5>\n" +
"                  <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n" +
"                    <span aria-hidden=\"true\">&times;</span>\n" +
"                  </button>\n" +
"                </div>\n" +
"                <div class=\"modal-body\">\n" +
"                 para resolver la suma de matrices, ambas matrices deben de ser del mismo tamaño nxm, con eso verificado, se suman los valores de la matriz A y la matriz B, ambas en la posicion ij, y la respuesta tomara esa posicion: \n"
                        + "<p>Matriz A, posicion (1,1) = "+ tMedias.matrices[(i*2) + 0][0][0] +"</p>" 
                        + "<p>Matriz B, posicion (1,1) = "+ tMedias.matrices[(i*2) + 1][0][0] +"</p>" 
                        + "<b>Matriz Respuesta, posicion (1,1) = "+tMedias.respuestas[i][0][0]+"</b><p></p>"
                        + "Se sigue este procedimiento hasta obtener la matriz final:<br>"
                        + "<b>Matriz Respuesta = <br>"+dibujarMatriz(tMedias.respuestas[i])+"</b><p></p>"+
"                </div>\n" +
"                <div class=\"modal-footer\">\n" +
"                  <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n" +
"                </div>\n" +
"              </div>\n" +
"            </div>\n" +
"          </div>";

                if(!tMedias.resultados[i]){
                    archivo +="\n" +
                "\n" +
                "        <div class=\"modal\" id=\"modal3"+i+"\" tabindex=\"-1\" role=\"dialog\" aria-hidden=\"true\">\n" +
                "            <div class=\"modal-dialog\" role=\"document\">\n" +
                "              <div class=\"modal-content\">\n" +
                "                <div class=\"modal-header\">\n" +
                "                  <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Errores</h5>\n" +
                "                  <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n" +
                "                    <span aria-hidden=\"true\">&times;</span>\n" +
                "                  </button>\n" +
                "                </div>\n" +
                "                <div class=\"modal-body\">\n" +
                "                  El error se encuentra en la(s) siguiente(s) posicion(es):<p></p>"+
                "                   <d>"+dibujarMatrizErrores(tMedias.respuestas[i], tMedias.error[i])+"</d>"+
                "                  Matriz Ingresada:<p></p>"+
                "                   <d>"+dibujarMatriz(tMedias.error[i])+"</d>"+
                "                  Matriz Correcta:<p></p>"+
                "                   <d>"+dibujarMatriz(tMedias.respuestas[i])+"</d>"+
                "                </div>\n" +
                "                <div class=\"modal-footer\">\n" +
                "                  <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          </div>\n" ;
                }
            }
        }

        //#endregion
        
        archivo +="\n" +
        "</body>\n" +
        "</html>";

        try
        {
            fichero = new FileWriter(rutaReporte+"\\trampas.html");
            pw = new PrintWriter(fichero);

            for (int i = 0; i <= 0; i++)
                pw.println(archivo);


            System.out.println("Reporte generado.");
            System.out.println("Regresando al menu");
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void generarReporte2(faciles tFaciles, medias tMedias, dificiles tDificiles, Jugador jugador){
        System.out.println("Ingrese la ruta donde desea guardar su reporte: ");
        String rutaReporte = new Scanner(System.in).nextLine();
        
        //Generación del reporte en java:
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        int trampasFaciles = 0;
        int trampasMedias = 0;
        int trampasDificiles = 0;
        for (int i = 0; i < 2; i++) {
            if(tFaciles.trampas[i]!= 0 )trampasFaciles++;
            if(tMedias.trampas[i]!= 0 )trampasMedias++;
        }
        System.out.println("Aun en desarrollo ... No terminado");
    }

    private String dibujarMatriz(int[][] matriz){
        String retorno = "<pre>";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if(j == 0) retorno += ("| ");
                retorno += String.format("%5d", matriz[i][j]);
                if(j == matriz[i].length - 1) retorno +=(" |\n");
            }
        }
        retorno += "</pre>";
        return retorno;
    }

    private String dibujarMatriz(double[][] matriz){
        String retorno = "<pre>";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if(j == 0) retorno += ("| ");
                retorno += String.format("%9.3f", matriz[i][j]);
                if(j == matriz[i].length - 1) retorno +=(" |\n");
            }
        }
        retorno += "</pre>";
        return retorno;
    }

    private String dibujarMatrizGaussJordan(double[][] matriz){
        String retorno = "<pre>";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < (matriz[i].length*2); j++) {
                if(j == 0) retorno += ("| ");
                if(j < matriz[i].length)retorno += String.format("%9.3f", matriz[i][j]);
                else if( i == (j-matriz[i].length)) retorno += String.format("%9.3f", 1.000);
                else retorno += String.format("%9.3f", 0.000);
                if(j == (matriz[i].length*2) - 1) retorno +=(" |\n");
            }
        }
        retorno += "</pre>";
        return retorno;
    }

    private String dibujarMatrizGaussJordanInversa(double[][] matriz){
        String retorno = "<pre>";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < (matriz[i].length*2); j++) {
                if(j == 0) retorno += ("| ");
                if(j >= matriz[i].length)retorno += String.format("%9.3f", matriz[i][j-matriz[i].length]);
                else if( i == j) retorno += String.format("%9.3f", 1.000);
                else retorno += String.format("%9.3f", 0.000);
                if(j == (matriz[i].length*2) - 1) retorno +=(" |\n");
            }
        }
        retorno += "</pre>";
        return retorno;
    }

    private String dibujarMatrizMenor(double[][] matriz, int filaColumEliminada){
        String retorno = "<pre>";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if(i != filaColumEliminada && j == 0) retorno += ("| ");
                if( i != filaColumEliminada && j != filaColumEliminada){                    
                    retorno += String.format("%9.3f", matriz[i][j]);
                }
                if(i != filaColumEliminada && j == matriz[i].length - 1) retorno +=(" |\n");
            }
        }
        retorno += "</pre>";
        return retorno;
    }

    private String dibujarMatrizErrores(int[][] matriz, int[][] matrizComparar){
        String retorno = "<pre>";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if(j == 0) retorno += ("| ");
                if(matriz[i][j] == matrizComparar[i][j])retorno += "  bien";
                else retorno += " error";
                if(j == matriz[i].length - 1) retorno +=(" |\n");
            }
        }
        retorno += "</pre>";
        return retorno;
    }

    private String dibujarMatrizErrores(double[][] matriz, double[][] matrizComparar){
        String retorno = "<pre>";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if(j == 0) retorno += ("| ");
                if(matriz[i][j] == matrizComparar[i][j])retorno += "  bien";
                else retorno += " error";
                if(j == matriz[i].length - 1) retorno +=(" |\n");
            }
        }
        retorno += "</pre>";
        return retorno;
    }
}
