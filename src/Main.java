import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Zadaj cestu k svojmu vstupnému súboru
        String subor = "src/snehuliak.txt";
        // 1. Načítaj všetky tvary
        Map<String, Tvar> tvary = Parser.nacitajTvaryZoSuboru(subor);

        // 2. Zarovnaj prvý tvar (napr. hlavu) do stredu scény
        TvarUtils.centrovatPrvyTvar(tvary, 200, 120);

        // 3. Vypočítaj relatívne pozície ostatných tvarov
        TvarUtils.vypocitajRelPozicie(tvary);

        // 4. Vypíš info o tvaroch (alebo ich pošli do vykresľovania)
        for (Tvar tvar : tvary.values()) {
            System.out.println(tvar);
        }

        // Ak máš vykresľovacie okno, tu by si zavolal draw metódy,
        // kde budeš tvary z mapy posielať na plátno.
    }
}