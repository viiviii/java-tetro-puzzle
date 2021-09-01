package tetro.shape;

import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.Objects;

public final class BlockShape {
    private static final int SIZE = 4;
    private final Offsets offsets;

    private BlockShape(Offsets offsets) {
        this.offsets = offsets;
    }

    // TODO
    public static final BlockShape from(Offsets offsets) {
        validate(offsets);
        return new BlockShape(offsets);
    }

    private static void validate(Offsets offsets) throws IllegalArgumentException {
        if (offsets.size() != SIZE) {
            throw new IllegalArgumentException("'offsets.size()' is not equal to BlockShape size: " +
                    "<offsets size> " + offsets.size() + ", " +
                    "<shape size> " + SIZE);
        }

        if (hasInvalidOffset(offsets)) {
            throw new IllegalArgumentException("Has invalid Offset in 'offsets': " +
                    "<offsets> " + offsets);
        }
    }

    private static boolean hasInvalidOffset(Offsets offsets) {
        return offsets.toImmutableSet().stream().anyMatch(e -> !canContain(e));
    }

    public static boolean canContain(Offset offset) {
        return offset.x >= 0 && offset.y >= 0 && offset.x < SIZE && offset.y < SIZE;
    }

    public Offsets offsets() {
        return offsets;
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
        offsets.toImmutableSet().forEach(offset -> grid[offset.y][offset.x] = FILL);
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