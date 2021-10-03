package tetro;

import tetro.grid.AbstractGrid;
import tetro.grid.cells.AbstractFillCells;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.Objects;

public final class Block extends AbstractGrid<Block.Shape> {
    private Shape shape;
    private final BlockType type;
    private final int rotation;

    private Block(Shape shape, BlockType type, int rotation) {
        super(Shape.SIZE, shape);
        this.shape = shape;
        this.type = type;
        this.rotation = rotation;
    }

    public Block(Offsets offsets, BlockType type, int rotation) {
        this(new Shape(offsets), type, rotation);
    }

    @Override
    public int length() {
        return Shape.SIZE;
    }

    @Override
    public Shape cells() {
        return shape;
    }

    public BlockType type() {
        return this.type;
    }

    public int rotation() {
        return this.rotation;
    }

    public Block translateTo(Offset offset) {
        final Offsets translateOffsets = this.cells().offsets().translateTo(offset);
        return new Block(translateOffsets, this.type, this.rotation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Block)) return false;
        if (!super.equals(o)) return false;
        Block other = (Block) o;
        return rotation == other.rotation && type == other.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, rotation);
    }

    protected static final class Shape extends AbstractFillCells {
        private static final int SIZE = 4;

        private final Offsets offsets;

        public Shape(Offsets offsets) {
            validate(offsets);
            this.offsets = offsets;
        }

        private static void validate(Offsets offsets) {
            if (offsets.size() == SIZE) return;
            throw new IllegalArgumentException("'offsets.size()' is not equal to SIZE: "
                    + "<offsets size> " + offsets.size() + ", <SIZE> " + SIZE);
        }

        @Override
        public Offsets offsets() {
            return this.offsets;
        }

        @Override
        public String toString() {
            return "Shape{" + offsets + '}';
        }
    }
}
