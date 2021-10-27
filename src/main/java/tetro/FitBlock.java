package tetro;


import tetro.cell.Cells;

import java.util.Objects;

public final class FitBlock {
    private final Block block;
    private final Offset offset;

    public FitBlock(Block block, Offset offset) {
        this.block = block;
        this.offset = offset;
    }

    public Cells cells() {
        return this.block.cells().translateTo(offset);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FitBlock)) return false;
        FitBlock that = (FitBlock) o;
        return this.block.equals(that.block) && this.offset.equals(that.offset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(block, offset);
    }
}