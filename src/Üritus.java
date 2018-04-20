import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Üritus {
    List<Üritus> sobivad = new ArrayList<>();

    private String nimetus;
    private Date aeg;
    private String asutus;
    private String saal;
    private double täishind;

    public Üritus(String nimetus, Date aeg, String asutus, String saal, double täishind) {
        this.nimetus = nimetus;
        this.aeg = aeg;
        this.asutus = asutus;
        this.saal = saal;
        this.täishind = täishind;
    }

    public String getNimetus() {
        return nimetus;
    }

    public Date getAeg() {
        return aeg;
    }

    public String getAsutus() {
        return asutus;
    }

    public String getSaal() {
        return saal;
    }

    public double getTäishind() {
        return täishind;
    }

    @Override
    public String toString() {
        return "{" +
                "nimetus='" + nimetus + '\'' +
                ", aeg=" + aeg +
                ", asutus='" + asutus + '\'' +
                ", saal='" + saal + '\'' +
                ", täishind='" + täishind + '\'' +
                '}';
    }
}
