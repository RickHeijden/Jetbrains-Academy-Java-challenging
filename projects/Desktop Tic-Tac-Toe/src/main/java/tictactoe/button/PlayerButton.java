package tictactoe.button;

import tictactoe.game.Game;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PlayerButton extends JButton {

    public PlayerButton(Game game, int number) {
        setDefaultSettings(number);
        game.addPlayerButton(this);
        addActionListener(actionListener());
    }

    private void setDefaultSettings(int number) {
        this.setName("ButtonPlayer" + number);
        this.setText("Human");
    }

    private ActionListener actionListener() {
        return e -> {
            if (this.getText().equals("Human")) {
                this.setText("Robot");
            } else if (this.getText().equals("Robot")) {
                this.setText("Human");
            }
        };
    }
}
