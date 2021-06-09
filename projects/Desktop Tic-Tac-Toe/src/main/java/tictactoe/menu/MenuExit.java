package tictactoe.menu;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuExit extends JMenuItem {

    public MenuExit() {
        setSettings();
        addActionListener(actionListener());
    }

    private void setSettings() {
        this.setName("MenuExit");
        this.setText("Exit");
    }

    private ActionListener actionListener() {
        return e -> System.exit(0);
    }
}
