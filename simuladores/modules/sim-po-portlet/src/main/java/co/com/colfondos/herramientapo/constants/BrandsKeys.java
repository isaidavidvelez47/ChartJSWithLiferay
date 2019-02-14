package co.com.colfondos.herramientapo.constants;

public class BrandsKeys {

    public static final String COLFONDOS = "Colfondos";

    public static final String PORVENIR = "Porvenir";

    public static final String PROTECCION = "Proteccion";

    public static final String OLD_MUTUAL = "Old Mutual";

    public static String PORTFOLIO;


    public static final int N_COLFONDOS = 0;
    public static final int N_PORVENIR = 1;
    public static final int N_PROTECCION = 2;
    public static final int N_OLD_MUTUAL = 3;
    public static final int N_PORTFOLIO = 4;

    public static int getBrandNumber(String brand) {
        switch (brand) {
            case COLFONDOS: return N_COLFONDOS;
            case PORVENIR: return N_PORVENIR;
            case PROTECCION: return N_PROTECCION;
            case OLD_MUTUAL: return N_OLD_MUTUAL;
            default: return -1;
        }
    }
}
