package tictactoe.button;

import tictactoe.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CellButton  extends JButton {

    private final Game game;
    private final int row;
    private final int column;

    public CellButton(Game game, int row, int column) {
        this.game = game;
        this.row = row;
        this.column = column;
        setDefaultSettings((char) ('A' + column) + "" + (row + 1));
        addActionListener(actionListener());
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    private void setDefaultSettings(String name) {
        this.setText(" ");
        this.setName("Button" + name);
        this.setFocusPainted(false);
        this.setFont(new Font("Arial", Font.BOLD, 24));
        this.setEnabled(false);
    }

    private ActionListener actionListener() {
        return e -> {
            if (this.getText().equals(" ")) {
                this.game.setCell(this.row, this.column, true);
            }
        };
    }
}
