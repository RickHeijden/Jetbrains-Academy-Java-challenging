package tictactoe.game;

import tictactoe.button.PlayerButton;
import tictactoe.button.StatusLabel;
import tictactoe.player.AI;

import java.util.*;

public class Game {

    private final Grid grid;
    private final List<PlayerButton> playerButtons;
    private boolean start;
    private AI player1;
    private AI player2;
    private int turn;

    public Game(StatusLabel status) {
        this.grid = new Grid(this, status);
        this.playerButtons = new ArrayList<>();
        this.start = false;
    }

    public void start() throws InterruptedException {
        startSettings();
        run();
    }

    public void reset() {
        this.start = false;
        this.grid.reset();
        this.playerButtons.forEach((button) -> button.setEnabled(true));
    }

    public void setCell(int row, int column, boolean human) {
        if (isStart()) {
            if (turn % 2 == 0) {
                if ( (player1 == null && !human) || (player1 != null && human) ) {
                    return;
                }
            } else if ((player2 == null && !human) || (player2 != null && human)) {
                return;
            }
            if (grid.setCell(row, column)) {
                turn++;
            }
        }
    }

    public void addPlayerButton(PlayerButton button) {
        this.playerButtons.add(button);
    }

    public boolean isStart() {
        return this.start;
    }

    public Grid getGrid() {
        return this.grid;
    }

    public AI getPlayer1() {
        return player1;
    }

    public AI getPlayer2() {
        return player2;
    }

    public List<PlayerButton> getPlayerButtons() {
        return playerButtons;
    }

    private void startSettings() {
        this.start = true;
        this.turn = 0;
        Arrays.asList(this.grid.getGrid())
                .forEach((row) -> Arrays.asList(row).forEach((cell) -> cell.setEnabled(true)));
        this.playerButtons.forEach((button) -> button.setEnabled(false));
        this.player1 = this.playerButtons.get(0).getText().equals("Robot") ? new AI(this) : null;
        this.player2 = this.playerButtons.get(1).getText().equals("Robot") ? new AI(this) : null;
        this.grid.setStatus(Grid.Status.IN_PROGRESS);
    }

    private void run() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (grid.isFinished()) {
                    this.cancel();
                }
                if (turn % 2 == 0 && player1 != null) {
                    player1.turn();
                } else if (player2 != null) {
                    player2.turn();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
}
