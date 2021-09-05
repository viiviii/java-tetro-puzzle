package tetro.shape;

import tetro.block.BlockType;
import tetro.offset.Offsets;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// TODO: BlockShape.from(shapeOffsets).rotate(rotation).create();
public final class BlockShapes {
    public static final int SIZE;
    private static final Set<BlockShape> shapes;

    static {
        shapes = allBlockShapes();
        SIZE = shapes.size();
    }

    private static Set<BlockShape> allBlockShapes() {
        return Arrays.stream(BlockType.values())
                .flatMap(blockType -> allShapesOf(blockType))
                .collect(Collectors.toUnmodifiableSet());
    }

    private static Stream<BlockShape> allShapesOf(BlockType blockType) {
        final Offsets offsets = blockType.offsets;
        return IntStream.range(0, blockType.numberOfShapes)
                .mapToObj(rotation -> BlockShape.from(offsets).rotate(rotation));
    }
}
