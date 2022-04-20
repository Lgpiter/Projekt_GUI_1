package Menu;

import Kontenery.Kontener_Podstawowy;

import java.util.ArrayList;

public class Wagon {
    private int counter = 0;
    ArrayList<Kontener_Podstawowy> kontenery;

    public Wagon(){
        this.kontenery = new ArrayList<Kontener_Podstawowy>();
    }

    public void add_kontener(Kontener_Podstawowy kontener){
        if(counter < 10) {
            kontenery.add(kontener);
            counter++;
        }
        else{
            kontenery.clear();
            counter = 0;
            System.out.println("Zaladowano 10 kontener do wagonu, wiec obecny wagon musial odjechac, poczekaj na pyrzjazd kolejnego");
            try {
                Thread.sleep(5000); // 5000 = 5s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
