package Kontenery;

import Menu.Nadawca;

public class Kontener_Toksyczny_Ciekly
extends Kontener_Ciezki
implements Ciekle{
    double pojemnoscKontenera;
    public Kontener_Toksyczny_Ciekly(double waga_kontenera, double waga_ladunku, Nadawca nadawca, double pojemnoscKontenera, int numer_certyfikatu) {
        super(waga_kontenera, waga_ladunku, nadawca,numer_certyfikatu);
        setPojemnoscKontenera(pojemnoscKontenera);
        set_typ("ToksycznyCiekly");
    }

    @Override
    public void setPojemnoscKontenera(double pojemnoscKontenera) {
        this.pojemnoscKontenera = pojemnoscKontenera;
    }

    @Override
    public double getPojemnosc_kontenera() {
        return pojemnoscKontenera;
    }

    @Override
    public String toString() {
        return super.toString() + " Pojemnosc: " + getPojemnosc_kontenera();
    }
}
