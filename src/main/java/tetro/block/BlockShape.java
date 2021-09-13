package tetro.block;

import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.*;
import java.util.function.Predicate;

final class BlockShape {
    public static final int SIZE = 4;
    private final Offsets offsets;

    private BlockShape(Offsets offsets) {
        validate(offsets);
        this.offsets = offsets;
    }

    private BlockShape(Offset o1, Offset o2, Offset o3, Offset o4) {
        this(Offsets.of(Set.of(o1, o2, o3, o4)));
    }

    public BlockShape(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        this(Offset.of(x1, y1), Offset.of(x2, y2), Offset.of(x3, y3), Offset.of(x4, y4));
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
        final Predicate<Offset> validRange = (e) -> (e.x >= 0 && e.x < SIZE) && (e.y >= 0 && e.y < SIZE);
        return offsets.stream().anyMatch(offset -> validRange.negate().test(offset));
    }

    public Offsets offsets() {
        return this.offsets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockShape)) return false;
        BlockShape that = (BlockShape) o;
        return this.offsets.equals(that.offsets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offsets);
    }

    @Override
    public String toString() {
        return "BlockShape{" + offsets + '}';
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
        for (boolean[] row : grid) {
            sb.append(System.lineSeparator());
            for (boolean filled : row) {
                sb.append(String.format("%-2s", filled ? "■" : "□"));
            }
        }
        return sb.toString();
    }
}