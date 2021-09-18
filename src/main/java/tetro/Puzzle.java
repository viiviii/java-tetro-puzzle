package tetro;

import tetro.block.Block;
import tetro.block.Blocks;
import tetro.block.FitBlock;
import tetro.offset.Offset;

import java.util.HashSet;
import java.util.Set;

// TODO: 클래스명
public final class Puzzle {
    private final Blocks blocks; // TODO

    public Puzzle(Blocks blocks) {
        this.blocks = blocks;
    }


    // TODO: 메서드명
    public Set<Set<FitBlock>> put(EmptyGrid emptyGrid) throws Exception {
        Set<Set<FitBlock>> combinations = combinationsFitOf(emptyGrid);
        return combinations;
    }

    // TODO: 메서드명
    private Set<Set<FitBlock>> combinationsFitOf(EmptyGrid emptyGrid) {
        final Set<Set<FitBlock>> result = new HashSet<>();
        if (emptyGrid.isFull()) return result;
        final Set<FitBlock> blocks = blocksFittingTo(emptyGrid);

        for (FitBlock block : blocks) {
            final EmptyGrid remainingEmptyGrid = emptyGrid.fit(block);

            if (remainingEmptyGrid.isFull()) {
                result.add(blocks);
                return result;
            }
            Set<Set<FitBlock>> combinations = combinationsFitOf(remainingEmptyGrid);
            if (combinations.isEmpty()) continue;

            for (Set<FitBlock> combination : combinations) {
                combination.add(block);
            }
            result.addAll(combinations);
        }
        return result;
    }

    // TODO: 메서드명
    private Set<FitBlock> blocksFittingTo(EmptyGrid emptyGrid) {
        final Set result = new HashSet();
        final Offset offsetToStartFitting = emptyGrid.first();
        for (Block block : blocks.all()) {
            for (int rotation = 0; rotation < block.numberOfShapes(); rotation++) {
                final FitBlock fitBlock = new FitBlock(block, rotation, offsetToStartFitting);
                final boolean fitted = emptyGrid.canFit(fitBlock);
                if (fitted) result.add(fitBlock);
            }
        }
        return result;
    }
}
