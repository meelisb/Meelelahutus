import java.util.Date;

public class Sport extends Üritus {

    public Sport(String nimetus, Date aeg, String asutus, String saal, double täishind) {
        super(nimetus, aeg, asutus, saal, täishind);
    }

    @Override
    public String toString() {
        return "Spordiüritus" + super.toString();
    }
}
