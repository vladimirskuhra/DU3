import java.util.Map;

public class Kruh implements Tvar {
    private final fri.shapesge.Kruh kruh;

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
                int[] novaPozicia = vypocitajRelativnuPoziciu(referencnyKruh, smer);
                x = novaPozicia[0];
                y = novaPozicia[1];
            }
        }

        this.kruh.zmenPolohu(x, y);
        this.kruh.zmenPriemer(polomer * 2); // ShapesGE používa priemer
        this.kruh.zmenFarbu(farba);
    }

    @Override
    public void vykresli() {
        this.kruh.zobraz();
    }

    private int[] vypocitajRelativnuPoziciu(Kruh referencnyKruh, String smer) {
        int refX = referencnyKruh.kruh.getX();
        int refY = referencnyKruh.kruh.getY();
        int refPriemer = referencnyKruh.kruh.getPriemer();

        switch (smer) {
            case "hore":
                return new int[]{refX, refY - refPriemer};
            case "dole":
                return new int[]{refX, refY + refPriemer};
            case "vpravo":
                return new int[]{refX + refPriemer, refY};
            case "vlavo":
                return new int[]{refX - refPriemer, refY};
            default:
                throw new IllegalArgumentException("Neplatná hodnota smeru: " + smer);
        }
    }
}