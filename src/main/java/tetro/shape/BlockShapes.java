package tetro.shape;

import tetro.block.BlockType;
import tetro.offset.Offsets;

import java.util.ArrayList;
import java.util.List;

// TODO: BlockShape.from(shapeOffsets).rotate(rotation).create();
public final class BlockShapes {
    private static final List<BlockShape> shapes;

    static {
        List<BlockShape> allShapes = new ArrayList<>();
        BlockType[] allBlockTypes = BlockType.values();
        for (BlockType type : allBlockTypes) {
            Offsets shapeOffsets = type.offsets;
            for (int rotation = 0; rotation < type.numberOfShapes; rotation++) {
                BlockShape shape = BlockShape.of(shapeOffsets, rotation);
                allShapes.add(shape);
            }
        }
        shapes = allShapes;
    }

    public static List<BlockShape> all() {
        return shapes;
    }
}
