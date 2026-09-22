# ETAPA 1
* El método agregarArchivo se encarga de:
    Lo que hace el metodo agregarArchivo es que recibe una carpeta, recibe el tipo de archivo, el nombre del archivo y su tamaño. Si el tipo de documento es .pdf, entonces se añade como justo, un tipo de archivo pdf en la carpeta que se indicó en la entrada. Si es de tipo .txt entonces se añade como un tipo de archivo .txt en la carpeta que se indicó en la entrada
    El método no devuelve nada.

* El método obtenerTamanio se encarga de:
    Lo que hacer el metodo de obtenerTamanio es que recibe una carpeta y declara una variable que se llama total y la inicializa en 0. A partir de un for each, recorre todos los archivos de la carpeta, accede al tamaño de cada archivo y lo suma en la variable total pero, tambien la carpeta puede tener subcarpetas, y es necesario obtener tambien el tamaño de esas subcarpetas para sumarlas al total y eso es lo que hace el segundo for each, recorre las subcarpetas que tiene, obtiene su tamaño y lo suma a la variable total. Devuelve el 

* El método enviarResultado se encarga de:
    Recibe una carpeta y una cadena llamada destino, envia un correo electronico al destino, que ahora entendemos que debe de ser un correo electronico. En dicho correo electronico se envia el tamaño total de la carpeta que se indicó. No devuelve nada.     

