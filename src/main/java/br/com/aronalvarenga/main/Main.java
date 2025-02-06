package br.com.aronalvarenga.main;

import br.com.aronalvarenga.view.LoginView;
import javax.swing.SwingUtilities;

/**
 *
 * @author Aron
 */

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            loginView.setVisible(true);
        });
    }
}

