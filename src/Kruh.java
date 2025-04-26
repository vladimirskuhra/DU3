
import java.util.Map;

public class Kruh implements Tvar {
    private final fri.shapesge.Kruh kruh;

    public Kruh() {
        this.kruh = new fri.shapesge.Kruh();
    }

    @Override
    public void nacitaj(Map<String, String> vlastnosti) {
        int x = Integer.parseInt(vlastnosti.getOrDefault("x", "0"));
        int y = Integer.parseInt(vlastnosti.getOrDefault("y", "0"));
        int polomer = Integer.parseInt(vlastnosti.getOrDefault("polomer", "30"));
        String farba = vlastnosti.getOrDefault("farba", "blue");

        this.kruh.zmenPolohu(x, y);
        this.kruh.zmenPriemer(polomer);
        this.kruh.zmenFarbu(farba);
    }

    @Override
    public void vykresli() {
        this.kruh.zobraz();
    }
}