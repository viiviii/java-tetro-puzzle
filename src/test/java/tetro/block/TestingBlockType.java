package tetro.block;

import tetro.offset.Offset;
import tetro.offset.Offsets;

// TODO: 못없애나?
public enum TestingBlockType {
    O(0, 0, 1, 0, 0, 1, 1, 1),
    I(0, 0, 0, 1, 0, 2, 0, 3),
    S(1, 0, 2, 0, 0, 1, 1, 1),
    Z(0, 0, 1, 0, 1, 1, 2, 1),
    T(0, 0, 1, 0, 2, 0, 1, 1),
    J(1, 0, 1, 1, 0, 2, 1, 2),
    L(0, 0, 0, 1, 0, 2, 1, 2);

    TestingBlockType(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        final Offset o1 = Offset.of(x1, y1);
        final Offset o2 = Offset.of(x2, y2);
        final Offset o3 = Offset.of(x3, y3);
        final Offset o4 = Offset.of(x4, y4);
        this.offsets = Offsets.of(o1, o2, o3, o4);
    }

    public final Offsets offsets;
}
