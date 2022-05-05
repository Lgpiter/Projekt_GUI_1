package Menu;

import Kontenery.Kontener_Podstawowy;
import java.util.ArrayList;

public class Wagon{
    private int licznik = 0;
    private boolean czy_wylaczyc = false;
    ArrayList<Kontener_Podstawowy> kontenery;

    Thread wagon_watek;

    public Wagon(){
        this.kontenery = new ArrayList<>();

        wagon_watek = new Thread (() -> {
            while (!Thread.interrupted()) {
                if(czy_wylaczyc)
                    wagon_watek.interrupt();
                else {
                    if (licznik == 10) {
                        kontenery.clear();
                        licznik = 0;
                        System.out.println("Zaladowano 10 kontener do wagonu, wiec obecny wagon musial odjechac, poczekaj na pyrzjazd kolejnego");
                        try {
                            Thread.sleep(30000); // 30 sekund
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        wagon_watek.start();
    }

    public void dodaj_kontener(Kontener_Podstawowy kontener){
            kontenery.add(kontener);
            licznik++;
    }

    public void zatrzymaj_wagon(){
        this.czy_wylaczyc = true;
    }

}
