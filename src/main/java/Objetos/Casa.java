package Objetos;

import java.util.ArrayList;
import java.util.List;

public class Casa {

    //Dimensiones del piso
    public int ancho;
    public int largo;

    private final ArrayList<Objeto_Casa> objetos;

    public Casa(int ancho, int largo){
        this.objetos = new ArrayList<Objeto_Casa>();
        this.ancho = ancho;
        this.largo = largo;
    }

    public int getAncho(){
        return this.ancho;
    }
    public int getLargo(){
        return this.largo;
    }

    public void agregar_objeto(Objeto_Casa objeto){
        objeto.calculate_next_pos(ancho, largo);
        objetos.add(objeto);
    }

    public boolean eliminar_objeto(Objeto_Casa nuevo_valor_objeto){
        for (int i=0; i<objetos.size();i++){
            if(objetos.get(i).getID().equals(nuevo_valor_objeto.getID())){
                objetos.remove(i);
                return true;
            }
        }
        return false;
    }

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

    public boolean actualizar_objeto(Objeto_Casa nuevo_valor_objeto){
        for (Objeto_Casa objeto : objetos) {
            if (objeto.getID().equals(nuevo_valor_objeto.getID())) {
                objeto.setObjeto(nuevo_valor_objeto);
                return true;
            }
        }
        return false;
    }

    public String[] mostrar_objeto(int i){
        for (Objeto_Casa objeto : objetos){
            objeto.calculate_next_pos(ancho, largo);
        }
        return objetos.get(i).getInfo();
    }
}
