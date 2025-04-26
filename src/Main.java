import java.util.List;

public class Main {
    public static void main(String[] args) {
        MapovacTvarov registry = new MapovacTvarov();

        // Registrujeme všetky tvary a ich fabriky
        registry.pridajTvar("Kruh", new KruhFabrika());
        registry.pridajTvar("Obdlznik", new ObdlznikFabrika());
        registry.pridajTvar("Trojuholnik", new TrojuholnikFabrika());

        // Načítame tvary zo súboru a zobrazíme ich
        CitacSuboru citac = new CitacSuboru(registry);
        try {
            List<Tvar> tvary = citac.nacitajTvary("snehuliak.txt");
            for (Tvar tvar : tvary) {
                tvar.vykresli();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

