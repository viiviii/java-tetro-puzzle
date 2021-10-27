package tetro;

import tetro.grid.Grid;
import tetro.grid.cells.AbstractCells;
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

    protected static final class Blanks extends AbstractCells {
        private final Offsets offsets;

        public Blanks(Offsets offsets) {
            this.offsets = offsets;
        }

        @Override
        public Offsets offsets() {
            return this.offsets;
        }

        @Override
        public String toString() {
            return "Blanks{" + offsets + '}';
        }
    }
}
