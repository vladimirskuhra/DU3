public class Obdlznik extends Tvar {
    private int sirka;
    private int vyska;
    private fri.shapesge.Obdlznik sgObdlznik;

    public Obdlznik(String meno, String farba, int x, int y, int sirka, int vyska, RelPozicia relPozicia) {
        super(TypTvaru.OBDLZNIK, meno, farba, x, y, relPozicia);
        this.sirka = sirka;
        this.vyska = vyska;
        this.sgObdlznik = new fri.shapesge.Obdlznik(x, y);
        this.sgObdlznik.zmenStrany(sirka, vyska);
        this.sgObdlznik.zmenFarbu(farba);
        this.sgObdlznik.zmenPolohu(x, y);
    }

    public int getSirka() { return sirka; }
    public int getVyska() { return vyska; }

    public void setSirka(int sirka) {
        this.sirka = sirka;
        this.sgObdlznik.zmenStrany(this.sirka, this.vyska);
    }

    public void setVyska(int vyska) {
        this.vyska = vyska;
        this.sgObdlznik.zmenStrany(this.sirka, this.vyska);
    }

    @Override
    public void setFarba(String farba) {
        super.setFarba(farba);
        sgObdlznik.zmenFarbu(farba);
    }

    @Override
    public void setX(int x) {
        super.setX(x);
        sgObdlznik.zmenPolohu(x, this.y);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        sgObdlznik.zmenPolohu(this.x, y);
    }

    @Override
    public void vykresli(Object graphics) {
        sgObdlznik.zobraz();
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