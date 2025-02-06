package br.com.aronalvarenga.dao;
import br.com.aronalvarenga.model.Funcionario;
import br.com.aronalvarenga.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aron
 */

public class FuncionarioDAO {

    public boolean cadastrar(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionarios(nome, data_admissao, salario, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setDate(2, new java.sql.Date(funcionario.getDataAdmissao().getTime()));
            stmt.setDouble(3, funcionario.getSalario());
            stmt.setBoolean(4, funcionario.isStatus());
            return stmt.executeUpdate() > 0;
        }
    }

    public List<Funcionario> listar() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setDataAdmissao(rs.getDate("data_admissao"));
                f.setSalario(rs.getDouble("salario"));
                f.setStatus(rs.getBoolean("status"));
                funcionarios.add(f);
            }
        }
        return funcionarios;
    }
}

