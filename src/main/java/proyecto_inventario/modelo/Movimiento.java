package proyecto_inventario.modelo;

import java.time.LocalDate;

public class Movimiento {

    public static final String TIPO_ENTRADA = "E";
    public static final String TIPO_SALIDA = "S";

    private int id_movimiento;
    private Producto producto;
    private String tipo;
    private int cantidad;
    private LocalDate fecha_movimiento;

    public Movimiento(){

    }
    public Movimiento(Producto producto, String tipo, int cantidad, LocalDate fecha_movimiento) {
        this.producto = producto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha_movimiento = fecha_movimiento;
    }

    public int getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(int id_movimiento) {
        this.id_movimiento = id_movimiento;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFecha_movimiento() {
        return fecha_movimiento;
    }

    public void setFecha_movimiento(LocalDate fecha_movimiento) {
        this.fecha_movimiento = fecha_movimiento;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "id_movimiento=" + id_movimiento +
                ", producto=" + producto.getId() + producto.getNombre() +
                ", tipo='" + tipo + '\'' +
                ", cantidad=" + cantidad +
                ", fecha_movimiento=" + fecha_movimiento +
                '}';
    }
}
