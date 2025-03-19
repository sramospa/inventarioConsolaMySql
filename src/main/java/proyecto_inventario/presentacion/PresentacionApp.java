package proyecto_inventario.presentacion;

import proyecto_inventario.dao.CategoriaDAO;
import proyecto_inventario.dao.MovimientoDAO;
import proyecto_inventario.dao.ProductoDAO;
import proyecto_inventario.modelo.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PresentacionApp {
    public static void main(String[] args) {
        boolean salir = false;
        IProductoDAO productoDAO = new ProductoDAO();
        Scanner consola = new Scanner(System.in);

        while (!salir){
           int opcion = mostrarMenu(consola);
           salir = ejecutarOpcion(opcion,productoDAO,consola);
        }


    }

    public static int mostrarMenu(Scanner consola){
        System.out.println("""
        *** INVENTARIO
        1. Listar Productos
        2. Buscar Productos
        3. Agregar Productos
        4. Modificar Productos
        5. Eliminar Productos
        6. Listar Categorias
        7. Listar productos por Categoria
        8. Entrada y salida de un producto
        9. Salir
        Elije una opcion:\s""");
        int opcion = consola.nextInt();
        return opcion;
    }

    public static boolean ejecutarOpcion(int opcion, IProductoDAO productoDAO,Scanner consola){
        boolean salir = false;
        switch (opcion){
            case 1 -> {
                //listar producto
                System.out.println("Listado de productos");
                List<Producto> productos = productoDAO.listarProductos();
                productos.forEach(System.out::println);
            }
            case 2 -> {
                //Buscar producto
                System.out.print("Digite el id del producto: ");
                int idProducto = consola.nextInt();
                Producto producto = new Producto(idProducto);
                boolean encontrado = productoDAO.buscarProducto(producto);
                if (encontrado){
                    System.out.println("Producto encontrado: " + producto);
                }else{
                    System.out.println("Producto no encontrado");
                }
            }
            case 3 -> {
                //Agregar producto
                Producto producto = new Producto();
                ICategoriaDAO categoriaDAO = new CategoriaDAO();
                Categoria categoriaSeleccionada = new Categoria();

                consola.nextLine();
                System.out.print("Digite el nombre: ");
                producto.setNombre(consola.nextLine());
                System.out.print("Digite Precio: ");
                producto.setPrecio(consola.nextFloat());
                List<Categoria> categorias = categoriaDAO.listarCategorias();
                categorias.forEach(System.out::println);

                System.out.println("Digite la categoria: ");
                int idCategoria = consola.nextInt();

                for(Categoria categoria : categorias){
                    if(categoria.getId() == idCategoria){
                        categoriaSeleccionada = categoria;
                        break;
                    }
                }
                producto.setCategoria(categoriaSeleccionada);


                 boolean agregado = productoDAO.agregarProducto(producto);
                if(agregado){
                    System.out.println("Producto agregado " +  producto);
                }else {
                    System.out.println("Producto no agregado");
                }
            }
            case 4 -> {
                //modificar
                System.out.print("Digite el id del producto a modificar: ");
                int idProducto = consola.nextInt();
                Producto producto = new Producto(idProducto);
                consola.nextLine();
                System.out.print("Digite el nombre a modificar: ");
                producto.setNombre(consola.nextLine());
                System.out.print("Digite el precio: ");
                producto.setPrecio(consola.nextFloat());

                System.out.println("Digite la categoria: ");
                Categoria categoria = new Categoria(consola.nextInt());
                producto.setCategoria(categoria);

                boolean modificado = productoDAO.modificarProducto(producto);
                if(modificado){
                    System.out.println("Producto modificado " +  producto);
                }else{
                    System.out.println("Producto no modificado");
                }

            }
            case 5 -> {
                //eliminar producto
                System.out.print("Digite el id del producto a eliminar: ");
                int idProducto = consola.nextInt();
                Producto producto = new Producto(idProducto);
                boolean eliminado = productoDAO.eliminarProducto(producto);
                if (eliminado){
                    System.out.println("Producto  eliminado ");
                }else{
                    System.out.println("Producto no eliminado");
                }
            }
            case 6 ->{
                //Listar las categorias
                ICategoriaDAO categoriaDAO = new CategoriaDAO();
                List<Categoria> categorias  = categoriaDAO.listarCategorias();
                categorias.forEach(System.out::println);
            }
            case 7 -> {
                //Listar producto por categoria
                System.out.print("Digite la categoria: ");
                int idCategoria = consola.nextInt();
                List<Producto> productos = productoDAO.listarProductosPorCategoria(idCategoria);
                productos.forEach(System.out::println);
            }
            case 8 -> {
                IMovimientoDAO movimientoDAO = new MovimientoDAO();
                Movimiento movimiento = new Movimiento();
                System.out.println("Digite el id del producto");
                Producto producto = new Producto(consola.nextInt());
                consola.nextLine();
                System.out.println("Tipo de operacion E/S:");
                String tpoOperacion = consola.nextLine();
                while (tpoOperacion.equals(Movimiento.TIPO_ENTRADA) && tpoOperacion.equals(Movimiento.TIPO_SALIDA)) {
                    System.out.println("Digite un tipo de entrada valido E/S");
                     tpoOperacion = consola.nextLine();
                }
                movimiento.setTipo(tpoOperacion);
                movimiento.setProducto(producto);
                System.out.println("Digite la cantidad a entrar");
                movimiento.setCantidad(consola.nextInt());
                movimiento.setFecha_movimiento(LocalDate.now());

                boolean entrada = movimientoDAO.entradaProducto(movimiento);
                if(entrada){
                    System.out.println("Entrada registrada " + movimiento);
                }else{
                    System.out.println("Entrada no registrada");
                }

                boolean actualizaStock = productoDAO.actualizarStockProducto(movimiento);
                if (actualizaStock){
                    System.out.println("Stock del producto actualizado");
                }else{
                    System.out.println("No actualizo el stock del producto");
                }





            }
        }
        System.out.println("salir " + salir);
        return salir;
    }
}
