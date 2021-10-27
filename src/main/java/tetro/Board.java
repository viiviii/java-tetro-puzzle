package tetro;

import tetro.grid.Grid;
import tetro.offset.Offsets;

public final class Board {
    private static final int LENGTH = 9;

    private final Grid grid;

    private Board(Grid grid) {
        this.grid = grid;
    }

    // todo
    public Board(Offsets offsets) {
        this(new Grid(LENGTH, offsets));
    }

    public Offsets blankOffsets() {
        return grid.cells();
    }
}
