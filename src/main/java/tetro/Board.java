package tetro;

import tetro.grid.AbstractGrid;
import tetro.grid.cells.AbstractBlankCells;
import tetro.grid.cells.AbstractCells;
import tetro.offset.Offsets;

public final class Board extends AbstractGrid<Board.BlankCells> {

    private Board(int length, BlankCells blankCells) {
        super(length, blankCells);
    }

    public Board(Offsets offsets) {
        this(9, new BlankCells(offsets));
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
