package tetro.grid.cells;

public abstract class AbstractFillCells extends AbstractCells {

    @Override
    public final CellState state() {
        return CellState.fill;
    }
}
