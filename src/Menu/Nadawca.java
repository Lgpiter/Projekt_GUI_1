package Menu;

import java.time.LocalDate;

public class Nadawca implements Comparable<Nadawca>{
    private  String imie;
    private  String nazwisko;
    private  String pesel;
    private int ilosc_upomnien = 0;
    private static int licznik = 1;
    private int numer;

    public Nadawca(String imie, String nazwisko, String pesel){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        numer = licznik++;
    }

    public String get_imie() {
        return imie;
    }

    public void setImie(String imie){this.imie = imie;}

    public String get_nazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public int get_ilosc_upomnien() {
        return ilosc_upomnien;
    }

    public void setIlosc_upomnien(int ilosc_upomnien) {
        this.ilosc_upomnien = ilosc_upomnien;
    }

    public int getNumer() {
        return numer;
    }

    public void set_numer(int numer){this.numer = numer;}

    public void upomnij(){
        ilosc_upomnien++;
    }

    public LocalDate dataUrodzenia(){
        char pom[] = pesel.toCharArray();

        int[] data= new int[pom.length];
        for(int i = 0; i < pom.length;i++){
            data[i] = pom[i] - '0';
        }


        int rok, miesiac,dzien;
        LocalDate data_urodzenia;


        if(data[0] > 2){
            rok = 1900 + data[0]*10 + data[1];
            miesiac = data[2]*10 + data[3];
            dzien = data[4]*10 + data[5];
        }
        else{
            rok = 2000 + data[0]*10 + data[1];
            miesiac = (data[2]*10 + data[3]) - 20;
            dzien = data[4]*10 + data[5];
        }
        data_urodzenia = LocalDate.of(rok,miesiac,dzien);

        return data_urodzenia;
    }

    @Override
    public String toString() {
        return "NUMER NADAWCY: " + numer +
                "\nIMIE NADAWCY: " + imie  +
                "\nNAZWISKO: " + nazwisko +
                "\nPESEL: " + pesel +
                "\nILOSC UPOMNIEN: " + ilosc_upomnien;
    }

    @Override
    public int compareTo(Nadawca o) {
        return this.get_imie().compareTo(o.get_imie());
    }
}
