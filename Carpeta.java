import java.util.ArrayList;
import java.util.List;

public class Carpeta implements Elemento {
        String nombre;
        List<Elemento> elementos = new ArrayList<>();

        Carpeta(String nombre) {
            this.nombre = nombre;
        }

        //se añade para composite
        public void agregar(Elemento elemento){
            elementos.add(elemento);
        }

        @Override
        public int obtenerTamanio(){
            int total = 0;
            for(Elemento elemento : elementos){
                total += elemento.obtenerTamanio();
            }
            return total;
        }

    }