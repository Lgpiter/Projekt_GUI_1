package Kontenery;

import Menu.Nadawca;

public class Kontener_Wybuchowe
extends Kontener_Ciezki {
    private int stopien_Zagrozenia; //przewozenie w wyższej grozi wybuchem

    public Kontener_Wybuchowe(double waga_kontenera, double waga_ladunku, Nadawca nadawca, int stopien_Zagrozenia) {
        super(waga_kontenera, waga_ladunku, nadawca,7);
        this.stopien_Zagrozenia = stopien_Zagrozenia;
        this.set_typ("Wybuchowy");

    }

    public int getStopien_Zagrozenia() {
        return stopien_Zagrozenia;
    }

    @Override
    public String toString() {
        return super.toString() + "\nStopien zagrozenia wybuchem w skali od 1-10: " + getStopien_Zagrozenia();
    }

}
