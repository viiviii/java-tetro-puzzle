package tetro;

import tetro.grid.Grid;
import tetro.grid.Cells;

public final class Board {
    private static final int LENGTH = 9;

    private final Grid grid;

    private Board(Grid grid) {
        this.grid = grid;
    }

    // todo
    public Board(Cells cells) {
        this(new Grid(LENGTH, cells));
    }

    public Cells cells() {
        return grid.cells();
    }
}
