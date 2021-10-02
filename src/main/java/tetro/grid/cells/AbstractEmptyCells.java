package tetro.grid.cells;

public abstract class AbstractEmptyCells extends AbstractCells {

    @Override
    public final CellState state() {
        return CellState.empty;
    }
}
