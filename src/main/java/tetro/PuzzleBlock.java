package tetro;

import tetro.grid.cells.AbstractNonBlankCells;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.Objects;

public final class PuzzleBlock extends AbstractNonBlankCells {
    private final Block block;
    private final Offset offsetInThePuzzle;

    public PuzzleBlock(Block block, Offset offsetInThePuzzle) {
        this.block = block;
        this.offsetInThePuzzle = offsetInThePuzzle;
    }

    @Override
    public Offsets offsets() {
        return this.block.cells().offsets().translateTo(offsetInThePuzzle);
    }

    // todo: 메서드명, 클래스명 fillState랑 헷갈림
    public PuzzleBlock.State blockState() {
        return new State(this.block, this.offsetInThePuzzle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PuzzleBlock)) return false;
        if (!super.equals(o)) return false;
        PuzzleBlock that = (PuzzleBlock) o;
        return block.equals(that.block) && offsetInThePuzzle.equals(that.offsetInThePuzzle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), block, offsetInThePuzzle);
    }

    public static final class State {
        private final BlockType type;
        private final int rotation;
        private final Offset offset;

        // for testing
        public State(BlockType type, int rotation, Offset offset) {
            this.type = type;
            this.rotation = rotation;
            this.offset = offset;
        }

        public State(Block block, Offset offset) {
            this(block.type(), block.rotation(), offset);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State)) return false;
            State that = (State) o;
            return rotation == that.rotation && type == that.type && offset.equals(that.offset);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, rotation, offset);
        }
    }
}