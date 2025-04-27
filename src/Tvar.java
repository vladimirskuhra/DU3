import java.awt.Color;

public abstract class Tvar {
    protected TypTvaru typ;
    protected String meno;
    protected String farbaString; // uložíme aj textovú farbu (napr. "red")
    protected Color farba;        // aj Color objekt
    protected int x;
    protected int y;
    protected RelPozicia relPozicia;

    public Tvar(TypTvaru typ, String meno, String farbaString, int x, int y, RelPozicia relPozicia) {
        this.typ = typ;
        this.meno = meno;
        this.farbaString = farbaString;
        this.farba = Farby.fromString(farbaString); // prevod na Color
        this.x = x;
        this.y = y;
        this.relPozicia = relPozicia;
    }

    public void setFarba(String farbaString) {
        this.farbaString = farbaString;
        this.farba = Farby.fromString(farbaString);
    }

    public String getFarbaString() {
        return farbaString;
    }
    public Color getFarba() {
        return farba;
    }

    public TypTvaru getTyp() {
        return typ;
    }
    public String getMeno() {
        return meno;
    }
    public void setMeno(String meno) {
        this.meno = meno;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public RelPozicia getRelPozicia() {
        return relPozicia;
    }
    public void setRelPozicia(RelPozicia relPozicia) {
        this.relPozicia = relPozicia;
    }

    public abstract void vykresli(Object graphics);

    public abstract double getPolovicaSirka();


    public abstract double getPolovicaVyska();
}