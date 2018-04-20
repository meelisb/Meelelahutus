public class Asutus {
    private String nimetus;
    private String aadress;
    private int soodustus;

    public Asutus(String nimetus, String aadress, int soodustus) {
        this.nimetus = nimetus;
        this.aadress = aadress;
        this.soodustus = soodustus;
    }

    public String getNimetus() {
        return nimetus;
    }

    public String getAadress() {
        return aadress;
    }

    public int getSoodustus() {
        return soodustus;
    }

    @Override
    public String toString() {
        return "Asutus{" +
                "nimetus='" + nimetus + '\'' +
                ", aadress='" + aadress + '\'' +
                ", soodustuse protsent=" + soodustus +
                '}';
    }
}
