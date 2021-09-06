package tetro.block;

import tetro.shape.BlockShape;

public abstract class Block {
    private final BlockShape shape;

    protected Block(BlockShape shape) {
        this.shape = shape;
    }

    // TODO
    @Override
    public String toString() {
        return "Block{" + "shape=" + shape.toGridString() + '}';
    }
}
