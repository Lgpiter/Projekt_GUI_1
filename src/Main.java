import Menu.*;
import Statek.*;



public class Main implements Port_Glowny {
    public static void main(String[] args) {
        /*
        Kontener_Podstawowy kontener_podstawowy = new Kontener_Podstawowy(300, 50, "Mirek");

        Kontener_Ciezki kontener_ciezki = new Kontener_Ciezki(500,1000,"Franek", 8);

        Kontener_Chłodniczy kontener_chlodniczy = new Kontener_Chłodniczy(500,300,"Jasiek", -100,7);

        Kontener_Ciekle kontener_ciekle = new Kontener_Ciekle(400,100,"Maniek", 300);

        Kontener_Wybuchowe kontenerWybuchowe = new Kontener_Wybuchowe(100,20,"Wariat",5);

        Kontener_Toksyczne_Sypkie kontener_toksyczne_sypkie = new Kontener_Toksyczne_Sypkie(50,10,true,"Chemik",12);

        Kontener_Toksyczny_Ciekly kontener_toksyczny_ciekly = new Kontener_Toksyczny_Ciekly(70,32,"Singed", 64,120);


         */
        Statek testowy = new Statek("Tytanik",10000,4,75,1000000000,4);

        /*
        try{
            testowy.zaladuj_kontener(kontener_podstawowy);
            testowy.zaladuj_kontener(kontener_ciezki);
            testowy.zaladuj_kontener(kontener_chlodniczy);
            testowy.zaladuj_kontener(kontener_ciekle);
            testowy.zaladuj_kontener(kontenerWybuchowe);
            testowy.zaladuj_kontener(kontener_toksyczne_sypkie);
            testowy.zaladuj_kontener(kontener_toksyczny_ciekly);
        } catch (Error_Waga | Error_Siec | Error_niebezpieczne | Error_Ciezkie | Error_liczbaKontenerow e ) {
            e.printStackTrace();
        }

         */

        testowy.konteneryNaStatku();

       new Menu();

      //  new Data();

    }
}
