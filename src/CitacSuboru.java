import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CitacSuboru {
    private final MapovacTvarov registry;

    public CitacSuboru(MapovacTvarov registry) {
        this.registry = registry;
    }

    /**
     * Načíta tvary zo súboru a vráti ich ako zoznam objektov.
     * @param cestaSuboru Cesta k textovému súboru, ktorý obsahuje definície tvarov.
     * @return Zoznam tvarov (napr. Kruh, Obdlznik, Trojuholnik).
     * @throws FileNotFoundException Ak súbor neexistuje.
     */
    public List<Tvar> nacitajTvary(String cestaSuboru) throws FileNotFoundException {
        List<Tvar> tvary = new ArrayList<>();
        Scanner scanner = new Scanner(new File(cestaSuboru));

        String aktualnyTvar = null;
        Map<String, String> vlastnosti = null;

        while (scanner.hasNextLine()) {
            String riadok = scanner.nextLine().trim();

            // Ak narazíme na prázdny riadok, ukončíme aktuálny tvar
            if (riadok.isEmpty()) {
                if (aktualnyTvar != null && vlastnosti != null) {
                    Tvar tvar = vytvorTvar(aktualnyTvar, vlastnosti);
                    tvary.add(tvar);
                }
                aktualnyTvar = null;
                vlastnosti = null;
                continue;
            }

            // Ak riadok začína veľkým písmenom, ide o nový tvar
            if (Character.isUpperCase(riadok.charAt(0))) {
                aktualnyTvar = riadok.split(" ", 2)[0]; // Napr. "Kruh"
                vlastnosti = new HashMap<>();
            } else {
                // Riadok obsahuje vlastnosť (kľúč a hodnota)
                String[] casti = riadok.split(" ", 2);
                if (casti.length == 2 && vlastnosti != null) {
                    vlastnosti.put(casti[0], casti[1]);
                }
            }
        }

        // Spracujeme posledný tvar, ak existuje
        if (aktualnyTvar != null && vlastnosti != null) {
            Tvar tvar = vytvorTvar(aktualnyTvar, vlastnosti);
            tvary.add(tvar);
        }

        scanner.close();
        return tvary;
    }

    /**
     * Vytvorí tvar pomocou registrovanej fabriky.
     * @param typ Názov typu tvaru (napr. "Kruh", "Obdlznik").
     * @param vlastnosti Mapa vlastností tvaru.
     * @return Objekt typu Tvar.
     */
    private Tvar vytvorTvar(String typ, Map<String, String> vlastnosti) {
        Tvar tvar = registry.vytvorTvar(typ);
        tvar.nacitaj(vlastnosti);
        return tvar;
    }
}