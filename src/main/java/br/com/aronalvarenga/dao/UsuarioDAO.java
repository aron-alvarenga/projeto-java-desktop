package br.com.aronalvarenga.dao;
import br.com.aronalvarenga.model.Usuario;
import br.com.aronalvarenga.util.ConnectionUtil;
import br.com.aronalvarenga.util.CriptografiaUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Aron
 */

public class UsuarioDAO {
    
    public boolean cadastrar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios(nome, email, senha) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            // Criptografa a senha antes de salvar
            stmt.setString(3, CriptografiaUtil.sha256(usuario.getSenha()));
            return stmt.executeUpdate() > 0;
        }
    }
    
    public Usuario login(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, CriptografiaUtil.sha256(senha));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                return usuario;
            }
        }
        return null;
    }
}

