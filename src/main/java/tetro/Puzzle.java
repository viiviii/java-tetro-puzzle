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
    public Set<Set<FitBlock>> put(EmptyGrids emptyGrids) throws Exception {
        Set<Set<FitBlock>> combinations = combinationsFitOf(emptyGrids);
        return combinations;
    }

    // TODO: 메서드명
    private Set<Set<FitBlock>> combinationsFitOf(EmptyGrids emptyGrids) {
        final Set<Set<FitBlock>> result = new HashSet<>();
        if (emptyGrids.isFull()) return result;
        final Set<FitBlock> blocks = blocksFittingTo(emptyGrids);

        for (FitBlock block : blocks) {
            final EmptyGrids remainingEmptyGrids = emptyGrids.fit(block);

            if (remainingEmptyGrids.isFull()) {
                result.add(blocks);
                return result;
            }
            Set<Set<FitBlock>> combinations = combinationsFitOf(remainingEmptyGrids);
            if (combinations.isEmpty()) continue;

            for (Set<FitBlock> combination : combinations) {
                combination.add(block);
            }
            result.addAll(combinations);
        }
        return result;
    }

    // TODO: 메서드명
    private Set<FitBlock> blocksFittingTo(EmptyGrids emptyGrids) {
        final Set result = new HashSet();
        final Offset offsetToStartFitting = emptyGrids.first();
        for (Block block : blocks.all()) {
            for (int rotation = 0; rotation < block.numberOfShapes(); rotation++) {
                final FitBlock fitBlock = new FitBlock(block, rotation, offsetToStartFitting);
                final boolean fitted = emptyGrids.canFit(fitBlock);
                if (fitted) result.add(fitBlock);
            }
        }
        return result;
    }
}
