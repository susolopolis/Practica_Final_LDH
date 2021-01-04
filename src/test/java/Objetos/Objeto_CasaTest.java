package Objetos;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class Objeto_CasaTest {

    Casa casa_ejemplo = new Casa(100,100);/**< Un objeto Casa. Instancia del objeto Casa que se usará para ejecutar las pruebas sobre él. */

    /**
     * Test para comprobar la creación de un objeto del hogar.
     *
     * En este test se realiza la creación de un objeto Objeto_Casa por medio del constructor de la clase,
     * pasando los parámetros correspondientes que establecen las propiedades del objeto.
     */

    @Test
    public void test_crear(){
        System.out.println("Test de creacion en proceso...");
        Objeto_Casa objeto = new Objeto_Casa("Ejemplo","ejemplo","ejemplo","ejemplo",casa_ejemplo);
    }
    /**
     * Test para comprobar la modificación de los objetos.
     *
     * En este test se realiza el "setteo" del objeto, de tal forma que se puedan transferir todas las propiedades
     * de un objeto distinto al que se está testando (primera instancia de Objeto_Casa creado).
     */
    @Test
    public void test_set(){
        System.out.println("Test de set en proceso...");
        Objeto_Casa objeto = new Objeto_Casa("Ejemplo","ejemplo","ejemplo","ejemplo",casa_ejemplo);
        Objeto_Casa objeto2 = new Objeto_Casa("Ejemplo2","ejemplo2","ejemplo2","ejemplo2",casa_ejemplo);
        objeto.setObjeto(objeto2);
    }
    /**
     * Test para comprobar la toma de valores de las propiedades del objeto.
     *
     * En este test se comprueba el correcto funcionamiento de los 'getters' pertenecientes a la clase
     * Objeto_Casa, de tal forma que los valores indicados han de ser aquellos que se hayan establecido
     * al crear un objeto inicial con el constructor de la propia clase.
     */
    @Test
    public void test_getters(){
        Objeto_Casa objeto = new Objeto_Casa("Ejemplo","marca","modelo","valor",casa_ejemplo);

        assertEquals("Ejemplo",objeto.getID());
        assertEquals("marca",objeto.getMarca());
        assertEquals("modelo",objeto.getModelo());
        assertEquals("valor",objeto.getValor());

    }
    /**
     * Test para comprobar la redefinición del método toString().
     *
     * En este test se comprueba la reimplementación del método toString() de tal forma que se compruebe
     * que la cadena que devuelva el método informe de los valores que presenta el objeto Objeto_Casa
     * para los atributos: ID; y modelo.
     */
    @Test
    public void test_toString(){
        Objeto_Casa objeto = new Objeto_Casa("Ejemplo","marca","modelo","valor",casa_ejemplo);
        assertEquals("ID: Ejemplo | Modelo: modelo",objeto.toString());
    }

}
