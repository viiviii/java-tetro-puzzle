package tetro.block;

import tetro.Offset;

import java.util.Objects;
import java.util.Set;

public final class BlockShape {
    private static final int SIZE = 4;

    private final Set<Offset> offsets;

    public BlockShape(Set<Offset> offsets) {
        // TODO: validateArgumentSize 메서드 위치 변경 -> 방어적 복사
        validateArgumentSize(offsets);
        this.offsets = Set.copyOf(offsets);
    }

    private void validateArgumentSize(Set<Offset> offsets) {
        if (offsets != null && offsets.size() == SIZE) return;
        throw new IllegalArgumentException("argument size is not equal to block shape size");
    }

    @Override
    public int hashCode() {
        return Objects.hash(offsets);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockShape)) return false;
        BlockShape that = (BlockShape) o;
        return this.offsets.equals(that.offsets);
    }

    public Set<Offset> offsets() {
        return this.offsets;
    }

    @Override
    public String toString() {
        final boolean[][] matrix = createMatrixByOffsets();
        return toStringBy(matrix);
    }

    // TODO: 메서드명 뭔가 이상함
    private boolean[][] createMatrixByOffsets() {
        final boolean FILL = true;
        final boolean[][] matrix = new boolean[SIZE][SIZE];
        this.offsets.forEach(offset -> matrix[offset.y][offset.x] = FILL);
        return matrix;
    }

    private String toStringBy(boolean[][] matrix) {
        final StringBuilder sb = new StringBuilder();
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