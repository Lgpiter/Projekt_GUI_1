package Menu;

import Kontenery.Kontener_Podstawowy;

import java.time.LocalDate;

public class IrresponsibleSenderWithDangerousGoods extends Exception{
    public IrresponsibleSenderWithDangerousGoods(int numer_kontenera, LocalDate data_przybycia, LocalDate data_usuniecia){
        super("Zostal usuniety kontener numer: " + numer_kontenera +
                "\nData przybycia do magazynu: " + data_przybycia +
                "\nData usuniecia z magazynu: " + data_usuniecia);
    }
}
