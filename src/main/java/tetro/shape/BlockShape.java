package tetro.shape;

import tetro.Rotatable;
import tetro.block.BlockType;
import tetro.offset.Offsets;

import java.util.Objects;

// TODO: BlockShape offset이랑 blockType.offset이 같이 있으면 이름이 모호함
// TODO: from이랑 rotate는 항상 같이 호출됨
public final class BlockShape implements Rotatable<BlockShape> {
    private final BlockType type;
    private final Offsets offsets;

    private BlockShape(BlockType type, Offsets offsets) {
        this.type = type;
        this.offsets = offsets;
    }

    // TODO
    public static BlockShape from(BlockType blockType) {
        return new BlockShape(blockType, blockType.offsets);
    }

    // TODO: rotate + translateToZeroOffset 합친 메서드?
    @Override
    public BlockShape rotate(int rotation) {
        final Offsets rotateOffsets = offsets.rotate(rotation).translateToZeroOffset();
        return new BlockShape(type, rotateOffsets);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockShape)) return false;
        BlockShape that = (BlockShape) o;
        return this.type == that.type && this.offsets.equals(that.offsets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, offsets);
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