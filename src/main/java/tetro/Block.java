package tetro;

import tetro.grid.Grid;
import tetro.grid.Cells;

import java.util.Objects;

public final class Block {
    public static final int SIZE = 4;

    private final Grid grid;

    private final BlockType type;
    private final int rotation;

    private Block(Grid grid, BlockType type, int rotation) {
        this.grid = grid;
        this.type = type;
        this.rotation = rotation;
    }

    public static Block of(Cells cells, BlockType type, int rotation) {
        validate(cells.size());
        return new Block(new Grid(Block.SIZE, cells), type, rotation);

    }

    private static void validate(int size) {
        if (size == Block.SIZE) return;
        throw new IllegalArgumentException("'size' is not equal to Block.SIZE: "
                + "<size> " + size + ", <Block.SIZE> " + Block.SIZE);
    }

    public Cells cells() {
        return grid.cells();
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
}
