package tetro.cell;

import tetro.OffsetBase;
import tetro.Translatable;

public final class Cell extends OffsetBase implements Comparable<Cell>, Translatable<Cell> {

    public Cell(int x, int y) {
        super(x, y);
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
    public Cell translateBy(int translateX, int translateY) {
        return Cell.of(this.x + translateX, this.y + translateY);
    }

    @Override
    public String toString() {
        return "Cell{" + "x=" + x + ", y=" + y + '}';
    }
}
