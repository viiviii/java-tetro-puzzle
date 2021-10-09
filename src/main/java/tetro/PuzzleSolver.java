package tetro;

import tetro.offset.Offset;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class PuzzleSolver {
    private PuzzleSolver() {
    }

    public static Set<Puzzle.FitCells> allFitCombinations(Puzzle puzzle) {
        final Set<Puzzle> combinations = combinationsFitOf(puzzle);
        return combinations.stream()
                .map(e -> e.fitCells())
                .collect(Collectors.toUnmodifiableSet());
    }

    // TODO: 메서드명
    private static Set<Puzzle> combinationsFitOf(Puzzle puzzle) {
        assert puzzle.remainingBlankCells().size() != 0;
        final Set<Puzzle> result = new HashSet<>();
        final Offset offset = puzzle.remainingBlankCells().first(); // todo: 변수명

        for (Block block : Blocks.all()) { // todo: Blocks.all()
            Optional<Puzzle> optional = puzzle.put(block, offset); // todo: 변수명
            if (optional.isEmpty()) continue;

            Puzzle nextPuzzle = optional.get(); // todo: 변수명
            if (nextPuzzle.completed()) {
                result.add(nextPuzzle);
                return result;
            }

            Set<Puzzle> nextCombinations = combinationsFitOf(nextPuzzle);
            if (nextCombinations.isEmpty()) continue;

            result.addAll(nextCombinations);
        }
        return result;
    }
}
