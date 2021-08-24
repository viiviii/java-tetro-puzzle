package tetro.block;

import tetro.Offset;

public enum BlockType {
    I(0, 0, 0, 1, 0, 2, 0, 3),
    O(0, 0, 1, 0, 0, 1, 1, 1),
    T(0, 0, 1, 0, 2, 0, 1, 1),
    S(1, 0, 2, 0, 0, 1, 1, 1),
    Z(0, 0, 1, 0, 1, 1, 2, 1),
    J(1, 0, 1, 1, 0, 2, 1, 2),
    L(0, 0, 0, 1, 0, 2, 1, 2);

    public final BlockShape shape;

    // TODO: o1x, o1y, ... 이 부분
    BlockType(int o1x, int o1y, int o2x, int o2y, int o3x, int o3y, int o4x, int o4y) {
        final Offset o1 = Offset.of(o1x, o1y);
        final Offset o2 = Offset.of(o2x, o2y);
        final Offset o3 = Offset.of(o3x, o3y);
        final Offset o4 = Offset.of(o4x, o4y);
        this.shape = new BlockShape(o1, o2, o3, o4);
        System.out.println(this.getClass() + "." + this.name() + shape); // TODO: 제거
    }
}
