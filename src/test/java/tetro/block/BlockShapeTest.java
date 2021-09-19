package tetro.block;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tetro.data.BlockShapesData;
import tetro.offset.Offset;

import static org.junit.jupiter.api.Assertions.*;

public class BlockShapeTest {
    final int NOT_ROTATION = 0;

    BlockShape oBlockShape;
    BlockShape sBlockShape;

    @BeforeEach
    public void beforeEach() {
        BlockShapesData data = new BlockShapesData();
        BlockShapes blockShapes = BlockShapes.from(data);
        oBlockShape = blockShapes.get(BlockType.O, NOT_ROTATION);
        sBlockShape = blockShapes.get(BlockType.S, NOT_ROTATION);
    }

    @Test
    public void equals_sameOffsets_returnsTrue() throws Exception {
        //given
        BlockShape shape1 = oBlockShape;
        BlockShape shape2 = new BlockShape(BlockType.O, NOT_ROTATION, 0, 0, 1, 0, 0, 1, 1, 1);

        //when
        boolean actual = shape1.equals(shape2);

        //then
        assertTrue(actual);
    }

    @Test
    public void equals_differentOffsets_returnsFalse() throws Exception {
        //given
        BlockShape shape1 = oBlockShape;
        BlockShape shape2 = sBlockShape;

        //when
        boolean actual = shape1.equals(shape2);

        //then
        assertFalse(actual);
    }

    // TODO
    @Test
    public void validate_duplicateOffset_throwsException() throws Exception {
        //given
        Offset offset = Offset.of(2, 3);

        //then
        assertThrows(IllegalArgumentException.class,
                () -> new BlockShape(BlockType.L, NOT_ROTATION, offset.x, offset.y, 1, 0, offset.x, offset.y, 4, 6));
    }
}
