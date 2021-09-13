package tetro.block;

import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.Objects;

// TODO: 클래스명
public final class FitBlock {
    private final Block block;
    private final int rotation;
    private final Offset offset;

    // TODO: 생성 최선인가?
    public FitBlock(Block block, int rotation, Offset offset) {
        this.block = block;
        this.rotation = rotation;
        this.offset = offset;
    }

    public Offsets offsets() {
        return block.shape(rotation).offsets().translateTo(offset);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FitBlock)) return false;
        FitBlock that = (FitBlock) o;
        return this.rotation == that.rotation
                && this.block.equals(that.block)
                && this.offset.equals(that.offset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(block, rotation, offset);
    }

    @Override
    public String toString() {
        return getClass() + "{" + "block=" + block + ", rotation=" + rotation + ", offset=" + offset + '}';
    }
}
