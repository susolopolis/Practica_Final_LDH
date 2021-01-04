package Objetos;

import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.security.SecureRandom;

public class Ruta {

    public static final int COTA_DE_DESPLAZAMIENTO_X = 100; /**< Un entero. La cota de desplazamiento para la posición X */
    public static final int COTA_DE_DESPLAZAMIENTO_Y = 45; /**< Un entero. La cota de desplazamiento para la posición Y */

    private int pos_actual_x; /**< Un entero. El valor que tendrá la posición X. */
    private int pos_actual_y; /**< Un entero. El valor que tendrá la posición Y. */

    private Random ran1 = SecureRandom.getInstanceStrong(); /**< Valor 1 aleatorio para la generacion de la posicion */
    private Random ran2 = SecureRandom.getInstanceStrong(); /**< Valor 2 aleatorio para la generacion de la posicion */

    private Casa casa;

    /**
     * Constructor vacío de la clase de la ruta.
     *
     * En este constructor vacío se inicializan las posiciones X e Y del objeto a 0.
     */

    public Ruta() throws NoSuchAlgorithmException {
        pos_actual_x = 0;
        pos_actual_y = 0;
    }

    /**
     * Constructor de la clase de la ruta con el entorno en el que se está simulando.
     *
     * En este constructor se asigna el valor del hogar que se pasa como parámetro al atributo 'casa' de la ruta.
     * @param casa El entorno en el que se desea ejecutar la simulación de movimiento.
     */

    public Ruta(Casa casa) throws NoSuchAlgorithmException {
        this();
        this.casa = casa;
    }

    /**
     * Método que simula y calcula las posiciones X e Y del objeto para esta ruta.
     *
     * En este método se calculan las posiciones X e Y del objeto en base a un rangoX y rangoY que serán las cotas
     * sobre las que se deseará ejecutar la simulación, de tal modo que se pueda ajustar y reflejar un desplazamiento
     * del objeto más realista. Para aportar cierta naturalidad a la operación, se dota de aleatoriedad a ésta y se
     * distingue entre 4 casos de ejecución: incrementar X y decrementar Y; decrementar X e incrementar Y;
     * decrementar X e Y; incrementar X e Y.
     * @param rangoX Un entero. El rango en el que se desea incrementar/decrementar el valor de X.
     * @param rangoY Un entero. El rango en el que se desea incrementar/decrementar el valor de Y.
     */

    public void calcular_pos_actual(int rangoX, int rangoY){

        int valor_X ;
        int valor_Y ;

        double random;

        if(pos_actual_x > 0 && pos_actual_y > 0){
            do{
                valor_X = pos_actual_x;
                valor_Y = pos_actual_y;

                random = new SecureRandom().nextDouble();

                if(random < 0.25){
                    valor_X += ran1.nextInt(rangoX);
                    valor_Y -= ran2.nextInt(rangoY);
                } else if (random < 0.5){
                    valor_X -= ran1.nextInt(rangoX);
                    valor_Y += ran2.nextInt(rangoY);
                } else if (random < 0.75){
                    valor_X -= ran1.nextInt(rangoX);
                    valor_Y -= ran2.nextInt(rangoY);
                } else {
                    valor_X += ran1.nextInt(rangoX);
                    valor_Y += ran2.nextInt(rangoY);
                }
            } while((valor_X <= 0 || valor_X >= casa.getAncho()) || (valor_Y <= 0 || valor_Y >= casa.getLargo()));

        } else {
            valor_X = ran1.nextInt(rangoX);
            valor_Y = ran2.nextInt(rangoY);
        }

        pos_actual_x = valor_X;
        pos_actual_y = valor_Y;
    }
    /**
     * Getter de la posición X de la ruta.
     *
     * Este método devuelve el valor que tiene el atributo 'pos_actual_x' de la ruta.
     * @return La posición X de la ruta en la que se encuentra el objeto.
     */
    public int get_pos_x(){
        return pos_actual_x;
    }

    /**
     * Getter de la posición Y de la ruta.
     *
     * Este método devuelve el valor que tiene el atributo 'pos_actual_y' de la ruta.
     * @return La posición Y de la ruta en la que se encuentra el objeto.
     */
    public int get_pos_y(){
        return pos_actual_y;
    }
}
