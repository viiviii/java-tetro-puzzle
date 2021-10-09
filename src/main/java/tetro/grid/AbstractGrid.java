package tetro.grid;

import tetro.grid.cells.AbstractCells;
import tetro.offset.Offset;

import java.util.Objects;
import java.util.function.Predicate;

public abstract class AbstractGrid<E extends AbstractCells> implements Grid<E> {

    protected AbstractGrid(int length, E cells) {
        validate(length, cells);
    }

    private void validate(int length, E cells) throws IllegalArgumentException {
        validateLength(length);
        validateCapacity(length, cells);
        validateCellsRange(length, cells);
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

    private void validateCellsRange(int length, E cells) throws IllegalArgumentException {
        final Predicate<Offset> validRange = (e) -> (e.x >= 0 && e.x < length) && (e.y >= 0 && e.y < length);
        final boolean valid = cells.offsets().stream().allMatch(offset -> validRange.test(offset));
        if (valid) return;
        throw new IllegalArgumentException("Has invalid cell in 'cells': <cells> " + cells);
    }

    @Override
    public abstract int length();

    @Override
    public abstract E cells();

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
