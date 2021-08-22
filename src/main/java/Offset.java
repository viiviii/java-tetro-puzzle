
/**
* 좌표의 x, y 위치를 나타낸다
* */
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
