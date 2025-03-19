package proyecto_inventario.conexion;

import java.sql.*;/// importa el tipo Connection
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class Conexion {

    public static Connection getConexion(){
        Connection conexion = null;
        var baseDatos = "inventario";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "root";
        var password = "admin";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url,usuario,password);

        }catch (Exception e){
            System.out.println("Error al conectarnos a la BD " + e.getMessage());
        }
        return conexion;

    }

    public static void main(String[] args) {
        var conexion = Conexion.getConexion();
        if(conexion != null)
            System.out.println("Conexion exitosa " + conexion);
        else
            System.out.println("Error al conectarse");
    }
}
