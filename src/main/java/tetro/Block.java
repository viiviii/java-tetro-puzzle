package tetro;

import tetro.grid.Grid;
import tetro.grid.cells.AbstractCells;
import tetro.offset.Offsets;

import java.util.Objects;

public final class Block {
    private final Grid<Shape> shapeGrid;
    private final BlockType type;
    private final int rotation;

    private Block(Grid<Shape> shapeGrid, BlockType type, int rotation) {
        this.shapeGrid = shapeGrid;
        this.type = type;
        this.rotation = rotation;
    }

    private Block(Shape shape, BlockType type, int rotation) {
        this(new Grid(Shape.SIZE, shape), type, rotation);
    }

    // todo
    public Block(Offsets offsets, BlockType type, int rotation) {
        this(new Shape(offsets), type, rotation);
    }

    public Shape shape() {
        return shapeGrid.cells();
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

    protected static final class Shape extends AbstractCells {
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
