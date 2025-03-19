package proyecto_inventario.modelo;

import java.util.List;

public interface IProductoDAO {
    List<Producto> listarProductos();
    boolean buscarProducto(Producto producto);
    boolean agregarProducto(Producto producto);
    boolean modificarProducto(Producto producto);
    boolean eliminarProducto(Producto producto);
    List<Producto> listarProductosPorCategoria(int idCategoria);
    boolean actualizarStockProducto(Movimiento movimiento);
}
