package tetro.shape;

import tetro.Offset;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public final class BlockShape extends Shape {
    private static final int SIZE = 4;

    public BlockShape(Set<Offset> offsets) {
        super(offsets);
    }

    @Override
    protected void validate(Set<Offset> offsets) throws IllegalArgumentException {
        if (shapeSizeNotEquals(offsets.size())) {
            throw new IllegalArgumentException("offsets size is not equal to shape size: " +
                    "<offsets size> " + (offsets == null ? "null" : offsets.size()) + ", " +
                    "<shape size> " + SIZE);
        }

        final Optional<Offset> invalidOffset = invalidRangeOf(offsets);
        if(invalidOffset.isPresent()) {
            throw new IllegalArgumentException("offset out of range: " +
                    "<actual> " + invalidOffset.get() + ", " +
                    "<range> Between " + Offset.ZERO + " and " + Offset.of(SIZE, SIZE));
        }
    }

    private boolean shapeSizeNotEquals(int size) {
        return SIZE != size;
    }

    private Optional<Offset> invalidRangeOf(Set<Offset> offsets) {
        final Predicate<Offset> invalid = offset ->
                offset.x < 0 || offset.x > SIZE || offset.y < 0 || offset.y > SIZE;
        return offsets.stream().filter(e -> invalid.test(e)).findAny();
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