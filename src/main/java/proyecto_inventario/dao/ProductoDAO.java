package proyecto_inventario.dao;

import com.mysql.cj.util.DnsSrv;
import proyecto_inventario.conexion.Conexion;
import proyecto_inventario.modelo.Categoria;
import proyecto_inventario.modelo.IProductoDAO;
import proyecto_inventario.modelo.Movimiento;
import proyecto_inventario.modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements IProductoDAO
{

    @Override
    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();
        PreparedStatement ps; //prepara la sentencia de sql hacia la bd
        ResultSet rs; //contiene el resultado de la consulta
        Connection con = Conexion.getConexion();
        String sql = "SELECT p.id,p.nombre,p.precio,p.cantidad,c.id idcategoria,c.nombre nombrecategoria FROM productos p JOIN categorias c ON p.categoria = c.id";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                Categoria categoria = new Categoria(rs.getInt("idcategoria"),rs.getString("nombrecategoria"));

                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setCategoria(categoria);
                productos.add(producto);


            }
        }catch (Exception e){
            System.out.println("Error al listar clientes " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerra la conexion " + e.getMessage());
            }
        }
        return productos;
    }

    @Override
    public boolean buscarProducto(Producto producto) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        String sql = "SELECT p.id,p.nombre,p.precio,p.cantidad,c.id idcategoria,c.nombre nombrecategoria FROM productos p JOIN categorias c ON p.categoria = c.id WHERE p.id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1,producto.getId());
            rs = ps.executeQuery();
            if (rs.next()){
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setCantidad(rs.getInt("cantidad"));

                Categoria categoria = new Categoria(rs.getInt("idcategoria"),rs.getString("nombrecategoria"));
                producto.setCategoria(categoria);
                return true;
            }

        }catch (Exception e){
            System.out.println("Error al consultar el producto por el id " + e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion");
            }
        }
        return false;
    }

    @Override
    public boolean agregarProducto(Producto producto) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        String sql = "INSERT INTO productos (nombre,precio,cantidad,categoria) VALUES (?,?,?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,producto.getNombre());
            ps.setFloat(2,producto.getPrecio());
            ps.setInt(3,producto.getCantidad());
            ps.setInt(4,producto.getCategoria().getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al insertar un producto " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion");
            }
        }
        return false;
    }

    @Override
    public boolean modificarProducto(Producto producto) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        String sql = "UPDATE productos SET nombre = ?,precio =?,categoria = ? WHERE id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,producto.getNombre());
            ps.setFloat(2,producto.getPrecio());
            ps.setInt(3,producto.getCategoria().getId());
            ps.setInt(4,producto.getId());
            ps.execute();
            return  true;
        }catch (Exception e){
            System.out.println("Error al modifica el producto " + e.getMessage()) ;
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion");
            }

        }
        return false;
    }

    @Override
    public boolean eliminarProducto(Producto producto) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        String sql = "DELETE FROM productos WHERE id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1,producto.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al eliminar el producto " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al eliminar el producto " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public List<Producto> listarProductosPorCategoria(int idCategoria) {
        List<Producto> productos = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        String sql = "SELECT * FROM productos WHERE categoria = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1,idCategoria);
            rs = ps.executeQuery();
            while (rs.next()){
               Producto producto = new Producto();
               producto.setNombre(rs.getString("nombre"));
               producto.setPrecio(rs.getFloat("precio"));

               Categoria categoria = new Categoria(rs.getInt("categoria"));
               producto.setCategoria(categoria);
               productos.add(producto);
            }
        }catch (Exception e){
            System.out.println("Error al consultar los productos por categoria");
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion");
            }
        }
        return productos;
    }

    @Override
    public boolean actualizarStockProducto(Movimiento movimiento) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        String sql = "UPDATE PRODUCTOS set cantidad = cantidad + ? WHERE id = ?";
        try{
            ps = con.prepareStatement(sql);

            int cantidadStock = movimiento.getTipo().equals(movimiento.TIPO_ENTRADA) ? movimiento.getCantidad() : -movimiento.getCantidad();

            ps.setInt(1,cantidadStock);
            ps.setInt(2,movimiento.getProducto().getId());
            ps.execute();
            return  true;
        }catch (Exception e){
            System.out.println("Error al actualizar el stock del producto " + e.getMessage());
        }finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion");
            }
        }
        return false;
    }


    public static void main(String[] args) {
        //Listar productos
        IProductoDAO productoDAO = new ProductoDAO();
//
//        //Listar clientes
        System.out.println("Listar productos");
       List<Producto> producto =productoDAO.listarProductos();
        producto.forEach(System.out::println);

        //Buscar producto
//        System.out.println("Buscar por producto por id");
//        Producto producto = new Producto(1);
//        boolean encontrado = productoDAO.buscarProducto(producto);
//        if (encontrado){
//            System.out.println("Se encontro el producto " + producto);
//        }else{
//            System.out.println("No se encontro el producto " +  producto.getId());
//        }

        //agregar un producto
//        Categoria categoria = new Categoria(2,"Tecnologia");
//        Producto producto = new Producto("Portatil",4500000,0,categoria);
//        boolean agregado = productoDAO.agregarProducto(producto);
//        if (agregado){
//            System.out.println("Producto agregado " + producto);
//        }else{
//            System.out.println("Producto no agregado");
//        }

        //Modifica producto
//        Categoria categoria = new Categoria(2,"Tecnologia");
//        Producto producto = new Producto(2,"Portatil",4500000,0,categoria);
//        boolean modificado = productoDAO.modificarProducto(producto);
//        if(modificado){
//            System.out.println("Producto modificado " + producto);
//        }else{
//            System.out.println("Producto no modificado");
//        }

        //Eliminar el producto
//        Producto producto = new Producto(1);
//        boolean eliminado = productoDAO.eliminarProducto(producto);
//        if (eliminado){
//            System.out.println("Producto eliminado " );
//        }else{
//            System.out.println("Producto no eliminado");
//        }



    }


}
