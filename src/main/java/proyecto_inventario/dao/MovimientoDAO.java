package proyecto_inventario.dao;

import proyecto_inventario.conexion.Conexion;
import proyecto_inventario.modelo.IMovimientoDAO;
import proyecto_inventario.modelo.Movimiento;
import proyecto_inventario.presentacion.PresentacionApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MovimientoDAO implements IMovimientoDAO {
    @Override
    public boolean entradaProducto(Movimiento movimiento) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        String sql = "INSERT INTO MOVIMIENTOS(producto,tipo,cantidad,fecha_movimiento) VALUES (?,?,?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1,movimiento.getProducto().getId());
            ps.setString(2,movimiento.getTipo());
            ps.setInt(3,movimiento.getCantidad());
            ps.setDate(4,java.sql.Date.valueOf(movimiento.getFecha_movimiento()));
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al insertar el movimiento de entrada " + e.getMessage());
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
}
