package tetro;

import tetro.grid.AbstractGrid;
import tetro.grid.cells.AbstractBlankCells;
import tetro.grid.cells.AbstractNonBlankCells;
import tetro.offset.Offsets;

public final class Board extends AbstractGrid<Board.BlankCells> {
    private static final int LENGTH = 9;

    private final BlankCells blankCells;

    private Board(int length, BlankCells blankCells) {
        super(length, blankCells);
        this.blankCells = blankCells;
    }

    public Board(BlankCells cells) {
        this(LENGTH, cells);
    }

    public Board(Offsets offsets) {
        this(new BlankCells(offsets));
    }

    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public BlankCells cells() {
        return this.blankCells;
    }

    protected static final class BlankCells extends AbstractBlankCells {
        private final Offsets offsets;

        public BlankCells(Offsets offsets) {
            this.offsets = offsets;
        }

        // todo: 메서드명
        public boolean isNone() {
            return this.offsets().size() == 0;
        }

        public boolean canFit(AbstractNonBlankCells other) {
            return this.offsets().containsAll(other.offsets());
        }

        public BlankCells fit(AbstractNonBlankCells other) {
            final Offsets remaining = this.offsets().difference(other.offsets());
            return new BlankCells(remaining);
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
