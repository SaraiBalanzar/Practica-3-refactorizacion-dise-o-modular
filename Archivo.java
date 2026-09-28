

public abstract class Archivo implements Elemento {
        String nombre;
        int tamanio;

        Archivo(String nombre, int tamanio) {
            this.nombre = nombre;
            this.tamanio = tamanio;
        }

        public int obtenerTamanio() {
            return this.tamanio;
        }
    }
