package tictactoe.menu;

import tictactoe.button.ResetButton;
import tictactoe.game.Game;

import javax.swing.*;

public class GameMenu extends JMenu {

    private final Game game;
    private final ResetButton resetButton;

    public GameMenu(Game game, ResetButton resetButton) {
        super("Game");
        this.game = game;
        this.resetButton = resetButton;
        setSettings();
        createMenu();
    }

    private void setSettings() {
        this.setName("MenuGame");
    }

    private void createMenu() {
        this.add(new MenuStartGame(game, resetButton, "Human", "Human"));
        this.add(new MenuStartGame(game, resetButton, "Human", "Robot"));
        this.add(new MenuStartGame(game, resetButton, "Robot", "Human"));
        this.add(new MenuStartGame(game, resetButton, "Robot", "Robot"));
        this.addSeparator();
        this.add(new MenuExit());
    }
}
