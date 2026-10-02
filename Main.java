
// CODIGO MODIFICADO CON COMPOSITE DE LA PRACTICA 3.
// No esta refactorizado: el objetivo es que el equipo detecte y mejore su diseno.
    class CorreoLegacy {
        void send_email(String to, String body) {
            System.out.println("Para: " + to);
            System.out.println(body);
        }
    }

    public class Main {

        static void enviarResultado(Carpeta carpeta, String destino) {
            CorreoLegacy correo = new CorreoLegacy();
            correo.send_email(destino,
                    "Tamanio total: " + carpeta.obtenerTamanio());
        }

        //método para comprobar si el resultado es el correcto
        static void comprobar(String nombre, int esperado, int obtenido) {
            if (esperado == obtenido) {
                System.out.println("OK: " + nombre);
            } else {
                System.out.println("FALLO: " + nombre +
                " | esperado=" + esperado +
                " | obtenido=" + obtenido);
            }
        }

        public static void main(String[] args) {
            //Factory Method + Composite

            // Se instancia los creadores de archivos
            CreadorArchivo creadorPDF = new CreadorPDF();
            CreadorArchivo creadorTexto = new CreadorTexto();
            
            //Creamos las carpetas y agregamos los archivos para las pruebas
            Carpeta clase = new Carpeta("MyP");
            clase.agregar(creadorPDF.crearArchivo("practica.pdf", 120));
            clase.agregar(creadorTexto.crearArchivo("notas.txt", 80));

            Carpeta ejemplos = new Carpeta("Ejemplos");
            Archivo ejemplo = creadorTexto.crearArchivo("ejemplo.txt", 50);
            ejemplos.agregar(ejemplo);

            clase.agregar(ejemplos);

            // imprimimos el tamanio y la simulacion del correo
            System.out.println(clase.obtenerTamanio());
            enviarResultado(clase, "profesor@universidad.edu");

            //Pruebas
            System.out.println("\n***** Ejecutando las pruebas *****");

            // primero caso: carpeta vacía
            Carpeta vacia = new Carpeta("Vacia");
            comprobar("Carpeta vacia", 0, vacia.obtenerTamanio());

            // segundo caso: carpeta con un pdf de tamanio 120
            Carpeta arch1 = new Carpeta("Carpeta 120");
            arch1.agregar(creadorPDF.crearArchivo("instrucciones.pdf", 120));
            comprobar("Carpeta con PDF de 120", 120, arch1.obtenerTamanio());

            // tercer caso: carpeta con pdf de 120 y texto de 80
            Carpeta arch2 = new Carpeta("Carpeta 200");
            arch2.agregar(creadorPDF.crearArchivo("practica01.pdf", 120));
            arch2.agregar(creadorTexto.crearArchivo("claves.txt", 80));
            comprobar("Carpeta con PDF 120 y TXT 80", 200, arch2.obtenerTamanio());

            // cuarto caso: ejemplo completo con subcarpeta de 50
            Carpeta padre = new Carpeta("Carpeta padre");
            padre.agregar(creadorPDF.crearArchivo("practica.pdf", 120));
            padre.agregar(creadorTexto.crearArchivo("notas.txt", 80));

            Carpeta hija = new Carpeta("Carpeta hija");
            hija.agregar(creadorTexto.crearArchivo("ejemplo.txt", 50));
            padre.agregar(hija);

            comprobar("Ejemplo completo con subcarpeta (250)", 250, padre.obtenerTamanio());

            // quinto caso: Carpeta con un archivo de tamanio o
            Carpeta arch0 = new Carpeta("Carpeta cero");
            arch0.agregar(creadorTexto.crearArchivo("vacio.txt", 0));
            comprobar("Carpeta co archivo de 0 bytes", 0, arch0.obtenerTamanio());

        }
    }