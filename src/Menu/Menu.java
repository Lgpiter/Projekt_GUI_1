package Menu;

import Kontenery.*;
import Statek.*;

import java.io.*;
import java.util.Collections;
import java.util.Scanner;

public class Menu  {
    private final Port_Glowny port;
    Scanner scan = new Scanner(System.in);


    public Menu(Port_Glowny port){
        this.port = port;
        int wybor; // sluzy do poruszania sie po menu

        wypisz_menu(); // wypisuje poczatkowy stan menu
        wybor = scan.nextInt();
        while(wybor != 0) {
            switch (wybor) {
                case 1 -> menu_Kontenerow();
                case 2 -> menu_Statkow();
                case 3 -> menu_Rozladowania();
                case 4 -> menu_Zaladowania();
                case 5 -> port.wypisz_kontenery();
                case 6 -> wypisywanie_statkow();
                case 7 ->  usun_Statek();
                case 8 -> wypisz_kontenery_na_statku();
                case 9 -> zapisz_do_pliku();
                case 10 -> {
                    //petla sluzaca do "czyszczenia" konsoli
                    for (int i = 0; i < 100; i++)
                        System.out.println();
                }
                case 11 -> port.getMagazyn().pokaz_stan_magazynu();
                case 12 -> usun_kontener();
                case 13 -> port.wypisz_nadawcow();
                default -> System.out.println("Podales liczbe, ktorej nie ma w menu. Sprobuj jeszcze raz!");
            }


            wypisz_menu();
            wybor = scan.nextInt();
        }

        port.getMagazyn().zatrzymaj_magazyn();
        port.getWagon().zatrzymaj_wagon();

        zapisz_do_pliku();
    }

    public void wypisz_menu(){
        System.out.println("WITAMY W PROGRAMIE OBSLUGUJACYM PORT STATKOW");
        System.out.println("W przypadku checi zakonczenie dzialania wpisz 0");
        System.out.println("1.Stworz kontener");
        System.out.println("2.Stworz Statek");
        System.out.println("3.Rozladuj kontener");
        System.out.println("4.Zaladuj kontener");
        System.out.println("5.Pokaz kontenery w porcie");
        System.out.println("6.Pokaz statki w porcie");
        System.out.println("7.Wypusc statek z portu");
        System.out.println("8.Wypisz kontenery na danym statku");
        System.out.println("9.Zapisz do pliku");
        System.out.println("10.Wyczysz konsole");
        System.out.println("11.Pokaz stan magazynu");
        System.out.println("12.Usun kontener z magazynu");
        System.out.println("13.Wypisz nadawcow");
    }

    public void menu_Kontenerow(){
        int wybor;
        System.out.println("""
                Wybrales opcje Stworz kontener
                Jesli chcesz cofnac nacisnij klawisz 9
                Jesli chcesz zakonczyc dzialanie programu wpisz 0
                Wybierz typ kontenera do stworzenia:
                1.Podstawowy
                2.Ciezki
                3.Chlodniczy
                4.Ciekly
                5.Wybuchowy
                6.Toksyczny Sypki
                7.Toksyczny Ciekly"""
        );
        wybor = scan.nextInt();

        switch (wybor){
            case 9 -> wypisz_menu();
            case 1,2,3,4,5,6,7 -> stworzKontener(wybor);
            case 0 -> System.exit(0);
        }

    }

    public void stworzKontener(int wybor){
        double waga_kontenera,waga_ladunku, nowy_nadawca;
        String imie,nazwisko,pesel;
        Nadawca nadawca = null;
        System.out.println("Waga kontenera ->");
        waga_kontenera = scan.nextDouble();
        System.out.println("Waga ladunku ->");
        waga_ladunku = scan.nextDouble();
        scan.nextLine();


        System.out.println("Czy jestes nowym nadawca?");
        System.out.println("Jesli tak wpisz 1 w przeciwnym przypadku wpisz 0 i wybierz swoj numer z listy");
        nowy_nadawca = scan.nextInt();


        if (nowy_nadawca == 1) {
            System.out.println("Imie nadawcy ");
            scan.nextLine();
            imie = scan.nextLine();

            System.out.println("Nazwisko nadawcy ");
            nazwisko = scan.nextLine();

            System.out.println("Pesel nadawcy ");
            pesel = scan.nextLine();

            nadawca = new Nadawca(imie, nazwisko, pesel);
            port.getNadawcy().add(nadawca);
        }
        else{
            System.out.println("Oto lista nadawcow ");
            for(Nadawca n : port.getNadawcy())
                System.out.println(n);
            System.out.println("Wpisz swoj numer");
            int numer = scan.nextInt();
            for(Nadawca n : port.getNadawcy())
                if(n.getNumer() == numer)
                    nadawca = n;
        }

        switch (wybor){
            case 1 -> {
                Kontener_Podstawowy nowy = new Kontener_Podstawowy(waga_kontenera,waga_ladunku,nadawca);
                port.getKontenery().add(nowy);
            }
            case 2 ->{
                int numer_certyfikatu;
                System.out.println("Numer potrzebnego certyfikatu ->");
                numer_certyfikatu = scan.nextInt();
                Kontener_Ciezki nowy = new Kontener_Ciezki(waga_kontenera,waga_ladunku,nadawca,numer_certyfikatu);
                port.getKontenery().add(nowy);
            }
            case 3 ->{
                double temperatura_minimalna;
                int numer_certyfikatu;
                System.out.println("Numer potrzebnego certyfikatu ->");
                numer_certyfikatu = scan.nextInt();
                System.out.println("Podaj najnizsza temperature mozliwa do uzyskania w kontenerze ->");
                temperatura_minimalna = scan.nextDouble();
                Kontener_chlodniczy nowy = new Kontener_chlodniczy(waga_kontenera, waga_ladunku,nadawca,temperatura_minimalna, numer_certyfikatu);
                port.getKontenery().add(nowy);
            }
            case 4 ->{
                double pojemnosc_kontenera;
                System.out.println("Podaj pojemnosc kontenera ->");
                pojemnosc_kontenera = scan.nextDouble();
                Kontener_Ciekle nowy= new Kontener_Ciekle(waga_kontenera,waga_ladunku,nadawca,pojemnosc_kontenera);
                port.getKontenery().add(nowy);
            }
            case 5 ->{
                int stopien_zagrozenia, numer_certyfikatu;
                System.out.println("Podaj numer potrzebnego certyfikatu ->");
                numer_certyfikatu = scan.nextInt();
                System.out.println("Podaj stopien zagrozenia ->");
                stopien_zagrozenia = scan.nextInt();
                Kontener_Wybuchowe nowy = new Kontener_Wybuchowe(waga_kontenera,waga_ladunku,nadawca,numer_certyfikatu,stopien_zagrozenia);
                port.getKontenery().add(nowy);
            }
            case 6 ->{
                boolean radio_aktywnosc; // sprawdzic pisownie XD
                int tmp,numer_certyfikatu;
                System.out.println("Czy kontener ma miec mozliwosc przewozenia materialow radioaktywnych?" +
                        "\nwpisz 1 jesli tak lub 0 jesli nie");
                tmp = scan.nextInt();
                System.out.println("Numer potrzebnego certyfikatu ->");
                numer_certyfikatu = scan.nextInt();
                radio_aktywnosc = (tmp == 1);
                Kontener_Toksyczne_Sypkie nowy = new Kontener_Toksyczne_Sypkie(waga_kontenera,waga_ladunku, radio_aktywnosc, nadawca, numer_certyfikatu);
                port.getKontenery().add(nowy);
            }
            case 7 ->{
                double pojemnosc_kontenera;
                System.out.println("Podaj pojemnsoc kontenera ->");
                pojemnosc_kontenera = scan.nextDouble();
                int numer_certyfikatu;
                System.out.println("Numer potrzebnego certyfikatu ->");
                numer_certyfikatu = scan.nextInt();
                Kontener_Toksyczny_Ciekly nowy = new Kontener_Toksyczny_Ciekly(waga_kontenera,waga_ladunku,nadawca,pojemnosc_kontenera,numer_certyfikatu);
                port.getKontenery().add(nowy);
            }
        }
    }

    public void menu_Statkow(){
        System.out.println("""
                Wybrales opcje Stworz Statek
                Jesli chcesz cofnac nacisnij klawisz 9
                Jesli chcesz zakonczyc dzialanie programu wpisz 0
                Jesli chcesz stworzyc statek wpisz 1"""
        );
        int wybor = scan.nextInt();
        switch (wybor){
            case 9 -> wypisz_menu();
            case 1 -> stworzStatek();
            case 0 -> System.exit(0);
        }
    }

    public void stworzStatek(){
        Statek tmp;

        String nazwa;
        int limit_ciezkich;
        int limit_sieciowych;
        int limit_wszystkich;
        double maksymalna_ladownosc;
        int limit_niebezpiecznych;
        String port_macierzysty;
        String lokalizacja_zrodlowa;
        String lokalizacja_docelowa;

        System.out.println("Podaj dane statku, ktore chcesz stworzyc");
        scan.nextLine();
        System.out.println("Podaj nazwe statku");
        nazwa = scan.nextLine();
        System.out.println("Podaj port macierzysty");
        port_macierzysty = scan.nextLine();
        System.out.println("Podaj lokalizacje zrodlowa transportu");
        lokalizacja_zrodlowa = scan.nextLine();
        System.out.println("Podaj lokalizacje docelowa transportu");
        lokalizacja_docelowa = scan.nextLine();
        System.out.println("Podaj maksymalna ilosc kontenerow na statku");
        limit_wszystkich = scan.nextInt();
        System.out.println("Podaj limit ciezkich kontenerow jakie bedzie mozna zaladowac na statek");
        limit_ciezkich = scan.nextInt();
        System.out.println("Podaj limit kontenerow wymagajacych podlaczenia do sieci");
        limit_sieciowych = scan.nextInt();
        System.out.println("Podaj maksymalna ladownosc statku");
        maksymalna_ladownosc = scan.nextDouble();
        System.out.println("Podaj limit niebezpiecznych kontenerow, jakie bedzie mozna zaladowac na statek");
        limit_niebezpiecznych = scan.nextInt();

        tmp = new Statek(nazwa,limit_ciezkich,limit_sieciowych,limit_wszystkich,maksymalna_ladownosc,limit_niebezpiecznych,port_macierzysty,lokalizacja_zrodlowa,lokalizacja_docelowa);
        port.getStatki().add(tmp);
    }

    public void menu_Zaladowania(){
        System.out.println("""
                Wybrales opcje Zaladuj Kontener
                Jesli chcesz cofnac nacisnij klawisz 9
                Jesli chcesz zakonczyc dzialanie programu wpisz 0
                Jesli chcesz zaladowac kontener wpisz 1"""
        );
        int wybor = scan.nextInt();
        switch (wybor){
            case 9 -> wypisz_menu();
            case 1 -> zaladuj_Kontener();
            case 0 -> System.exit(0);
        }
    }

    public void zaladuj_Kontener(){
        int numer_kontenera,  numer_statku;

        System.out.println("Wybierz numer statku, na ktory chcesz zaladowac kontener");
        port.wypisz_statki();
        numer_statku = scan.nextInt();
        System.out.println("Wybierz numer kontenera, ktory chcesz zaladowac ");
        port.wypisz_kontenery();
        numer_kontenera = scan.nextInt();


        for(Kontener_Podstawowy k : port.getKontenery()) {
            if (k.getNumer_danego() == numer_kontenera){
                for(Statek s : port.getStatki())
                    if(s.get_numer_statku() == numer_statku) {
                        try{
                            s.zaladuj_kontener(k);
                        } catch (Error_Kontenery e ) {
                            e.printStackTrace();
                        }
                    }
            }
        }

        port.usun_kontener(numer_kontenera);
    }

    public void menu_Rozladowania(){
        System.out.println("""
                Wybrales opcje rozladuj Kontener
                Jesli chcesz cofnac nacisnij klawisz 9
                Jesli chcesz zakonczyc dzialanie programu wpisz 0
                Jesli chcesz rozladowac kontener wpisz 1"""
        );
        int wybor = scan.nextInt();

        switch (wybor){
            case 9 -> wypisz_menu();
            case 1 -> rozladuj_Kontener();
            case 0 -> System.exit(0);
        }
    }

    public void rozladuj_Kontener(){
        int numer_kontenera = 0,  numer_statku;

        System.out.println("Wybierz numer statku, z ktorego chcesz rozladowac kontener");
        port.wypisz_statki();
        numer_statku = scan.nextInt();
        

        for(Statek s : port.getStatki()) {
            if (s.get_numer_statku() == numer_statku) {
                s.kontenery_na_statku();
                System.out.println("Wybierz numer kontenera, ktory chcesz rozladowac ");
                numer_kontenera = scan.nextInt();
                for(Kontener_Podstawowy k : s.getKontenery_na_statku())
                    if(k.getNumer_danego() == numer_kontenera)
                        port.getKontenery().add(k);

                s.rozladuj_kontener(numer_kontenera);
            }
        }




        int wybor;
        System.out.println("Wybierz miejsce, w ktore chcesz go rozladowac");
        System.out.println("1.Do magazynu");
        System.out.println("2.Na wagon");
        wybor = scan.nextInt();

        switch (wybor){
            case 1 ->{
                for(Kontener_Podstawowy k : port.getKontenery()){
                    if(k.getNumer_danego() == numer_kontenera){
                       port.getMagazyn().dodaj_kontener(k);
                    }
                }
                port.usun_kontener(numer_kontenera);
            }
            case 2 ->{
                for(Kontener_Podstawowy k : port.getKontenery()){
                    if(k.getNumer_danego() == numer_kontenera){
                        port.getWagon().dodaj_kontener(k);
                    }
                }
                port.usun_kontener(numer_kontenera);
            }
        }

    }

    public void usun_Statek(){
        int numer_statku;

        System.out.println("Wybierz statek, ktory chcesz wypuscic w dalsza droge z portu");
        port.wypisz_statki();
        System.out.println("Podaj numer tego statku");
        numer_statku = scan.nextInt();
        port.wypusc_statek(numer_statku);
    }

    public void wypisz_kontenery_na_statku(){
        int numer_statku;

        System.out.println("Wybierz statek, ktorego chcesz zobaczyc kontenery");
        port.wypisz_statki();
        System.out.println("Podaj numer tego statku");
        numer_statku = scan.nextInt();

        System.out.println("KONTENERY NA STATKU " + numer_statku);
        for(Statek s: port.getStatki()){
            if(s.get_numer_statku() == numer_statku)
                s.kontenery_na_statku();
        }

    }

    public void wypisywanie_statkow(){
        port.wypisz_statki();
        System.out.println("Czy chcesz zobaczyc kontenery znajdujace sie na danym statku, jesli tak wpisz jego numer, jesli nie wpisz -1");
        int numer = scan.nextInt();
        boolean czy_skonczyc = (numer == -1);
        while (!czy_skonczyc) {
            for (Statek s : port.getStatki())
                if (numer == s.get_numer_statku()) {

                    s.kontenery_na_statku();
                    czy_skonczyc = true;
                }
            if(!czy_skonczyc){
                System.out.println("Musiales zle wpisac numer statku sprobuj jeszcze raz!");
                numer = scan.nextInt();
            }
        }

    }

    public void usun_kontener(){
        System.out.println("Wybierz numer kontenera z magazynu, ktory chcesz usunac");
        port.getMagazyn().pokaz_stan_magazynu();
        int numer = scan.nextInt();
        port.getMagazyn().usun_kontener(numer);
    }

    public void zapisz_do_pliku(){

        try {
            File plik1 = new File("nadawcy.txt");
            plik1.delete();
            plik1 = new File("nadawcy.txt");

            PrintWriter writer = new PrintWriter(plik1);

            Collections.sort(port.getNadawcy());
            for (Nadawca n : port.getNadawcy()) {
                writer.println("***");
                writer.println(n.toString());
                writer.println("***");
            }

            writer.close();

            File plik2 = new File("kontenery.txt");
            plik2.delete();
            plik2 = new File("kontenery.txt");

            PrintWriter writer2 = new PrintWriter(plik2);

            writer.println("KONTENERY W PORCIE");
            Collections.sort(port.getKontenery());
            for (Kontener_Podstawowy k : port.getKontenery()) {
                writer2.println("***");
                writer2.println(k.toString());
                writer2.println("***");
            }

            writer2.close();

            File plik3 = new File("statki.txt");
            plik3.delete();
            plik3 = new File("statki.txt");
            PrintWriter writer3 = new PrintWriter(plik3);

            Collections.sort(port.getStatki(), Collections.reverseOrder());
            for (Statek s : port.getStatki()) {
                writer3.println("/*");
                writer3.println(s.toString());
                writer3.println("*/");
                writer3.println("/");
                for (Kontener_Podstawowy k : s.getKontenery_na_statku()) {
                    writer3.println("***");
                    writer3.println(k.toString());
                    writer3.println("***");
                }
                writer3.println("///");
                writer3.println("Kontenery ze statku numer " + s.get_numer_statku());
                writer3.println();
            }

            writer3.close();

            File plik4 = new File("magazyn.txt");
            plik4.delete();
            plik4 = new File("magazyn.txt");
            PrintWriter writer4 = new PrintWriter(plik4);

            for(Kontener_Podstawowy k : port.getMagazyn().getKontenery_magazyn().keySet()){
                String pom = port.getMagazyn().getKontenery_magazyn().get(k).toString();
                writer4.println("***");
                writer4.println(k);
                writer4.println("***");
                writer4.println(pom);
            }

            writer4.close();

            File plik5 = new File("data.txt");
            plik5.delete();
            plik5 = new File("data.txt");
            PrintWriter writer5 = new PrintWriter(plik5);

            String pom = port.getMagazyn().getCzas().getData().toString();
            writer5.println(pom);

            writer5.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
