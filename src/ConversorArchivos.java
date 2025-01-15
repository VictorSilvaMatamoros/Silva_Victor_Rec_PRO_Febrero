import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConversorArchivos {

    // Aqui guardaremos la carpeta y el archivo seleccionados
    private static File carpetaSeleccionada;
    private static File archivoSeleccionado;

    private static List<Datos> datosList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    seleccionarCarpeta(scanner);
                    break;
                case 2:
                    leerFichero(scanner);
                    break;
                case 3:
                    convertirFichero(scanner);
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4); // Continuar hasta que se le des a salir

        scanner.close();
    }

    // Muestra las opciones del menú principal
    private static void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Seleccionar carpeta");
        System.out.println("2. Leer fichero");
        System.out.println("3. Conversión a");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // Método para seleccionar una carpeta y mostrar su contenido
    private static void seleccionarCarpeta(Scanner scanner) {
        System.out.print("Ingrese la ruta de la carpeta: "); // Solicitar la ruta de la carpeta
        String ruta = scanner.nextLine();
        File carpeta = new File(ruta); // Crear un objeto File con la ruta ingresada

        // Validar que la ruta ingresada sea una carpeta válida
        if (carpeta.exists() && carpeta.isDirectory()) {
            carpetaSeleccionada = carpeta; // Guardar la carpeta seleccionada
            System.out.println("Carpeta seleccionada: " + carpetaSeleccionada.getAbsolutePath());
            System.out.println("Contenido de la carpeta:");
            // Listar todos los archivos y carpetas que contiene la carpeta seleccionada
            for (File archivo : carpeta.listFiles()) {
                System.out.println(archivo.getName());
            }
        } else {
            System.out.println("La ruta no es válida o no es una carpeta.");
        }
    }

    // Método para leer un fichero seleccionado dentro de la carpeta
    private static void leerFichero(Scanner scanner) {
        if (carpetaSeleccionada == null) {
            System.out.println("Primero debe seleccionar una carpeta."); // Validamos que haya una carpeta seleccionada
            return;
        }

        System.out.print("Ingrese el nombre del fichero: "); // Se pide el nombre del archivo
        String nombreFichero = scanner.nextLine();
        archivoSeleccionado = new File(carpetaSeleccionada, nombreFichero); // Creamos el objeto File con la ruta completa

        // Validar que el archivo exista
        if (archivoSeleccionado.exists() && archivoSeleccionado.isFile()) {
            System.out.println("Fichero seleccionado: " + archivoSeleccionado.getName());

            datosList.clear(); // Limpiar la lista de datos antes de leer un nuevo archivo

            try (BufferedReader br = new BufferedReader(new FileReader(archivoSeleccionado))) {
                String linea;
                br.readLine();
                while ((linea = br.readLine()) != null) { // Leer cada línea del archivo
                    String[] datos = linea.split(","); // Dividir la línea por comas
                    if (datos.length == 2) { // Validar que tenga dos campos
                        datosList.add(new Datos(datos[0].trim(), datos[1].trim())); // Agregar los datos a la lista
                    }
                }
                System.out.println("Datos leídos correctamente:");
                for (Datos dato : datosList) {
                    System.out.println(dato); // Mostrar los datos leídos
                }
            } catch (IOException e) {
                System.out.println("Error al leer el fichero: " + e.getMessage());
            }
        } else {
            System.out.println("El fichero no existe o no es un archivo válido.");
        }
    }

    // Método para convertir el fichero a un formato seleccionado
    private static void convertirFichero(Scanner scanner) {
        if (archivoSeleccionado == null) {
            System.out.println("Primero debe seleccionar un fichero.");
            return;
        }

        System.out.println("Seleccione el formato de salida:");
        System.out.println("1. CSV");
        System.out.println("2. JSON");
        System.out.println("3. XML");
        System.out.print("Seleccione una opción: ");
        int formato = scanner.nextInt();
        scanner.nextLine();

        // Asignar la extensión del archivo según el formato seleccionado
        String extension = "";
        switch (formato) {
            case 1: extension = ".csv"; break;
            case 2: extension = ".json"; break;
            case 3: extension = ".xml"; break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        System.out.print("Ingrese el nombre del fichero de salida: "); // Pedir el nombre del archivo de salida
        String nombreSalida = scanner.nextLine() + extension;
        File ficheroSalida = new File(carpetaSeleccionada, nombreSalida); // Crear el archivo de salida

        try {
            switch (formato) {
                case 1: escribirCSV(ficheroSalida); break; // Convertir a CSV
                case 2: escribirJSON(ficheroSalida); break; // Convertir a JSON
                case 3: escribirXML(ficheroSalida); break; // Convertir a XML
            }
            System.out.println("Fichero convertido y guardado como: " + ficheroSalida.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al convertir el fichero: " + e.getMessage());
        }
    }

    // Escribir los datos en formato CSV
    private static void escribirCSV(File ficheroSalida) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ficheroSalida))) {
            writer.write("Campo1,Campo2\n"); // Escribir la cabecera
            for (Datos dato : datosList) {
                writer.write(dato.getCampo1() + "," + dato.getCampo2() + "\n"); // Escribir cada dato
            }
        }
    }

    // Escribir los datos en formato JSON
    private static void escribirJSON(File ficheroSalida) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ficheroSalida))) {
            writer.write("[\n"); // Abrir el array de JSON
            for (int i = 0; i < datosList.size(); i++) {
                Datos dato = datosList.get(i);
                writer.write("  {\n");
                writer.write("    \"campo1\": \"" + dato.getCampo1() + "\",\n");
                writer.write("    \"campo2\": \"" + dato.getCampo2() + "\"\n");
                writer.write("  }");
                if (i < datosList.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]\n"); // Cerrar el array de JSON
        }
    }

    // Escribir los datos en formato XML
    private static void escribirXML(File ficheroSalida) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ficheroSalida))) {
            writer.write("<DatosList>\n"); // Abrir el contenedor principal
            for (Datos dato : datosList) {
                writer.write("  <Datos>\n");
                writer.write("    <campo1>" + dato.getCampo1() + "</campo1>\n");
                writer.write("    <campo2>" + dato.getCampo2() + "</campo2>\n");
                writer.write("  </Datos>\n");
            }
            writer.write("</DatosList>\n"); // Cerrar el contenedor principal
        }
    }
}