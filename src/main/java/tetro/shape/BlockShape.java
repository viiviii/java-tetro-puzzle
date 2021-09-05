package tetro.shape;

import tetro.Rotatable;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.Objects;
import java.util.function.Predicate;

public final class BlockShape implements Rotatable<BlockShape> {
    private static final int SIZE = 4;
    private final Offsets offsets;

    private BlockShape(Offsets offsets) {
        this.offsets = offsets;
    }

    public static BlockShape from(Offsets offsets) {
        final Offsets positiveOffsets = offsets.translateToZeroOffset();
        validate(positiveOffsets);
        return new BlockShape(positiveOffsets);
    }

    private static void validate(Offsets offsets) throws IllegalArgumentException {
        if (offsets.size() != SIZE) {
            throw new IllegalArgumentException("'offsets.size()' is not equal to BlockShape size: " +
                    "<offsets size> " + offsets.size() + ", " +
                    "<shape size> " + SIZE);
        }

        if (hasInvalidOffset(offsets)) {
            throw new IllegalArgumentException("Has invalid Offset in 'offsets': " +
                    "<offsets> " + offsets + ", " +
                    "<valid> 0 <= xy < " + SIZE);
        }
    }

    private static boolean hasInvalidOffset(Offsets offsets) {
        final Predicate<Offset> validRange = (e) -> (e.x >= 0 && e.x < SIZE) && (e.y >= 0 && e.y < SIZE);
        return offsets.stream().anyMatch(e -> validRange.negate().test(e));
    }

    @Override
    public BlockShape rotate(int rotation) {
        final Offsets rotateOffsets = this.offsets.rotate(rotation);
        return BlockShape.from(rotateOffsets);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockShape)) return false;
        BlockShape that = (BlockShape) o;
        return offsets.equals(that.offsets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offsets);
    }

    @Override
    public String toString() {
        return "BlockShape{" + "offsets=" + offsets + '}';
    }

    public String toGridString() {
        final boolean[][] grid = gridFilledWithShapeOffsets();
        return toStringBy(grid);
    }

    private boolean[][] gridFilledWithShapeOffsets() {
        final boolean FILL = true;
        final boolean[][] grid = new boolean[SIZE][SIZE];
        offsets.stream().forEach(offset -> grid[offset.y][offset.x] = FILL);
        return grid;
    }

    private String toStringBy(boolean[][] grid) {
        final StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        for (boolean[] row : grid) {
            for (boolean filled : row) {
                sb.append(String.format("%-2s", filled ? "■" : ""));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}