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

        ////#region medias
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
                "                            <button type=\"button\" class=\"btn btn-danger\" data-toggle=\"modal\" data-target=\"#modal2"+i+"\">\n" +
                "                                Mostrar pasos para resolver\n" +
                "                              </button>\n";
                if(!tMedias.resultados[i]) archivo +="                            <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#modal3"+i+"\">\n" +
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

        if(trampasDificiles != 0){            
            for (int i = 0; i < trampasDificiles; i++) {
                
            }
        }else{
            archivo+= "<div class=\"mx-auto py-4\">\n" +
            "          <h3 class=\"text-center\">\n" +
            "              ¡¡¡No haz pasado por trampas!!!\n" +
            "          </h3>\n" +
            "      </div>\n";
        }

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
                "                  <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Modal title</h5>\n" +
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
                "                  <h5 class=\"modal-title\" id=\"exampleModalLongTitle\">Modal title</h5>\n" +
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
}
