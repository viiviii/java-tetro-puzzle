package tetro;

import tetro.block.*;
import tetro.offset.Offset;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// TODO: 클래스명
public final class Puzzle {
    private final BlockShapes blockShapes; // TODO

    public Puzzle(BlockShapes blockShapes) {
        this.blockShapes = blockShapes;
    }


    // TODO: 메서드명
    public Set<Set<Block>> put(EmptyGrid emptyGrid) throws Exception {
        Set<Set<Block>> combinations = combinationsFitOf(emptyGrid);
        return combinations;
    }

    // TODO: 메서드명
    private Set<Set<Block>> combinationsFitOf(EmptyGrid emptyGrid) {
        final Set<Set<Block>> result = new HashSet<>();
        if (emptyGrid.isFull()) return result;
        final Set<Block> blocks = blocksFittingTo(emptyGrid);

        for (Block block : blocks) {
            final EmptyGrid remainingEmptyGrid = emptyGrid.fit(block);

            if (remainingEmptyGrid.isFull()) {
                result.add(blocks);
                return result;
            }
            Set<Set<Block>> combinations = combinationsFitOf(remainingEmptyGrid);
            if (combinations.isEmpty()) continue;

            for (Set<Block> combination : combinations) {
                combination.add(block);
            }
            result.addAll(combinations);
        }
        return result;
    }

    // TODO: 메서드명, unmodifiableSet?
    private Set<Block> blocksFittingTo(EmptyGrid emptyGrid) {
        final Offset offsetOnTheBoard = emptyGrid.first();
        return this.blockShapes.all().stream()
                .map(shape -> new Block(shape, offsetOnTheBoard))
                .filter(block -> emptyGrid.canFit(block))
                .collect(Collectors.toSet());
    }
}
