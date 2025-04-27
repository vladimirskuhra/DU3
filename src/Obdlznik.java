import java.util.Map;

public class Obdlznik extends AbstractTvar {
    private final fri.shapesge.Obdlznik obdlznik;

    public Obdlznik() {
        this.obdlznik = new fri.shapesge.Obdlznik();
    }

    @Override
    public void nacitaj(Map<String, String> vlastnosti, Map<String, Tvar> pomenovaneTvary) {
        spracujZakladneVlastnosti(vlastnosti, pomenovaneTvary);

        // Process rectangle-specific properties
        String[] rozmery = vlastnosti.getOrDefault("rozmery", "30 30").split(" ");
        sirka = Integer.parseInt(rozmery[0]);
        vyska = Integer.parseInt(rozmery[1]);
        String farba = vlastnosti.getOrDefault("farba", "red");

        // Apply properties to the rectangle - no special cases for colors
        this.obdlznik.zmenPolohu(x, y);
        this.obdlznik.zmenStrany(sirka, vyska);
        this.obdlznik.zmenFarbu(farba);
    }

    @Override
    public int[] vypocitajRelativnuPoziciu(String smer, Tvar referencnyTvar) {
        int refX = referencnyTvar.getX();
        int refY = referencnyTvar.getY();
        int refSirka = referencnyTvar.getSirka();
        int refVyska = referencnyTvar.getVyska();

        // Center rectangles horizontally when placed above or below
        int centerOffset = (refSirka - sirka) / 2;

        switch (smer) {
            case "hore": return new int[]{refX + centerOffset, refY - vyska};
            case "dole": return new int[]{refX + centerOffset, refY + refVyska};
            case "vpravo": return new int[]{refX + refSirka, refY + (refVyska - vyska) / 2};
            case "vlavo": return new int[]{refX - sirka, refY + (refVyska - vyska) / 2};
            default: throw new IllegalArgumentException("Neplatná hodnota smeru: " + smer);
        }
    }

    @Override
    public void vykresli() {
        this.obdlznik.zobraz();
    }
}