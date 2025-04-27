

public class Kruh extends Tvar {
    private int polomer;
    private fri.shapesge.Kruh sgKruh;

    public Kruh(String meno, String farba, int x, int y, int polomer, RelPozicia relPozicia) {
        super(TypTvaru.KRUH, meno, farba, x, y, relPozicia);
        this.polomer = polomer;
        this.sgKruh = new fri.shapesge.Kruh(x, y);
        this.sgKruh.zmenPriemer(polomer);
        this.sgKruh.zmenFarbu(farba); // shapesge chce String!
        this.sgKruh.zmenPolohu(x, y);
    }

    public int getPolomer() {
        return polomer;
    }

    public void setPolomer(int polomer) {
        this.polomer = polomer;
        sgKruh.zmenPriemer(polomer);
    }

    @Override
    public void setX(int x) {
        super.setX(x);
        sgKruh.zmenPolohu(x, this.y);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        sgKruh.zmenPolohu(this.x, y);
    }

    @Override
    public void setFarba(String farbaString) {
        super.setFarba(farbaString);
        sgKruh.zmenFarbu(farbaString);
    }

    public int getPriemer() {
        return 2 * this.polomer;
    }

    @Override
    public void vykresli(Object graphics) {
        sgKruh.zobraz();
    }

    @Override
    public double getPolovicaSirka() {
        return this.getPriemer() / 2.0;
    }
    @Override
    public double getPolovicaVyska() {
        return this.getPriemer()/ 2.0;
    }
}