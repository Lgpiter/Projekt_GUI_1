package Menu;


import Kontenery.Kontener_Podstawowy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.temporal.ChronoUnit;


public class Magazyn extends Thread{
    public Data czas = new Data();
    private final HashMap<Kontener_Podstawowy, LocalDate> kontenery_magazyn = new HashMap<>();
    private final int limit_kontenerow;
    private boolean czy_wylaczyc = false;
    Thread magazyn_watek;

    public Magazyn(int limit_kontenerow) {
        this.limit_kontenerow = limit_kontenerow;

        magazyn_watek = new Thread(() -> {
            while (!Thread.interrupted()) {
                if(czy_wylaczyc)
                    magazyn_watek.interrupt();
                else {
                    czas.setData(); // przestawie o dobe do przodu
                    sprawdzenie_magazynu(); // sprawdza czy kontenery nie leza za dlugo
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        magazyn_watek.start();
    }

    public void dodaj_kontener(Kontener_Podstawowy k) {
        if (kontenery_magazyn.size() < limit_kontenerow) {
            if (k.upomnienia_nadawcy() < 2) {
                kontenery_magazyn.put(k, czas.getData());
            } else {
                System.out.println("Ten kontener nie moze zostac zaladowany do magazynu," +
                        "poniewaz ten dostawca dostal juz 2 upomnienia za zutylizowane kontenery");
            }
        } else {
            System.out.println("Nie mozna zaladowac kontenera, w magazynie jest juz zbyt wiele kontenerow");
        }
    }

    void zwroc_uwage(int numer_kontenera) throws IrresponsibleSenderWithDangerousGoods {
        for (Kontener_Podstawowy k : kontenery_magazyn.keySet()) {
            if (k.getNumer_danego() == numer_kontenera) {
                k.upomij_nadawce();
                throw new IrresponsibleSenderWithDangerousGoods(k.getNumer_danego(), kontenery_magazyn.get(k), czas.getData());
            }
        }
    }

    public void usun_kontener(int numer_kontenera){
        for(Kontener_Podstawowy k : kontenery_magazyn.keySet()) {
            if(k.getNumer_danego() == numer_kontenera) {
                kontenery_magazyn.remove(k);
                break;
            }
        }
    }

    public void pokaz_stan_magazynu() {
        for (Kontener_Podstawowy k : kontenery_magazyn.keySet()) {
           System.out.print(k);

            LocalDate data_kontenera = kontenery_magazyn.get(k);
            long ile_dni = ChronoUnit.DAYS.between(data_kontenera, czas.getData());

            if (k.get_typ().equals("Wybuchowy")) {
                System.out.print(" Do utylizacji kontenera pozostalo " + (5 - ile_dni));
            }

            if (k.get_typ().equals("ToksycznyCiekly") || k.get_typ().equals("ToksycznySypki"))
                System.out.print(" Do utylizacji kontenera pozostalo " + (14 - ile_dni));

            System.out.println();
            System.out.println();
        }
    }

    public void sprawdzenie_magazynu() {
        ArrayList <Kontener_Podstawowy> pom = new ArrayList<>();
        for (Kontener_Podstawowy k : kontenery_magazyn.keySet()) {

            LocalDate data_kontenera = kontenery_magazyn.get(k);
            long ile_dni = ChronoUnit.DAYS.between(data_kontenera,czas.getData());
            if (
                    (k.get_typ().equals("Wybuchowy") && ile_dni == 5) ||
                            (k.get_typ().equals("ToksycznyCiekly") && ile_dni == 10) ||
                            (k.get_typ().equals("ToksycznySypki") && ile_dni == 14)
            ) {
                try {
                    pom.add(k);
                    zwroc_uwage(k.getNumer_danego());
                } catch (IrresponsibleSenderWithDangerousGoods e) {
                    System.out.println(e);
                }
            }
        }

        for (Kontener_Podstawowy kontener_podstawowy : pom) {
            usun_kontener(kontener_podstawowy.getNumer_danego());
        }

    }

    public void zatrzymaj_magazyn(){
        this.czy_wylaczyc = true;
    }

    public HashMap<Kontener_Podstawowy, LocalDate> getKontenery_magazyn() {
        return kontenery_magazyn;
    }

    public Data getCzas() {
        return czas;
    }
}
