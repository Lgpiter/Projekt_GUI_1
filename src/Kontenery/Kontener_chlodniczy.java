package Kontenery;

import Menu.Nadawca;

public class Kontener_chlodniczy
    extends Kontener_Ciezki
    implements Siec{
    private final double tempetarua_minimalna;

    public Kontener_chlodniczy(double waga_kontenera, double waga_ladunku, Nadawca nadawca, double tempetarua_minimalna, int numer_certyfikatu) {
        super(waga_kontenera, waga_ladunku, nadawca,numer_certyfikatu);
        this.set_typ("Chlodniczy");
        this.tempetarua_minimalna = tempetarua_minimalna;
        podlacz_do_sieci();
    }

    public double getTempetarua_minimalna() {
        return tempetarua_minimalna;
    }

    @Override
    public String toString() {
        return super.toString() + "\nMinimalna mozliwa temperatura w kontenerze: " +  getTempetarua_minimalna();
    }

    @Override
    public void podlacz_do_sieci() {}
}
