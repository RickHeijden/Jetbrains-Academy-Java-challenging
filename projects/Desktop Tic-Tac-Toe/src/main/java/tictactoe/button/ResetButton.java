package tictactoe.button;

import tictactoe.game.Game;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ResetButton extends JButton {

    private final Game game;

    public ResetButton(Game game) {
        this.game = game;
        setDefaultSettings();
        addActionListener(actionListener());
    }

    private void setDefaultSettings() {
        this.setText("Start");
        this.setName("ButtonStartReset");
    }

    private ActionListener actionListener() {
        return e -> {
            if (this.getText().equals("Start")) {
                try {
                    this.game.start();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                this.setText("Reset");
            } else {
                this.setText("Start");
                this.game.reset();
            }
        };
    }
}
