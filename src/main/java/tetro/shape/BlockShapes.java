package tetro.shape;

import tetro.block.BlockType;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public final class BlockShapes {
    private final EnumMap<BlockType, List<BlockShape>> shapes = new EnumMap(BlockType.class);

    // TODO
    public BlockShapes(BlockShapeData blockShapeData) {
        blockShapeData.map.forEach((k, v) -> shapes.put(k, blockShapeListBy(v)));
    }

    public List<BlockShape> get(BlockType blockType) {
        return shapes.get(blockType);
    }

    private static List<BlockShape> blockShapeListBy(List<List<Integer>> list) {
        return list.stream()
                .map(innerList -> new BlockShape(innerList))
                .collect(Collectors.toUnmodifiableList());
    }
}
