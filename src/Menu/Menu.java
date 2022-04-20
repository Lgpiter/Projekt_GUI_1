package Menu;

import Kontenery.*;
import Statek.*;

import java.util.Scanner;

public class Menu implements Port_Glowny {
    Scanner scan = new Scanner(System.in);


    public Menu(){
        int wybor; // sluzy do poruszania sie po menu

        wypisz_menu(); // wypisuje poczatkowy stan menu
        wybor = scan.nextInt();
        while(wybor != 0) {
            switch (wybor) {
                case 1 -> menu_Kontenerow();
                case 2 -> menu_Statkow();
                case 3 -> menu_Rozladowania();
                case 4 -> menu_Zaladowania();
                case 5 -> Port_Glowny.wypisz_kontenery();
                case 6 -> Port_Glowny.wypisz_statki();
                case 7 ->  usun_Statek();
                case 8 -> wypisz_kontenery_na_statku();
                default -> System.out.println("Podales liczbe, ktorej nie ma w menu. Sprobuj jeszcze raz!");
            }
            wypisz_menu();
            wybor = scan.nextInt();
        }

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

    //ta funkcje mozna poprawic grupujac kontenery chyba
    public void stworzKontener(int wybor){
        double waga_kontenera,waga_ladunku;
        String imie,nazwisko,pesel;
        System.out.println("Waga kontenera ->");
        waga_kontenera = scan.nextDouble();
        System.out.println("Waga ladunku ->");
        waga_ladunku = scan.nextDouble();
        scan.nextLine();
        System.out.println("Imie nadawcy ");
        imie = scan.nextLine();

        scan.nextLine();
        System.out.println("Nazwisko nadawcy ");
        nazwisko = scan.nextLine();

        scan.nextLine();
        System.out.println("Pesel nadawcy ");
        pesel = scan.nextLine();

        Nadawca nadawca = new Nadawca(imie,nazwisko,pesel);

        switch (wybor){
            case 1 -> {
                Kontener_Podstawowy nowy = new Kontener_Podstawowy(waga_kontenera,waga_ladunku,nadawca);
                Port_Glowny.kontenery.add(nowy);
            }
            case 2 ->{
                int numer_certyfikatu;
                System.out.println("Numer potrzebnego certyfikatu ->");
                numer_certyfikatu = scan.nextInt();
                Kontener_Ciezki nowy = new Kontener_Ciezki(waga_kontenera,waga_ladunku,nadawca,numer_certyfikatu);
                Port_Glowny.kontenery.add(nowy);
            }
            case 3 ->{
                double temperatura_minimalna;
                int numer_certyfikatu;
                System.out.println("Numer potrzebnego certyfikatu ->");
                numer_certyfikatu = scan.nextInt();
                System.out.println("Podaj najnizsza temperature mozliwa do uzyskania w kontenerze ->");
                temperatura_minimalna = scan.nextDouble();
                Kontener_Chłodniczy nowy = new Kontener_Chłodniczy(waga_kontenera, waga_ladunku,nadawca,temperatura_minimalna, numer_certyfikatu);
                Port_Glowny.kontenery.add(nowy);
            }
            case 4 ->{
                double pojemnosc_kontenera;
                System.out.println("Podaj pojemnosc kontenera ->");
                pojemnosc_kontenera = scan.nextDouble();
                Kontener_Ciekle nowy= new Kontener_Ciekle(waga_kontenera,waga_ladunku,nadawca,pojemnosc_kontenera);
                Port_Glowny.kontenery.add(nowy);
            }
            case 5 ->{
                int stopien_zagrozenia;
                System.out.println("Podaj stopien zagrozenia ->");
                stopien_zagrozenia = scan.nextInt();
                Kontener_Wybuchowe nowy = new Kontener_Wybuchowe(waga_kontenera,waga_ladunku,nadawca,stopien_zagrozenia);
                Port_Glowny.kontenery.add(nowy);
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
                Port_Glowny.kontenery.add(nowy);
            }
            case 7 ->{
                double pojemnosc_kontenera;
                System.out.println("Podaj pojemnsoc kontenera ->");
                pojemnosc_kontenera = scan.nextDouble();
                int numer_certyfikatu;
                System.out.println("Numer potrzebnego certyfikatu ->");
                numer_certyfikatu = scan.nextInt();
                Kontener_Toksyczny_Ciekly nowy = new Kontener_Toksyczny_Ciekly(waga_kontenera,waga_ladunku,nadawca,pojemnosc_kontenera,numer_certyfikatu);
                Port_Glowny.kontenery.add(nowy);
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

        System.out.println("Podaj dane statku, ktore chcesz stworzyc");
        scan.nextLine();
        System.out.println("Po pierwsze podaj imie");
        nazwa = scan.nextLine();
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

        tmp = new Statek(nazwa,limit_ciezkich,limit_sieciowych,limit_wszystkich,maksymalna_ladownosc,limit_niebezpiecznych);
        Port_Glowny.statki.add(tmp);
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
        Port_Glowny.wypisz_statki();
        numer_statku = scan.nextInt();
        System.out.println("Wybierz numer kontenera, ktory chcesz zaladowac ");
        numer_kontenera = scan.nextInt();


        for(Kontener_Podstawowy k : Port_Glowny.kontenery) {
            if (k.getNumer_danego() == numer_kontenera){
                for(Statek s : Port_Glowny.statki)
                    if(s.getNumer_Statku() == numer_statku) {
                        try{
                            s.zaladuj_kontener(k);
                        } catch (Error_Waga | Error_Siec | Error_niebezpieczne | Error_Ciezkie | Error_liczbaKontenerow e ) {
                            e.printStackTrace();
                        }
                    }
            }
        }

        Port_Glowny.usun_kontener(numer_kontenera);
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
        int numer_kontenera,  numer_statku;

        System.out.println("Wybierz numer statku, z ktorego chcesz rozladowac kontener");
        Port_Glowny.wypisz_statki();
        numer_statku = scan.nextInt();
        System.out.println("Wybierz numer kontenera, ktory chcesz rozladowac ");
        Port_Glowny.wypisz_kontenery();
        numer_kontenera = scan.nextInt();

        for(Statek s : Port_Glowny.statki)
            if(s.getNumer_Statku() == numer_statku)
                s.rozladuj_kontener(numer_kontenera);

        int wybor;
        System.out.println("Wybierz miejsce, w ktore chcesz go zaladowac");
        System.out.println("1.Do magazynu");
        System.out.println("2.Na wagon");
        wybor = scan.nextInt();

        switch (wybor){
            case 1 ->{
                for(Kontener_Podstawowy k : Port_Glowny.kontenery){
                    if(k.getNumer_danego() == numer_kontenera){
                        magazyn.dodaj_kontener(numer_kontenera);
                    }
                }
            }
            case 2 ->{
                for(Kontener_Podstawowy k : Port_Glowny.kontenery){
                    if(k.getNumer_danego() == numer_kontenera){
                        wagon.add_kontener(k);
                        kontenery.remove(k);
                    }
                }
            }
        }

    }

    public void usun_Statek(){
        int numer_statku;

        System.out.println("Wybierz statek, ktory chcesz wypuscic w dalsza droge z portu");
        Port_Glowny.wypisz_statki();
        System.out.println("Podaj numer tego statku");
        numer_statku = scan.nextInt();
        Port_Glowny.wypusc_statek(numer_statku);
    }

    public void wypisz_kontenery_na_statku(){
        int numer_statku;

        System.out.println("Wybierz statek, ktorego chcesz zobaczyc kontenery");
        Port_Glowny.wypisz_statki();
        System.out.println("Podaj numer tego statku");
        numer_statku = scan.nextInt();

        for(Statek s: Port_Glowny.statki){
            if(s.getNumer_Statku() == numer_statku)
                s.konteneryNaStatku();
        }

    }
}
