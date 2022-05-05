package Statek;

import Kontenery.Kontener_Podstawowy;

import java.util.ArrayList;
import java.util.Collections;

public class Statek implements Comparable<Statek>{
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
    private static int licznik = 1; // sluzy do numerowania statkow
    private  int numer_Statku;
    private final ArrayList<Kontener_Podstawowy> kontenery_na_statku = new ArrayList<>();

    private final String port_macierzysty;
    private final String lokalizacja_zrodlowa;
    private final String lokalizacja_docelowa;

    public Statek(String nazwa, int limit_ciezkich, int limit_sieciowych, int limit_wszystkich, double maksymalna_waga, int limit_niebezpiecznych,
                  String port_macierzysty, String lokalizacja_zrodlowa, String lokalizacja_docelowa){
        this.nazwa_Statku = nazwa;
        this.numer_Statku = licznik++;
        this.limit_ciezkich = limit_ciezkich;
        this.limit_sieciowych = limit_sieciowych;
        this.limit_wszystkich = limit_wszystkich;
        this.maksymalna_waga = maksymalna_waga;
        this.limit_niebezpiecznych = limit_niebezpiecznych;
        this.port_macierzysty = port_macierzysty;
        this.lokalizacja_zrodlowa = lokalizacja_zrodlowa;
        this.lokalizacja_docelowa = lokalizacja_docelowa;
    }

    public void zaladuj_kontener(Kontener_Podstawowy kontener) throws Error_Kontenery {
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
                             throw new Error_Kontenery("Za duza liczba kontenerow ciezkich");
                    }
                    case "Chlodniczy" -> {
                        if(ilosc_sieciowych < limit_sieciowych){
                            kontenery_na_statku.add(kontener);
                            obecna_waga += kontener.getWaga_kontenera();
                            ilosc_wszystkich++;
                            ilosc_sieciowych++;
                        }
                        else
                            throw new Error_Kontenery("Proba podlaczenia zbyt wielu kontenerow do sieci");
                    }
                    case "Wybuchowy", "ToksycznySypki" -> {
                        if(ilosc_niebezpiecznych < limit_niebezpiecznych){
                            kontenery_na_statku.add(kontener);
                            obecna_waga += kontener.getWaga_kontenera();
                            ilosc_wszystkich++;
                            ilosc_ciezkich++;
                            ilosc_niebezpiecznych++;
                        }
                        else
                            throw new Error_Kontenery("Zbyt duzo kontenerow niebezpiecznych");
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
                                throw new Error_Kontenery("Proba podlaczenia zbyt wielu kontenerow do sieci");
                        }
                        else
                            throw new Error_Kontenery("Zbyt duzo kontenerow niebezpiecznych");
                    }
                }
            }
            else{
               // throw new Error_Waga();
                throw new Error_Kontenery("Waga kontenerow na statku jest zbyt duza, nie mozna dodac nastepnego, bo statek zatonie!");
            }
        }
        else{
            throw new Error_Kontenery("Na statku znajduje sie zbyt wiele kontenerow");
            //throw new Error_Liczba_Kontenerow();
        }

        Collections.sort(kontenery_na_statku); //sortuje fajnie za każdym razem kontenery wobec wagi rosnąco
    }

    public String get_nazwa_statku() {
        return nazwa_Statku;
    }

    public int get_numer_statku(){return numer_Statku;}

    public void set_numer_statku(int numer){this.numer_Statku = numer;}

    public void setIlosc_niebezpiecznych(int ilosc_niebezpiecznych) {
        this.ilosc_niebezpiecznych = ilosc_niebezpiecznych;
    }

    public void setIlosc_sieciowych(int ilosc_sieciowych) {
        this.ilosc_sieciowych = ilosc_sieciowych;
    }

    public void setIlosc_ciezkich(int ilosc_ciezkich) {
        this.ilosc_ciezkich = ilosc_ciezkich;
    }

    public void setIlosc_wszystkich(int ilosc_wszystkich) {
        this.ilosc_wszystkich = ilosc_wszystkich;
    }

    public void setObecna_waga(double obecna_waga) {
        this.obecna_waga = obecna_waga;
    }

    public void rozladuj_kontener(int numer_kontenera){
        ArrayList<Kontener_Podstawowy> pom = new ArrayList<>();

        for(Kontener_Podstawowy k : kontenery_na_statku)
            if(k.getNumer_danego() == numer_kontenera)
                pom.add(k);

        kontenery_na_statku.removeAll(pom);
    }

    public void kontenery_na_statku(){
        for (Kontener_Podstawowy kontener_podstawowy : kontenery_na_statku) {
            System.out.println(kontener_podstawowy);
            System.out.println();
        }
    }

    @Override
    public int compareTo(Statek o) {
        return this.get_nazwa_statku().compareTo(o.get_nazwa_statku());
    }

    @Override
    public String toString() {
        return "Statek numer: " + numer_Statku +
                "\nNazwa statku: " + nazwa_Statku +
                "\nLimit niebezpiecznych: " + limit_niebezpiecznych +
                "\nIlosc niebezpiecznych: " + ilosc_niebezpiecznych +
                "\nLimit ciezkich: " + limit_ciezkich +
                "\nIlosc ciezkich: " + ilosc_ciezkich +
                "\nLimit sieciowych: " + limit_sieciowych +
                "\nIlosc sieciowych: " + ilosc_sieciowych +
                "\nLimit wszystkich: " + limit_wszystkich +
                "\nIlosc wszystkich: " + ilosc_wszystkich +
                "\nMaksymalna waga: " + maksymalna_waga +
                "\nObecna waga: " + obecna_waga +
                "\nPort macierzysty: " + port_macierzysty +
                "\nLokalizacja zrodlowa: " + lokalizacja_zrodlowa +
                "\nLokalizacja docelowa: " + lokalizacja_docelowa
                ;
    }

    public ArrayList<Kontener_Podstawowy> getKontenery_na_statku() {
        return kontenery_na_statku;
    }
}
