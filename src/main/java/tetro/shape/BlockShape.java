package tetro.shape;

import tetro.block.BlockType;
import tetro.offset.Offsets;

import java.util.Objects;

public final class BlockShape {
    private final Offsets offsets;

    private BlockShape(Offsets offsets) {
        this.offsets = offsets;
    }

    public static BlockShape from(BlockType blockType) {
        return new BlockShape(blockType.offsets);
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
        final int SIZE = offsets.size();
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