package tetro;

/**
 * 좌표의 x, y 위치를 나타낸다
 */
public final class Offset extends OffsetBase {

    public Offset(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return "Offset{" + "x=" + x + ", y=" + y + '}';
    }
}
