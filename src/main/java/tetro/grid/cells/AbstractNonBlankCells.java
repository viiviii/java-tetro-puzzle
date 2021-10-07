package tetro.grid.cells;

import tetro.offset.Offsets;

public abstract class AbstractNonBlankCells extends AbstractCells {

    @Override
    public final CellState state() {
        return CellState.NON_BLANK;
    }

    @Override
    public abstract Offsets offsets();
}
