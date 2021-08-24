package tetro;

import tetro.block.BlockShape;
import tetro.exception.OffsetOutOfBoundsException;

/**
 * 좌표의 x, y 위치를 나타낸다
 */
// TODO: 이펙티브 자바 equals를 재정의하려거든 hashCode도 재정의하라
// TODO: cache 배열에서 가져오면 equals, compareTo, hashCode, ...도 변경하는게 맞는건가?
public final class Offset implements Comparable<Offset> {
    public final int x;
    public final int y;

    public static final Offset INVALID = new Offset(-99999, -99999);
    public static final int LENGTH = Board.LENGTH;

    private static final Offset[] cache = new Offset[LENGTH * LENGTH];

    static {
        for (int x = 0; x < LENGTH; x++) {
            for (int y = 0; y < LENGTH; y++) {
                cache[index(x, y)] = new Offset(x, y);
            }
        }
    }

    private Offset(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Offset of(int x, int y) {
        return cache[index(x, y)];
    }

    private static Integer index(int x, int y) {
        if (invalidBounds(x, y)) {
            throw new OffsetOutOfBoundsException(x, y);
        }
        return x + (y * LENGTH);
    }

    private static boolean invalidBounds(int x, int y) {
        return x < 0 || y < 0 || x >= LENGTH || y >= LENGTH;
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
