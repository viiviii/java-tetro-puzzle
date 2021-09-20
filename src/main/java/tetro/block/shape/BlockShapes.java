package tetro.block.shape;

import tetro.block.BlockType;
import tetro.block.data.BlockShapesData;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class BlockShapes {

    private BlockShapes() {
    }

    private static final Map<BlockType, Set<BlockShape>> m = new EnumMap(BlockType.class);

    static {
        final BlockShapesData data = new BlockShapesData();
        data.forEach((type, value) -> m.put(type, blockShapes(type, value)));
    }

    private static Set<BlockShape> blockShapes(BlockType type, int[][] data) {
        final int NUMBER_OF_SHAPES = data.length;
        return IntStream.range(0, NUMBER_OF_SHAPES)
                .mapToObj(rotation -> new BlockShape(type, rotation, offsetsBy(data[rotation])))
                .collect(Collectors.toUnmodifiableSet());
    }

    private static Offsets offsetsBy(int[] data) {
        final int OFFSET_LENGTH = 2;
        final List<Offset> list = new ArrayList();
        for (int i = 0; i < data.length; i += OFFSET_LENGTH) {
            final int x = data[i];
            final int y = data[i + 1];
            list.add(Offset.of(x, y));
        }
        return Offsets.of(list);
    }

    public static Set<BlockShape> all() {
        return m.values().stream()
                .flatMap(e -> e.stream())
                .collect(Collectors.toUnmodifiableSet());
    }

    // TODO
    public static BlockShape get(BlockType blockType, int rotation) {
        final Set<BlockShape> blockShapes = m.get(blockType);
        final int NUMBER_OF_ROTATION = blockShapes.size();
        final int COLLECT_ROTATION = rotation % NUMBER_OF_ROTATION;
        return blockShapes.stream()
                .filter(shape -> shape.rotation() == COLLECT_ROTATION)
                .findAny().get();
    }

    public static Set<BlockShape> get(BlockType blockType) {
        return m.get(blockType);
    }
}
