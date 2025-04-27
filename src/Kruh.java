import java.util.Map;

public class Kruh extends AbstractTvar {
    private final fri.shapesge.Kruh kruh;
    private int priemer;

    public Kruh() {
        this.kruh = new fri.shapesge.Kruh();
        this.sirka = 0; // Will be set based on diameter
        this.vyska = 0;
    }

    @Override
    public void nacitaj(Map<String, String> vlastnosti, Map<String, Tvar> pomenovaneTvary) {
        spracujZakladneVlastnosti(vlastnosti, pomenovaneTvary);
        priemer = Integer.parseInt(vlastnosti.getOrDefault("polomer", "30")) * 2;
        this.sirka = priemer;
        this.vyska = priemer;
        String farba = vlastnosti.getOrDefault("farba", "blue");

        this.kruh.zmenPolohu(x, y);
        this.kruh.zmenPriemer(priemer);
        this.kruh.zmenFarbu(farba);
    }

    @Override
    public int[] vypocitajRelativnuPoziciu(String smer, Tvar referencnyTvar) {
        int refX = referencnyTvar.getX();
        int refY = referencnyTvar.getY();
        int refSirka = referencnyTvar.getSirka();
        int refVyska = referencnyTvar.getVyska();

        switch (smer) {
            case "hore": return new int[]{refX, refY - vyska};
            case "dole": return new int[]{refX, refY + refVyska};
            case "vpravo": return new int[]{refX + refSirka, refY};
            case "vlavo": return new int[]{refX - sirka, refY};
            default: throw new IllegalArgumentException("Neplatná hodnota smeru: " + smer);
        }
    }

    @Override
    public void vykresli() {
        this.kruh.zobraz();
    }
}