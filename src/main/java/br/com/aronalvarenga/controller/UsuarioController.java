package br.com.aronalvarenga.controller;
import br.com.aronalvarenga.view.LoginView;
import br.com.aronalvarenga.view.CadastroUsuarioView;
import br.com.aronalvarenga.model.Usuario;
import br.com.aronalvarenga.service.UsuarioService;
import br.com.aronalvarenga.view.FuncionarioView;

/**
 *
 * @author Aron
 */

public class UsuarioController {
    private UsuarioService usuarioService = new UsuarioService();
    private LoginView loginView;
    private CadastroUsuarioView cadastroView;

    public UsuarioController(LoginView loginView) {
        this.loginView = loginView;
    }

    public UsuarioController(CadastroUsuarioView cadastroView) {
        this.cadastroView = cadastroView;
    }

    public void cadastrar(String nome, String email, String senha) {
        if(nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
            cadastroView.exibirMensagem("Todos os campos são obrigatórios!");
            return;
        }
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        boolean sucesso = usuarioService.cadastrar(usuario);
        if(sucesso){
            cadastroView.exibirMensagem("Cadastro realizado com sucesso!");
            LoginView login = new LoginView();
            login.setVisible(true);
            cadastroView.dispose();
        } else {
            cadastroView.exibirMensagem("Falha no cadastro!");
        }
    }

    public void login(String email, String senha) {
        if(email.isEmpty() || senha.isEmpty()){
            loginView.exibirMensagem("Todos os campos são obrigatórios!");
            return;
        }
        Usuario usuario = usuarioService.login(email, senha);
        if(usuario != null){
            loginView.exibirMensagem("Login realizado com sucesso!");
            FuncionarioView funcionarioView = new FuncionarioView();
            funcionarioView.setVisible(true);
            loginView.dispose();
        } else {
            loginView.exibirMensagem("Email ou senha incorretos!");
        }
    }
}

