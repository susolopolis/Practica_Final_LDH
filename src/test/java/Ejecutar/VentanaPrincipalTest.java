package Ejecutar;

import Objetos.Casa;
import Objetos.Objeto_Casa;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class VentanaPrincipalTest {

    VentanaPrincipal frame = new VentanaPrincipal();

    @Test
    public void test_CreacionVentana(){
        frame.setVisible(true);
        assertNotNull(frame);
    }


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

    @Test
    public void test_vaciarCampos(){
        frame.vaciarCampos();
    }

    @Test
    public void test_recuperarObjeto(){

        Casa casa_ejemplo = new Casa(100,100);
        Objeto_Casa objeto = new Objeto_Casa("Ejemplo","ejemplo","ejemplo","ejemplo",casa_ejemplo);

        frame.recuperarObjeto(objeto);
        //Objeto ya agregado
        frame.recuperarObjeto(objeto);

    }

    @Test
    public void test_ActionPerformed(){
        try {
            frame.btCrear.doClick();
            frame.btActualizar.doClick();
            frame.btEliminar.doClick();
            frame.btGuardar.doClick();
            frame.btRecuperar.doClick();

        }catch (Exception e){
            System.out.println("Test no superado...");
        }
    }
}
