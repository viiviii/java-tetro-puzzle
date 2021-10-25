package tetro.grid.cells;

import tetro.grid.Cells;
import tetro.offset.Offsets;

import java.util.Objects;

public abstract class AbstractCells implements Cells {

    public abstract Offsets offsets();

    @Override
    public final int size() {
        return offsets().size();
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
