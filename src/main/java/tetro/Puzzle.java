package tetro;


import tetro.grid.cells.AbstractBlankCells;
import tetro.grid.cells.AbstractNonBlankCells;
import tetro.offset.Offsets;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

public final class Puzzle {
    private final NonBlanks nonBlanks = new NonBlanks(); // todo: mutable 명시적이게
    private final Board board;

    public Puzzle(Board board) {
        this.board = board;
    }

    public boolean hasBlanks() {
        return blanks().size() == 0;
    }

    public boolean put(FitBlock fitBlock) {
        return nonBlanks.add(fitBlock);
    }

    public boolean take(FitBlock fitBlock) {
        return nonBlanks.remove(fitBlock);
    }

    public Set<FitBlock> fitBlockSet() {
        return nonBlanks.fitBlockSet();
    }

    public Puzzle.Blanks blanks() {
        final Board.Blanks remainingBoardBlanks = board.blanks().difference(nonBlanks);
        return new Puzzle.Blanks(remainingBoardBlanks);
    }

    public static final class Blanks extends AbstractBlankCells {
        private final Offsets offsets;

        public Blanks(Board.Blanks boardBlanks) {
            this.offsets = boardBlanks.offsets();
        }

        public boolean canFit(FitBlock puzzleBlock) {
            return this.offsets().containsAll(puzzleBlock.offsets());
        }

        @Override
        public Offsets offsets() {
            return this.offsets;
        }
    }

    private final class NonBlanks extends AbstractNonBlankCells {
        private final Set<FitBlock> fitBlocks = new HashSet<>();

        public boolean add(FitBlock fitBlock) {
            final boolean canFit = Puzzle.this.blanks().canFit(fitBlock);
            return canFit && fitBlocks.add(fitBlock);
        }

        public boolean remove(FitBlock fitBlock) {
            return fitBlocks.remove(fitBlock);
        }

        public Set<FitBlock> fitBlockSet() {
            return Set.copyOf(fitBlocks);
        }

        @Override
        public Offsets offsets() {
            return fitBlocks.stream()
                    .flatMap(e -> e.offsets().stream())
                    .collect(collectingAndThen(Collectors.toSet(), Offsets::of));
        }
    }
}
