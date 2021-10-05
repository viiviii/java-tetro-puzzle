package tetro;

import tetro.grid.AbstractGrid;
import tetro.grid.cells.AbstractEmptyCells;
import tetro.grid.cells.AbstractFillCells;
import tetro.offset.Offsets;

public final class Board extends AbstractGrid<Board.EmptyCells> {
    private static final int LENGTH = 9;

    private final EmptyCells emptyCells;

    private Board(int length, EmptyCells emptyCells) {
        super(length, emptyCells);
        this.emptyCells = emptyCells;
    }

    public Board(EmptyCells cells) {
        this(LENGTH, cells);
    }

    public Board(Offsets offsets) {
        this(new EmptyCells(offsets));
    }

    @Override
    public int length() {
        return LENGTH;
    }

    @Override
    public EmptyCells cells() {
        return this.emptyCells;
    }

    protected static final class EmptyCells extends AbstractEmptyCells {
        private final Offsets offsets;

        public EmptyCells(Offsets offsets) {
            this.offsets = offsets;
        }

        // todo: 메서드명
        public boolean isNone() {
            return this.offsets().size() == 0;
        }

        public boolean canFit(AbstractFillCells other) {
            return this.offsets().containsAll(other.offsets());
        }

        public EmptyCells fit(AbstractFillCells other) {
            final Offsets remaining = this.offsets().difference(other.offsets());
            return new EmptyCells(remaining);
        }

        @Override
        public Offsets offsets() {
            return this.offsets;
        }

        @Override
        public String toString() {
            return "EmptyCells{" + offsets + '}';
        }
    }
}
