package tetro;

import java.util.Objects;

public abstract class OffsetBase {
    public final int x;
    public final int y;

    protected OffsetBase(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OffsetBase)) return false;
        OffsetBase that = (OffsetBase) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "OffsetBase{" + "x=" + x + ", y=" + y + '}';
    }
}
