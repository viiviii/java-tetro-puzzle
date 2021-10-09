package tetro;

import tetro.grid.cells.AbstractNonBlankCells;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.*;
import java.util.stream.Collectors;

public final class Puzzle {
    private final Board board;
    private final FitCells fitCells;
    private final Board.BlankCells remainingBlankCells;

    private Puzzle(Board board, FitCells fitCells) {
        this.board = board;
        this.fitCells = fitCells;
        this.remainingBlankCells = board.cells().difference(fitCells);
    }

    public Puzzle(Board board) {
        this(board, FitCells.EMPTY);
    }

    public boolean completed() {
        return remainingBlankCells.size() == 0;
    }

    public Board.BlankCells remainingBlankCells() {
        return remainingBlankCells;
    }

    public FitCells fitCells() {
        return fitCells;
    }

    // todo: 메서드명이랑 Optional 리턴하는게 안어울려
    public Optional<Puzzle> put(Block block, Offset boardOffset) {
        final FitBlock fitBlock = new FitBlock(block, boardOffset);
        final boolean canFit = remainingBlankCells.containsAll(fitBlock);
        return canFit
                ? Optional.of(new Puzzle(this.board, fitCells.add(fitBlock)))
                : Optional.empty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Puzzle)) return false;
        Puzzle that = (Puzzle) o;
        return board.equals(that.board)
                && fitCells.equals(that.fitCells)
                && remainingBlankCells.equals(that.remainingBlankCells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, fitCells, remainingBlankCells);
    }

    // todo: 클래스명
    public static final class FitCells extends AbstractNonBlankCells {
        public static final FitCells EMPTY = new FitCells(Offsets.EMPTY, Collections.EMPTY_SET);

        private final Offsets offsets;
        private final Set<FitBlock> fitBlocks;

        private FitCells(Offsets offsets, Set<FitBlock> fitBlocks) {
            this.offsets = offsets;
            this.fitBlocks = fitBlocks;
        }

        public FitCells add(FitBlock fitBlock) {
            final Offsets newOffsets = this.offsets.add(fitBlock.offsets());
            final Set<FitBlock> newBlocks = new HashSet<>(this.fitBlocks);
            newBlocks.add(fitBlock);
            return new FitCells(newOffsets, newBlocks);
        }

        @Override
        public Offsets offsets() {
            return this.offsets;
        }

        // todo
        public Set<FitBlock.State> blockStates() {
            return this.fitBlocks.stream()
                    .map(e -> e.blockState())
                    .collect(Collectors.toUnmodifiableSet());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FitCells)) return false;
            if (!super.equals(o)) return false;
            FitCells that = (FitCells) o;
            return offsets.equals(that.offsets) && fitBlocks.equals(that.fitBlocks);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), offsets, fitBlocks);
        }
    }
}
