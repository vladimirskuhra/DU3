import java.util.*;
import java.io.*;

public class Parser {
    public static Map<String, Tvar> nacitajTvaryZoSuboru(String subor) {
        Map<String, Tvar> tvary = new LinkedHashMap<>();
        Map<TypTvaru, Integer> pocitadlo = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(subor))) {
            String riadok;
            List<String> blok = new ArrayList<>();
            while ((riadok = br.readLine()) != null) {
                riadok = riadok.trim();
                if (riadok.isEmpty()) {
                    spracujBlok(blok, tvary, pocitadlo);
                    blok.clear();
                } else if (!riadok.startsWith("#")) {
                    blok.add(riadok);
                }
            }
            if (!blok.isEmpty()) {
                spracujBlok(blok, tvary, pocitadlo);
            }
        } catch (Exception e) {
            System.err.println("Chyba pri čítaní/parsing súboru: " + e.getMessage());
            e.printStackTrace();
        }
        return tvary;
    }

    private static void spracujBlok(List<String> blok, Map<String, Tvar> tvary, Map<TypTvaru, Integer> pocitadlo) {
        if (blok.isEmpty()) return;
        try {
            String hlavicka = blok.get(0);
            String[] hlavickaTok = hlavicka.split("\\s+");
            String typText = hlavickaTok[0];
            String meno;
            TypTvaru typ;
            try {
                typ = TypTvaru.valueOf(typText.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.err.println("Neznámy typ tvaru: " + typText);
                return;
            }
            // Ak v hlavicke nie je meno, vygeneruj automaticky
            if (hlavickaTok.length < 2) {
                int count = pocitadlo.getOrDefault(typ, 0) + 1;
                meno = typText.toLowerCase() + count;
                pocitadlo.put(typ, count);
            } else {
                meno = hlavickaTok[1];
            }

            int x = 0, y = 0, priemer = 0, sirka = 0, vyska = 0;
            String farba = "black";
            RelPozicia relPozicia = null;
            String smer = null, ref = null;
            int dx = 0, dy = 0;

            for (int i = 1; i < blok.size(); i++) {
                String riadok = blok.get(i);
                String[] t = riadok.split("\\s+");
                if (t.length < 2) continue;
                switch (t[0].toLowerCase()) {
                    case "polomer":
                        priemer = 2 * Integer.parseInt(t[1]);
                        break;
                    case "priemer":
                        priemer = Integer.parseInt(t[1]);
                        break;
                    case "rozmery":
                        if (t.length >= 3) {
                            sirka = Integer.parseInt(t[1]);
                            vyska = Integer.parseInt(t[2]);
                        }
                        break;
                    case "sirka":
                        sirka = Integer.parseInt(t[1]);
                        break;
                    case "vyska":
                        vyska = Integer.parseInt(t[1]);
                        break;
                    case "farba":
                        farba = t[1];
                        break;
                    case "x":
                        x = Integer.parseInt(t[1]);
                        break;
                    case "y":
                        y = Integer.parseInt(t[1]);
                        break;
                    case "pozicia":
                        smer = t[1];
                        ref = t[2];
                        if (t.length > 3) dx = Integer.parseInt(t[3]);
                        if (t.length > 4) dy = Integer.parseInt(t[4]);
                        relPozicia = new RelPozicia(smer, ref, dx, dy);
                        x = 0; y = 0;
                        break;
                }
            }

            Tvar tvar = null;
            switch (typ) {
                case KRUH:
                    tvar = new Kruh(meno, farba, x, y, priemer, relPozicia);
                    break;
                case OBDLZNIK:
                    tvar = new Obdlznik(meno, farba, x, y, sirka, vyska, relPozicia);
                    break;
                case TROJUHOLNIK:
                    tvar = new Trojuholnik(meno, farba, x, y, sirka, vyska, relPozicia);
                    break;
            }
            if (tvar != null) {
                tvary.put(meno, tvar);
            }
        } catch (Exception e) {
            System.err.println("Chyba pri spracovaní bloku: " + e.getMessage());
        }
    }
}