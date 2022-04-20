package Kontenery;

import Menu.Nadawca;

public class Kontener_Ciezki
extends Kontener_Podstawowy{
    private int numerPotrzebnegoCertyfikatu;

    public Kontener_Ciezki(double waga_kontenera, double waga_ladunku, Nadawca nadawca, int numerPotrzebnegoCertyfikatu) {
        super(waga_kontenera, waga_ladunku, nadawca);
        this.set_typ("Ciezki");
        this.numerPotrzebnegoCertyfikatu = numerPotrzebnegoCertyfikatu;
    }

    public int getNumerPotrzebnegoCertyfikatu() {
        return numerPotrzebnegoCertyfikatu;
    }

    @Override
    public String toString() {
        return super.toString() + "\nNumer potrzebnego certyfikatu: " + getNumerPotrzebnegoCertyfikatu();
    }
}


