package Kontenery;

import Menu.Nadawca;

public class Kontener_Ciekle
    extends Kontener_Podstawowy
    implements Ciekle{
    private double pojemnosc_kontenera;

    public Kontener_Ciekle(double waga_kontenera, double waga_ladunku, Nadawca nadawca, double pojemnosc_kontenera) {
        super(waga_kontenera, waga_ladunku, nadawca);
        set_typ("Ciekle");
        setPojemnoscKontenera(pojemnosc_kontenera);
    }

    public double getPojemnosc_kontenera() {
        return pojemnosc_kontenera;
    }

    @Override
    public void setPojemnoscKontenera(double gestosc) {
        this.pojemnosc_kontenera = gestosc;
    }

    @Override
    public String toString() {
        return super.toString() + "\nPojemnosc kontenera: " + getPojemnosc_kontenera();
    }
}
