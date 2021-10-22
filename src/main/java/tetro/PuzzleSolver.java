package tetro;

import tetro.offset.Offset;

import java.util.HashSet;
import java.util.Optional;
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
        final Set<Puzzle> result = new HashSet<>();
        final Offset first = puzzle.blanks().first();
        for (Block block : Blocks.all()) {
            final FitBlock fitBlock = new FitBlock(block, first);
            boolean put = puzzle.put(fitBlock);
            if (!put) continue;
            if (!puzzle.hasBlanks()) {
                result.add(puzzle);
                return result;
            }

            Set<Puzzle> remainingCombinations = combinationsFitOf(puzzle);
            if (remainingCombinations.isEmpty()) continue;

            result.addAll(remainingCombinations);
        }
        return result;
    }
}
