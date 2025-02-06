package br.com.aronalvarenga.service;
import br.com.aronalvarenga.dao.UsuarioDAO;
import br.com.aronalvarenga.model.Usuario;
import java.sql.SQLException;

/**
 *
 * @author Aron
 */

public class UsuarioService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public boolean cadastrar(Usuario usuario) {
        try {
            return usuarioDAO.cadastrar(usuario);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Usuario login(String email, String senha) {
        try {
            return usuarioDAO.login(email, senha);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
