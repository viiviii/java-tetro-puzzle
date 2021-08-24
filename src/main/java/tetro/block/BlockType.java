package tetro.block;

import tetro.Offset;

import java.util.Set;

public enum BlockType {
    I(0, 0, 0, 1, 0, 2, 0, 3),
    O(0, 0, 1, 0, 0, 1, 1, 1),
    T(0, 0, 1, 0, 2, 0, 1, 1),
    S(1, 0, 2, 0, 0, 1, 1, 1),
    Z(0, 0, 1, 0, 1, 1, 2, 1),
    J(1, 0, 1, 1, 0, 2, 1, 2),
    L(0, 0, 0, 1, 0, 2, 1, 2);

    private final Set<Offset> offsets;

    BlockType(int o1x, int o1y, int o2x, int o2y, int o3x, int o3y, int o4x, int o4y) {
        final Offset o1 = Offset.of(o1x, o1y);
        final Offset o2 = Offset.of(o2x, o2y);
        final Offset o3 = Offset.of(o3x, o3y);
        final Offset o4 = Offset.of(o4x, o4y);
        this.offsets = Set.of(o1, o2, o3, o4);
        System.out.println("BlockType." + this + toMatrixString());
    }

    private String toMatrixString() {
        final int MAX_BLOCK_LENGTH = 4;
        final boolean FILL = true;
        boolean[][] matrix = new boolean[MAX_BLOCK_LENGTH][MAX_BLOCK_LENGTH];
        this.offsets.forEach(o -> matrix[o.y][o.x] = FILL);
        return createStringBy(matrix);
    }

    private String createStringBy(boolean[][] matrix) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        for (boolean[] row : matrix) {
            for (boolean element : row) {
                sb.append(String.format("%-2s", element ? "■" : ""));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
