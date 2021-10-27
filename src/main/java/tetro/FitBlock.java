package tetro;


import tetro.cell.Offsets;

import java.util.Objects;

public final class FitBlock {
    private final Block block;
    private final Offset offsetInThePuzzle;

    public FitBlock(Block block, Offset offsetInThePuzzle) {
        this.block = block;
        this.offsetInThePuzzle = offsetInThePuzzle;
    }

    public Offsets offsets() {
        return this.block.shapeOffsets().translateTo(offsetInThePuzzle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FitBlock)) return false;
        FitBlock that = (FitBlock) o;
        return this.block.equals(that.block) && this.offsetInThePuzzle.equals(that.offsetInThePuzzle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(block, offsetInThePuzzle);
    }
}