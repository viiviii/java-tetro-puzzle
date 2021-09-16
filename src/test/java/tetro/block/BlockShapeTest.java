package tetro.block;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tetro.offset.Offset;

import static org.junit.jupiter.api.Assertions.*;

public class BlockShapeTest {
    BlockShape oBlockShape;
    BlockShape sBlockShape;

    @BeforeEach
    public void beforeEach() {
        final int NOT_ROTATION = 0;
        BlockShapeData data = new BlockShapeData();
        Blocks blocks = new Blocks(data);
        oBlockShape = blocks.get(BlockType.O).shape(NOT_ROTATION);
        sBlockShape = blocks.get(BlockType.S).shape(NOT_ROTATION);
    }

    @Test
    public void equals_sameOffsets_returnsTrue() throws Exception {
        //given
        BlockShape shape1 = oBlockShape;
        BlockShape shape2 = new BlockShape(0, 0, 1, 0, 0, 1, 1, 1);

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

    @Test
    public void validate_duplicateOffset_throwsException() throws Exception {
        //given
        Offset offset = Offset.of(2, 3);

        //then
        assertThrows(IllegalArgumentException.class,
                () -> new BlockShape(offset.x, offset.y, 1, 0, offset.x, offset.y, 4, 6));
    }
}
