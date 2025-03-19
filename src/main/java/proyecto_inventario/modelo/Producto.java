package proyecto_inventario.modelo;

import proyecto_inventario.util.Utilidades;

public class Producto {
    private int id;
    private String nombre;
    private float precio;
    private int cantidad = 0;
    private  Categoria categoria;

    private static int totalProductos = 0; //contador de productos creados


    public Producto (){

    }

    public Producto(int id){
        this.id = id;
    }
    public Producto(int id, String nombre, float precio, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
       // this.cantidad = cantidad;
        this.categoria = categoria;

    }

    public Producto (String nombre, float precio, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        //this.cantidad = cantidad;
        this.categoria  = categoria;
        totalProductos ++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }


    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public static int getTotalProductos() {
        return totalProductos;
    }

    public static void setTotalProductos(int totalProductos) {
        Producto.totalProductos = totalProductos;
    }

    @Override
    public String toString() {
        double precioConIva = Utilidades.calcularPrecioConIva(precio);

        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio base=" + precio +
                ", precio con Iva =" + precioConIva +
                ", cantidad=" + cantidad +
                ", categoria=" + categoria.getId() +
                "-"+ categoria.getNombre() +
                '}';
    }


}
