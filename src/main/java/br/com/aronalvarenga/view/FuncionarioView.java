package br.com.aronalvarenga.view;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import br.com.aronalvarenga.controller.FuncionarioController;
import java.text.ParseException;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Aron
 */

public class FuncionarioView extends JFrame {
    private JTextField nomeField;
    private JTextField dataAdmissaoField;
    private JTextField salarioField;
    private JCheckBox statusCheckBox;
    private JButton cadastrarButton;
    private JTable funcionariosTable;
    private FuncionarioController funcionarioController;

    private static final Color PRIMARY_COLOR = new Color(70, 130, 180);
    private static final Color SECONDARY_COLOR = new Color(245, 245, 245);
    private static final Color TEXT_COLOR = new Color(51, 51, 51);

    public FuncionarioView() {
        funcionarioController = new FuncionarioController(this);
        setTitle("Cadastro de Funcionários");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setLocationRelativeTo(null);
        funcionarioController.carregarFuncionarios();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(SECONDARY_COLOR);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(SECONDARY_COLOR);
        formPanel.setBorder(new EmptyBorder(60, 40, 40, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setForeground(TEXT_COLOR);
        nomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        formPanel.add(nomeLabel, gbc);

        nomeField = createStyledTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 2;
        formPanel.add(nomeField, gbc);

        JLabel dataLabel = new JLabel("Data de Admissão:");
        dataLabel.setForeground(TEXT_COLOR);
        dataLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        formPanel.add(dataLabel, gbc);

        dataAdmissaoField = createStyledTextField();
        dataAdmissaoField.setPreferredSize(new Dimension(300, 35));
        dataAdmissaoField.setFont(new Font("Arial", Font.PLAIN, 14));
        dataAdmissaoField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 2;
        formPanel.add(dataAdmissaoField, gbc);

        JLabel salarioLabel = new JLabel("Salário:");
        salarioLabel.setForeground(TEXT_COLOR);
        salarioLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        formPanel.add(salarioLabel, gbc);

        salarioField = createStyledTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 2;
        formPanel.add(salarioField, gbc);

        statusCheckBox = new JCheckBox("Ativo");
        statusCheckBox.setBackground(SECONDARY_COLOR);
        statusCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        formPanel.add(statusCheckBox, gbc);

        cadastrarButton = createStyledButton("Cadastrar", PRIMARY_COLOR, Color.WHITE);
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1;
        formPanel.add(cadastrarButton, gbc);

        cadastrarButton.addActionListener(e -> {
            funcionarioController.cadastrarFuncionario(
                nomeField.getText(),
                dataAdmissaoField.getText(),
                salarioField.getText(),
                statusCheckBox.isSelected()
            );
            nomeField.setText("");
            dataAdmissaoField.setText("");
            salarioField.setText("");
        });

        funcionariosTable = new JTable(new DefaultTableModel(new Object[]{"ID", "Nome", "Data de Admissão", "Salário", "Status"}, 0));
        JScrollPane scrollPane = new JScrollPane(funcionariosTable);
        estilizarTabela();

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, formPanel, scrollPane);
        splitPane.setDividerLocation(250);
        mainPanel.add(splitPane, BorderLayout.CENTER);
        add(mainPanel);
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

    private void estilizarTabela() {
        funcionariosTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        funcionariosTable.getTableHeader().setBackground(PRIMARY_COLOR);
        funcionariosTable.getTableHeader().setForeground(Color.WHITE);
        funcionariosTable.setFont(new Font("Arial", Font.PLAIN, 14));
        funcionariosTable.setRowHeight(25);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        funcionariosTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        funcionariosTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        funcionariosTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
    }

    public void atualizarTabela(Object[][] dados) {
        DefaultTableModel model = new DefaultTableModel(
            dados,
            new Object[]{"ID", "Nome", "Data de Admissão", "Salário", "Status"}
        );
        funcionariosTable.setModel(model);
        estilizarTabela();
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}