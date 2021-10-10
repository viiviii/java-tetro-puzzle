package tetro.grid;

import tetro.grid.cells.AbstractCells;
import tetro.offset.Offset;

import java.util.Objects;
import java.util.function.Predicate;

public final class Grid<E extends AbstractCells> {
    private final int length;
    private final E cells;

    public Grid(int length, E cells) {
        validate(length, cells);
        this.length = length;
        this.cells = cells;
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

    public final int length() {
        return this.length;
    }

    public final E cells() {
        return this.cells;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grid)) return false;
        Grid<?> that = (Grid<?>) o;
        return length == that.length && cells.equals(that.cells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, cells);
    }
}
