package tictactoe.menu;

import tictactoe.button.ResetButton;
import tictactoe.game.Game;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuStartGame extends JMenuItem {

    private final Game game;
    private final ResetButton resetButton;
    private final String player1;
    private final String player2;

    public MenuStartGame(Game game, ResetButton resetButton, String player1, String player2) {
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
        this.resetButton = resetButton;
        setSettings();
        addActionListener(actionListener());
    }

    private void setSettings() {
        this.setName("Menu" + this.player1 + this.player2);
        this.setText(this.player1 + " vs " + this.player2);
    }

    private ActionListener actionListener() {
        return e -> {
            try {
                this.game.reset();
                this.game.getPlayerButtons().get(0).setText(player1);
                this.game.getPlayerButtons().get(1).setText(player2);
                this.resetButton.setText("Reset");
                this.game.start();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        };
    }
}
