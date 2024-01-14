package test;

import java.sql.Connection;
import java.sql.*;

/**
 *
 * @author cleys
 */
public class TestMysqlJDBC {

    public static void main(String[] args) {
        
        String urlConexion = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            //Creamos el objetto conexion para conectarnos a la BD.
            Connection conexion = DriverManager.getConnection(urlConexion, "root", "admin");
            //Cremos un objeto tipo Statement para ejecutar las sentencias
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
            //Creamos un objeto Tipo ResultSet para que nos de el resultado de la sentencia
            ResultSet resultado = sentencia.executeQuery(sql);
            while (resultado.next()){
                System.out.print("Id Persona: " + resultado.getInt("id_persona"));
                System.out.print(" Nombre: " + resultado.getString("nombre"));
                System.out.print(" Apellido: " + resultado.getString("apellido"));
                System.out.print(" Email: " + resultado.getString("email"));
                System.out.print(" Telefono: " + resultado.getString("telefono"));
                System.out.println();
            }
            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
