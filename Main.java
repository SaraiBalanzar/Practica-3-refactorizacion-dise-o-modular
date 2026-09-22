import java.util.ArrayList;
import java.util.List;

// CODIGO INICIAL DE LA PRACTICA 3.
// No esta refactorizado: el objetivo es que el equipo detecte y mejore su diseno.
    abstract class Archivo {
        String nombre;
        int tamanio;

        Archivo(String nombre, int tamanio) {
            this.nombre = nombre;
            this.tamanio = tamanio;
        }
    }

    class ArchivoPDF extends Archivo {
        ArchivoPDF(String nombre, int tamanio) {
            super(nombre, tamanio);
        }
    }

    class ArchivoTexto extends Archivo {
        ArchivoTexto(String nombre, int tamanio) {
            super(nombre, tamanio);
        }
    }

    class Carpeta {
        String nombre;
        List<Archivo> archivos = new ArrayList<>();
        List<Carpeta> subcarpetas = new ArrayList<>();

        Carpeta(String nombre) {
            this.nombre = nombre;
        }
    }

    class CorreoLegacy {
        void send_email(String to, String body) {
            System.out.println("Para: " + to);
            System.out.println(body);
        }
    }

    public class Main {
        static void agregarArchivo(Carpeta carpeta,
                String tipo, String nombre, int tamanio) {
            if (tipo.equals("pdf")) {
                carpeta.archivos.add(new ArchivoPDF(nombre, tamanio));
            } else if (tipo.equals("txt")) {
                carpeta.archivos.add(new ArchivoTexto(nombre, tamanio));
            }
        }
        /* lo que hace el metodo agregarArchivo es que recibe una carpeta, recibe el tipo de archivo, el nombre del archivo y su tamaño
               Si el tipo de documento es .pdf, entonces se añade como justo, un tipo de archivo pdf en la carpeta que se indicó en la entrada
               Si es de tipo .txt entonces se añade como un tipo de archivo .txt en la carpeta que se indicó en la entrada
               El método no devuelve nada.*/

        static int obtenerTamanio(Carpeta carpeta) {
            int total = 0;
            for (Archivo archivo : carpeta.archivos) {
                total += archivo.tamanio;
            }
            for (Carpeta subcarpeta : carpeta.subcarpetas) {
                total += obtenerTamanio(subcarpeta);
            }
            return total;
        }

        /* lo que hacer el metodo de obtenerTamanio es que recibe una carpeta y declara una variable que se llama total y la inicializa en 0
           a partir de un for each, recorre todos los archivos de la carpeta, accede al tamaño de cada archivo y lo suma en la variable total
           pero, tambien la carpeta puede tener subcarpetas, y es necesario obtener tambien el tamaño de esas subcarpetas para sumarlas al total
           y eso es lo que hace el segundo for each, recorre las subcarpetas que tiene y obtiene su tamaño y lo suma a la variable total */

        static void enviarResultado(Carpeta carpeta, String destino) {
            CorreoLegacy correo = new CorreoLegacy();
            correo.send_email(destino,
                    "Tamanio total: " + obtenerTamanio(carpeta));
        }

        /* recibe una carpeta y una cadena llamada destino, envia un correo electronico al destino, que ahora entendemos  que debe de ser un 
           correo electronico. En dicho correo electronico se envia el tamaño total de la carpeta que se indicó. No devuelve nada. */

        public static void main(String[] args) {
            Carpeta clase = new Carpeta("MyP");
            agregarArchivo(clase, "pdf", "practica.pdf", 120);
            agregarArchivo(clase, "txt", "notas.txt", 80);

            Carpeta ejemplos = new Carpeta("Ejemplos");
            agregarArchivo(ejemplos, "txt", "ejemplo.txt", 50);
            clase.subcarpetas.add(ejemplos);

            System.out.println(obtenerTamanio(clase));
            enviarResultado(clase, "profesor@universidad.edu");
        }
    }