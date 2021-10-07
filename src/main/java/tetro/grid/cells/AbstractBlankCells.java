package tetro.grid.cells;

import tetro.offset.Offsets;

public abstract class AbstractBlankCells extends AbstractCells {

    @Override
    public final CellState state() {
        return CellState.BLANK;
    }

    @Override
    public abstract Offsets offsets();
}
