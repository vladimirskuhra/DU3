import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Load only necessary shapes
        MapovacTvarov registry = new MapovacTvarov();
        registry.pridajTvar("Kruh", new KruhFabrika());
        registry.pridajTvar("Obdlznik", new ObdlznikFabrika());
        registry.pridajTvar("Trojuholnik", new TrojuholnikFabrika());

        try {
            List<Tvar> tvary = new CitacSuboru(registry)
                    .nacitajTvary("src/pozicie-trojuholniky.txt");

            // Process shapes in batches if file is large
            for (Tvar tvar : tvary) {
                tvar.vykresli();
            }
        } catch (Exception e) {
            System.err.println("Chyba: " + e.getMessage());
            e.printStackTrace();
        }
    }
}