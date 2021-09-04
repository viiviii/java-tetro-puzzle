package tetro.shape;

import tetro.block.BlockType;
import tetro.offset.Offsets;

import java.util.HashSet;
import java.util.Set;

// TODO: BlockShape.from(shapeOffsets).rotate(rotation).create();
public final class BlockShapes {
    private static final Set<BlockShape> shapes;

    static {
        Set<BlockShape> allShapes = new HashSet<>();
        BlockType[] allBlockTypes = BlockType.values();
        for (BlockType type : allBlockTypes) {
            Offsets shapeOffsets = type.offsets;
            for (int rotation = 0; rotation < type.numberOfShapes; rotation++) {
                BlockShape shape = BlockShape.from(shapeOffsets).rotate();
                allShapes.add(shape);
            }
        }
        shapes = allShapes;
    }

    public static Set<BlockShape> all() {
        return shapes;
    }
}
