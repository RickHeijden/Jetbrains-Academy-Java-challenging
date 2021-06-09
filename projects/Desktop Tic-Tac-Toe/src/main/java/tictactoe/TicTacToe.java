package tictactoe;

import tictactoe.button.PlayerButton;
import tictactoe.button.ResetButton;
import tictactoe.button.StatusLabel;
import tictactoe.frames.Board;
import tictactoe.game.Game;
import tictactoe.menu.GameMenu;

import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JFrame {

    private final Game game;
    private final StatusLabel statusLabel;
    private final ResetButton resetButton;

    public TicTacToe() {
        this.statusLabel = new StatusLabel();
        this.game = new Game(this.statusLabel);
        this.resetButton = new ResetButton(this.game);
        setSettings();
        createAndAddComponents();
        setVisible(true);
    }

    private void setSettings() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setTitle("Tic Tac Toe");
        setResizable(false);
        setLayout(new BorderLayout());
    }

    private void createAndAddComponents() {
        createAndAddToolbar();
        createAndAddBoard();
        createAndAddState();
        createAndAddMenu();
    }

    private void createAndAddToolbar() {
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(1, 3));
        toolbar.add(new PlayerButton(this.game, 1));
        toolbar.add(this.resetButton);
        toolbar.add(new PlayerButton(this.game, 2));
        this.add(toolbar, BorderLayout.NORTH);
    }

    private void createAndAddBoard() {
        JPanel board = new Board(this.game.getGrid());
        this.add(board, BorderLayout.CENTER);
    }

    private void createAndAddState() {
        JPanel state = new JPanel();
        state.setLayout(new BorderLayout());
        state.add(this.statusLabel, BorderLayout.WEST);
        this.add(state, BorderLayout.SOUTH);
    }

    private void createAndAddMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new GameMenu(this.game, this.resetButton));
        this.setJMenuBar(menuBar);
    }
}
