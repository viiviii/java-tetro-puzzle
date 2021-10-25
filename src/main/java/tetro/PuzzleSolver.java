package tetro;

import tetro.offset.Offset;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class PuzzleSolver {
    private PuzzleSolver() {
    }

    public static Set<Set<FitBlock>> allFitCombinations(Puzzle puzzle) {
        final Set<Puzzle> combinations = combinationsFitOf(puzzle);
        return combinations.stream()
                .map(e -> e.fitBlockSet())
                .collect(Collectors.toUnmodifiableSet());
    }

    // TODO: 메서드명, 변수명, Blocks.all(0
    private static Set<Puzzle> combinationsFitOf(Puzzle puzzle) {
        if (!puzzle.hasBlanks()) return Collections.EMPTY_SET;
        final Set<Puzzle> result = new HashSet<>();
        final Offset first = puzzle.blanks().offsets().first();

        for (Block block : Blocks.all()) {
            final FitBlock fitBlock = new FitBlock(block, first);
            final Set<Puzzle> combinations = recursivePutWith(fitBlock, copyOf(puzzle));

            if (combinations.isEmpty()) continue;
            result.addAll(combinations);
        }
        return result;
    }

    private static Puzzle copyOf(Puzzle puzzle) {
        Board.Blanks blanks = puzzle.boardBlanks();
        Set<FitBlock> fitBlocks = puzzle.fitBlockSet();
        Puzzle newPuzzle = new Puzzle(new Board(blanks.offsets()));
        fitBlocks.forEach(e -> newPuzzle.put(e));
        return newPuzzle;
    }

    private static Set<Puzzle> recursivePutWith(FitBlock fitBlock, Puzzle puzzle) {
        if (!puzzle.put(fitBlock)) return Collections.EMPTY_SET;
        if (puzzle.hasBlanks())
            return combinationsFitOf(puzzle);
        else
            return Set.of(puzzle);
    }
}
