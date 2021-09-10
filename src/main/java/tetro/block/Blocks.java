package tetro.block;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public final class Blocks {
    private final EnumMap<BlockType, Block> blocks = new EnumMap(BlockType.class);

    // TODO
    public Blocks(BlockShapeData blockShapeData) {
        blockShapeData.map
                .forEach((k, v) -> blocks.put(k, new Block(k, blockShapes(v))));
    }

    public Block get(BlockType blockType) {
        return blocks.get(blockType);
    }

    private List<BlockShape> blockShapes(List<List<Integer>> list) {
        return list.stream()
                .map(innerList -> new BlockShape(innerList))
                .collect(Collectors.toUnmodifiableList());
    }
}
