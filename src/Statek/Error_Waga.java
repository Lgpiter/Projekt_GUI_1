package Statek;

public class Error_Waga extends Exception{
    public Error_Waga(){
        super("Waga kontenerow na statku jest zbyt duza, nie mozna dodac nastepnego, bo statek zatonie!");
    }
}
