package tetro.block;

import tetro.data.BlockShapesData;

import java.util.*;
import java.util.stream.Collectors;

public final class BlockShapes {

    private BlockShapes() {
    }

    private static final Set<BlockShape> cache = new HashSet();

    // TODO
    public static BlockShapes from(BlockShapesData blockShapesData) {
        if (cache.isEmpty()) {
            blockShapesData.map.forEach((type, value) -> cache.addAll(blockShapes(type, value)));
        }
        return new BlockShapes();
    }

    // TODO
    public Set<BlockShape> get(BlockType blockType) {
        return cache.stream()
                .filter(shape -> shape.type() == blockType)
                .collect(Collectors.toUnmodifiableSet());
    }

    public BlockShape get(BlockType blockType, int rotation) {
        return cache.stream()
                .filter(shape -> shape.type() == blockType && shape.rotation() == rotation)
                .findAny().get();
    }

    public Set<BlockShape> all() {
        return Set.copyOf(cache);
    }

    // TODO
    private static List<BlockShape> blockShapes(BlockType type, List<List<Integer>> list) {
        final List<BlockShape> result = new ArrayList<>();
        for (int rotation = 0; rotation < list.size(); rotation++) {
            result.add(toBlockShape(type, rotation, list.get(rotation)));
        }
        return result;
    }

    // TODO
    private static BlockShape toBlockShape(BlockType type, int rotation, List<Integer> list) {
        return new BlockShape(
                type, rotation,
                list.get(0), list.get(1), list.get(2), list.get(3),
                list.get(4), list.get(5), list.get(6), list.get(7));
    }
}
