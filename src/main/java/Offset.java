
/**
* 좌표의 x, y 위치를 나타낸다
* */
// TODO: 이펙티브 자바 equals를 재정의하려거든 hashCode도 재정의하라
public class Offset {
    final int x;
    final int y;

    public Offset(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(!getClass().equals(obj.getClass())) return false;
        Offset other = (Offset) obj;
        return this.x == other.x && this.y == other.y;
    }
}
