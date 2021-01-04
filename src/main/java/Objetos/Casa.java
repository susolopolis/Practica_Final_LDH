package Objetos;

import java.util.ArrayList;
import java.util.List;

public class Casa {

    public int ancho;
    /**
     * < Un entero. Las dimensiones del ancho de la casa.
     */
    public int largo;
    /**
     * < Un entero. Las dimensiones del largo de la casa.
     */

    private final ArrayList<Objeto_Casa> objetos; /**< Una lista de objetos de la casa. Una lista con los objetos contenidos en el hogar. */

    /**
     * Constructor de la clase de la casa.
     * <p>
     * En este constructor se inicializa el objeto Casa pasándole como parámetro las dimensiones de ancho x largo
     * que se desean para el hogar. Además, se inicializa la lista de objetos declarada anteriormente
     * dentro de los atributos de la clase.
     *
     * @param ancho Un entero. Valor que tomará las dimensiones para el ancho del nuevo objeto de la casa.
     * @param largo Un entero. Valor que tomará las dimensiones para el largo del nuevo objeto de la casa.
     */
    public Casa(int ancho, int largo) {
        this.objetos = new ArrayList<Objeto_Casa>();
        this.ancho = ancho;
        this.largo = largo;
    }

    /**
     * Getter del ancho de la casa.
     * <p>
     * Este método devuelve el valor que tiene el atributo 'ancho' del objeto Casa.
     *
     * @return El ancho de la casa.
     */
    public int getAncho() {
        return this.ancho;
    }

    /**
     * Getter del largo de la casa.
     * <p>
     * Este método devuelve el valor que tiene el atributo 'largo' del objeto Casa.
     *
     * @return El largo de la casa.
     */
    public int getLargo() {
        return this.largo;
    }

    /**
     * Método que permite agregar objetos a la casa.
     * <p>
     * En este método se incorpora el objeto deseado al objeto de la casa sobre el que se está trabajando.
     * Para ello se añade el objeto en cuestión a la lista, pero previamente se recalcula la simulación del
     * movimiento para ese objeto.
     *
     * @param objeto El objeto que se desea añadir a la lista de objetos en el hogar.
     */
    public void agregar_objeto(Objeto_Casa objeto) {
        objeto.calculate_next_pos(ancho, largo);
        objetos.add(objeto);
    }

    /**
     * Método que permite eliminar objetos de la casa.
     * <p>
     * En este método se implementa la operación para poder eliminar el objeto deseado de la lista actual de objetos en el hogar.
     * Para acceder al objeto en cuestión y eliminarlo se hace una comparación por ID, ya que ésta ha de ser única para
     * cada objeto. Una vez realizada la operación se informa del resultado a través de un booleano.
     *
     * @param nuevo_valor_objeto El objeto que se desea eliminar de la lista de objetos en el hogar.
     * @return Un estado booleano informando de la satisfactoriedad de la operación.
     */
    public boolean eliminar_objeto(Objeto_Casa nuevo_valor_objeto) {
        for (int i = 0; i < objetos.size(); i++) {
            if (objetos.get(i).getID().equals(nuevo_valor_objeto.getID())) {
                objetos.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Método que permite eliminar una lista de objetos de la casa.
     *
     * En este método se implementa la operación para poder eliminar una lista de objetos deseada de la lista de objetos
     * en el hogar hasta el momento. Para ello se recorre secuencialmente la lista como parámetro y la lista de la clase
     * de tal forma que se comparen los objetos por ID para poder proceder al borrado de estos. Una vez realizada la operación
     * se informa del resultado a través de un valor booleano.
     * @param listaObjetosAEliminar La lista de objetos que se desea eliminar de la lista de objetos en el hogar.
     * @return Un estado booleano informando de la satisfactoriedad de la operación.
     */
    public boolean eliminar_objetos(List<Objeto_Casa> listaObjetosAEliminar){
        for(Objeto_Casa objetoAEliminar : listaObjetosAEliminar){
            for(Objeto_Casa objetoABuscar : objetos){
                if(objetoABuscar.getID().equals(objetoAEliminar.getID())){
                    objetos.remove(objetoABuscar);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Método que permite actualizar un objeto en el hogar.
     *
     * En este método se permite actualizar los datos de un objeto, de tal modo que usando un objeto de entrada se
     * transfieran todos los valores de sus propiedades al objeto con un mismo ID presente en la lista de objetos
     * en el hogar. Una vez realizada la operación se informa del resultado a través de un valor booleano.
     * @param nuevo_valor_objeto El objeto con la información que se desea actualizar.
     * @return Un estado booleano informando de la satisfactoriedad de la operación.
     */
    public boolean actualizar_objeto(Objeto_Casa nuevo_valor_objeto){
        for (Objeto_Casa objeto : objetos) {
            if (objeto.getID().equals(nuevo_valor_objeto.getID())) {
                objeto.setObjeto(nuevo_valor_objeto);
                return true;
            }
        }
        return false;
    }

    /**
     * Método que permite mostrar un objeto del hogar.
     *
     * En este método se devuelve un vector de cadenas con la información correspondiente a cada objeto presente
     * en la lista actual de objetos en el hogar. Para ello se indica el índice del objeto que se desea mostrar
     * y posteriormente se simula el desplazamiento de posición y se devuelve la información para ese objeto.
     * @param i Un entero. El índice del objeto en la lista de objetos en el hogar sobre el que se desea conocer su información.
     * @return Un vector de String's con la información sobre el elemento deseado.
     */
    public String[] mostrar_objeto(int i){
        for (Objeto_Casa objeto : objetos){
            objeto.calculate_next_pos(ancho, largo);
        }
        return objetos.get(i).getInfo();
    }
}
