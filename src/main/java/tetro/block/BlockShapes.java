package tetro.block;

import tetro.data.BlockShapesData;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class BlockShapes {

    private BlockShapes() {
    }

    private static final EnumMap<BlockType, List<BlockShape>> cache = new EnumMap(BlockType.class);

    // TODO
    public static BlockShapes from(BlockShapesData blockShapesData) {
        if (cache.isEmpty()) {
            blockShapesData.map.forEach((type, value) -> cache.put(type, blockShapes(value)));
        }
        return new BlockShapes();
    }

    public List<BlockShape> get(BlockType blockType) {
        return cache.get(blockType);
    }

    // TODO
    private static List<BlockShape> blockShapes(List<List<Integer>> list) {
        return list.stream()
                .map(innerList -> toBlockShape(innerList))
                .collect(Collectors.toUnmodifiableList());
    }

    // TODO
    private static BlockShape toBlockShape(List<Integer> list) {
        return new BlockShape(
                list.get(0), list.get(1), list.get(2), list.get(3),
                list.get(4), list.get(5), list.get(6), list.get(7));
    }
}
