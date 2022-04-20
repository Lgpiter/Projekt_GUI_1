package Menu;

import java.time.LocalDate;

public class Data implements Comparable<Data>{
    private LocalDate data = LocalDate.of(2020, 1, 8);

    public Data(){
        Thread czas = new Thread(() ->{
            while(!Thread.interrupted())
                try {
                    this.data = data.plusDays(1);
                    System.out.println(data);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });

        czas.start();

    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public int compareTo(Data data) {
        return getData().compareTo(data.getData());
    }
}
