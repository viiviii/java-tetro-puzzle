package tetro.shape;

import tetro.Offset;

import java.util.Set;
import java.util.function.Predicate;

public final class BlockShape extends Shape {
    private static final int SIZE = 4;

    private static final Predicate<Offset> validOffset = offset ->
            offset.x >= 0 && offset.y >= 0 && offset.x < SIZE && offset.y < SIZE;

    public BlockShape(Set<Offset> offsets) {
        super(offsets);
    }

    @Override
    protected void validate(Set<Offset> offsets) throws IllegalArgumentException {
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

    private boolean hasInvalidOffset(Set<Offset> offsets) {
        return offsets.stream().anyMatch(validOffset.negate());
    }

    public static boolean canContain(Offset offset) {
        return validOffset.test(offset);
    }

    public String toGridString() {
        final boolean[][] grid = gridFilledWithShapeOffsets();
        return toStringBy(grid);
    }

    private boolean[][] gridFilledWithShapeOffsets() {
        final boolean FILL = true;
        final boolean[][] grid = new boolean[SIZE][SIZE];
        this.offsets().forEach(offset -> grid[offset.y][offset.x] = FILL);
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