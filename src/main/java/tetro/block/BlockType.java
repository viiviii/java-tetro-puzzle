package tetro.block;

import tetro.offset.Offset;
import tetro.offset.Offsets;

public enum BlockType {
    O(1,0, 0, 1, 0, 0, 1, 1, 1),
    I(2,0, 0, 0, 1, 0, 2, 0, 3),
    S(2,1, 0, 2, 0, 0, 1, 1, 1),
    Z(2,0, 0, 1, 0, 1, 1, 2, 1),
    T(4,0, 0, 1, 0, 2, 0, 1, 1),
    J(4,1, 0, 1, 1, 0, 2, 1, 2),
    L(4,0, 0, 0, 1, 0, 2, 1, 2);

    public final int numberOfShapes;
    public final Offsets offsets;

    // TODO: o1x, o1y, ... 이 부분
    BlockType(int numberOfShapes, int o1x, int o1y, int o2x, int o2y, int o3x, int o3y, int o4x, int o4y) {
        this.numberOfShapes = numberOfShapes;
        final Offset o1 = Offset.of(o1x, o1y);
        final Offset o2 = Offset.of(o2x, o2y);
        final Offset o3 = Offset.of(o3x, o3y);
        final Offset o4 = Offset.of(o4x, o4y);
        this.offsets = Offsets.of(o1, o2, o3, o4);
    }
}
