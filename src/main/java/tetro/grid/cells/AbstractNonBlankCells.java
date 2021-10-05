package tetro.grid.cells;

public abstract class AbstractNonBlankCells extends AbstractCells {

    @Override
    public final CellState state() {
        return CellState.NON_BLANK;
    }
}
