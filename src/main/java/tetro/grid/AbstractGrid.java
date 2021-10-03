package tetro.grid;

import tetro.grid.cells.AbstractCells;

import java.util.Objects;

public abstract class AbstractGrid<E extends AbstractCells> implements Grid<E> {

    protected AbstractGrid(int length, E cells) {
        validate(length, cells);
    }

    private void validate(int length, E cells) throws IllegalArgumentException {
        validateLength(length);
        validateCapacity(length, cells);
    }

    private void validateLength(int length) throws IllegalArgumentException {
        if (length >= 0) return;
        throw new IllegalArgumentException("'length should be greater than zero: <length> " + length);
    }

    private void validateCapacity(int length, E cells) throws IllegalArgumentException {
        final int capacity = length * length;
        if (cells.size() <= capacity) return;
        throw new IllegalArgumentException("'cells.size()' is greater than capacity: " +
                "<cells size> " + cells.size() + ", <capacity> " + capacity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractGrid)) return false;
        AbstractGrid that = (AbstractGrid) o;
        return this.length() == that.length() && this.cells().equals(that.cells());
    }

    @Override
    public int hashCode() {
        return Objects.hash(length(), cells());
    }
}
