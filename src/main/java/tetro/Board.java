package tetro;

import tetro.grid.AbstractGrid;
import tetro.grid.cells.AbstractEmptyCells;
import tetro.grid.cells.AbstractFillCells;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class Board extends AbstractGrid<Board.EmptyCells> {

    private Board(int length, EmptyCells cells) {
        super(length, cells);
    }

    public Board(Offsets offsets) {
        this(9, new EmptyCells(offsets));
    }

    public Set<Set<Block>> allFitCombinations() {
        final Set<Set<Block>> combinations = combinationsFitOf(this.cells());
        return combinations;
    }

    // TODO: 메서드명
    private Set<Set<Block>> combinationsFitOf(EmptyCells emptyCells) {
        final Set<Set<Block>> result = new HashSet<>();
        if (emptyCells.isNone()) return result;

        // todo: blocksFittingTo랑 fit이 떨어져있음
        final Set<Block> blocks = blocksFittingTo(emptyCells);

        for (Block block : blocks) {
            final EmptyCells remainingEmptyCells = emptyCells.fit(block.cells());

            if (remainingEmptyCells.isNone()) {
                result.add(blocks);
                return result;
            }
            Set<Set<Block>> combinations = combinationsFitOf(remainingEmptyCells);
            if (combinations.isEmpty()) continue;

            for (Set<Block> combination : combinations) {
                combination.add(block);
            }
            result.addAll(combinations);
        }
        return result;
    }

    // TODO: 메서드명, unmodifiableSet?
    private Set<Block> blocksFittingTo(EmptyCells emptyCells) {
        final Offset firstEmptyCellOffset = emptyCells.offsets().first();
        return Blocks.all().stream()
                .map(block -> block.translateTo(firstEmptyCellOffset))
                .filter(block -> emptyCells.canFit(block.cells()))
                .collect(Collectors.toSet());
    }

    protected static final class EmptyCells extends AbstractEmptyCells {
        private final Offsets offsets;

        public EmptyCells(Offsets offsets) {
            this.offsets = offsets;
        }

        // todo: 메서드명
        public boolean isNone() {
            return this.offsets().size() == 0;
        }

        public boolean canFit(AbstractFillCells other) {
            return this.offsets().containsAll(other.offsets());
        }

        public EmptyCells fit(AbstractFillCells other) {
            final Offsets remaining = this.offsets().difference(other.offsets());
            return new EmptyCells(remaining);
        }

        @Override
        public Offsets offsets() {
            return this.offsets;
        }
    }
}
