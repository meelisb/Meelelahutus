import java.util.Date;

public class Film extends Üritus {

    public Film(String nimetus, Date aeg, String asutus, String saal, double täishind) {
        super(nimetus, aeg, asutus, saal, täishind);
    }

    @Override
    public String toString() {
        return "Film" + super.toString();
    }
}