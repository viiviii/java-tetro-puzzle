package tetro.block;

import tetro.shape.BlockShape;

public abstract class Block {
    private final BlockType type;
    private final BlockShape shape;

    protected Block(BlockType type, BlockShape shape) {
        this.type = type;
        this.shape = shape;
        System.out.println("this = " + this); // TODO: 제거
    }

    public BlockType type() {
        return type;
    }

    public BlockShape shape() {
        return shape;
    }

    // TODO
    @Override
    public String toString() {
        return "Block{" + "type=" + type + ", shape=" + shape + '}';
    }
}
