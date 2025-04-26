public class TvorcaKruhu implements FabrikaTvarov {
    @Override
    public Tvar vytvorTvar() {
        return new Kruh();
    }
}
