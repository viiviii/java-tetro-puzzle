package tetro.shape;

import tetro.Rotatable;
import tetro.block.BlockType;
import tetro.offset.Offsets;

import java.util.Objects;

public final class BlockShape {
    private final BlockType type;
    private final Offsets shape;

    private BlockShape(BlockType type, Offsets shape) {
        this.type = type;
        this.shape = shape;
    }

    // TODO: 뭔가 지저분해
    public static Rotatable<BlockShape> from(BlockType blockType) {
        return (rotation) -> {
            final Offsets rotatedShape = blockType.offsets
                    .rotate(rotation)
                    .translateToZeroOffset();
            return new BlockShape(blockType, rotatedShape);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockShape)) return false;
        BlockShape that = (BlockShape) o;
        return this.type == that.type && this.shape.equals(that.shape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, shape);
    }

    public String toGridString() {
        final boolean[][] grid = gridFilledWithShapeOffsets();
        return toStringBy(grid);
    }

    private boolean[][] gridFilledWithShapeOffsets() {
        final int SIZE = shape.size();
        final boolean FILL = true;
        final boolean[][] grid = new boolean[SIZE][SIZE];
        shape.stream().forEach(offset -> grid[offset.y][offset.x] = FILL);
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