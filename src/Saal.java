public class Saal {
    private String nimetus;
    private int kohti;

    public Saal(String nimetus, int kohti) {
        this.nimetus = nimetus;
        this.kohti = kohti;
    }

    public String getNimetus() {
        return nimetus;
    }

    public int getKohti() {
        return kohti;
    }

    public void setKohti(int kohti) {
        this.kohti = kohti;
    }

    public void vähendaKohti(int arv){
        setKohti(getKohti() - arv);
    }

    @Override
    public String toString() {
        return "Saal{" +
                "nimetus='" + nimetus + '\'' +
                ", kohti=" + kohti +
                '}';
    }
}
