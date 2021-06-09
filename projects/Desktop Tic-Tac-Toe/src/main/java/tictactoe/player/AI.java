package tictactoe.player;

import tictactoe.button.CellButton;
import tictactoe.button.StatusLabel;
import tictactoe.game.Game;
import tictactoe.util.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AI {

    protected final Game game;

    public AI(Game game) {
        this.game = game;
    }

    public void turn() {
        if (this.game.getGrid().isFinished()) {
            return;
        }
        int[] bestMove = miniMax(copyGrid(this.game.getGrid().getGridArray()), this.game.getGrid().getCharacter());
        this.game.setCell(bestMove[0] / 3, bestMove[0] % 3, false);
    }

    public int[] miniMax(CellButton[] grid, Character symbol) {
        int[] available = emptyIndexies(grid);

        Character curSymbol = this.game.getGrid().getCharacter();
        if (winning(grid, curSymbol.getReverse().getCharacter())) {
            return new int[]{-1, -10};
        } else if (winning(grid, curSymbol.getCharacter())) {
            return new int[]{-1, 10};
        } else if (available.length == 0) {
            return new int[]{-1, 0};
        }

        List<int[]> moves = new ArrayList<>();
        for (int i : available) {
            int[] move = new int[2];
            grid[i].setText(symbol.getCharacter());
            move[0] = i;
            move[1] = miniMax(grid, symbol.getReverse())[1];
            grid[i].setText(Character.EMPTY.getCharacter());
            moves.add(move);
        }

        int bestScore;
        if (symbol.getCharacter().equals(this.game.getGrid().getCharacter().getCharacter())) {
            bestScore = moves.stream().map((move) -> move[1]).max(Integer::compareTo).get();
        } else {
            bestScore = moves.stream().map((move) -> move[1]).min(Integer::compareTo).get();
        }
        return moves.stream().filter((move) -> move[1] == bestScore).limit(1).collect(Collectors.toList()).get(0);
    }

    private CellButton[] copyGrid(CellButton[] grid) {
        CellButton[] copyGrid = new CellButton[this.game.getGrid().getGridArray().length];
        Game tempGame = new Game(new StatusLabel());
        for (int i = 0; i < grid.length; i++) {
            int row = grid[i].getRow();
            int column = grid[i].getColumn();
            copyGrid[i] = new CellButton(tempGame, row, column);
            copyGrid[i].setText(grid[i].getText());
        }
        return copyGrid;
    }

    private int[] emptyIndexies(CellButton[] grid) {
        return IntStream.iterate(0, x -> x + 1).limit(grid.length)
                .filter((x) -> grid[x].getText().equals(Character.EMPTY.getCharacter())).toArray();
    }

    private boolean winning(CellButton[] grid, String symbol) {
        return (grid[0].getText().equals(symbol) && grid[1].getText().equals(symbol) &&
                grid[2].getText().equals(symbol)) ||
                (grid[3].getText().equals(symbol) && grid[4].getText().equals(symbol) &&
                        grid[5].getText().equals(symbol)) ||
                (grid[6].getText().equals(symbol) && grid[7].getText().equals(symbol) &&
                        grid[8].getText().equals(symbol)) ||
                (grid[0].getText().equals(symbol) && grid[3].getText().equals(symbol) &&
                        grid[6].getText().equals(symbol)) ||
                (grid[1].getText().equals(symbol) && grid[4].getText().equals(symbol) &&
                        grid[7].getText().equals(symbol)) ||
                (grid[2].getText().equals(symbol) && grid[5].getText().equals(symbol) &&
                        grid[8].getText().equals(symbol)) ||
                (grid[0].getText().equals(symbol) && grid[4].getText().equals(symbol) &&
                        grid[8].getText().equals(symbol)) ||
                (grid[2].getText().equals(symbol) && grid[4].getText().equals(symbol) &&
                        grid[6].getText().equals(symbol));
    }
}
