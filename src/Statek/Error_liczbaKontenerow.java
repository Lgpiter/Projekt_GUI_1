package Statek;

public class Error_liczbaKontenerow
extends Exception{
    public Error_liczbaKontenerow(){
        super("Na statku znajduje sie zbyt wiele kontenerow");
    }
}
