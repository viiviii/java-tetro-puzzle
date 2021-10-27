package tetro.cell;

import tetro.Translatable;

import java.util.Objects;

public final class Cell implements Comparable<Cell>, Translatable<Cell> {
    public final int x;
    public final int y;

    private Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // TODO: 기존 코드와 호환을 위해 임시로 둠
    public static Cell of(int x, int y) {
        return new Cell(x, y);
    }

    @Override
    public int compareTo(Cell other) {
        return this.y == other.y
                ? Integer.compare(this.x, other.x)
                : Integer.compare(this.y, other.y);
    }

    @Override
    public Cell translate(int translateX, int translateY) {
        return Cell.of(this.x + translateX, this.y + translateY);
    }

    public Cell difference(Cell other) {
        return Cell.of(this.x - other.x, this.y - other.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell that = (Cell) o;
        return this.x == that.x && this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Cell{" + "x=" + x + ", y=" + y + '}';
    }
}
