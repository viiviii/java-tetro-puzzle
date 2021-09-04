package tetro.block;

import tetro.offset.Offset;
import tetro.offset.Offsets;

public enum BlockType {
    O(1, 0, 0, 1, 0, 0, 1, 1, 1),
    I(2, 0, 0, 0, 1, 0, 2, 0, 3),
    S(2, 1, 0, 2, 0, 0, 1, 1, 1),
    Z(2, 0, 0, 1, 0, 1, 1, 2, 1),
    T(4, 0, 0, 1, 0, 2, 0, 1, 1),
    J(4, 1, 0, 1, 1, 0, 2, 1, 2),
    L(4, 0, 0, 0, 1, 0, 2, 1, 2);

    public final int numberOfShapes;
    public final Offsets offsets;

    BlockType(int numberOfShapes, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        this.numberOfShapes = numberOfShapes;
        final Offset o1 = Offset.of(x1, y1);
        final Offset o2 = Offset.of(x2, y2);
        final Offset o3 = Offset.of(x3, y3);
        final Offset o4 = Offset.of(x4, y4);
        this.offsets = Offsets.of(o1, o2, o3, o4);
    }
}
