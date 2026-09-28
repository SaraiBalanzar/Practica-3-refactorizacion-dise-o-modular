
// CODIGO MODIFICADO CON COMPOSITE DE LA PRACTICA 3.
// No esta refactorizado: el objetivo es que el equipo detecte y mejore su diseno.
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
                carpeta.elementos.add(new ArchivoPDF(nombre, tamanio));
            } else if (tipo.equals("txt")) {
                carpeta.elementos.add(new ArchivoTexto(nombre, tamanio));
            }
        }

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

        //metodo agregado para poder hacer las pruebas
        static void agregarSubcarpeta(Carpeta padre, Carpeta hija){
            padre.agregar(hija);
        }

        public static void main(String[] args) {
            Carpeta clase = new Carpeta("MyP");
            agregarArchivo(clase, "pdf", "practica.pdf", 120);
            agregarArchivo(clase, "txt", "notas.txt", 80);

            Carpeta ejemplos = new Carpeta("Ejemplos");
            agregarArchivo(ejemplos, "txt", "ejemplo.txt", 50);
            clase.agregar(ejemplos);

            System.out.println(clase.obtenerTamanio());
            enviarResultado(clase, "profesor@universidad.edu");

            //veamos si el método de comprobar hace lo que debería de hacer con una carpeta vacía.
            System.out.println("Pruebas:");
            Carpeta vacia = new Carpeta("Vacia");
            int total = vacia.obtenerTamanio(); 
            comprobar("Carpeta vacia", 0, total); 

            //veamos ahora los otros 4 casos que aparecen en la guía de la práctica:

            //carpeta con un pdf de 120
            System.out.println("-Carpeta con un archivo");
            Carpeta arch = new Carpeta("Carpeta 120");
            agregarArchivo(arch, "pdf", "instrucciones.pdf", 120);
            int total2 = arch.obtenerTamanio();
            comprobar("Carpeta 120", 120, total2); 

            //carpeta con pdf de 120 y texto de 80
            System.out.println("-Carpeta con 2 archivos");
            Carpeta arch2 = new Carpeta("Carpeta 200");
            agregarArchivo(arch2, "pdf", "practica01.pdf", 100);
            agregarArchivo(arch2, "txt", "claves.txt", 100);
            int total3 = arch2.obtenerTamanio(); 
            comprobar("Carpeta 200", 200, total3); 

            //ejemplo completo con subcarpeta de 50
            System.out.println("-Carpeta con subcarpeta y archivos en cada una");
            Carpeta sub = new Carpeta("Carpeta hija");
            agregarArchivo(sub, "pdf", "notas.pdf", 100);
            agregarArchivo(sub, "txt", "tokens.txt", 100);
            Carpeta padre = new Carpeta("Carpeta padre");
            agregarArchivo(sub, "pdf", "presentacion.pdf", 50);
            agregarSubcarpeta(padre, sub);
            int total4 = padre.obtenerTamanio();
            comprobar("Carpeta 250", 250, total4);
        }
    }