package tetro;

import tetro.grid.Grid;
import tetro.grid.cells.AbstractBlankCells;
import tetro.grid.cells.AbstractCells;
import tetro.offset.Offsets;

public final class Board {
    private static final int LENGTH = 9;

    private final Grid<BlankCells> grid;

    private Board(Grid<BlankCells> grid) {
        this.grid = grid;
    }

    private Board(BlankCells cells) {
        this(new Grid(LENGTH, cells));
    }

    // todo
    public Board(Offsets offsets) {
        this(new BlankCells(offsets));
    }

    public BlankCells blanks() {
        return grid.cells();
    }

    protected static final class BlankCells extends AbstractBlankCells {
        private final Offsets offsets;

        public BlankCells(Offsets offsets) {
            this.offsets = offsets;
        }

        public BlankCells difference(AbstractCells cells) {
            final Offsets difference = Offsets.difference(offsets(), cells.offsets());
            return new BlankCells(difference);
        }

        public boolean containsAll(AbstractCells cells) {
            return offsets().containsAll(cells.offsets());
        }

        @Override
        public Offsets offsets() {
            return this.offsets;
        }

        @Override
        public String toString() {
            return "BlankCells{" + offsets + '}';
        }
    }
}
