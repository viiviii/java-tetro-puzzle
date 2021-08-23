
/**
 * 좌표의 x, y 위치를 나타낸다
 */
// TODO: 이펙티브 자바 equals를 재정의하려거든 hashCode도 재정의하라
public class Offset implements Comparable<Offset> {
    final int x;
    final int y;

    public Offset(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!getClass().equals(obj.getClass())) return false;
        Offset other = (Offset) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int compareTo(Offset other) {
        return this.y == other.y
                ? Integer.compare(this.x, other.x)
                : Integer.compare(this.y, other.y);
    }
}
