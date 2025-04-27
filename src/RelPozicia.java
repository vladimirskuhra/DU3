public class RelPozicia {
    public String smer;          // napr. "hore", "dole", "vpravo", "vlavo"
    public String referencnyTvar; // meno iného tvaru (napr. "hlava")
    public int dx = 0;           // posun x (voliteľný)
    public int dy = 0;           // posun y (voliteľný)

    public RelPozicia(String smer, String referencnyTvar, int dx, int dy) {
        this.smer = smer;
        this.referencnyTvar = referencnyTvar;
        this.dx = dx;
        this.dy = dy;
    }

    public RelPozicia(String smer, String referencnyTvar) {
        this(smer, referencnyTvar, 0, 0);
    }
}