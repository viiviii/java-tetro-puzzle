package tetro.shape;

import org.junit.jupiter.api.Test;
import tetro.block.BlockType;
import tetro.offset.Offsets;

import static org.junit.jupiter.api.Assertions.*;

public class BlockShapeTest {

    @Test
    public void equals_sameShapes_returnsTrue() throws Exception {
        //given
        BlockShape shape1 = BlockShape.from(BlockType.O.offsets);
        BlockShape shape2 = BlockShape.from(BlockType.O.offsets);

        //when
        boolean actual = shape1.equals(shape2);

        //then
        assertTrue(actual);
    }

    @Test
    public void equals_differentShapes_returnsFalse() throws Exception {
        //given
        BlockShape shape1 = BlockShape.from(BlockType.S.offsets);
        BlockShape shape2 = BlockShape.from(BlockType.Z.offsets);

        //when
        boolean actual = shape1.equals(shape2);

        //then
        assertFalse(actual);
    }

    @Test
    public void toGridString_startedZeroOffset_doesNotThrowsException() throws Exception {
        //given
        Offsets offsets = BlockType.O.offsets;

        //when
        BlockShape shape = BlockShape.from(offsets);

        //then
        assertDoesNotThrow(() -> shape.toGridString());
    }

    @Test
    public void toGridString_notStartedZeroOffset_doesNotThrowsException() throws Exception {
        //given
        Offsets offsets = BlockType.S.offsets;

        //when
        BlockShape shape = BlockShape.from(offsets);

        //then
        assertDoesNotThrow(() -> shape.toGridString());
    }
}
