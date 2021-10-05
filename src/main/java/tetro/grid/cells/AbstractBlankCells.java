package tetro.grid.cells;

public abstract class AbstractBlankCells extends AbstractCells {

    @Override
    public final CellState state() {
        return CellState.BLANK;
    }
}
