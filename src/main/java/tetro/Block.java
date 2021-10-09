package tetro;

import tetro.grid.AbstractGrid;
import tetro.grid.cells.AbstractNonBlankCells;
import tetro.offset.Offsets;

import java.util.Objects;

public final class Block extends AbstractGrid<Block.Shape> {
    private final BlockType type;
    private final int rotation;

    private Block(Shape shape, BlockType type, int rotation) {
        super(Shape.SIZE, shape);
        this.type = type;
        this.rotation = rotation;
    }

    public Block(Offsets offsets, BlockType type, int rotation) {
        this(new Shape(offsets), type, rotation);
    }

    public BlockType type() {
        return this.type;
    }

    public int rotation() {
        return this.rotation;
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

    protected static final class Shape extends AbstractNonBlankCells {
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
