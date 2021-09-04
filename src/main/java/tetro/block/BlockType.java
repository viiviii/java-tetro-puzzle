package tetro.block;

import tetro.shape.BlockShape;

import java.util.ArrayList;
import java.util.List;

public enum BlockType {
    O(1, 0, 0, 1, 0, 0, 1, 1, 1),
    I(2, 0, 0, 0, 1, 0, 2, 0, 3),
    S(2, 1, 0, 2, 0, 0, 1, 1, 1),
    Z(2, 0, 0, 1, 0, 1, 1, 2, 1),
    T(4, 0, 0, 1, 0, 2, 0, 1, 1),
    J(4, 1, 0, 1, 1, 0, 2, 1, 2),
    L(4, 0, 0, 0, 1, 0, 2, 1, 2);

    public final BlockShapes shapes;

    BlockType(int numberOfShapes, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        this.shapes = BlockShapes.of(numberOfShapes, x1, y1, x2, y2, x3, y3, x4, y4);
    }

    private static class BlockShapes {
        final List<BlockShape> shapes;

        private BlockShapes(List<BlockShape> shapes) {
            this.shapes = shapes;
        }

        // TODO
        private static BlockShapes of(int numberOfShapes, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
            return new BlockShapes(new ArrayList<BlockShape>());
        }
    }
}
