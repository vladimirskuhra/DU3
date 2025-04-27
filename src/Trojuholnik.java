public class Trojuholnik extends Tvar {
    private int sirka;
    private int vyska;
    private fri.shapesge.Trojuholnik sgTrojuholnik;

    public Trojuholnik(String meno, String farba, int x, int y, int sirka, int vyska, RelPozicia relPozicia) {
        super(TypTvaru.TROJUHOLNIK, meno, farba, x, y, relPozicia);
        this.sirka = sirka;
        this.vyska = vyska;
        this.sgTrojuholnik = new fri.shapesge.Trojuholnik(x, y);
        this.sgTrojuholnik.zmenRozmery(vyska, sirka); // POZOR: shapesge má (vyska, zakladna)
        this.sgTrojuholnik.zmenFarbu(farba);
        this.sgTrojuholnik.zmenPolohu(x, y);
    }

    public int getSirka() { return sirka; }
    public int getVyska() { return vyska; }

    public void setSirka(int sirka) {
        this.sirka = sirka;
        this.sgTrojuholnik.zmenRozmery(this.vyska, this.sirka);
    }

    public void setVyska(int vyska) {
        this.vyska = vyska;
        this.sgTrojuholnik.zmenRozmery(this.vyska, this.sirka);
    }

    @Override
    public void setFarba(String farba) {
        super.setFarba(farba);
        this.sgTrojuholnik.zmenFarbu(farba);
    }

    @Override
    public void setX(int x) {
        super.setX(x);
        sgTrojuholnik.zmenPolohu(x, this.y);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        sgTrojuholnik.zmenPolohu(this.x, y);
    }

    @Override
    public void vykresli(Object graphics) {
        sgTrojuholnik.zobraz();
    }

    @Override
    public double getPolovicaSirka() {
        return sirka / 2.0;
    }

    @Override
    public double getPolovicaVyska() {
        return vyska / 2.0;
    }
}