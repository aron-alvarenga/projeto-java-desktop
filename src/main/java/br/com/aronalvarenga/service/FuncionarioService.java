package br.com.aronalvarenga.service;
import br.com.aronalvarenga.dao.FuncionarioDAO;
import br.com.aronalvarenga.model.Funcionario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aron
 */

public class FuncionarioService {
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public boolean cadastrar(Funcionario funcionario) {
        try {
            return funcionarioDAO.cadastrar(funcionario);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Funcionario> listar() {
        try {
            return funcionarioDAO.listar();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

