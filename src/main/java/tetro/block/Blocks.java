package tetro.block;

import tetro.data.BlockShapesData;

import java.util.EnumMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class Blocks {
    private final EnumMap<BlockType, Block> blocks = new EnumMap(BlockType.class);

    // TODO
    public Blocks(BlockShapesData blockShapesData) {
        blockShapesData.map
                .forEach((k, v) -> blocks.put(k, new Block(k, blockShapes(v))));
    }

    public Block get(BlockType blockType) {
        return blocks.get(blockType);
    }

    public Set<Block> all() {
        return Set.copyOf(blocks.values());
    }

    private List<BlockShape> blockShapes(List<List<Integer>> list) {
        return list.stream()
                .map(innerList -> toBlockShape(innerList))
                .collect(Collectors.toUnmodifiableList());
    }

    // TODO
    private BlockShape toBlockShape(List<Integer> list) {
        return new BlockShape(
                list.get(0), list.get(1), list.get(2), list.get(3),
                list.get(4), list.get(5), list.get(6), list.get(7));
    }
}
