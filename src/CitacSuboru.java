import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CitacSuboru {
    private final MapovacTvarov registry;
    private final Map<String, Tvar> pomenovaneTvary;

    public CitacSuboru(MapovacTvarov registry) {
        this.registry = registry;
        this.pomenovaneTvary = new HashMap<>();
    }

    public List<Tvar> nacitajTvary(String cestaSuboru) throws FileNotFoundException {
        List<Tvar> tvary = new ArrayList<>();
        Scanner scanner = new Scanner(new File(cestaSuboru));

        String aktualnyTvar = null;
        Map<String, String> vlastnosti = null;

        while (scanner.hasNextLine()) {
            String riadok = scanner.nextLine().trim();

            if (riadok.isEmpty()) {
                if (aktualnyTvar != null && vlastnosti != null) {
                    Tvar tvar = vytvorTvar(aktualnyTvar, vlastnosti);
                    tvary.add(tvar);

                    // Ak má tvar názov, uložíme ho
                    if (vlastnosti.containsKey("nazov")) {
                        pomenovaneTvary.put(vlastnosti.get("nazov"), tvar);
                    }
                }
                aktualnyTvar = null;
                vlastnosti = null;
                continue;
            }

            if (Character.isUpperCase(riadok.charAt(0))) {
                aktualnyTvar = riadok.split(" ")[0];
                vlastnosti = new HashMap<>();
                if (riadok.split(" ").length > 1) {
                    vlastnosti.put("nazov", riadok.split(" ")[1]);
                }
            } else {
                String[] casti = riadok.split(" ", 2);
                if (casti.length == 2) {
                    vlastnosti.put(casti[0], casti[1]);
                }
            }
        }

        if (aktualnyTvar != null && vlastnosti != null) {
            Tvar tvar = vytvorTvar(aktualnyTvar, vlastnosti);
            tvary.add(tvar);
            if (vlastnosti.containsKey("nazov")) {
                pomenovaneTvary.put(vlastnosti.get("nazov"), tvar);
            }
        }

        scanner.close();
        return tvary;
    }

    private Tvar vytvorTvar(String typ, Map<String, String> vlastnosti) {
        Tvar tvar = registry.vytvorTvar(typ);
        if (tvar == null) {
            throw new IllegalArgumentException("Tvar typu '" + typ + "' nebol registrovaný.");
        }

        tvar.nacitaj(vlastnosti, pomenovaneTvary);
        return tvar;
    }
}