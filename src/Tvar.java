import java.util.Map;

public interface Tvar {
    void nacitaj(Map<String, String> vlastnosti, Map<String, Tvar> pomenovaneTvary);
    void vykresli();
    int[] vypocitajRelativnuPoziciu(String smer, Tvar referencnyTvar);

    int getX();
    int getY();
    int getSirka();
    int getVyska();
}