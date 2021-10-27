package tetro;

import tetro.cell.Offsets;

import java.util.HashSet;
import java.util.Objects;
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
        return remainingBlankOffsets().size() != 0;
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

    public Offsets boardBlankOffsets() {
        return board.blankOffsets();
    }

    public Offsets remainingBlankOffsets() {
        return board.blankOffsets().difference(nonBlanks.offsets());
    }

    private final class NonBlanks {
        private final Set<FitBlock> fitBlocks = new HashSet<>();

        public boolean add(FitBlock fitBlock) {
            return canFit(fitBlock.offsets()) && fitBlocks.add(fitBlock);
        }

        private boolean canFit(Offsets offsets) {
            return Puzzle.this.remainingBlankOffsets().containsAll(offsets);
        }

        public boolean remove(FitBlock fitBlock) {
            return fitBlocks.remove(fitBlock);
        }

        public Set<FitBlock> fitBlockSet() {
            return Set.copyOf(fitBlocks);
        }

        public Offsets offsets() {
            return fitBlocks.stream()
                    .flatMap(e -> e.offsets().stream())
                    .collect(collectingAndThen(Collectors.toSet(), Offsets::of));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof NonBlanks)) return false;
            NonBlanks nonBlanks = (NonBlanks) o;
            return fitBlocks.equals(nonBlanks.fitBlocks);
        }

        @Override
        public int hashCode() {
            return Objects.hash(fitBlocks);
        }
    }
}
