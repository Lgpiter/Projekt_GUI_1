package Menu;

import Kontenery.Kontener_Podstawowy;

import java.time.LocalDate;
import java.util.HashMap;
import java.time.temporal.ChronoUnit;

public class Magazyn implements Port_Glowny{
    public Data czas = new Data();
    HashMap<Kontener_Podstawowy, LocalDate> kontenery_magazyn = new HashMap<>();
    private final int limit_kontenerow;

    public Magazyn(int limit_kontenerow){
        this.limit_kontenerow = limit_kontenerow;

        Thread thread = new Thread(() -> {
            while(!Thread.interrupted()){
                try {
                    Thread.sleep(5000);

                    for(Kontener_Podstawowy k : kontenery_magazyn.keySet()){
                        LocalDate data_kontenera = kontenery_magazyn.get(k);
                        long ile_dni = ChronoUnit.DAYS.between(czas.getData(),data_kontenera);
                        if(
                                (k.get_typ().equals("Wybuchowy") && ile_dni == 5) ||
                                (k.get_typ().equals("ToksycznyCiekly") && ile_dni == 10) ||
                                (k.get_typ().equals("ToksycznySypki") && ile_dni == 14)
                        ){
                            try {
                                k.upomij_nadawce();
                                usun_kontener(k.getNumer_danego());
                            }catch (IrresponsibleSenderWithDangerousGoods e){
                                e.printStackTrace();
                            }
                        }

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void dodaj_kontener(int numer_kontenera){
        if(kontenery_magazyn.size() < limit_kontenerow){
            for(Kontener_Podstawowy k : Port_Glowny.kontenery) {
                if (k.getNumer_danego() == numer_kontenera) {
                    if(k.upomnienia_nadawcy() < 2) {
                        Port_Glowny.kontenery.remove(k);
                        kontenery_magazyn.put(k, czas.getData());
                    }
                    else{
                        System.out.println("Ten kontener nie moze zostac zaladowany do magazynu," +
                                "poniewaz ten dostawca dostal juz 2 upomnienia za zutylizowane kontenery");
                    }
                }
            }
        }

    }

    void usun_kontener(int numer_kontenera) throws IrresponsibleSenderWithDangerousGoods {
        for (Kontener_Podstawowy k : kontenery_magazyn.keySet()) {
            if (k.getNumer_danego() == numer_kontenera) {
                kontenery_magazyn.remove(k);
                k.upomij_nadawce();
                throw new IrresponsibleSenderWithDangerousGoods(k.getNumer_danego(),kontenery_magazyn.get(k),czas.getData());
            }
        }

    }

}
