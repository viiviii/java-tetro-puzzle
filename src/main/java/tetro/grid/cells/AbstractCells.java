package tetro.grid.cells;

import tetro.grid.Grid;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.Objects;

public abstract class AbstractCells implements Grid.Cells<Offset> {

    abstract public Offsets offsets();

    @Override
    public final int size() {
        return offsets().size();
    }

    @Override
    public final Offset first() {
        return offsets().first();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractCells)) return false;
        AbstractCells that = (AbstractCells) o;
        return offsets().equals(that.offsets());
    }

    @Override
    public int hashCode() {
        return Objects.hash(offsets());
    }
}
