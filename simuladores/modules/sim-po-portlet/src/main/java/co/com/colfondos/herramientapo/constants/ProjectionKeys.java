package co.com.colfondos.herramientapo.constants;

public class ProjectionKeys {

    public static final String CURRENT = "actual";

    public static final String CONSERVATIVE = "conservador";

    public static final String EXPECTED = "esperado";

    public static final String OPTIMISTIC = "optimistico";

    public static String getProjectionName(int projection) {
        switch (projection) {
            case 0: return CURRENT;
            case 1: return EXPECTED;
            case 2: return CONSERVATIVE;
            case 3: return OPTIMISTIC;
            default: return "";
        }
    }
}
