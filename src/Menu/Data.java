package Menu;


import java.time.LocalDate;


public class Data implements Comparable<Data>{

    private LocalDate data = LocalDate.of(2020, 1, 8);

    public  LocalDate getData() {
        return data;
    }

    public  void setData(){data = data.plusDays(1);}

    public void ustawienie_poczatkowe(LocalDate data_wejsciowa){this.data = data_wejsciowa;}


    @Override
    public int compareTo(Data data) {
        return getData().compareTo(data.getData());
    }

}
