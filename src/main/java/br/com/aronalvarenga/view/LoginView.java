package br.com.aronalvarenga.view;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import br.com.aronalvarenga.controller.UsuarioController;

/**
 *
 * @author Aron
 */

public class LoginView extends JFrame {
    private JTextField emailField;
    private JPasswordField senhaField;
    private JButton loginButton;
    private JButton cadastroButton;
    private UsuarioController usuarioController;
    
    private static final Color PRIMARY_COLOR = new Color(70, 130, 180);
    private static final Color SECONDARY_COLOR = new Color(245, 245, 245);
    private static final Color TEXT_COLOR = new Color(51, 51, 51);
    
    public LoginView() {
        usuarioController = new UsuarioController(this);
        setTitle("Sistema de Login");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(SECONDARY_COLOR);
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(SECONDARY_COLOR);
        formPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        
        JLabel titleLabel = new JLabel("Bem-vindo", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(PRIMARY_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        formPanel.add(titleLabel, gbc);
        
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(TEXT_COLOR);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        formPanel.add(emailLabel, gbc);
        
        emailField = createStyledTextField();
        gbc.gridy = 2;
        formPanel.add(emailField, gbc);
        
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setForeground(TEXT_COLOR);
        senhaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 3;
        formPanel.add(senhaLabel, gbc);
        
        senhaField = createStyledPasswordField();
        gbc.gridy = 4;
        formPanel.add(senhaField, gbc);
        
        loginButton = createStyledButton("Entrar", PRIMARY_COLOR, Color.WHITE);
        gbc.gridy = 5;
        gbc.insets = new Insets(20, 0, 10, 0);
        formPanel.add(loginButton, gbc);
        
        cadastroButton = createStyledButton("Criar Nova Conta", Color.WHITE, PRIMARY_COLOR);
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 10, 0);
        formPanel.add(cadastroButton, gbc);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);
        
        loginButton.addActionListener(e ->
            usuarioController.login(emailField.getText(), new String(senhaField.getPassword()))
        );
        
        cadastroButton.addActionListener(e -> {
            CadastroUsuarioView cadastroView = new CadastroUsuarioView();
            cadastroView.setVisible(true);
            this.dispose();
        });
    }
    
    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(300, 35));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }
    
    private JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setPreferredSize(new Dimension(300, 35));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }
    
    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(300, 40));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}