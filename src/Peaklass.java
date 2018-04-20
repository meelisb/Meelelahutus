// Programmi autorid: Kristi Kukk, Meelis Burget

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Peaklass {

    // Loetakse failist meelelahutusürituste andmed
    static List<Üritus> loeÜritused(String nimi) throws FileNotFoundException {
        List<Üritus> üritused = new ArrayList<Üritus>();
        File fail = new File(nimi);
        Scanner sc = new Scanner(fail);
        String liik = "";
        String nimetus = "";
        Date aeg = new Date();
        String asutus = "";
        String saal = "";
        double täishind = 0.0;

        while (sc.hasNext()) {
            String rida = sc.nextLine();
            String[] rida1 = rida.split(";");
            liik = rida1[0];
            nimetus = rida1[1];
            aeg = new Date(Integer.parseInt(rida1[2]), Integer.parseInt(rida1[3]), Integer.parseInt(rida1[4]), Integer.parseInt(rida1[5]), Integer.parseInt(rida1[6]));
            asutus = rida1[7];
            saal = rida1[8];
            täishind = Double.parseDouble(rida1[9]);

            if (liik.equals("film")) {
                Film uus = new Film(nimetus, aeg, asutus, saal, täishind);
                üritused.add(uus);
//                System.out.println(uus);
            } else if (liik.equals("etendus")) {
                Etendus uus = new Etendus(nimetus, aeg, asutus, saal, täishind);
                üritused.add(uus);
//                System.out.println(uus);
            } else if (liik.equals("spordiüritus")) {
                Sport uus = new Sport(nimetus, aeg, asutus, saal, täishind);
                üritused.add(uus);
//                System.out.println(uus);
            }  /*else {
                System.out.println("Muu");
            }*/
        }
        sc.close();
        return üritused;
    }

    // Loetakse failist saalide mahutavuse andmed
    static List<Saal> loeSaalid(String nimi) throws FileNotFoundException {
        List<Saal> saalid = new ArrayList<Saal>();
        File fail = new File(nimi);
        Scanner sc = new Scanner(fail, "UTF-8");

        String nimetus = "";
        int kohti = 0;

        while (sc.hasNext()) {
            String rida = sc.nextLine();
            String[] rida1 = rida.split(";");
            nimetus = rida1[0];
            kohti = Integer.parseInt(rida1[1]);
            Saal saal = new Saal(nimetus, kohti);
            saalid.add(saal);
        }
        sc.close();
        return saalid;
    }

    // Loetakse failist asutuste andmed
    static List<Asutus> loeAsutused(String nimi) throws FileNotFoundException {
        List<Asutus> asutused = new ArrayList<Asutus>();
        File fail = new File(nimi);
        Scanner sc = new Scanner(fail, "UTF-8");

        String nimetus = "";
        String aadress = "";
        int soodustus = 0;

        while (sc.hasNext()) {
            String rida = sc.nextLine();
            String[] rida1 = rida.split(";");
            nimetus = rida1[0];
            aadress = rida1[1];
            soodustus = Integer.parseInt(rida1[2]);
            Asutus asutus = new Asutus(nimetus, aadress, soodustus);
            asutused.add(asutus);
        }
        sc.close();
        return asutused;
    }

    public static void main(String[] args) throws Exception {
        List<Üritus> üritused = loeÜritused("yritused.txt");
        List<Saal> saalid = loeSaalid("saalid.txt");
        List<Asutus> asutused = loeAsutused("asutused.txt");

        // Öeldakse kasutajale, mida programmiga teha saab ja küsitakse teda huvitav ürituste periood.
        System.out.println("Siin saad osta pileteid valitud kultuurisündmustele.");
        Date algusKuupäev = stringKuupäevaks(küsiInfot("Otsin üritusi valitud perioodist. Palun sisesta perioodi alguskuupäev:"));
        Date lõppKuupäev = stringKuupäevaks(küsiInfot("Palun sisesta perioodi lõppkuupäev:"));

        // Antakse kasutajale ürituste loetelu valitud perioodil.
        List<Üritus> sobivad = new ArrayList<>();
        System.out.println("");
        System.out.println("Sobivasse perioodi jäävad järgmised üritused: ");

        int sobivadJrn = 1;
        for (int i = 0; i < üritused.size(); i++) {
            Date aeg = üritused.get(i).getAeg();
            if (aeg.after(algusKuupäev) && aeg.before(lõppKuupäev)) {
                sobivad.add(üritused.get(i));
                System.out.println(sobivadJrn + ". " + sobivad.get(sobivadJrn - 1));
                sobivadJrn++;
            }
        }

        //Küsitakse andmeid sobiva ürituse ja soovitavate piletite arvu kohta.
        int valitud = Integer.parseInt(küsiInfot("Vali üritus, mille pileteid soovid. Sisesta ürituse järjekorranumber: "));
        String saal = sobivad.get(valitud - 1).getSaal();
        String asutus = sobivad.get(valitud - 1).getAsutus();
        int soodusPileteid = Integer.parseInt(küsiInfot("Mitu sooduspiletit soovid?"));
        int täisPileteid = Integer.parseInt(küsiInfot("Mitu täishinnaga piletit soovid?"));
        int mituKohta = soodusPileteid + täisPileteid;

        //Kontrollitakse, kas on piisavalt kohti ja kui on, siis arvutatakse lõpphind ja küsitakse, kas kasutaja soovib kohad broneerida.
        Random rand = new Random();
        for (int i = 0; i < saalid.size(); i++) {
            String valitudSaal = saalid.get(i).getNimetus();
            int broneeritudKohti = rand.nextInt(saalid.get(i).getKohti() + 1);
            if (saal.equals(valitudSaal)) {
                if (saalid.get(i).getKohti() < broneeritudKohti + mituKohta) {
                    System.out.println("\n" + "Saalis pole enam piisavalt kohti! Kohti ei broneeritud!");
                } else {
                    for (int x = 0; x < asutused.size(); x++) {
                        String valitudAsutus = asutused.get(x).getNimetus();
                        if (asutus.equals(valitudAsutus)) {
                            Hind uus = new Hind();
                            uus.arvutaHind(asutused.get(x), sobivad.get(valitud - 1).getTäishind(), soodusPileteid, täisPileteid);
                            String broneerida = küsiInfot("Kas soovite kohad broneerida? (Jah / Ei)");
                            if (broneerida.equalsIgnoreCase("Jah")) {
                                saalid.get(i).vähendaKohti(mituKohta);
                                System.out.println("\n" + "Kohad on broneeritud!");
                            } else {
                                System.out.println("Kohti ei broneeritud!");
                            }
                        }
                    }
                }
            }
        }
    }

    public static Date stringKuupäevaks(String kuupäev1) {
        String[] kuupäev = kuupäev1.split("\\.");
        Date uus = new Date(Integer.parseInt(kuupäev[2]) - 1900, Integer.parseInt(kuupäev[1]) - 1, Integer.parseInt(kuupäev[0]));
        return uus;
    }

    public static String küsiInfot(String küsimus) {
        System.out.println("\n" + küsimus);
        Scanner scan = new Scanner(System.in);
        String valitud = scan.nextLine();
        return valitud;
    }
}