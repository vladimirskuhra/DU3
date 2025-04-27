import java.util.Map;

public abstract class AbstractTvar implements Tvar {
    protected int x, y;
    protected int sirka, vyska;

    protected void spracujZakladneVlastnosti(Map<String, String> vlastnosti,
                                             Map<String, Tvar> pomenovaneTvary) {
        // Predvolené sú 0
        int dx = Integer.parseInt(vlastnosti.getOrDefault("x", "0"));
        int dy = Integer.parseInt(vlastnosti.getOrDefault("y", "0"));

        if (vlastnosti.containsKey("pozicia")) {
            String[] poziciaParams = vlastnosti.get("pozicia").split(" ");
            String smer = poziciaParams[0];
            String referencnyNazov = poziciaParams[1];

            Tvar referencnyTvar = pomenovaneTvary.get(referencnyNazov);
            int[] novaPozicia = vypocitajRelativnuPoziciu(smer, referencnyTvar);
            x = novaPozicia[0] + dx;
            y = novaPozicia[1] + dy;
        } else {
            x = dx;
            y = dy;
        }
    }


    @Override
    public int getX() { return x; }
    @Override
    public int getY() { return y; }
    @Override
    public int getSirka() { return sirka; }
    @Override
    public int getVyska() { return vyska; }
}