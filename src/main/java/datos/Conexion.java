package datos;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author cleys
 */
public class Conexion {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin";
    private  static BasicDataSource dataSource;
    
    //Configurando un POOL DE CONEXIONES
    public static DataSource getDataSource(){
        if(dataSource == null)
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(JDBC_USER);
        dataSource.setPassword(JDBC_PASSWORD);
        //Definimos el tamaño inicial del POOL DE CONEXIONES
        dataSource.setInitialSize(5);
        return dataSource;
    }
    
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    public static void close(PreparedStatement psmtm) throws SQLException {
        psmtm.close();
    }

    public static void close(Connection conx) throws SQLException {
        conx.close();
    }
}
