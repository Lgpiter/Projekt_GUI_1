package Kontenery;

public abstract class Kontener{
    private double waga_kontenera;
    private String typ;
    private static int identyfikator = 347;
    private final int numer_danego;

    public Kontener(double waga_kontenera){
        this.waga_kontenera = waga_kontenera;
        numer_danego = this.identyfikator++;
    }

    public int getNumer_danego(){
        return numer_danego;
    }


    @Override
    public String toString() {
        return "Kontener numer: " + numer_danego +
                "\nTyp: " + get_typ() +
                "\nWaga_kontenera: " + waga_kontenera ;
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
