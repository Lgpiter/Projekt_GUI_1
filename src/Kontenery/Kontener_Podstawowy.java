package Kontenery;

import Menu.Nadawca;

public class Kontener_Podstawowy
    extends Kontener implements Comparable<Kontener_Podstawowy>{
    private final Nadawca nadawca;

    public Kontener_Podstawowy(double waga_kontenera, double waga_ladunku, Nadawca nadawca) {
        super(waga_kontenera + waga_ladunku, waga_ladunku);
        this.nadawca = nadawca;
        this.set_typ("Podstawowy");
    }

    public int upomnienia_nadawcy(){
        return nadawca.get_ilosc_upomnien();
    }

    public void upomij_nadawce(){
        this.nadawca.upomnij();
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n|Nadawca " + nadawca;
    }

    @Override
    public int compareTo(Kontener_Podstawowy o) {
        return (int)(this.getWaga_kontenera() - o.getWaga_kontenera());
    }


}
