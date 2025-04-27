import java.util.Map;

public class Kruh extends AbstractTvar {
    private final fri.shapesge.Kruh kruh;
    private int polomer;

    public Kruh() {
        this.kruh = new fri.shapesge.Kruh();
    }

    @Override
    public void nacitaj(Map<String, String> vlastnosti, Map<String, Tvar> pomenovaneTvary) {
        int x = Integer.parseInt(vlastnosti.getOrDefault("x", "0"));
        int y = Integer.parseInt(vlastnosti.getOrDefault("y", "0"));
        int polomer = Integer.parseInt(vlastnosti.getOrDefault("polomer", "30"));
        String farba = vlastnosti.getOrDefault("farba", "blue");

        if (vlastnosti.containsKey("pozicia")) {
            String[] poziciaParams = vlastnosti.get("pozicia").split(" ");
            String smer = poziciaParams[0];
            String referencnyNazov = poziciaParams[1];

            Tvar referencnyTvar = pomenovaneTvary.get(referencnyNazov);
            if (referencnyTvar instanceof Kruh referencnyKruh) {
                int[] novaPozicia = vypocitajRelativnuPoziciu(smer, referencnyKruh);
                x = novaPozicia[0];
                y = novaPozicia[1];
            }
        }

        this.kruh.zmenPolohu(x, y);
        this.kruh.zmenPriemer(polomer * 2); // ShapesGE uses diameter
        this.kruh.zmenFarbu(farba);
    }

    @Override
    public int[] vypocitajRelativnuPoziciu(String smer, Tvar referencnyTvar) {
        int refX = referencnyTvar.getX();
        int refY = referencnyTvar.getY();
        int refSirka = referencnyTvar.getSirka();
        int refVyska = referencnyTvar.getVyska();

        // Center smaller objects on larger ones horizontally
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
        this.kruh.zobraz();
    }
}