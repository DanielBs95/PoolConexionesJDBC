package datos;

import static datos.Conexion.*;
import domain.UsuarioDTO;
import java.sql.*;
import java.util.*;

/**
 *
 * @author cleys
 */
public class UsuarioDaoJDBC implements IUsuarioDAO{

    private Connection conexionTransaccional;
    private static final String SQL_SELECT = "SELECT id_usuario, user, password FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario (user, password) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario set user = ?, password = ? WHERE id_usuario = ? ";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";

    public UsuarioDaoJDBC() {

    }

    public UsuarioDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public List<UsuarioDTO> select() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<UsuarioDTO> usuarios = new ArrayList<>();
        UsuarioDTO usuario;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idUser = rs.getInt("id_usuario");
                String user = rs.getString("user");
                String password = rs.getString("password");
                /*
                Recuperamos los datos obtenidos desde la BD, lo pasamos al constructor UsuarioDTO
                luego lo añadimos a la LISTA USUARIOS
                 */
                usuario = new UsuarioDTO(idUser, user, password);
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(ps);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }

        return usuarios;
    }

    public int insert(UsuarioDTO usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            registros = ps.executeUpdate();
        } finally {
            try {
                close(ps);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return registros;
    }

    public int update(UsuarioDTO usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setInt(3, usuario.getIdUsuario());
            registros = ps.executeUpdate();
        } finally {
            try {
                close(ps);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return registros;
    }

    public int delete(UsuarioDTO usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, usuario.getIdUsuario());
            registros = ps.executeUpdate();
        } finally {
            try {
                close(ps);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
