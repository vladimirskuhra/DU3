import java.util.Map;

public class Trojuholnik extends AbstractTvar {
    private final fri.shapesge.Trojuholnik trojuholnik;

    public Trojuholnik() {
        this.trojuholnik = new fri.shapesge.Trojuholnik();
    }

    @Override
    public void nacitaj(Map<String, String> vlastnosti, Map<String, Tvar> pomenovaneTvary) {
        spracujZakladneVlastnosti(vlastnosti, pomenovaneTvary);

        sirka = Integer.parseInt(vlastnosti.getOrDefault("sirka", "30"));
        vyska = Integer.parseInt(vlastnosti.getOrDefault("vyska", "30"));
        String farba = vlastnosti.getOrDefault("farba", "green");

        // Uprava súradníc
        int adjustedX = x + sirka / 2;
        int adjustedY = y + vyska;

        this.trojuholnik.zmenPolohu(adjustedX, adjustedY);
        this.trojuholnik.zmenRozmery(vyska, sirka);
        this.trojuholnik.zmenFarbu(farba);

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
        this.trojuholnik.zobraz();
    }
}