package tetro.block.shape;

import org.junit.jupiter.api.Test;
import tetro.block.BlockType;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import static org.junit.jupiter.api.Assertions.*;

public class BlockShapeTest {
    final int NOT_ROTATION = 0;

    @Test
    public void equals_sameShape_returnsTrue() throws Exception {
        //given
        BlockShape shape1 = BlockShapes.get(BlockType.O, NOT_ROTATION);
        BlockShape shape2 = BlockShapes.get(BlockType.O, NOT_ROTATION);

        //when
        boolean actual = shape1.equals(shape2);

        //then
        assertTrue(actual);
    }

    @Test
    public void equals_differentShape_returnsFalse() throws Exception {
        //given
        BlockShape shape1 = BlockShapes.get(BlockType.O, NOT_ROTATION);
        BlockShape shape2 = BlockShapes.get(BlockType.S, NOT_ROTATION);

        //when
        boolean actual = shape1.equals(shape2);

        //then
        assertFalse(actual);
    }

    @Test
    public void validate_duplicateOffset_throwsException() throws Exception {
        //given
        Offset duplicateOffset = Offset.of(2, 3);
        Offsets offsets = Offsets.of(Offset.of(0, 0), duplicateOffset, Offset.of(4, 5), duplicateOffset);

        //then
        assertThrows(IllegalArgumentException.class,
                () -> new BlockShape(BlockType.L, NOT_ROTATION, offsets));
    }
}
