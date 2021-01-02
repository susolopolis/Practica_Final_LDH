package Objetos;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class Objeto_CasaTest {

    Casa casa_ejemplo = new Casa(100,100);

    @Test
    public void test_crear(){
        System.out.println("Test de creacion en proceso...");
        Objeto_Casa objeto = new Objeto_Casa("Ejemplo","ejemplo","ejemplo","ejemplo",casa_ejemplo);
    }

    @Test
    public void test_set(){
        System.out.println("Test de set en proceso...");
        Objeto_Casa objeto = new Objeto_Casa("Ejemplo","ejemplo","ejemplo","ejemplo",casa_ejemplo);
        Objeto_Casa objeto2 = new Objeto_Casa("Ejemplo2","ejemplo2","ejemplo2","ejemplo2",casa_ejemplo);
        objeto.setObjeto(objeto2);
    }

    @Test
    public void test_getters(){
        Objeto_Casa objeto = new Objeto_Casa("Ejemplo","marca","modelo","valor",casa_ejemplo);

        assertEquals("Ejemplo",objeto.getID());
        assertEquals("marca",objeto.getMarca());
        assertEquals("modelo",objeto.getModelo());
        assertEquals("valor",objeto.getValor());

    }

    @Test
    public void test_toString(){
        Objeto_Casa objeto = new Objeto_Casa("Ejemplo","marca","modelo","valor",casa_ejemplo);
        assertEquals("ID: Ejemplo | Modelo: modelo",objeto.toString());
    }

}
