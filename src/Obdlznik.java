import java.util.Map;

public class Obdlznik implements Tvar {
    private final fri.shapesge.Obdlznik obdlznik;

    public Obdlznik() {
        this.obdlznik = new fri.shapesge.Obdlznik();
    }

    @Override
    public void nacitaj(Map<String, String> vlastnosti) {
        int x = Integer.parseInt(vlastnosti.getOrDefault("x", "0"));
        int y = Integer.parseInt(vlastnosti.getOrDefault("y", "0"));
        int sirka = Integer.parseInt(vlastnosti.getOrDefault("rozmery", "30").split(" ")[0]);
        int vyska = Integer.parseInt(vlastnosti.getOrDefault("rozmery", "30").split(" ")[1]);
        String farba = vlastnosti.getOrDefault("farba", "red");

        this.obdlznik.zmenPolohu(x, y);
        this.obdlznik.zmenStrany(sirka, vyska);
        this.obdlznik.zmenFarbu(farba);
    }

    @Override
    public void vykresli() {
        this.obdlznik.zobraz();
    }
}