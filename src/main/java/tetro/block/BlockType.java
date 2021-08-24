package tetro.block;

import tetro.Offset;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        System.out.println("BlockType." + this + toMatrixString()); // TODO: 제거
    }

    // TODO: 리팩토링
    // TODO: 1번 이상 회전일 때 구현
    public Set<Offset> rotateOnce() {
        List<Integer> offsetX = new ArrayList<>();
        List<Integer> offsetY = new ArrayList<>();
        Set<Offset> rotateOffsets = new HashSet<>();
        int minOffsetX = 0;
        for (Offset offset : this.offsets) {
            int rotatedOffsetX = -offset.y;
            int rotatedOffsetY = offset.x;
            offsetX.add(rotatedOffsetX);
            offsetY.add(rotatedOffsetY);
            if (rotatedOffsetX < minOffsetX) {
                minOffsetX = rotatedOffsetX;
            }
        }
        for (int i = 0; i < offsetX.size(); i++) {
            int x = offsetX.get(i) + Math.abs(minOffsetX);
            int y = offsetY.get(i);
            rotateOffsets.add(Offset.of(x, y));
        }
        return Set.copyOf(rotateOffsets);
    }

    private String toMatrixString() {
        boolean[][] matrix = createMatrixBy(this.offsets);
        return createStringBy(matrix);
    }

    private boolean[][] createMatrixBy(Set<Offset> offsets) {
        final int MAX_BLOCK_LENGTH = 4;
        final boolean FILL = true;
        boolean[][] matrix = new boolean[MAX_BLOCK_LENGTH][MAX_BLOCK_LENGTH];
        offsets.forEach(offset -> matrix[offset.y][offset.x] = FILL);
        return matrix;
    }

    private String createStringBy(boolean[][] matrix) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        for (boolean[] row : matrix) {
            for (boolean filled : row) {
                sb.append(String.format("%-2s", filled ? "■" : ""));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
