package tetro.shape;

import tetro.offset.Offset;
import tetro.offset.Offsets;

public final class BlockShape extends Offsets {
    private static final int SIZE = 4;

    private BlockShape(Offsets offsets) {
        super(offsets);
    }

    // TODO
    public static final BlockShape from(Offsets offsets){
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

    public String toGridString() {
        final boolean[][] grid = gridFilledWithShapeOffsets();
        return toStringBy(grid);
    }

    private boolean[][] gridFilledWithShapeOffsets() {
        final boolean FILL = true;
        final boolean[][] grid = new boolean[SIZE][SIZE];
        this.toImmutableSet().forEach(offset -> grid[offset.y][offset.x] = FILL);
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