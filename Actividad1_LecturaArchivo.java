import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  

public class Actividad1_LecturaArchivo {

    public static void main(String[] args) {
         leerArchivoDeTexto();
    }


    public static void leerArchivoDeTexto() {
        /** 
        Guarda el contenido de un archivo de texto, en caso de una excepción, se llama de manera recursiva.
        
        @exception 
            FileNotFoundException: Cuando el archivo no se encuentra en la ruta específicada, la función se 
            invoca a si misma de manera recursiva. 
        */
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

    public static void leerArchivoDeExcel() {
        /** 
        Imprime el contenido de un archivo de Excel.
        */
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduzca la ubicación del archivo");
            FileInputStream fis=new FileInputStream(new File(sc.nextLine()));  
            HSSFWorkbook wb = new HSSFWorkbook(fis);   
            HSSFSheet sheet=wb.getSheetAt(0);  

            for (Row row: sheet) {  
                for (Cell cell: row) {
                    System.out.println(cell.getStringCellValue());
                }
            }

            sc.close();
            wb.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado, revisa la ubicación");
            leerArchivoDeExcel();
        } catch (IOException e) {
            System.out.println("Archivo no encontrado, revisa la ubicación");
            leerArchivoDeExcel();
        }
    }

    public static void dividirArchivoDeTexto(String contenido) {
        /** 
        Examina un texto en busca de la información completa de un usuario y la muestra en pantalla.

        Dada una cadena de texto, busca cada campo de información relacionado a un usuario y la muestra en
        pantalla.

        @param
            String contenido: Es una cadena de texto que contiene todo el contenido de un archivo.txt

        
        @exception 
            RuntimeException: Cuando se genere una anomalia respecto a los caracteres de apertura y cierre,
            se tendrá que proveer otro archivo para su procesamiento.
        */
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
        /** 
        Examina un texto en busca de la cantidad de caracteres de apertura y cierre.

        Dada una cadena de texto, busca cada cada caracter de apertura y cierre a fin de ser información
        utiliaza para su posterior procesamiento.

        @param
            String contenido: Es una cadena de texto que contiene todo el contenido de un archivo.txt
            char caracter: Es el caracter qué se va a buscar en contenido.

        
        @return 
            int contador = Es el número de coincidencias qué el caracter aparece en el contenido.
        */
        int contador = 0;

        for (int i = 0; i < contenido.length(); i++) {
            if (contenido.charAt(i) == caracter) {
                contador++;
            } 
        }
        return contador;
    }
}