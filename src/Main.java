import java.util.List;

public class Main {
    public static void main(String[] args) {
        MapovacTvarov registry = new MapovacTvarov();

        // Registrácia tvarov a ich fabrík
        registry.pridajTvar("Kruh", new KruhFabrika());
        registry.pridajTvar("Obdlznik", new ObdlznikFabrika());
        registry.pridajTvar("Trojuholnik", new TrojuholnikFabrika());

        // Načítanie a spracovanie súboru
        CitacSuboru citac = new CitacSuboru(registry);
        try {
            List<Tvar> tvary = citac.nacitajTvary("src/pozicie.txt");

            // Vykreslenie tvarov
            for (Tvar tvar : tvary) {
                tvar.vykresli();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}