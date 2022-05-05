package Kontenery;

public abstract class Kontener{
    private final double waga_kontenera;
    private final double waga_ladunku;
    private String typ;
    private static int identyfikator = 1;
    private int numer_danego;

    public Kontener(double waga_kontenera, double waga_ladunku){
        this.waga_kontenera = waga_kontenera;
        this.waga_ladunku = waga_ladunku;
        numer_danego = identyfikator++;
    }

    public int getNumer_danego(){
        return numer_danego;
    }

    public void setNumer_danego(int numer_danego) {
        this.numer_danego = numer_danego;
    }

    @Override
    public String toString() {
        return
                "Kontener: " + typ +
                        "\nWaga kontenera: " + (waga_kontenera - waga_ladunku) +
                        "\nWaga ladunku: " + waga_ladunku +
                        "\nNumer: " + numer_danego;
    }

    public double getWaga_kontenera() {
        return waga_kontenera;
    }

    public void set_typ(String typ){
        this.typ = typ;
    }

    public String get_typ(){
        return this.typ;
    }
}
