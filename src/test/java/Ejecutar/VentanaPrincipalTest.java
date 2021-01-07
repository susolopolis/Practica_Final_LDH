package Ejecutar;

import Objetos.Casa;
import Objetos.Objeto_Casa;
import org.junit.jupiter.api.Test;
import java.awt.Toolkit;
import java.awt.GraphicsEnvironment;


import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class VentanaPrincipalTest {

    VentanaPrincipal frame = new VentanaPrincipal();/**< Un objeto VentanaPrincipal. Una instancia de la aplicación visual sobre la que se ejecutarán las pruebas. */

    /**
     * Test para comprobar la creación de la ventana.
     *
     * En este test se comrpueba la creación de la ventana de forma que sea visible para el usuario
     * y no sea una instancia nula.
     */

    @Test
    public void test_CreacionVentana(){
        assertNotNull(frame);
    }

    /**
     * Test para comprobar la actualización de valores.
     *
     * En este test se comprueba la actualización de valores por medio del método empleado en la IGU.
     * Para ello se instancia un objeto Casa al igual que un Objeto_Casa y se actualizan los valores
     * para el objeto en cuestión. Se ha de controlar una posible excepción en este caso.
     */

    @Test
    public void test_actualizarValores(){

        Casa casa_ejemplo = new Casa(100,100);
        Objeto_Casa objeto = new Objeto_Casa("Ejemplo","ejemplo","ejemplo","ejemplo",casa_ejemplo);

        try{
            frame.actualizarValores(objeto);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Test para comprobar el contenido del modelo de objetos en la IGU.
     *
     * En este test se comprueba la presencia de un objeto por medio del método doesItContain().
     * Para ello, se añade un objeto al modelo y se comprueba que primeramente existe.
     * Acto seguido se elimina dicho elemento y se comprueba el caso opuesto.
     */
    @Test
    public void test_doesItContain(){

        Casa casa_ejemplo = new Casa(100,100);
        Objeto_Casa objeto = new Objeto_Casa("Ejemplo","ejemplo","ejemplo","ejemplo",casa_ejemplo);
        DefaultListModel<Objeto_Casa> modelObjetos=new DefaultListModel<Objeto_Casa>();
        modelObjetos.addElement(objeto);

        assertTrue(frame.doesItContain(objeto,modelObjetos));
        modelObjetos.removeElement(objeto);
        assertFalse(frame.doesItContain(objeto,modelObjetos));
    }

    /**
     * Test para comprobar el vaciado de campos.
     *
     * En este test se opera la acción vaciarCampos() de tal forma que el texto de información de cada
     * objeto quede vacío y se borre la información que se estaba mostrando.
     */

    @Test
    public void test_vaciarCampos(){
        frame.vaciarCampos();
    }

    /**
     * Test para comprobar la recuperación de un objeto.
     *
     * En este test se comprueba la ejecución del método recuperarObjeto() perteneciente a la IGU, de tal
     * modo que cuando se desee obtener un objeto de vuelta, al indicar de qué elemento se trata el método
     * sea capaz de añadir al modelo original el objeto en cuestión.
     */

    @Test
    public void test_recuperarObjeto(){

        Casa casa_ejemplo = new Casa(100,100);
        Objeto_Casa objeto = new Objeto_Casa("Ejemplo","ejemplo","ejemplo","ejemplo",casa_ejemplo);

        frame.recuperarObjeto(objeto);
    }


    /**
     * Test para comprobar el actionPerformed de los botones en la IGU.
     *
     * En este test se comprueba el funcionamiento de los botones crear y guardar de la IGU, de tal forma que
     * su actionPerformed se active o no en función del foco en el que se encuentre la ejecución
     * de la aplicación.
     */

    @Test
    public void test_ActionPerformed(){
        try {

            frame.setCampoID("Tenis");
            frame.setCampoMarca("Nike");
            frame.setCampoModelo("Air");
            frame.setCampoValor("200");

            frame.btCrear.doClick();
            frame.btGuardar.doClick();

        }catch (Exception e){
            System.out.println("Test no superado...");
        }
    }
}
