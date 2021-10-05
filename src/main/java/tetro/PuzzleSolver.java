package tetro;

import tetro.offset.Offset;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class PuzzleSolver {
    private PuzzleSolver() {
    }

    public static Set<Puzzle.FittedBlocks> allFitCombinations(Puzzle puzzle) {
        final Set<Puzzle> combinations = combinationsFitOf(puzzle);
        return combinations.stream()
                .map(e -> e.blocks())
                .collect(Collectors.toUnmodifiableSet());
    }

    // TODO: 메서드명
    private static Set<Puzzle> combinationsFitOf(Puzzle puzzle) {
        if (puzzle.remainEmptyCells().isNone()) return Collections.EMPTY_SET;
        final Set<Puzzle> result = new HashSet<>();
        final Offset firstEmptyOffset = puzzle.remainEmptyCells().first(); // todo: 변수명

        for (Block block : Blocks.all()) { // todo: Blocks.all()
            Optional<Puzzle> optional = puzzle.fit(block, firstEmptyOffset); // todo: 변수명
            if (optional.isEmpty()) continue;

            Puzzle blockPutPuzzle = optional.get(); // todo: 변수명
            if (blockPutPuzzle.remainEmptyCells().isNone()) {
                result.add(blockPutPuzzle);
                return result;
            }

            Set<Puzzle> combinations = combinationsFitOf(blockPutPuzzle);
            if (combinations.isEmpty()) continue;

            result.addAll(combinations);
        }
        return result;
    }
}
