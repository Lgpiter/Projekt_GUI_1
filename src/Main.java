import Kontenery.*;
import Menu.*;
import Statek.*;

import java.io.File;

public class Main{
    public static void main(String[] args) {
        Port_Glowny port = new Port_Glowny();

        File plik1 = new File("nadawcy.txt");
        File plik2 = new File("kontenery.txt");
        File plik3 = new File("statki.txt");
        File plik4 = new File("data.txt");
        File plik5 = new File("magazyn.txt");


        if(plik1.exists() && plik2.exists() && plik3.exists() && plik4.exists() && plik5.exists()){
            port.wczytaj_nadawcow(plik1);
            port.wczytaj_kontenery(plik2);
            port.wczytaj_statki(plik3);
            port.wczytaj_date(plik4);
            port.wczytaj_magazyn(plik5);
        }
        else {

            Statek statek1 = new Statek("Statek 1", 100, 10, 100, 10000000, 10, "Gdansk", "Gdansk", "Sztokholm");
            Statek statek2 = new Statek("Statek 2", 20000, 2, 200, 1000000000, 20, "Francja", "Belgia", "Polska");
            Statek statek3 = new Statek("Statek 3", 30000, 3, 300, 1000000000, 30, "Walia", "Hiszpania", "Chiny");
            Statek statek4 = new Statek("Statek 4", 40000, 4, 400, 1000000000, 40, "Wlochy", "Gdansk", "USA");
            Statek statek5 = new Statek("Statek 5", 50000, 5, 500, 1000000000, 50, "Brazylia", "Portugalia", "Indie");
            port.getStatki().add(statek1);
            port.getStatki().add(statek2);
            port.getStatki().add(statek3);
            port.getStatki().add(statek4);
            port.getStatki().add(statek5);

            Nadawca nadawca1 = new Nadawca("Piotr", "Kowalski", "77051252779");
            Nadawca nadawca2 = new Nadawca("Maciek", "Sokolowski", "85062631416");
            Nadawca nadawca3 = new Nadawca("Kuba", "Gregorowicz", "01321119495");
            Nadawca nadawca4 = new Nadawca("Julia", "Gregula", "03311053848");
            port.getNadawcy().add(nadawca1);
            port.getNadawcy().add(nadawca2);
            port.getNadawcy().add(nadawca3);
            port.getNadawcy().add(nadawca4);

            Kontener_Podstawowy kontener_podstawowy1 = new Kontener_Podstawowy(100, 10, nadawca1);
            Kontener_Podstawowy kontener_podstawowy2 = new Kontener_Podstawowy(200, 20, nadawca2);
            Kontener_Podstawowy kontener_podstawowy3 = new Kontener_Podstawowy(300, 30, nadawca3);
            Kontener_Podstawowy kontener_podstawowy4 = new Kontener_Podstawowy(400, 40, nadawca4);
            Kontener_Podstawowy kontener_podstawowy5 = new Kontener_Podstawowy(500, 50, nadawca1);



            Kontener_Ciezki kontener_ciezki1 = new Kontener_Ciezki(100, 10, nadawca1, 1);
            Kontener_Ciezki kontener_ciezki2 = new Kontener_Ciezki(200, 20, nadawca2, 2);
            Kontener_Ciezki kontener_ciezki3 = new Kontener_Ciezki(300, 30, nadawca3, 3);
            Kontener_Ciezki kontener_ciezki4 = new Kontener_Ciezki(400, 40, nadawca4, 4);


            Kontener_chlodniczy kontener_chlodniczy1 = new Kontener_chlodniczy(100, 10, nadawca1, -100, 1);
            Kontener_chlodniczy kontener_chlodniczy2 = new Kontener_chlodniczy(200, 10, nadawca2, -200, 2);
            Kontener_chlodniczy kontener_chlodniczy3 = new Kontener_chlodniczy(300, 10, nadawca3, -300, 3);
            Kontener_chlodniczy kontener_chlodniczy4 = new Kontener_chlodniczy(400, 10, nadawca4, -400, 4);
            Kontener_chlodniczy kontener_chlodniczy5 = new Kontener_chlodniczy(500, 10, nadawca2, -500, 5);


            Kontener_Ciekle kontener_ciekle1 = new Kontener_Ciekle(100, 10, nadawca1, 100);
            Kontener_Ciekle kontener_ciekle2 = new Kontener_Ciekle(200, 20, nadawca2, 200);
            Kontener_Ciekle kontener_ciekle3 = new Kontener_Ciekle(300, 30, nadawca3, 300);


            Kontener_Wybuchowe kontener_wybuchowe1 = new Kontener_Wybuchowe(100, 10, nadawca1, 1, 1);
            Kontener_Wybuchowe kontener_wybuchowe2 = new Kontener_Wybuchowe(200, 20, nadawca2, 2, 1);
            Kontener_Wybuchowe kontener_wybuchowe3 = new Kontener_Wybuchowe(300, 30, nadawca4, 3, 4);
            Kontener_Wybuchowe kontener_wybuchowe4 = new Kontener_Wybuchowe(400, 40, nadawca1, 4, 3);
            Kontener_Wybuchowe kontener_wybuchowe5 = new Kontener_Wybuchowe(500, 50, nadawca2, 6, 2);
            Kontener_Wybuchowe kontener_wybuchowe6 = new Kontener_Wybuchowe(600, 60, nadawca3, 5, 2);



            Kontener_Toksyczne_Sypkie kontener_toksyczne_sypkie1 = new Kontener_Toksyczne_Sypkie(100, 10, true, nadawca1, 1);
            Kontener_Toksyczne_Sypkie kontener_toksyczne_sypkie2 = new Kontener_Toksyczne_Sypkie(100, 10, false, nadawca1, 1);
            Kontener_Toksyczne_Sypkie kontener_toksyczne_sypkie3 = new Kontener_Toksyczne_Sypkie(100, 10, true, nadawca1, 1);




            Kontener_Toksyczny_Ciekly kontener_toksyczny_ciekly1 = new Kontener_Toksyczny_Ciekly(10000, 10, nadawca1, 300, 4);
            Kontener_Toksyczny_Ciekly kontener_toksyczny_ciekly2 = new Kontener_Toksyczny_Ciekly(100, 10, nadawca1, 300, 4);
            Kontener_Toksyczny_Ciekly kontener_toksyczny_ciekly3 = new Kontener_Toksyczny_Ciekly(100, 10, nadawca1, 300, 4);




            Kontener_Wybuchowe kontener_magazynowy_1 = new Kontener_Wybuchowe(100, 10, nadawca1, 1, 1);
            Kontener_Podstawowy kontener_magazynowy_2 = new Kontener_Podstawowy(50, 10, nadawca2);
            Kontener_Wybuchowe kontener_magazynowy_3 = new Kontener_Wybuchowe(200, 10, nadawca3, 1, 2);
            Kontener_Ciekle kontener_magazynowy_4 = new Kontener_Ciekle(300, 50, nadawca4, 300);
            port.getMagazyn().dodaj_kontener(kontener_magazynowy_1);
            port.getMagazyn().dodaj_kontener(kontener_magazynowy_2);
            port.getMagazyn().dodaj_kontener(kontener_magazynowy_3);
            port.getMagazyn().dodaj_kontener(kontener_magazynowy_4);


            try {
                statek1.zaladuj_kontener(kontener_podstawowy1);
                statek1.zaladuj_kontener(kontener_ciezki1);
                statek1.zaladuj_kontener(kontener_ciezki2);
                statek1.zaladuj_kontener(kontener_chlodniczy1);
                statek1.zaladuj_kontener(kontener_chlodniczy2);
                statek1.zaladuj_kontener(kontener_toksyczny_ciekly1);
                statek1.zaladuj_kontener(kontener_toksyczny_ciekly2);
                statek1.zaladuj_kontener(kontener_toksyczny_ciekly3);
                statek1.zaladuj_kontener(kontener_wybuchowe1);
                statek1.zaladuj_kontener(kontener_wybuchowe2);
                statek1.zaladuj_kontener(kontener_wybuchowe3);
                statek1.zaladuj_kontener(kontener_wybuchowe4);


                statek2.zaladuj_kontener(kontener_podstawowy2);
                statek2.zaladuj_kontener(kontener_ciezki2);
                statek2.zaladuj_kontener(kontener_chlodniczy2);
                statek2.zaladuj_kontener(kontener_ciekle1);
                statek2.zaladuj_kontener(kontener_wybuchowe3);

                statek3.zaladuj_kontener(kontener_podstawowy3);
                statek3.zaladuj_kontener(kontener_ciezki3);
                statek3.zaladuj_kontener(kontener_chlodniczy3);
                statek3.zaladuj_kontener(kontener_ciekle2);
                statek3.zaladuj_kontener(kontener_wybuchowe4);

                statek4.zaladuj_kontener(kontener_podstawowy4);
                statek4.zaladuj_kontener(kontener_ciezki4);
                statek4.zaladuj_kontener(kontener_chlodniczy4);
                statek4.zaladuj_kontener(kontener_wybuchowe5);


                statek5.zaladuj_kontener(kontener_podstawowy5);
                statek5.zaladuj_kontener(kontener_chlodniczy5);
                statek5.zaladuj_kontener(kontener_ciekle3);
                statek5.zaladuj_kontener(kontener_wybuchowe6);
                statek5.zaladuj_kontener(kontener_toksyczne_sypkie1);
                statek5.zaladuj_kontener(kontener_toksyczne_sypkie2);
                statek5.zaladuj_kontener(kontener_toksyczne_sypkie3);
            } catch (Error_Kontenery e) {
                e.printStackTrace();
            }

        }


     new Menu(port);
    }

}
