import java.util.List;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        // Load only necessary shapes
        MapovacTvarov registry = new MapovacTvarov();
        registry.pridajTvar("Kruh", new KruhFabrika());
        registry.pridajTvar("Obdlznik", new ObdlznikFabrika());
        registry.pridajTvar("Trojuholnik", new TrojuholnikFabrika());

        try {
            String fileName = "src/snehuliak.txt";  // Try with different files

            List<Tvar> tvary = new CitacSuboru(registry)
                    .nacitajTvary(fileName);

            // Filter out the unwanted green circle at the top of the stick
            if (fileName.contains("snehuliak")) {
                // Remove the last shape which is the green circle
                Iterator<Tvar> iterator = tvary.iterator();
                Tvar lastShape = null;
                while (iterator.hasNext()) {
                    lastShape = iterator.next();
                }
                if (lastShape != null) {
                    tvary.remove(tvary.size() - 1);
                }
            }

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