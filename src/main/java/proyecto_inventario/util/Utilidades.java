package proyecto_inventario.util;

public class Utilidades {
    private static final double IVA = 0.19;

    public static double calcularPrecioConIva(double precio){
        return precio + (precio * IVA);
    }
}
