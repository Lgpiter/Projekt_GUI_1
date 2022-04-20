package Statek;

import Kontenery.*;
import Menu.Port_Glowny;

import java.util.ArrayList;
import java.util.Collections;

public class Statek implements Comparable<Statek>, Port_Glowny {
    private final String nazwa_Statku;
    private final int limit_niebezpiecznych;
    private int ilosc_niebezpiecznych = 0;
    private final int limit_ciezkich;
    private int ilosc_ciezkich = 0;
    private final int limit_sieciowych;
    private int ilosc_sieciowych;
    private final int limit_wszystkich;
    private int ilosc_wszystkich;
    private final double maksymalna_waga;
    private double obecna_waga = 0;
    private static int licznik = 347; // sluzy do numerowania statkow
    private final int numer_Statku;
    public ArrayList<Kontener_Podstawowy> kontenery_na_statku = new ArrayList<>();

    public Statek(String nazwa, int limit_ciezkich, int limit_sieciowych, int limit_wszystkich, double maksymalna_waga, int limit_niebezpiecznych){
        this.nazwa_Statku = nazwa;
        this.numer_Statku = licznik++;
        this.limit_ciezkich = limit_ciezkich;
        this.limit_sieciowych = limit_sieciowych;
        this.limit_wszystkich = limit_wszystkich;
        this.maksymalna_waga = maksymalna_waga;
        this.limit_niebezpiecznych = limit_niebezpiecznych;
    }

    public void zaladuj_kontener(Kontener_Podstawowy kontener) throws Error_liczbaKontenerow, Error_Waga, Error_Siec, Error_Ciezkie, Error_niebezpieczne {
        if(ilosc_wszystkich < limit_wszystkich){ //glowny if do sprawdzenia czy nie mamy za duzo kontenerow
            if(obecna_waga + kontener.getWaga_kontenera() < maksymalna_waga) {// 2 krok sprawdzamy czy waga jest git
                switch (kontener.get_typ()) {//switch, ktory leci nam po typach kontenerow
                    case "Podstawowy", "Ciekle" -> {
                        kontenery_na_statku.add(kontener);
                        obecna_waga += kontener.getWaga_kontenera();
                        ilosc_wszystkich++;
                    }
                    case "Ciezki" -> {
                        if(ilosc_ciezkich < limit_ciezkich){
                            kontenery_na_statku.add(kontener);
                            obecna_waga += kontener.getWaga_kontenera();
                            ilosc_wszystkich++;
                            ilosc_ciezkich++;
                        }
                        else
                            throw new Error_Ciezkie();
                    }
                    case "Chlodniczy" -> {
                        if(ilosc_sieciowych < limit_sieciowych){
                            kontenery_na_statku.add(kontener);
                            obecna_waga += kontener.getWaga_kontenera();
                            ilosc_wszystkich++;
                            ilosc_sieciowych++;
                        }
                        else
                            throw new Error_Siec();
                    }
                    case "Wybuchowy", "ToksycznySypki" -> {
                        if(ilosc_niebezpiecznych < limit_niebezpiecznych){
                            kontenery_na_statku.add(kontener);
                            obecna_waga += kontener.getWaga_kontenera();
                            ilosc_wszystkich++;
                            ilosc_ciezkich++;
                            ilosc_niebezpiecznych++;
                        }
                    }
                    case "ToksycznyCiekly" -> {
                        if(ilosc_niebezpiecznych < limit_niebezpiecznych){
                            if(ilosc_sieciowych < limit_sieciowych){
                                kontenery_na_statku.add(kontener);
                                obecna_waga += kontener.getWaga_kontenera();
                                ilosc_wszystkich++;
                                ilosc_ciezkich++;
                                ilosc_niebezpiecznych++;
                                ilosc_sieciowych++;
                            }
                            else
                                throw new Error_Siec();
                        }
                        else
                            throw new Error_niebezpieczne();
                    }
                }
            }
            else{
                throw new Error_Waga();
            }
        }
        else{
            throw new Error_liczbaKontenerow();
        }

        Collections.sort(kontenery_na_statku); //sortuje fajnie za każdym razem kontenery wobec wagi rosnąco
    }

    public String getNazwa_Statku() {
        return nazwa_Statku;
    }

    public int getNumer_Statku(){return numer_Statku;}

    public void rozladuj_kontener(int numer_kontenera){
        for(Kontener_Podstawowy k : kontenery_na_statku){
            if(k.getNumer_danego() == numer_kontenera){
                Port_Glowny.kontenery.add(k);
                kontenery_na_statku.remove(k);
            }
        }
    }

    @Override
    public String toString() {
        return "Statek{" +
                "nazwa_Statku='" + nazwa_Statku + '\'' +
                ", limit_niebezpiecznych=" + limit_niebezpiecznych +
                ", ilosc_niebezpiecznych=" + ilosc_niebezpiecznych +
                ", numer_Statku=" + numer_Statku +
                '}';
    }

    public void konteneryNaStatku(){
        for (Kontener_Podstawowy kontener_podstawowy : kontenery_na_statku) {
            System.out.println(kontener_podstawowy);

        }
    }

    @Override
    public int compareTo(Statek o) {
        return this.getNazwa_Statku().compareTo(o.getNazwa_Statku());
    }

}
