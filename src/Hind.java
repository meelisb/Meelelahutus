public class Hind {

    public void arvutaHind(Asutus object, double täishind, int soodusPileteid, int täisPileteid) {
        // Arvutatakse piletite soodushind ja koguhind.
        int protsent = object.getSoodustus();
        double soodusHind = täishind - (protsent * täishind / 100);
        double lõppHind = täisPileteid * täishind + soodusPileteid * soodusHind;
        System.out.println("\n" + "Lõpphind on: " + lõppHind);
    }
}
