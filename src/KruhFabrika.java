public class KruhFabrika implements FabrikaTvarov {
    @Override
    public Tvar vytvorTvar() {
        return new Kruh();
    }
}
