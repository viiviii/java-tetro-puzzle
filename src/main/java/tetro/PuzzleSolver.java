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
        final Offset first = puzzle.blanks().first();
        Board currentBoard = new Board(puzzle.boardBlanks().offsets());
        Set<FitBlock> currentFitBlocks = puzzle.fitBlockSet();

        for (Block block : Blocks.all()) {
            final FitBlock fitBlock = new FitBlock(block, first);
            Puzzle newPuzzle = new Puzzle(currentBoard);
            currentFitBlocks.forEach(e -> newPuzzle.put(e));
            boolean put = newPuzzle.put(fitBlock);
            if (!put) continue;
            if (!newPuzzle.hasBlanks()) {
                result.add(newPuzzle);
                return result;
            }

            Set<Puzzle> remainingCombinations = combinationsFitOf(newPuzzle);
            if (remainingCombinations.isEmpty()) continue;

            result.addAll(remainingCombinations);
        }
        return result;
    }
}
