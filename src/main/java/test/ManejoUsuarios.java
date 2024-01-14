package test;

import datos.Conexion;
import datos.IUsuarioDAO;
import datos.UsuarioDaoJDBC;
import domain.UsuarioDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author cleys
 */
public class ManejoUsuarios {

    public static void main(String[] args) {
        Connection conexion = null;
        
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            IUsuarioDAO usuarioDao = new UsuarioDaoJDBC(conexion);
            List<UsuarioDTO> listaUsuarios= usuarioDao.select();
            
            for (UsuarioDTO usuario : listaUsuarios) {
                System.out.println("Usuario DTO = " + usuario);
            }
            conexion.commit();
            System.out.println("Se hizo un commint transaccional");
        } catch (SQLException ex) {
            try {
                ex.printStackTrace(System.out);
                System.out.println("Entramos a rollback");
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }

}
