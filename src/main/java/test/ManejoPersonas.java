package test;

import datos.Conexion;
import datos.IPersonaDAO;
import datos.PersonaDaoJDBC;
import domain.PersonaDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author cleys
 */
public class ManejoPersonas {

    public static void main(String[] args) {
        Connection conexion = null;
        try {
            //Aplicando transaccion jdbc
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            
            IPersonaDAO personaDAO = new PersonaDaoJDBC(conexion);
            List<PersonaDTO> listaPersonas = personaDAO.select();
            for (PersonaDTO persona : listaPersonas) {
                System.out.println("Persona DTO = " + persona);
            }
            
            conexion.commit();
            System.out.println("Se hizo commit de la transaccion");
        } catch (SQLException ex) {
            try {
                ex.printStackTrace(System.out);
                System.out.println("Entramos al rollback");
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }
}
