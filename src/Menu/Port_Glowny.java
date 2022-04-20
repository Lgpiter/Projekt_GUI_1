package Menu;

import Kontenery.*;
import Statek.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public interface Port_Glowny {
    ArrayList<Kontener_Podstawowy> kontenery = new ArrayList<>();
    ArrayList<Statek> statki = new ArrayList<>();
    Wagon wagon = new Wagon();
    Magazyn magazyn = new Magazyn(50);

    static void wypisz_statki(){
        System.out.println("LISTA STATKOW ZNAJDUJACYCH SIE W PORCIE");
        for(Statek s : statki)
            System.out.println(s);
    }

    static void wypisz_kontenery(){
        System.out.println("LISTA KONTENEROW ZNAJDUJACYCH SIE OBECNIE W PORCIE:");
        for(Kontener_Podstawowy k : kontenery)
            System.out.println(k);
    }

    static void usun_kontener(int numer){
        kontenery.removeIf(k -> k.getNumer_danego() == numer); // linijka podpowiedziana przez Intelij
    }

    static void zapisz() {
        PrintWriter zapis = null;
        try {
            zapis = new PrintWriter("zapis.txt");
            zapis.println("Kontenery");
            for(Kontener_Podstawowy k : kontenery)
                zapis.println(k);
            System.out.println("Statki");
            for(Statek s: statki)
                zapis.println(s);
            for(Kontener_Podstawowy k : magazyn.kontenery_magazyn.keySet())
                zapis.println(k);
            zapis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    static void wypusc_statek(int numer){
        statki.removeIf(s -> s.getNumer_Statku() == numer);
    }


}
