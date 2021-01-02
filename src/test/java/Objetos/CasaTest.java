package Objetos;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CasaTest {
    Casa casa_ejemplo = new Casa(500,300);

    @Test
    public void test_ancho(){
        System.out.println("Test de anchura en proceso...");
        assertEquals(500,casa_ejemplo.getAncho());
    }

    @Test
    public void test_largo(){
        System.out.println("Test de longitud en proceso...");
        assertEquals(300,casa_ejemplo.getLargo());
    }

    @Test
    public void test_agregar_objeto(){
        System.out.println("Test de agregacion de objeto en proceso...");
        Casa casa2 = new Casa(100,100);
        Objeto_Casa objeto = new Objeto_Casa("Ejemplo","ejemplo","ejemplo","ejemplo",casa2);
        casa_ejemplo.agregar_objeto(objeto);
    }

    @Test
    public void test_eliminar_objeto(){
        System.out.println("Test de eliminacion de objeto en proceso...");
        Objeto_Casa objeto = new Objeto_Casa("Ejemplo","ejemplo","ejemplo","ejemplo",casa_ejemplo);
        casa_ejemplo.agregar_objeto(objeto);

        assertTrue(casa_ejemplo.eliminar_objeto(objeto));
        assertFalse(casa_ejemplo.eliminar_objeto(objeto));
    }

    @Test
    public void test_eliminar_objetos(){
        System.out.println("Test de eliminacion de objetos en proceso...");
        List<Objeto_Casa> objetos_eliminar = new ArrayList<Objeto_Casa>();
        List<Objeto_Casa> objetos_eliminar2 = new ArrayList<Objeto_Casa>();

        Objeto_Casa objeto1 = new Objeto_Casa("Ejemplo","ejemplo","ejemplo","ejemplo",casa_ejemplo);
        Objeto_Casa objeto2 = new Objeto_Casa("Ejemplo2","ejemplo2","ejemplo2","ejemplo2",casa_ejemplo);
        casa_ejemplo.agregar_objeto(objeto1);
        casa_ejemplo.agregar_objeto(objeto2);

        objetos_eliminar.add(objeto1);
        objetos_eliminar.add(objeto2);

        Objeto_Casa objeto3 = new Objeto_Casa("Ejemplo3","ejemplo","ejemplo","ejemplo",casa_ejemplo);
        Objeto_Casa objeto4 = new Objeto_Casa("Ejemplo4","ejemplo2","ejemplo2","ejemplo2",casa_ejemplo);

        objetos_eliminar2.add(objeto3);
        objetos_eliminar2.add(objeto4);

        assertTrue(casa_ejemplo.eliminar_objetos(objetos_eliminar));
        assertFalse(casa_ejemplo.eliminar_objetos(objetos_eliminar2));
    }

    @Test
    public void test_actualizar_objeto(){
        System.out.println("Test de actualizacion de objeto en proceso...");
        Casa casa2 = new Casa(100,100);
        Objeto_Casa objeto = new Objeto_Casa("Ejemplo","ejemplo","ejemplo","ejemplo",casa_ejemplo);
        casa_ejemplo.agregar_objeto(objeto);

        Objeto_Casa objeto2 = new Objeto_Casa("Ejemplo","ejemplo2","ejemplo2","ejemplo2",casa2);
        Objeto_Casa objeto3 = new Objeto_Casa("Ejemplo2","ejemplo2","ejemplo2","ejemplo2",casa2);

        assertTrue(casa_ejemplo.actualizar_objeto(objeto2));
        assertFalse(casa_ejemplo.actualizar_objeto(objeto3));
    }

    @Test
    public void test_mostrar_objeto(){
        System.out.println("Test de mostrar objeto en proceso...");
        Objeto_Casa objeto = new Objeto_Casa("Ejemplo","ejemplo","ejemplo","ejemplo",casa_ejemplo);
        casa_ejemplo.agregar_objeto(objeto);
        casa_ejemplo.mostrar_objeto(0);
    }
}
