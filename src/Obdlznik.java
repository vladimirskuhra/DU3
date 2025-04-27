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

        // Apply properties to the rectangle
        this.obdlznik.zmenPolohu(x, y);
        this.obdlznik.zmenStrany(sirka, vyska);
        this.obdlznik.zmenFarbu(farba);

        if (farba.equals("brown")) {
            this.obdlznik.zmenPolohu(x, y + vyska);  // Posunieme o výšku dole
        } else {
            this.obdlznik.zmenPolohu(x, y);
        }
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
        this.obdlznik.zobraz();
    }
}