package proyecto_inventario.modelo;

import java.util.ArrayList;
import java.util.List;

public interface ICategoriaDAO {
    List<Categoria>  listarCategorias();
    boolean buscarCategoria(Categoria categoria);
    boolean agregarCategoria(Categoria categoria);
    boolean modificarCategoria(Categoria categoria);
    boolean eliminarCategoria(Categoria categoria);


}
