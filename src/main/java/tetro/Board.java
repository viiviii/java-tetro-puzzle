package tetro;

import tetro.grid.Grid;
import tetro.grid.cells.AbstractCells;
import tetro.offset.Offsets;

public final class Board {
    private static final int LENGTH = 9;

    private final Grid<Blanks> grid;

    private Board(Grid<Blanks> grid) {
        this.grid = grid;
    }

    private Board(Blanks cells) {
        this(new Grid(LENGTH, cells));
    }

    // todo
    public Board(Offsets offsets) {
        this(new Blanks(offsets));
    }

    public Blanks blanks() {
        return grid.cells();
    }

    protected static final class Blanks extends AbstractCells {
        private final Offsets offsets;

        public Blanks(Offsets offsets) {
            this.offsets = offsets;
        }

        public Blanks difference(AbstractCells cells) {
            final Offsets difference = offsets.difference(cells.offsets());
            return new Blanks(difference);
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
            return "Blanks{" + offsets + '}';
        }
    }
}
