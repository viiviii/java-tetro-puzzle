package tetro.block;

import tetro.block.shape.BlockShape;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.Objects;

public final class Block {
    private final BlockShape shape;
    private final Offset offsetOnTheBoard;

    public Block(BlockShape shape, Offset offsetOnTheBoard) {
        this.shape = shape;
        this.offsetOnTheBoard = offsetOnTheBoard;
    }

    public Offsets offsets() {
        return shape.offsets().translateTo(offsetOnTheBoard);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Block)) return false;
        Block that = (Block) o;
        return this.shape.equals(that.shape) && this.offsetOnTheBoard.equals(that.offsetOnTheBoard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, offsetOnTheBoard);
    }

    @Override
    public String toString() {
        return "Block{" +
                "shape=" + shape +
                ", offsetOnTheBoard=" + offsetOnTheBoard +
                '}';
    }
}
