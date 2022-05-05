package Kontenery;

import Menu.Nadawca;

public class Kontener_Toksyczne_Sypkie
extends Kontener_Ciezki{
    boolean radio_aktywne;

    public Kontener_Toksyczne_Sypkie(double waga_kontenera, double waga_ladunku, boolean radio_aktywne, Nadawca nadawca, int numer_certyfikatu) {
        super(waga_kontenera, waga_ladunku, nadawca,numer_certyfikatu);
        this.radio_aktywne = radio_aktywne;
        this.set_typ("ToksycznySypki");
    }

    @Override
    public String toString() {
        return super.toString() + "\nCzy radioaktywne " + (radio_aktywne? "tak" : "nie");
    }
}
