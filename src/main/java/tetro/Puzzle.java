package tetro;

import tetro.grid.AbstractGrid;
import tetro.grid.cells.AbstractNonBlankCells;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.*;
import java.util.stream.Collectors;

public final class Puzzle extends AbstractGrid<Puzzle.FitBlocks> {
    private final Board board;
    private final FitBlocks fittedBlocks;

    private Puzzle(Board board, FitBlocks fittedBlocks) {
        super(board.length(), fittedBlocks);
        this.board = board;
        this.fittedBlocks = fittedBlocks;
    }

    public Puzzle(Board board) {
        this(board, FitBlocks.NONE); // todo
    }

    public Optional<Puzzle> fit(Block block, Offset boardOffset) {
        final FitBlock puzzleBlock = new FitBlock(block, boardOffset);
        final boolean canFit = remainBlankCells().canFit(puzzleBlock);
        if (!canFit) return Optional.empty();
        return Optional.of(new Puzzle(this.board, fittedBlocks.add(puzzleBlock)));
    }

    @Override
    public int length() {
        return board.length();
    }

    @Override
    public FitBlocks cells() {
        return this.fittedBlocks;
    }

    public Board.BlankCells remainBlankCells() {
        return board.cells().fit(this.cells());
    }

    public FitBlocks blocks() {
        return this.fittedBlocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Puzzle)) return false;
        if (!super.equals(o)) return false;
        Puzzle that = (Puzzle) o;
        return board.equals(that.board) && fittedBlocks.equals(that.fittedBlocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), board, fittedBlocks);
    }

    // todo: 클래스명
    public static final class FitBlocks extends AbstractNonBlankCells {
        public static final FitBlocks NONE = new FitBlocks(Collections.EMPTY_SET);

        private final Set<FitBlock> puzzleBlocks;

        public FitBlocks(Set<FitBlock> puzzleBlocks) {
            this.puzzleBlocks = puzzleBlocks;
        }

        public FitBlocks add(FitBlock puzzleBlock) {
            final Set<FitBlock> newInstance = new HashSet<>(this.puzzleBlocks);
            newInstance.add(puzzleBlock);
            return new FitBlocks(newInstance);
        }

        @Override
        public Offsets offsets() {
            // todo
            final Set<Offset> set = this.puzzleBlocks.stream()
                    .map(e -> e.offsets())
                    .flatMap(e -> e.stream())
                    .collect(Collectors.toUnmodifiableSet());
            return Offsets.of(set);
        }

        // todo: 메서드명
        public Set<FitBlock.State> blockStates() {
            return this.puzzleBlocks.stream()
                    .map(e -> e.blockState())
                    .collect(Collectors.toUnmodifiableSet());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FitBlocks)) return false;
            if (!super.equals(o)) return false;
            FitBlocks that = (FitBlocks) o;
            return puzzleBlocks.equals(that.puzzleBlocks);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), puzzleBlocks);
        }
    }
}
