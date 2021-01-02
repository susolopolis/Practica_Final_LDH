package Objetos;

/*! Clase complementaria de la lógica que instancia un objeto para la casa */
public class Objeto_Casa{

    private final String ID; /**< Una cadena. El ID del objeto. */
    private String marca; /**< Una cadena. La marca del objeto. */
    private String modelo; /**< Una cadena. El modelo del objeto. */
    private String valor; /**< Una cadena. El valor del objeto. */
    private Ruta ruta; /**< Un objeto Ruta. La ruta del objeto. */

    /**
     * Constructor de la clase del objeto de la casa.
     *
     * En este constructor se inicializa el objeto de la Casa pasándole como parámetros el ID; la marca; el modelo;
     * su valor; así como la referencia sobre la casa sobre la que se quiere simular su ruta.
     * @param ID Una cadena. Valor que tomará el ID para el nuevo objeto del hogar.
     * @param marca Una cadena. Valor que tomará la marca para el nuevo objeto del hogar.
     * @param modelo Una cadena. Valor que tomará el modelo para el nuevo objeto del hogar.
     * @param valor Una cadena. Valor que tomará el valor para el nuevo objeto del hogar.
     * @param casa Un objeto Casa. Valor que tomará
     */
    public Objeto_Casa(String ID,String marca, String modelo,String valor, Casa casa){
        this.ID = ID;
        this.marca = marca;
        this.modelo = modelo;
        this.valor = valor;
        this.ruta = new Ruta(casa);
    }

    /**
     * Setter del objeto de la casa.
     *
     * Este método establece el nuevo valor que tomarán los atributos que conforman la clase (menos el ID) en función
     * del objeto que se le ha pasado como parámetro.
     * @param nuevo_objeto Un objeto de tipo Objeto_Casa. El objeto del que se desean tomar los nuevos valores.
     */
    public void setObjeto(Objeto_Casa nuevo_objeto){
        this.modelo = nuevo_objeto.getModelo();
        this.marca = nuevo_objeto.getMarca();
        this.valor = nuevo_objeto.getValor();
        this.ruta = nuevo_objeto.getRuta();
    }

    /**
     * Getter del ID del objeto.
     *
     * Este método devuelve el valor que tiene el objeto de la casa para el atributo 'ID'.
     * @return El ID del objeto.
     */
    public String getID(){return ID;}

    /**
     * Getter de la marca del objeto.
     *
     * Este método devuelve el valor que tiene el objeto de la casa para el atributo 'marca'.
     * @return La marca del objeto.
     */
    public String getMarca(){
        return marca;
    }

    /**
     * Getter del modelo del objeto.
     *
     * Este método devuelve el valor que tiene el objeto de la casa para el atributo 'modelo'.
     * @return El modelo del objeto.
     */
    public String getModelo(){
        return modelo;
    }

    /**
     * Getter para el valor del objeto.
     *
     * Este método devuelve el valor que tiene el objeto de la casa para el atributo 'valor'.
     * @return El valor del objeto.
     */
    public String getValor(){
        return valor;
    }

    /**
     * Getter de la ruta del objeto.
     *
     * Este método devuelve el valor que tiene el objeto de la casa para el atributo 'ruta'.
     * @return La ruta del objeto.
     */
    public Ruta getRuta(){
        return ruta;
    }

    /**
     * Método que permite calcular la siguiente posición del objeto.
     *
     * En este método se implementa la acción de simulación de movimiento del objeto, de tal forma que en base al
     * ancho y largo indicados, accediendo a la ruta del objeto se ejecuta la operación correspondiente y se actualiza
     * el valor que toma.
     * @param ancho Un entero. Las dimensiones del ancho de la casa en la que se encuentra el objeto.
     * @param largo Un entero. Las dimensiones del largo de la casa en la que se encuentra el objeto.
     */
    public void calculate_next_pos(int ancho,int largo) {
        ruta.calcular_pos_actual(ancho,largo);
    }

    /**
     * Getter sobre la información del objeto.
     *
     * Este método devuelve un array de String que contendrá por orden los valores que tiene el objeto para el ID, marca,
     *  modelo, valor, posición X y posición Y respectivamente.
     * @return La información sobre el objeto indexada en un array de String.
     */
    public String[] getInfo(){
        int x,y;
        x = ruta.get_pos_x();
        y = ruta.get_pos_y();

        return new String[]{this.getID() , this.getMarca(), this.getModelo()
                , this.getValor(), Integer.toString(x), Integer.toString(y)};
    }

    /**
     * Redefinición del método toString del objeto del hogar.
     *
     * En esta reimplementación se devuelve una cadena que únicamente indique el estado del atributo 'ID' y 'modelo'.
     * @return Una cadena con la información sobre ID y modelo del objeto del hogar.
     */
    @Override
    public String toString() {
        return "ID: " + getID() + " | Modelo: " + getModelo();
    }
}
