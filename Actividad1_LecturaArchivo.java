import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Actividad1_LecturaArchivo {

    public static void main(String[] args) {
         leerArchivoDeTexto();
    }


    public static void leerArchivoDeTexto() {
        try  {
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduzca la ubicación del archivo");
            Scanner fc = new Scanner(new File(sc.nextLine()));
            String contenido = "";

            while (fc.hasNextLine()) {
                contenido += fc.nextLine() + ' ';
            }
            
            dividirArchivoDeTexto(contenido);
            sc.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado, revisa la ubicación");
            leerArchivoDeTexto();
        } 
    }


    public static void dividirArchivoDeTexto(String contenido) {
        int apertura = contarCaracter(contenido, '{');
        int cierre = contarCaracter(contenido, '}');

        try {
            if ((apertura == 0 || cierre == 0) || apertura != cierre) {
                throw new RuntimeException("Formato de archivo invalido, intentalo de nuevo");
            } else if (apertura == cierre) {
                for (int i = 0; i < apertura; i++) {
                    String usuario = contenido.substring(contenido.indexOf('{'), contenido.indexOf('}'));

                    System.out.println(usuario);
                    contenido = contenido.substring(contenido.indexOf('}') + 1, contenido.length());
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            leerArchivoDeTexto();
        }
    }


    public static int contarCaracter(String contenido, char caracter) {
        int contador = 0;

        for (int i = 0; i < contenido.length(); i++) {
            if (contenido.charAt(i) == caracter) {
                contador++;
            } 
        }
        return contador;
    }
}