import java.util.Date;

public class Etendus extends Üritus {

    public Etendus(String nimetus, Date aeg, String asutus, String saal, double täishind) {
        super(nimetus, aeg, asutus, saal, täishind);
    }

    @Override
    public String toString() {
        return "Etendus" + super.toString();
    }
}

