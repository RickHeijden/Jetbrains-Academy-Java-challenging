package tictactoe.game;

import tictactoe.button.CellButton;
import tictactoe.button.StatusLabel;
import tictactoe.util.Character;

import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Grid {

    private final CellButton[][] grid;
    private final StatusLabel statusLabel;
    private final int FIELD_SIZE = 3;
    private final Game game;
    private Status status;
    private Character character;

    public Grid(Game game, StatusLabel statusLabel) {
        this.game = game;
        this.grid = new CellButton[FIELD_SIZE][FIELD_SIZE];
        this.status = Status.NOT_STARTED;
        Stream.iterate(0, row -> row + 1).limit(FIELD_SIZE).forEach(
                (row) -> Stream.iterate(0, column -> column + 1).limit(FIELD_SIZE)
                        .forEach((column) -> this.grid[row][column] = new CellButton(game, row, column)));
        this.statusLabel = statusLabel;
        updateLabel();
        this.character = Character.X;
    }

    public boolean isFinished() {
        return !(this.status == Status.IN_PROGRESS);
    }

    public void setStatus(Status status) {
        this.status = status;
        updateLabel();
    }

    public int getFIELD_SIZE() {
        return FIELD_SIZE;
    }

    public CellButton[][] getGrid() {
        return this.grid;
    }

    public CellButton[] getGridArray() {
        return Arrays.stream(this.grid).flatMap(row -> Arrays.stream(row.clone())).collect(Collectors.toList())
                .toArray(CellButton[]::new);
    }

    public Character getCharacter() {
        return this.character;
    }

    public CellButton getCell(int row, int column) {
        return this.grid[row][column];
    }

    public void reset() {
        Arrays.asList(this.grid).forEach((row) -> Arrays.asList(row).forEach((cell) -> cell.setText(" ")));
        Arrays.asList(this.grid).forEach((row) -> Arrays.asList(row).forEach((cell) -> cell.setEnabled(false)));
        this.status = Status.NOT_STARTED;
        this.character = Character.X;
        updateLabel();
    }

    public boolean setCell(int row, int column) {
        if ((this.status == Status.IN_PROGRESS)) {
            this.grid[row][column].setText(this.character.getCharacter());
            this.character = this.character.getReverse();
            result();
            return true;
        }
        return false;
    }

    private void result() {
        if (won(Character.X.getCharacter())) {
            this.status = Status.XWIN;
        } else if (won(Character.O.getCharacter())) {
            this.status = Status.OWIN;
        } else if (!contains(Character.EMPTY.getCharacter())) {
            this.status = Status.DRAW;
        }
        if (this.status != Status.IN_PROGRESS) {
            Arrays.asList(this.grid).forEach((row) -> Arrays.asList(row).forEach((cell) -> cell.setEnabled(false)));
        }
        updateLabel();
    }

    public boolean won(String symbol) {
        IntPredicate rowFunction = (i) -> Stream.iterate(0, x -> x + 1).limit(FIELD_SIZE)
                .allMatch((x) -> this.grid[i][x].getText().equals(symbol));
        IntPredicate colFunction = (i) -> Stream.iterate(0, x -> x + 1).limit(FIELD_SIZE)
                .allMatch((x) -> this.grid[x][i].getText().equals(symbol));

        boolean rowCol =
                Stream.iterate(0, x -> x + 1).limit(FIELD_SIZE).anyMatch((x) -> rowFunction.or(colFunction).test(x));
        boolean diag1 = Stream.iterate(0, x -> x + 1).limit(FIELD_SIZE)
                .allMatch((x) -> this.grid[x][x].getText().equals(symbol));
        boolean diag2 = Stream.iterate(0, x -> x + 1).limit(FIELD_SIZE)
                .allMatch((x) -> this.grid[x][FIELD_SIZE - 1 - x].getText().equals(symbol));
        return rowCol || diag1 || diag2;
    }

    public boolean contains(String symbol) {
        return Arrays.stream(grid)
                .anyMatch((row) -> Arrays.stream(row).anyMatch((cell) -> cell.getText().equals(symbol)));
    }

    private void updateLabel() {
        if (this.status == Status.NOT_STARTED || this.status == Status.DRAW) {
            this.statusLabel.setText(this.status.getText());
        } else {
            String player;
            if ((this.character == Character.X && this.game.getPlayer1() == null) ||
                    (this.character == Character.O && this.game.getPlayer2() == null)) {
                player = "Human";
            } else {
                player = "Robot";
            }
            if (this.status == Status.IN_PROGRESS) {
                this.statusLabel.setText(String.format(this.status.getText(), player, this.character.getCharacter()));
            } else {
                this.statusLabel.setText(String.format(this.status.getText(), player));
            }
        }
    }

    public enum Status {
        NOT_STARTED("Game is not started"),
        IN_PROGRESS("The turn of %s Player (%s)"),
        XWIN("The %s Player (X) wins"),
        OWIN("The %s Player (O) wins"),
        DRAW("Draw");

        private final String text;

        Status(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
