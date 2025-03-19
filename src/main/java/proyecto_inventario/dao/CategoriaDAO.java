package proyecto_inventario.dao;

import proyecto_inventario.conexion.Conexion;
import proyecto_inventario.modelo.Categoria;
import proyecto_inventario.modelo.ICategoriaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements ICategoriaDAO {
    @Override
    public List<Categoria> listarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        String sql = "SELECT * FROM categorias";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }
        }catch (Exception e){
            System.out.println("Error al consultar las categorias");
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerra la conexion");
            }
        }
        return categorias;
    }

    @Override
    public boolean buscarCategoria(Categoria categoria) {
        return false;
    }

    @Override
    public boolean agregarCategoria(Categoria categoria) {
        return false;
    }

    @Override
    public boolean modificarCategoria(Categoria categoria) {
        return false;
    }

    @Override
    public boolean eliminarCategoria(Categoria categoria) {
        return false;
    }
}
