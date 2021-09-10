package tetro.block;

import java.util.List;
import java.util.Objects;

public final class Block {
    private final BlockType type;
    private final List<BlockShape> shapes;

    public Block(BlockType type, List<BlockShape> shapes) {
        this.type = type;
        this.shapes = shapes;
    }

    public BlockShape shape(int rotation) {
        return shapes.get(rotation);
    }

    public int numberOfShapes() {
        return shapes.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Block)) return false;
        Block that = (Block) o;
        return this.type == that.type && this.shapes.equals(that.shapes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, shapes);
    }

    @Override
    public String toString() {
        return "Block{" + "type=" + type + ", shapes=" + shapes + '}';
    }
}
