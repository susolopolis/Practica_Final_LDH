package logica;

import java.util.Random;

public class Ruta {

    public static final int COTA_DE_DESPLAZAMIENTO_X = 100;
    public static final int COTA_DE_DESPLAZAMIENTO_Y = 45;

    private int pos_actual_x;
    private int pos_actual_y;

    private Casa casa;


    public Ruta(){
        pos_actual_x = 0;
        pos_actual_y = 0;
    }

    public Ruta(Casa casa){
        this();
        this.casa = casa;
    }

    public void calcular_pos_actual(int rangoX, int rangoY){
        Random ran1 = new Random();
        Random ran2 = new Random();

        int valor_X ;
        int valor_Y ;

        double random;

        if(pos_actual_x > 0 && pos_actual_y > 0){
            do{
                valor_X = pos_actual_x;
                valor_Y = pos_actual_y;

                random = Math.random();

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

    public int get_pos_x(){
        return pos_actual_x;
    }

    public int get_pos_y(){
        return pos_actual_y;
    }
}
