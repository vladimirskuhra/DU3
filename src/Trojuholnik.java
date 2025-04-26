import java.util.Map;

public class Trojuholnik implements Tvar {
    private final fri.shapesge.Trojuholnik trojuholnik;

    public Trojuholnik() {
        this.trojuholnik = new fri.shapesge.Trojuholnik();
    }

    @Override
    public void nacitaj(Map<String, String> vlastnosti) {
        int x = Integer.parseInt(vlastnosti.getOrDefault("x", "0"));
        int y = Integer.parseInt(vlastnosti.getOrDefault("y", "0"));
        int vyska = Integer.parseInt(vlastnosti.getOrDefault("vyska", "30"));
        int sirka = Integer.parseInt(vlastnosti.getOrDefault("sirka", "30"));
        String farba = vlastnosti.getOrDefault("farba", "green");

        // Prepočet pozície na základe ohraničujúceho obdĺžnika
        int trojuholnikX = x + sirka / 2;
        int trojuholnikY = y;

        this.trojuholnik.zmenPolohu(trojuholnikX, trojuholnikY);
        this.trojuholnik.zmenRozmery(vyska, sirka);
        this.trojuholnik.zmenFarbu(farba);
    }

    @Override
    public void vykresli() {
        this.trojuholnik.zobraz();
    }
}