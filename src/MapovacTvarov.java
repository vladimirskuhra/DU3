import java.util.HashMap;
import java.util.Map;

public class MapovacTvarov {
    private final Map<String, FabrikaTvarov> mapa;

    public MapovacTvarov() {
        mapa = new HashMap<>();
    }

    public void pridajTvar(String typTvaru, FabrikaTvarov fabrika) {
        mapa.put(typTvaru, fabrika);
    }

    public Tvar vytvorTvar(String typTvaru) {
        FabrikaTvarov fabrika = mapa.get(typTvaru);
        if (fabrika == null) {
            throw new IllegalArgumentException("neznamy typ tvaru: " + typTvaru);
        }
        return fabrika.vytvorTvar();
    }
}
