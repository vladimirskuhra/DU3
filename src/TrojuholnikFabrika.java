public class TrojuholnikFabrika implements FabrikaTvarov {
    @Override
    public Tvar vytvorTvar() {
        return new Trojuholnik();
    }
}
