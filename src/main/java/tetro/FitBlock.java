package tetro;

import tetro.grid.cells.AbstractCells;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.Objects;

public final class FitBlock extends AbstractCells {
    private final Block block;
    private final Offset offsetInThePuzzle;

    public FitBlock(Block block, Offset offsetInThePuzzle) {
        this.block = block;
        this.offsetInThePuzzle = offsetInThePuzzle;
    }

    @Override
    public Offsets offsets() {
        return this.block.shapeOffsets().translateTo(offsetInThePuzzle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FitBlock)) return false;
        if (!super.equals(o)) return false;
        FitBlock that = (FitBlock) o;
        return block.equals(that.block) && offsetInThePuzzle.equals(that.offsetInThePuzzle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), block, offsetInThePuzzle);
    }
}