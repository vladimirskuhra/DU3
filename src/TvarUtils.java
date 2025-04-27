import java.util.Map;

public class TvarUtils {
    // Zarovná prvý tvar do stredu, ostatné podľa relatívnej pozície
    public static void centrovatPrvyTvar(Map<String, Tvar> tvary, int stredX, int stredY) {
        for (Tvar tvar : tvary.values()) {
            if (tvar.getRelPozicia() == null) {
                tvar.setX(stredX);
                tvar.setY(stredY);
                break;
            }
        }
    }

    // Jednoduchý univerzálny výpočet relatívnych pozícií
    public static void vypocitajRelPozicie(Map<String, Tvar> tvary) {
        for (Tvar tvar : tvary.values()) {
            RelPozicia rel = tvar.getRelPozicia();
            if (rel == null) continue;

            Tvar ref = tvary.get(rel.referencnyTvar);
            if (ref == null) continue;

            double refX = ref.getX();
            double refY = ref.getY();
            double refPolSirka = ref.getPolovicaSirka();
            double refPolVyska = ref.getPolovicaVyska();
            double tvarPolSirka = tvar.getPolovicaSirka();
            double tvarPolVyska = tvar.getPolovicaVyska();

            double noveX = refX;
            double noveY = refY;

            switch (rel.smer.toLowerCase()) {
                case "dole":
                    noveY = refY + refPolVyska + tvarPolVyska;
                    break;
                case "hore":
                    noveY = refY - refPolVyska - tvarPolVyska;
                    break;
                case "vpravo":
                    noveX = refX + refPolSirka + tvarPolSirka;
                    break;
                case "vlavo":
                    noveX = refX - refPolSirka - tvarPolSirka;
                    break;
            }

            noveX += rel.dx;
            noveY += rel.dy;

            tvar.setX((int)Math.round(noveX));
            tvar.setY((int)Math.round(noveY));
        }
    }
}