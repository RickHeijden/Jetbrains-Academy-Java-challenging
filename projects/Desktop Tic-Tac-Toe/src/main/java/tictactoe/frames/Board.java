package tictactoe.frames;

import tictactoe.game.Grid;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Stream;

public class Board extends JPanel {

    public Board(Grid grid) {
        int size = grid.getFIELD_SIZE();
        setLayout(new GridLayout(size, size));

        Stream.iterate(size - 1, row -> row - 1).limit(size).forEach(
                (row) -> Stream.iterate(0, column -> column + 1).limit(size)
                        .forEach((column) -> add(grid.getCell(row, column))));
    }
}
