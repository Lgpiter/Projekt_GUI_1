package Menu;

import Kontenery.*;
import Statek.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Port_Glowny {
    private final ArrayList<Kontener_Podstawowy> kontenery = new ArrayList<Kontener_Podstawowy>();
    private final ArrayList<Statek> statki = new ArrayList<>();
    private final Wagon wagon = new Wagon();
    private final Magazyn magazyn = new Magazyn(100);
    private final ArrayList<Nadawca> nadawcy = new ArrayList<Nadawca>();

    public Port_Glowny(){}

    public void wypisz_statki(){
        System.out.println("LISTA STATKOW ZNAJDUJACYCH SIE W PORCIE");
        for(Statek s : statki) {
            System.out.println(s);
            System.out.println();
        }
    }

    public void wypisz_kontenery(){
        System.out.println("LISTA KONTENEROW ZNAJDUJACYCH SIE OBECNIE W PORCIE:");
        for(Kontener_Podstawowy k : kontenery) {
            System.out.println(k);
            System.out.println();
        }
    }

    public void usun_kontener(int numer){
        ArrayList<Kontener_Podstawowy> tmp = new ArrayList<>();

        for(Kontener_Podstawowy k : kontenery)
            if(k.getNumer_danego() == numer)
                tmp.add(k);

        kontenery.removeAll(tmp);
    }

    public void wypusc_statek(int numer){
        statki.removeIf(s -> s.get_numer_statku() == numer);
    }

    public ArrayList<Kontener_Podstawowy> getKontenery() {
        return kontenery;
    }

    public ArrayList<Statek> getStatki() {
        return statki;
    }

    public Wagon getWagon() {
        return wagon;
    }

    public Magazyn getMagazyn() {return magazyn;}

    public ArrayList<Nadawca> getNadawcy() {
        return nadawcy;
    }

    public void wczytaj_nadawcow(File plik){
        String imie = "";
        String nazwisko = "";
        String pesel = "";
        int ilosc_upomnien = 0;
        int numer = 0;
        try{
            int licznik = 0;
            BufferedReader czytaj = new BufferedReader(new FileReader(plik));
            String pom;
            while ((pom = czytaj.readLine()) != null) {
                if(pom.equals("***") && (licznik % 2 == 0))
                {
                    imie = "";
                    nazwisko = "";
                    pesel = "";
                    ilosc_upomnien = 0;
                    numer = 0;
                    licznik++;
                }
                else if(pom.equals("***") && (licznik%2 != 0)){
                    Nadawca nadawca = new Nadawca(imie,nazwisko,pesel);
                    nadawca.setIlosc_upomnien(ilosc_upomnien);
                    nadawca.set_numer(numer);
                    nadawcy.add(nadawca);
                    licznik++;
                }

                if(pom.startsWith("NUMER")) {
                    pom = pom.replace("NUMER NADAWCY: ", "");
                    numer = Integer.parseInt(pom);
                }
                else if(pom.startsWith("IMIE")){
                    pom = pom.replace("IMIE NADAWCY: ","");
                    imie = pom;
                }
                else if(pom.startsWith("NAZWISKO")){
                    pom = pom.replace("NAZWISKO: ", "");
                    nazwisko = pom;
                }
                else if(pom.startsWith("PESEL")){
                    pom = pom.replace("PESEL: ","");
                    pesel = pom;
                }
                else if(pom.startsWith("ILOSC")){
                    pom = pom.replace("ILOSC UPOMNIEN: ", "");
                    ilosc_upomnien = Integer.parseInt(pom);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void wypisz_nadawcow(){
        for(Nadawca n :nadawcy) {
            System.out.println(n);
            System.out.println();
        }
    }

    public void wczytaj_kontenery(File plik){
        double waga_kontenera = 0;
        double waga_ladunku = 0;
        int numer = 0;
        Nadawca nadawca;

        try {
            BufferedReader czytaj = new BufferedReader(new FileReader(plik));
            String pom;
            String pom_kontenera;
            int licznik = 0;
            int numer_nadawcy = 0;

            while ((pom = czytaj.readLine()) != null) {
                //fragment kodu do czytania kontenerow podstawowych
                if(pom.startsWith("Kontener: Podstawowy")) {
                    while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                        if (pom_kontenera.startsWith("Waga kontenera")) {
                            pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                            waga_kontenera = Double.parseDouble(pom_kontenera);
                        } else if (pom_kontenera.startsWith("Waga ladunku")) {
                            pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                            waga_ladunku = Double.parseDouble(pom_kontenera);
                        } else if (pom_kontenera.startsWith("Numer")) {
                            pom_kontenera = pom_kontenera.replace("Numer: ", "");
                            numer = Integer.parseInt(pom_kontenera);
                        } else if (pom_kontenera.startsWith("|")) {
                            pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                            numer_nadawcy = Integer.parseInt(pom_kontenera);
                        }
                    }
                    for (Nadawca n : nadawcy) {
                        if (n.getNumer() == numer_nadawcy) {
                            Kontener_Podstawowy tmp = new Kontener_Podstawowy(waga_kontenera,waga_ladunku,n);
                            kontenery.add(tmp);
                        }

                    }
                }

                //fragment kodu do czytania kontenerow ciezkich
                if(pom.startsWith("Kontener: Ciezki")){
                    int numer_certyfikatu = 0;
                    while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                        if (pom_kontenera.startsWith("Waga kontenera")) {
                            pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                            waga_kontenera = Double.parseDouble(pom_kontenera);
                        } else if (pom_kontenera.startsWith("Waga ladunku")) {
                            pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                            waga_ladunku = Double.parseDouble(pom_kontenera);
                        } else if(pom_kontenera.startsWith("Numer potrzebnego")){
                            pom_kontenera = pom_kontenera.replace("Numer potrzebnego certyfikatu: ","");
                            numer_certyfikatu = Integer.parseInt(pom_kontenera);
                        }else if (pom_kontenera.startsWith("Numer")) {
                            pom_kontenera = pom_kontenera.replace("Numer: ", "");
                            numer = Integer.parseInt(pom_kontenera);
                        } else if (pom_kontenera.startsWith("|")) {
                            pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                            numer_nadawcy = Integer.parseInt(pom_kontenera);
                        }
                    }
                    for (Nadawca n : nadawcy) {
                        if (n.getNumer() == numer_nadawcy) {
                            Kontener_Ciezki tmp = new Kontener_Ciezki(waga_kontenera,waga_ladunku,n,numer_certyfikatu);
                            kontenery.add(tmp);
                        }

                    }
                }

                //fragment do wczytywania kontenerow chlodniczych
                if(pom.startsWith("Kontener: Chlodniczy")){
                    int numer_certyfikatu = 0;
                    double minimalna_temperatura = 0;
                    while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                        if (pom_kontenera.startsWith("Waga kontenera")) {
                            pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                            waga_kontenera = Double.parseDouble(pom_kontenera);
                        } else if (pom_kontenera.startsWith("Waga ladunku")) {
                            pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                            waga_ladunku = Double.parseDouble(pom_kontenera);
                        } else if(pom_kontenera.startsWith("Numer potrzebnego")){
                            pom_kontenera = pom_kontenera.replace("Numer potrzebnego certyfikatu: ","");
                            numer_certyfikatu = Integer.parseInt(pom_kontenera);
                        }else if (pom_kontenera.startsWith("Numer")) {
                            pom_kontenera = pom_kontenera.replace("Numer: ", "");
                            numer = Integer.parseInt(pom_kontenera);
                        } else if (pom_kontenera.startsWith("|")) {
                            pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                            numer_nadawcy = Integer.parseInt(pom_kontenera);
                        }else if(pom_kontenera.startsWith("Minimalna")){
                            pom_kontenera = pom_kontenera.replace("Minimalna mozliwa temperatura w kontenerze: ","");
                            minimalna_temperatura = Double.parseDouble(pom_kontenera);
                        }
                    }
                    for (Nadawca n : nadawcy) {
                        if (n.getNumer() == numer_nadawcy) {
                            Kontener_chlodniczy tmp = new Kontener_chlodniczy(waga_kontenera,waga_ladunku,n,minimalna_temperatura,numer_certyfikatu);
                            kontenery.add(tmp);
                        }

                    }
                }

                //fragment do wczytywania kontenerow cieklych
                if(pom.startsWith("Kontener: Ciekle")){
                    double pojemnsoc_kontenera = 0;
                    while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                        if (pom_kontenera.startsWith("Waga kontenera")) {
                            pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                            waga_kontenera = Double.parseDouble(pom_kontenera);
                        } else if (pom_kontenera.startsWith("Waga ladunku")) {
                            pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                            waga_ladunku = Double.parseDouble(pom_kontenera);
                        }else if (pom_kontenera.startsWith("Numer")) {
                            pom_kontenera = pom_kontenera.replace("Numer: ", "");
                            numer = Integer.parseInt(pom_kontenera);
                        } else if (pom_kontenera.startsWith("|")) {
                            pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                            numer_nadawcy = Integer.parseInt(pom_kontenera);
                        }else if(pom_kontenera.startsWith("Pojemnosc")){
                            pom_kontenera = pom_kontenera.replace("Pojemnosc kontenera: ","");
                            pojemnsoc_kontenera = Double.parseDouble(pom_kontenera);
                        }
                    }
                    for (Nadawca n : nadawcy) {
                        if (n.getNumer() == numer_nadawcy) {
                            Kontener_Ciekle tmp = new Kontener_Ciekle(waga_kontenera,waga_ladunku,n,pojemnsoc_kontenera);
                            kontenery.add(tmp);
                        }

                    }
                }

                //fragment do wczytywania kontenerow wybuchowych
                if(pom.startsWith("Kontener: Wybuchowy")){
                    int numer_certyfikatu = 0;
                    int stopien_zagrozenia = 0;
                    while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                        if (pom_kontenera.startsWith("Waga kontenera")) {
                            pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                            waga_kontenera = Double.parseDouble(pom_kontenera);
                        } else if (pom_kontenera.startsWith("Waga ladunku")) {
                            pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                            waga_ladunku = Double.parseDouble(pom_kontenera);
                        } else if(pom_kontenera.startsWith("Numer potrzebnego")){
                            pom_kontenera = pom_kontenera.replace("Numer potrzebnego certyfikatu: ","");
                            numer_certyfikatu = Integer.parseInt(pom_kontenera);
                        }else if (pom_kontenera.startsWith("Numer")) {
                            pom_kontenera = pom_kontenera.replace("Numer: ", "");
                            numer = Integer.parseInt(pom_kontenera);
                        } else if (pom_kontenera.startsWith("|")) {
                            pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                            numer_nadawcy = Integer.parseInt(pom_kontenera);
                        }else if(pom_kontenera.startsWith("Stopien")){
                            pom_kontenera = pom_kontenera.replace("Stopien zagrozenia wybuchem w skali od 1-10: ","");
                            stopien_zagrozenia = Integer.parseInt(pom_kontenera);
                        }
                    }
                    for (Nadawca n : nadawcy) {
                        if (n.getNumer() == numer_nadawcy) {
                            Kontener_Wybuchowe tmp = new Kontener_Wybuchowe(waga_kontenera,waga_ladunku,n,numer_certyfikatu,stopien_zagrozenia);
                            kontenery.add(tmp);
                        }

                    }
                }

                //fragment kodu do czytania kontenerow toksycznych sypkich
                if(pom.startsWith("Kontener: ToksycznySypki")){
                    int numer_certyfikatu = 0;
                    boolean czy_radioaktywne = false;
                    while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                        if (pom_kontenera.startsWith("Waga kontenera")) {
                            pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                            waga_kontenera = Double.parseDouble(pom_kontenera);
                        } else if (pom_kontenera.startsWith("Waga ladunku")) {
                            pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                            waga_ladunku = Double.parseDouble(pom_kontenera);
                        } else if(pom_kontenera.startsWith("Numer potrzebnego")){
                            pom_kontenera = pom_kontenera.replace("Numer potrzebnego certyfikatu: ","");
                            numer_certyfikatu = Integer.parseInt(pom_kontenera);
                        }else if (pom_kontenera.startsWith("Numer")) {
                            pom_kontenera = pom_kontenera.replace("Numer: ", "");
                            numer = Integer.parseInt(pom_kontenera);
                        } else if (pom_kontenera.startsWith("|")) {
                            pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                            numer_nadawcy = Integer.parseInt(pom_kontenera);
                        }else if(pom_kontenera.startsWith("Czy radioaktywne")){
                            pom_kontenera = pom_kontenera.replace("Czy radioaktywne ","");
                            czy_radioaktywne = pom_kontenera.equals("tak");
                        }
                    }
                    for (Nadawca n : nadawcy) {
                        if (n.getNumer() == numer_nadawcy) {
                            Kontener_Toksyczne_Sypkie tmp = new Kontener_Toksyczne_Sypkie(waga_kontenera,waga_ladunku,czy_radioaktywne,n,numer_certyfikatu);
                            kontenery.add(tmp);
                        }

                    }
                }

                //fragment kodu do czytania kontenerow toksycznych cieklych
                if(pom.startsWith("Kontener: ToksycznyCiekly")){
                    int numer_certyfikatu = 0;
                    double pojemnosc = 0;
                    while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                        if (pom_kontenera.startsWith("Waga kontenera")) {
                            pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                            waga_kontenera = Double.parseDouble(pom_kontenera);
                        } else if (pom_kontenera.startsWith("Waga ladunku")) {
                            pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                            waga_ladunku = Double.parseDouble(pom_kontenera);
                        } else if(pom_kontenera.startsWith("Numer potrzebnego")){
                            pom_kontenera = pom_kontenera.replace("Numer potrzebnego certyfikatu: ","");
                            numer_certyfikatu = Integer.parseInt(pom_kontenera);
                        }else if (pom_kontenera.startsWith("Numer")) {
                            pom_kontenera = pom_kontenera.replace("Numer: ", "");
                            numer = Integer.parseInt(pom_kontenera);
                        } else if (pom_kontenera.startsWith("|")) {
                            pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                            numer_nadawcy = Integer.parseInt(pom_kontenera);
                        }else if(pom_kontenera.startsWith("Pojemnosc")){
                            pom_kontenera = pom_kontenera.replace("Pojemnosc kontenera: ","");
                            pojemnosc = Double.parseDouble(pom_kontenera);
                        }
                    }
                    for (Nadawca n : nadawcy) {
                        if (n.getNumer() == numer_nadawcy) {
                            Kontener_Toksyczny_Ciekly tmp = new Kontener_Toksyczny_Ciekly(waga_kontenera,waga_ladunku,n,pojemnosc,numer_certyfikatu);
                            kontenery.add(tmp);
                        }

                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void wczytaj_statki(File plik){
        String nazwa_statku = "";
        int limit_niebezpiecznych = 0;
        int ilosc_niebezpiecznych = 0;
        int limit_ciezkich = 0;
        int ilosc_ciezkich = 0;
        int limit_sieciowych = 0;
        int ilosc_sieciowych = 0;
        int limit_wszystkich = 0;
        int ilosc_wszystkich = 0;
        double maksymalna_waga = 0;
        double obecna_waga = 0;
        int numer_statku = 0;
        String port_macierzysty = "";
        String lokalizacja_zrodlowa = "";
        String lokalizacja_docelowa = "";

        ArrayList<Kontener_Podstawowy> lista_pom = new ArrayList<>();
        try {
            BufferedReader czytaj = new BufferedReader(new FileReader(plik));
            String pom;
            String pom2;
            while ((pom = czytaj.readLine()) != null) {
                if (pom.equals("/*")) {

                    nazwa_statku = "";
                    limit_niebezpiecznych = 0;
                    ilosc_niebezpiecznych = 0;
                    limit_ciezkich = 0;
                    ilosc_ciezkich = 0;
                    limit_sieciowych = 0;
                    ilosc_sieciowych = 0;
                    limit_wszystkich = 0;
                    ilosc_wszystkich = 0;
                    maksymalna_waga = 0;
                    obecna_waga = 0;
                    numer_statku = 0;
                    port_macierzysty = "";
                    lokalizacja_zrodlowa = "";
                    lokalizacja_docelowa = "";
                } else if (pom.equals("*/")) {
                    Statek tmp = new Statek(nazwa_statku, limit_ciezkich, limit_sieciowych, limit_wszystkich, maksymalna_waga, limit_niebezpiecznych, port_macierzysty, lokalizacja_zrodlowa, lokalizacja_docelowa);
                    tmp.set_numer_statku(numer_statku);
                    tmp.setIlosc_niebezpiecznych(ilosc_niebezpiecznych);
                    tmp.setIlosc_ciezkich(ilosc_ciezkich);
                    tmp.setIlosc_sieciowych(ilosc_sieciowych);
                    tmp.setIlosc_wszystkich(ilosc_wszystkich);
                    tmp.setObecna_waga(obecna_waga);
                    statki.add(tmp);
                } else if (pom.startsWith("Statek numer")) {
                    pom = pom.replace("Statek numer: ", "");
                    numer_statku = Integer.parseInt(pom);
                } else if (pom.startsWith("Nazwa statku")) {
                    pom = pom.replace("Nazwa statku: ", "");
                    nazwa_statku = pom;
                } else if (pom.startsWith("Limit niebezpiecznych")) {
                    pom = pom.replace("Limit niebezpiecznych: ", "");
                    limit_niebezpiecznych = Integer.parseInt(pom);
                } else if (pom.startsWith("Ilosc niebezpiecznych")) {
                    pom = pom.replace("Ilosc niebezpiecznych: ", "");
                    ilosc_niebezpiecznych = Integer.parseInt(pom);
                } else if (pom.startsWith("Limit ciezkich")) {
                    pom = pom.replace("Limit ciezkich: ", "");
                    limit_ciezkich = Integer.parseInt(pom);
                } else if (pom.startsWith("Ilosc ciezkich")) {
                    pom = pom.replace("Ilosc ciezkich: ", "");
                    ilosc_ciezkich = Integer.parseInt(pom);
                } else if (pom.startsWith("Limit sieciowych: ")) {
                    pom = pom.replace("Limit sieciowych: ", "");
                    limit_sieciowych = Integer.parseInt(pom);
                } else if (pom.startsWith("Ilosc sieciowych")) {
                    pom = pom.replace("Ilosc sieciowych: ", "");
                    ilosc_sieciowych = Integer.parseInt(pom);
                } else if (pom.startsWith("Limit wszystkich")) {
                    pom = pom.replace("Limit wszystkich: ", "");
                    limit_wszystkich = Integer.parseInt(pom);
                } else if (pom.startsWith("Ilosc wszystkich")) {
                    pom = pom.replace("Ilosc wszystkich: ", "");
                    ilosc_wszystkich = Integer.parseInt(pom);
                } else if (pom.startsWith("Maksymalna")) {
                    pom = pom.replace("Maksymalna waga: ", "");
                    maksymalna_waga = Double.parseDouble(pom);
                } else if (pom.startsWith("Obecna")) {
                    pom = pom.replace("Obecna waga: ", "");
                    obecna_waga = Double.parseDouble(pom);
                } else if (pom.startsWith("Port macierzysty")) {
                    pom = pom.replace("Port macierzysty: ", "");
                    port_macierzysty = pom;
                } else if (pom.startsWith("Lokalizacja zrodlowa")) {
                    pom = pom.replace("Lokalizacja zrodlowa: ", "");
                    lokalizacja_zrodlowa = pom;
                } else if (pom.startsWith("Lokalizacja docelowa")) {
                    pom = pom.replace("Lokalizacja docelowa: ", "");
                    lokalizacja_docelowa = pom;
                } else if (pom.equals("/")) {
                    //while do wczytania kontenerow na danycm statku
                    while (!(pom2 = czytaj.readLine()).equals("///")) {
                        double waga_kontenera = 0;
                        double waga_ladunku = 0;
                        int numer = 0;
                        Nadawca nadawca;
                        String pom_kontenera;
                        int numer_nadawcy = 0;

                        //fragment kodu do czytania kontenerow podstawowych
                        if (pom2.startsWith("Kontener: Podstawowy")) {
                            while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                                if (pom_kontenera.startsWith("Waga kontenera")) {
                                    pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                                    waga_kontenera = Double.parseDouble(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Waga ladunku")) {
                                    pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                                    waga_ladunku = Double.parseDouble(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Numer")) {
                                    pom_kontenera = pom_kontenera.replace("Numer: ", "");
                                    numer = Integer.parseInt(pom_kontenera);
                                } else if (pom_kontenera.startsWith("|")) {
                                    pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                                    numer_nadawcy = Integer.parseInt(pom_kontenera);
                                }
                            }
                            for (Nadawca n : nadawcy) {
                                if (n.getNumer() == numer_nadawcy) {
                                    Kontener_Podstawowy tmp = new Kontener_Podstawowy(waga_kontenera, waga_ladunku, n);
                                    lista_pom.add(tmp);
                                }

                            }
                        }

                        //fragment kodu do czytania kontenerow ciezkich
                        if (pom2.startsWith("Kontener: Ciezki")) {
                            int numer_certyfikatu = 0;
                            while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                                if (pom_kontenera.startsWith("Waga kontenera")) {
                                    pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                                    waga_kontenera = Double.parseDouble(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Waga ladunku")) {
                                    pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                                    waga_ladunku = Double.parseDouble(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Numer potrzebnego")) {
                                    pom_kontenera = pom_kontenera.replace("Numer potrzebnego certyfikatu: ", "");
                                    numer_certyfikatu = Integer.parseInt(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Numer")) {
                                    pom_kontenera = pom_kontenera.replace("Numer: ", "");
                                    numer = Integer.parseInt(pom_kontenera);
                                } else if (pom_kontenera.startsWith("|")) {
                                    pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                                    numer_nadawcy = Integer.parseInt(pom_kontenera);
                                }
                            }
                            for (Nadawca n : nadawcy) {
                                if (n.getNumer() == numer_nadawcy) {
                                    Kontener_Ciezki tmp = new Kontener_Ciezki(waga_kontenera, waga_ladunku, n, numer_certyfikatu);
                                    lista_pom.add(tmp);
                                }

                            }
                        }

                        //fragment do wczytywania kontenerow chlodniczych
                        if (pom2.startsWith("Kontener: Chlodniczy")) {
                            int numer_certyfikatu = 0;
                            double minimalna_temperatura = 0;
                            while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                                if (pom_kontenera.startsWith("Waga kontenera")) {
                                    pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                                    waga_kontenera = Double.parseDouble(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Waga ladunku")) {
                                    pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                                    waga_ladunku = Double.parseDouble(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Numer potrzebnego")) {
                                    pom_kontenera = pom_kontenera.replace("Numer potrzebnego certyfikatu: ", "");
                                    numer_certyfikatu = Integer.parseInt(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Numer")) {
                                    pom_kontenera = pom_kontenera.replace("Numer: ", "");
                                    numer = Integer.parseInt(pom_kontenera);
                                } else if (pom_kontenera.startsWith("|")) {
                                    pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                                    numer_nadawcy = Integer.parseInt(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Minimalna")) {
                                    pom_kontenera = pom_kontenera.replace("Minimalna mozliwa temperatura w kontenerze: ", "");
                                    minimalna_temperatura = Double.parseDouble(pom_kontenera);
                                }
                            }
                            for (Nadawca n : nadawcy) {
                                if (n.getNumer() == numer_nadawcy) {
                                    Kontener_chlodniczy tmp = new Kontener_chlodniczy(waga_kontenera, waga_ladunku, n, minimalna_temperatura, numer_certyfikatu);
                                    lista_pom.add(tmp);
                                }

                            }
                        }

                        //fragment do wczytywania kontenerow cieklych
                        if (pom2.startsWith("Kontener: Ciekle")) {
                            double pojemnsoc_kontenera = 0;
                            while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                                if (pom_kontenera.startsWith("Waga kontenera")) {
                                    pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                                    waga_kontenera = Double.parseDouble(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Waga ladunku")) {
                                    pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                                    waga_ladunku = Double.parseDouble(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Numer")) {
                                    pom_kontenera = pom_kontenera.replace("Numer: ", "");
                                    numer = Integer.parseInt(pom_kontenera);
                                } else if (pom_kontenera.startsWith("|")) {
                                    pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                                    numer_nadawcy = Integer.parseInt(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Pojemnosc")) {
                                    pom_kontenera = pom_kontenera.replace("Pojemnosc kontenera: ", "");
                                    pojemnsoc_kontenera = Double.parseDouble(pom_kontenera);
                                }
                            }
                            for (Nadawca n : nadawcy) {
                                if (n.getNumer() == numer_nadawcy) {
                                    Kontener_Ciekle tmp = new Kontener_Ciekle(waga_kontenera, waga_ladunku, n, pojemnsoc_kontenera);
                                    lista_pom.add(tmp);
                                }

                            }
                        }

                        //fragment do wczytywania kontenerow wybuchowych
                        if (pom2.startsWith("Kontener: Wybuchowy")) {
                            int numer_certyfikatu = 0;
                            int stopien_zagrozenia = 0;
                            while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                                if (pom_kontenera.startsWith("Waga kontenera")) {
                                    pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                                    waga_kontenera = Double.parseDouble(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Waga ladunku")) {
                                    pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                                    waga_ladunku = Double.parseDouble(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Numer potrzebnego")) {
                                    pom_kontenera = pom_kontenera.replace("Numer potrzebnego certyfikatu: ", "");
                                    numer_certyfikatu = Integer.parseInt(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Numer")) {
                                    pom_kontenera = pom_kontenera.replace("Numer: ", "");
                                    numer = Integer.parseInt(pom_kontenera);
                                } else if (pom_kontenera.startsWith("|")) {
                                    pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                                    numer_nadawcy = Integer.parseInt(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Stopien")) {
                                    pom_kontenera = pom_kontenera.replace("Stopien zagrozenia wybuchem w skali od 1-10: ", "");
                                    stopien_zagrozenia = Integer.parseInt(pom_kontenera);
                                }
                            }
                            for (Nadawca n : nadawcy) {
                                if (n.getNumer() == numer_nadawcy) {
                                    Kontener_Wybuchowe tmp = new Kontener_Wybuchowe(waga_kontenera, waga_ladunku, n, numer_certyfikatu, stopien_zagrozenia);
                                    lista_pom.add(tmp);
                                }

                            }
                        }

                        //fragment kodu do czytania kontenerow toksycznych sypkich
                        if (pom2.startsWith("Kontener: ToksycznySypki")) {
                            int numer_certyfikatu = 0;
                            boolean czy_radioaktywne = false;
                            while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                                if (pom_kontenera.startsWith("Waga kontenera")) {
                                    pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                                    waga_kontenera = Double.parseDouble(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Waga ladunku")) {
                                    pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                                    waga_ladunku = Double.parseDouble(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Numer potrzebnego")) {
                                    pom_kontenera = pom_kontenera.replace("Numer potrzebnego certyfikatu: ", "");
                                    numer_certyfikatu = Integer.parseInt(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Numer")) {
                                    pom_kontenera = pom_kontenera.replace("Numer: ", "");
                                    numer = Integer.parseInt(pom_kontenera);
                                } else if (pom_kontenera.startsWith("|")) {
                                    pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                                    numer_nadawcy = Integer.parseInt(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Czy radioaktywne")) {
                                    pom_kontenera = pom_kontenera.replace("Czy radioaktywne ", "");
                                    czy_radioaktywne = pom_kontenera.equals("tak");
                                }
                            }
                            for (Nadawca n : nadawcy) {
                                if (n.getNumer() == numer_nadawcy) {
                                    Kontener_Toksyczne_Sypkie tmp = new Kontener_Toksyczne_Sypkie(waga_kontenera, waga_ladunku, czy_radioaktywne, n, numer_certyfikatu);
                                    lista_pom.add(tmp);
                                }

                            }
                        }

                        //fragment kodu do czytania kontenerow toksycznych cieklych
                        if (pom2.startsWith("Kontener: ToksycznyCiekly")) {
                            int numer_certyfikatu = 0;
                            double pojemnosc = 0;
                            while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                                if (pom_kontenera.startsWith("Waga kontenera")) {
                                    pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                                    waga_kontenera = Double.parseDouble(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Waga ladunku")) {
                                    pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                                    waga_ladunku = Double.parseDouble(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Numer potrzebnego")) {
                                    pom_kontenera = pom_kontenera.replace("Numer potrzebnego certyfikatu: ", "");
                                    numer_certyfikatu = Integer.parseInt(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Numer")) {
                                    pom_kontenera = pom_kontenera.replace("Numer: ", "");
                                    numer = Integer.parseInt(pom_kontenera);
                                } else if (pom_kontenera.startsWith("|")) {
                                    pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                                    numer_nadawcy = Integer.parseInt(pom_kontenera);
                                } else if (pom_kontenera.startsWith("Pojemnosc")) {
                                    pom_kontenera = pom_kontenera.replace("Pojemnosc kontenera: ", "");
                                    pojemnosc = Double.parseDouble(pom_kontenera);
                                }
                            }
                            for (Nadawca n : nadawcy) {
                                if (n.getNumer() == numer_nadawcy) {
                                    Kontener_Toksyczny_Ciekly tmp = new Kontener_Toksyczny_Ciekly(waga_kontenera, waga_ladunku, n, pojemnosc, numer_certyfikatu);
                                    lista_pom.add(tmp);
                                }

                            }
                        }
                    }
                }
                else if(pom.startsWith("Kontenery ze statku numer")){
                    pom = pom.replace("Kontenery ze statku numer ","");
                    int dodawanie_do = Integer.parseInt(pom);
                    //wrzucenie kontenerow na statek przed rozpoczeciem tworzenia nastepnego
                    for(Statek s : statki){
                        if(s.get_numer_statku() == dodawanie_do){
                            s.getKontenery_na_statku().addAll(lista_pom);
                            lista_pom.clear();
                        }
                    }
                }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void wczytaj_date(File plik){
        String data;
        try {
            BufferedReader czytaj = new BufferedReader(new FileReader(plik));
            data = czytaj.readLine();
            LocalDate data_wejsciowa = LocalDate.parse(data);
            magazyn.getCzas().ustawienie_poczatkowe(data_wejsciowa);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void wczytaj_magazyn(File plik){
        try {
            BufferedReader czytaj = new BufferedReader(new FileReader(plik));
            String pom;
            String pom_kontenera;
            double waga_kontenera = 0;
            double waga_ladunku = 0;
            int numer = 0;
            Nadawca nadawca;
            int licznik = 0;
            int numer_nadawcy = 0;
            String pom_data;
            while((pom = czytaj.readLine()) != null){
                {
                    //fragment kodu do czytania kontenerow podstawowych
                    if(pom.startsWith("Kontener: Podstawowy")) {
                        while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                            if (pom_kontenera.startsWith("Waga kontenera")) {
                                pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                                waga_kontenera = Double.parseDouble(pom_kontenera);
                            } else if (pom_kontenera.startsWith("Waga ladunku")) {
                                pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                                waga_ladunku = Double.parseDouble(pom_kontenera);
                            } else if (pom_kontenera.startsWith("Numer")) {
                                pom_kontenera = pom_kontenera.replace("Numer: ", "");
                                numer = Integer.parseInt(pom_kontenera);
                            } else if (pom_kontenera.startsWith("|")) {
                                pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                                numer_nadawcy = Integer.parseInt(pom_kontenera);
                            }
                        }
                        for (Nadawca n : nadawcy) {
                            if (n.getNumer() == numer_nadawcy) {
                                Kontener_Podstawowy tmp = new Kontener_Podstawowy(waga_kontenera,waga_ladunku,n);
                                pom_data = czytaj.readLine();
                                LocalDate data = LocalDate.parse(pom_data);
                                magazyn.getKontenery_magazyn().put(tmp,data);
                            }

                        }
                    }

                    //fragment kodu do czytania kontenerow ciezkich
                    if(pom.startsWith("Kontener: Ciezki")){
                        int numer_certyfikatu = 0;
                        while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                            if (pom_kontenera.startsWith("Waga kontenera")) {
                                pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                                waga_kontenera = Double.parseDouble(pom_kontenera);
                            } else if (pom_kontenera.startsWith("Waga ladunku")) {
                                pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                                waga_ladunku = Double.parseDouble(pom_kontenera);
                            } else if(pom_kontenera.startsWith("Numer potrzebnego")){
                                pom_kontenera = pom_kontenera.replace("Numer potrzebnego certyfikatu: ","");
                                numer_certyfikatu = Integer.parseInt(pom_kontenera);
                            }else if (pom_kontenera.startsWith("Numer")) {
                                pom_kontenera = pom_kontenera.replace("Numer: ", "");
                                numer = Integer.parseInt(pom_kontenera);
                            } else if (pom_kontenera.startsWith("|")) {
                                pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                                numer_nadawcy = Integer.parseInt(pom_kontenera);
                            }
                        }
                        for (Nadawca n : nadawcy) {
                            if (n.getNumer() == numer_nadawcy) {
                                Kontener_Ciezki tmp = new Kontener_Ciezki(waga_kontenera,waga_ladunku,n,numer_certyfikatu);
                                pom_data = czytaj.readLine();
                                LocalDate data = LocalDate.parse(pom_data);
                                magazyn.getKontenery_magazyn().put(tmp,data);
                            }

                        }
                    }

                    //fragment do wczytywania kontenerow chlodniczych
                    if(pom.startsWith("Kontener: Chlodniczy")){
                        int numer_certyfikatu = 0;
                        double minimalna_temperatura = 0;
                        while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                            if (pom_kontenera.startsWith("Waga kontenera")) {
                                pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                                waga_kontenera = Double.parseDouble(pom_kontenera);
                            } else if (pom_kontenera.startsWith("Waga ladunku")) {
                                pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                                waga_ladunku = Double.parseDouble(pom_kontenera);
                            } else if(pom_kontenera.startsWith("Numer potrzebnego")){
                                pom_kontenera = pom_kontenera.replace("Numer potrzebnego certyfikatu: ","");
                                numer_certyfikatu = Integer.parseInt(pom_kontenera);
                            }else if (pom_kontenera.startsWith("Numer")) {
                                pom_kontenera = pom_kontenera.replace("Numer: ", "");
                                numer = Integer.parseInt(pom_kontenera);
                            } else if (pom_kontenera.startsWith("|")) {
                                pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                                numer_nadawcy = Integer.parseInt(pom_kontenera);
                            }else if(pom_kontenera.startsWith("Minimalna")){
                                pom_kontenera = pom_kontenera.replace("Minimalna mozliwa temperatura w kontenerze: ","");
                                minimalna_temperatura = Double.parseDouble(pom_kontenera);
                            }
                        }
                        for (Nadawca n : nadawcy) {
                            if (n.getNumer() == numer_nadawcy) {
                                Kontener_chlodniczy tmp = new Kontener_chlodniczy(waga_kontenera,waga_ladunku,n,minimalna_temperatura,numer_certyfikatu);
                                pom_data = czytaj.readLine();
                                LocalDate data = LocalDate.parse(pom_data);
                                magazyn.getKontenery_magazyn().put(tmp,data);
                            }

                        }
                    }

                    //fragment do wczytywania kontenerow cieklych
                    if(pom.startsWith("Kontener: Ciekle")){
                        double pojemnsoc_kontenera = 0;
                        while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                            if (pom_kontenera.startsWith("Waga kontenera")) {
                                pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                                waga_kontenera = Double.parseDouble(pom_kontenera);
                            } else if (pom_kontenera.startsWith("Waga ladunku")) {
                                pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                                waga_ladunku = Double.parseDouble(pom_kontenera);
                            }else if (pom_kontenera.startsWith("Numer")) {
                                pom_kontenera = pom_kontenera.replace("Numer: ", "");
                                numer = Integer.parseInt(pom_kontenera);
                            } else if (pom_kontenera.startsWith("|")) {
                                pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                                numer_nadawcy = Integer.parseInt(pom_kontenera);
                            }else if(pom_kontenera.startsWith("Pojemnosc")){
                                pom_kontenera = pom_kontenera.replace("Pojemnosc kontenera: ","");
                                pojemnsoc_kontenera = Double.parseDouble(pom_kontenera);
                            }
                        }
                        for (Nadawca n : nadawcy) {
                            if (n.getNumer() == numer_nadawcy) {
                                Kontener_Ciekle tmp = new Kontener_Ciekle(waga_kontenera,waga_ladunku,n,pojemnsoc_kontenera);
                                pom_data = czytaj.readLine();
                                LocalDate data = LocalDate.parse(pom_data);
                                magazyn.getKontenery_magazyn().put(tmp,data);
                            }

                        }
                    }

                    //fragment do wczytywania kontenerow wybuchowych
                    if(pom.startsWith("Kontener: Wybuchowy")){
                        int numer_certyfikatu = 0;
                        int stopien_zagrozenia = 0;
                        while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                            if (pom_kontenera.startsWith("Waga kontenera")) {
                                pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                                waga_kontenera = Double.parseDouble(pom_kontenera);
                            } else if (pom_kontenera.startsWith("Waga ladunku")) {
                                pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                                waga_ladunku = Double.parseDouble(pom_kontenera);
                            } else if(pom_kontenera.startsWith("Numer potrzebnego")){
                                pom_kontenera = pom_kontenera.replace("Numer potrzebnego certyfikatu: ","");
                                numer_certyfikatu = Integer.parseInt(pom_kontenera);
                            }else if (pom_kontenera.startsWith("Numer")) {
                                pom_kontenera = pom_kontenera.replace("Numer: ", "");
                                numer = Integer.parseInt(pom_kontenera);
                            } else if (pom_kontenera.startsWith("|")) {
                                pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                                numer_nadawcy = Integer.parseInt(pom_kontenera);
                            }else if(pom_kontenera.startsWith("Stopien")){
                                pom_kontenera = pom_kontenera.replace("Stopien zagrozenia wybuchem w skali od 1-10: ","");
                                stopien_zagrozenia = Integer.parseInt(pom_kontenera);
                            }
                        }
                        for (Nadawca n : nadawcy) {
                            if (n.getNumer() == numer_nadawcy) {
                                Kontener_Wybuchowe tmp = new Kontener_Wybuchowe(waga_kontenera,waga_ladunku,n,numer_certyfikatu,stopien_zagrozenia);
                                pom_data = czytaj.readLine();
                                LocalDate data = LocalDate.parse(pom_data);
                                magazyn.getKontenery_magazyn().put(tmp,data);
                            }

                        }
                    }

                    //fragment kodu do czytania kontenerow toksycznych sypkich
                    if(pom.startsWith("Kontener: ToksycznySypki")){
                        int numer_certyfikatu = 0;
                        boolean czy_radioaktywne = false;
                        while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                            if (pom_kontenera.startsWith("Waga kontenera")) {
                                pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                                waga_kontenera = Double.parseDouble(pom_kontenera);
                            } else if (pom_kontenera.startsWith("Waga ladunku")) {
                                pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                                waga_ladunku = Double.parseDouble(pom_kontenera);
                            } else if(pom_kontenera.startsWith("Numer potrzebnego")){
                                pom_kontenera = pom_kontenera.replace("Numer potrzebnego certyfikatu: ","");
                                numer_certyfikatu = Integer.parseInt(pom_kontenera);
                            }else if (pom_kontenera.startsWith("Numer")) {
                                pom_kontenera = pom_kontenera.replace("Numer: ", "");
                                numer = Integer.parseInt(pom_kontenera);
                            } else if (pom_kontenera.startsWith("|")) {
                                pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                                numer_nadawcy = Integer.parseInt(pom_kontenera);
                            }else if(pom_kontenera.startsWith("Czy radioaktywne")){
                                pom_kontenera = pom_kontenera.replace("Czy radioaktywne ","");
                                czy_radioaktywne = pom_kontenera.equals("tak");
                            }
                        }
                        for (Nadawca n : nadawcy) {
                            if (n.getNumer() == numer_nadawcy) {
                                Kontener_Toksyczne_Sypkie tmp = new Kontener_Toksyczne_Sypkie(waga_kontenera,waga_ladunku,czy_radioaktywne,n,numer_certyfikatu);
                                pom_data = czytaj.readLine();
                                LocalDate data = LocalDate.parse(pom_data);
                                magazyn.getKontenery_magazyn().put(tmp,data);
                            }

                        }
                    }

                    //fragment kodu do czytania kontenerow toksycznych cieklych
                    if(pom.startsWith("Kontener: ToksycznyCiekly")){
                        int numer_certyfikatu = 0;
                        double pojemnosc = 0;
                        while (!(pom_kontenera = czytaj.readLine()).equals("***")) {
                            if (pom_kontenera.startsWith("Waga kontenera")) {
                                pom_kontenera = pom_kontenera.replace("Waga kontenera: ", "");
                                waga_kontenera = Double.parseDouble(pom_kontenera);
                            } else if (pom_kontenera.startsWith("Waga ladunku")) {
                                pom_kontenera = pom_kontenera.replace("Waga ladunku: ", "");
                                waga_ladunku = Double.parseDouble(pom_kontenera);
                            } else if(pom_kontenera.startsWith("Numer potrzebnego")){
                                pom_kontenera = pom_kontenera.replace("Numer potrzebnego certyfikatu: ","");
                                numer_certyfikatu = Integer.parseInt(pom_kontenera);
                            }else if (pom_kontenera.startsWith("Numer")) {
                                pom_kontenera = pom_kontenera.replace("Numer: ", "");
                                numer = Integer.parseInt(pom_kontenera);
                            } else if (pom_kontenera.startsWith("|")) {
                                pom_kontenera = pom_kontenera.replace("|Nadawca NUMER NADAWCY: ", "");
                                numer_nadawcy = Integer.parseInt(pom_kontenera);
                            }else if(pom_kontenera.startsWith("Pojemnosc")){
                                pom_kontenera = pom_kontenera.replace("Pojemnosc kontenera: ","");
                                pojemnosc = Double.parseDouble(pom_kontenera);
                            }
                        }
                        for (Nadawca n : nadawcy) {
                            if (n.getNumer() == numer_nadawcy) {
                                Kontener_Toksyczny_Ciekly tmp = new Kontener_Toksyczny_Ciekly(waga_kontenera,waga_ladunku,n,pojemnosc,numer_certyfikatu);
                                pom_data = czytaj.readLine();
                                LocalDate data = LocalDate.parse(pom_data);
                                magazyn.getKontenery_magazyn().put(tmp,data);
                            }

                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
