package Menu;

import java.time.LocalDate;

public class Nadawca {
    private final String imie;
    private final String nazwisko;
    private final String pesel;
    private int ilosc_upomnien = 0;

    public Nadawca(String imie, String nazwisko, String pesel){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
    }

    public int getIlosc_upomnien() {
        return ilosc_upomnien;
    }

    public void upomnienie(){
        ilosc_upomnien++;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    static void dataUrodzenia(){
        String pesel = "991203221383218231";
        char pom[] = pesel.toCharArray();

        int[] data= new int[pom.length];
        for(int i = 0; i < pom.length;i++){
            data[i] = pom[i] - '0';
        }

        int rok, miesiac,dzien;
        LocalDate data_urodzenia;


        if(data[0] == '9'){
            rok = 1900 + data[0]*10 + data[1];
            System.out.println((int)data[0]);
            miesiac = data[2]*10 + data[3];
            dzien = data[4]*10 + data[5];
        }
        else{
            rok = 2000 + data[0]*10 + data[1];
            miesiac = (data[2]*10 + data[3]) - 20;
            dzien = data[4]*10 + data[5];
        }
        data_urodzenia = LocalDate.of(rok,miesiac,dzien);

        System.out.println("Nadawca urodzil sie " + data_urodzenia);
    }

    @Override
    public String toString() {
        return "Nadawca{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pesel='" + pesel + '\'' +
                ", ilosc_upomnien=" + ilosc_upomnien +
                '}';
    }
}
