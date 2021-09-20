package tetro.data;

import tetro.block.BlockType;

import java.util.*;
import java.util.function.BiConsumer;

public final class BlockShapesData {
    private final Map<BlockType, int[][]> m = new EnumMap(BlockType.class);

    {
        m.put(BlockType.O, oBlockShapes());
        m.put(BlockType.I, iBlockShapes());
        m.put(BlockType.S, sBlockShapes());
        m.put(BlockType.Z, zBlockShapes());
        m.put(BlockType.T, tBlockShapes());
        m.put(BlockType.J, jBlockShapes());
        m.put(BlockType.L, lBlockShapes());
    }

    public final void forEach(BiConsumer<BlockType, int[][]> action) {
        m.forEach(action);
    }

    /*
        ■ ■ □ □
        ■ ■ □ □
        □ □ □ □
        □ □ □ □  */
    int[][] oBlockShapes() {
        return new int[][]{
                {0, 0, 1, 0, 0, 1, 1, 1}
        };
    }

    /*
        ■ □ □ □    □ □ □ □
        ■ □ □ □    □ □ □ □
        ■ □ □ □    □ □ □ □
        ■ □ □ □    ■ ■ ■ ■  */
    int[][] iBlockShapes() {
        return new int[][]{
                {0, 0, 0, 1, 0, 2, 0, 3},
                {0, 3, 1, 3, 2, 3, 3, 3}
        };
    }

    /*
        ■ □ □ □    □ □ □ □
        ■ ■ □ □    □ ■ ■ □
        □ ■ □ □    ■ ■ □ □
        □ □ □ □    □ □ □ □  */
    int[][] sBlockShapes() {
        return new int[][]{
                {0, 0, 0, 1, 1, 1, 1, 2},
                {1, 1, 2, 1, 0, 2, 1, 2}
        };
    }

    /*
        □ ■ □ □    □ □ □ □
        ■ ■ □ □    ■ ■ □ □
        ■ □ □ □    □ ■ ■ □
        □ □ □ □    □ □ □ □  */
    int[][] zBlockShapes() {
        return new int[][]{
                {1, 0, 0, 1, 1, 1, 0, 2},
                {0, 1, 1, 1, 1, 2, 2, 2}
        };
    }

    /*
        □ ■ □ □    □ □ □ □    ■ □ □ □    □ □ □ □
        ■ ■ □ □    □ ■ □ □    ■ ■ □ □    ■ ■ ■ □
        □ ■ □ □    ■ ■ ■ □    ■ □ □ □    □ ■ □ □
        □ □ □ □    □ □ □ □    □ □ □ □    □ □ □ □  */
    int[][] tBlockShapes() {
        return new int[][]{
                {1, 0, 0, 1, 1, 1, 1, 2},
                {1, 1, 0, 2, 1, 2, 2, 2},
                {0, 0, 0, 1, 1, 1, 0, 2},
                {0, 1, 1, 1, 2, 1, 1, 2}
        };
    }

    /*
        ■ ■ □ □    □ □ □ □    □ ■ □ □    □ □ □ □
        ■ □ □ □    ■ ■ ■ □    □ ■ □ □    ■ □ □ □
        ■ □ □ □    □ □ ■ □    ■ ■ □ □    ■ ■ ■ □
        □ □ □ □    □ □ □ □    □ □ □ □    □ □ □ □  */
    int[][] jBlockShapes() {
        return new int[][]{
                {0, 0, 1, 0, 0, 1, 0, 2},
                {0, 1, 1, 1, 2, 1, 2, 2},
                {1, 0, 1, 1, 0, 2, 1, 2},
                {0, 1, 0, 2, 1, 2, 2, 2}
        };
    }

    /*
        ■ ■ □ □    □ □ □ □    ■ □ □ □    □ □ □ □
        □ ■ □ □    □ □ ■ □    ■ □ □ □    ■ ■ ■ □
        □ ■ □ □    ■ ■ ■ □    ■ ■ □ □    ■ □ □ □
        □ □ □ □    □ □ □ □    □ □ □ □    □ □ □ □  */
    int[][] lBlockShapes() {
        return new int[][]{
                {0, 0, 1, 0, 1, 1, 1, 2},
                {2, 1, 0, 2, 1, 2, 2, 2},
                {0, 0, 0, 1, 0, 2, 1, 2},
                {0, 1, 1, 1, 2, 1, 0, 2}
        };
    }
}
