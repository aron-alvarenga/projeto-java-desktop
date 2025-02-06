package br.com.aronalvarenga.controller;
import br.com.aronalvarenga.view.FuncionarioView;
import br.com.aronalvarenga.model.Funcionario;
import br.com.aronalvarenga.service.FuncionarioService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

/**
 *
 * @author Aron
 */

public class FuncionarioController {
    private FuncionarioService funcionarioService = new FuncionarioService();
    private FuncionarioView funcionarioView;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public FuncionarioController(FuncionarioView funcionarioView) {
        this.funcionarioView = funcionarioView;
    }

    public void cadastrarFuncionario(String nome, String dataAdmissaoStr, String salarioStr, boolean status) {
        if(nome.isEmpty() || dataAdmissaoStr.isEmpty() || salarioStr.isEmpty()){
            funcionarioView.exibirMensagem("Todos os campos devem ser preenchidos!");
            return;
        }
        try {
            Date dataAdmissao = dateFormat.parse(dataAdmissaoStr);
            double salario = Double.parseDouble(salarioStr);
            Funcionario funcionario = new Funcionario();
            funcionario.setNome(nome);
            funcionario.setDataAdmissao(dataAdmissao);
            funcionario.setSalario(salario);
            funcionario.setStatus(status);
            boolean sucesso = funcionarioService.cadastrar(funcionario);
            if(sucesso){
                funcionarioView.exibirMensagem("Funcionário cadastrado com sucesso!");
                carregarFuncionarios();
            } else {
                funcionarioView.exibirMensagem("Erro ao cadastrar funcionário!");
            }
        } catch (ParseException e) {
            funcionarioView.exibirMensagem("Data inválida! Utilize o formato yyyy-MM-dd.");
        } catch (NumberFormatException e) {
            funcionarioView.exibirMensagem("Salário inválido!");
        }
    }

    public void carregarFuncionarios() {
        List<Funcionario> lista = funcionarioService.listar();
        if (lista != null && !lista.isEmpty()) {
            Object[][] dados = new Object[lista.size()][5];
            for (int i = 0; i < lista.size(); i++) {
                Funcionario f = lista.get(i);
                dados[i][0] = f.getId();
                dados[i][1] = f.getNome();
                dados[i][2] = dateFormat.format(f.getDataAdmissao());
                dados[i][3] = f.getSalario();
                dados[i][4] = f.isStatus();
            }
            funcionarioView.atualizarTabela(dados);
        }
    }
}
