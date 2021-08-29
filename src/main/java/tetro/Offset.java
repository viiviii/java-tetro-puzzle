package tetro;

import java.util.Objects;

/**
 * 좌표의 x, y 위치를 나타낸다
 */
public final class Offset implements Comparable<Offset> {
    public static final Offset INVALID = new Offset(-99999, -99999);

    public final int x;
    public final int y;

    private Offset(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // TODO: 기존 코드와 호환을 위해 임시로 둠
    public static Offset of(int x, int y) {
        return new Offset(x, y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offset)) return false;
        Offset that = (Offset) o;
        return this.x == that.x && this.y == that.y;
    }

    @Override
    public int compareTo(Offset other) {
        return this.y == other.y
                ? Integer.compare(this.x, other.x)
                : Integer.compare(this.y, other.y);
    }

    @Override
    public String toString() {
        return "Offset{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
