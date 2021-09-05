package tetro.shape;

import org.junit.jupiter.api.Test;
import tetro.block.BlockType;

import static org.junit.jupiter.api.Assertions.*;

public class BlockShapeTest {

    @Test
    public void equals_sameShapes_returnsTrue() throws Exception {
        //given
        BlockShape shape1 = BlockShape.from(BlockType.O).rotate(3);
        BlockShape shape2 = BlockShape.from(BlockType.O).rotate(3);

        //when
        boolean actual = shape1.equals(shape2);

        //then
        assertTrue(actual);
    }

    @Test
    public void equals_differentShapes_returnsFalse() throws Exception {
        //given
        BlockShape shape1 = BlockShape.from(BlockType.S).rotate(1);
        BlockShape shape2 = BlockShape.from(BlockType.Z).rotate(2);

        //when
        boolean actual = shape1.equals(shape2);

        //then
        assertFalse(actual);
    }

    @Test
    public void toGridString_startedZeroOffset_doesNotThrowsException() throws Exception {
        //given
        BlockType type = BlockType.O;

        //when
        BlockShape shape = BlockShape.from(type).rotate(4);

        //then
        assertDoesNotThrow(() -> shape.toGridString());
    }

    @Test
    public void toGridString_notStartedZeroOffset_doesNotThrowsException() throws Exception {
        //given
        BlockType type = BlockType.S;

        //when
        BlockShape shape = BlockShape.from(type).rotate(2);

        //then
        assertDoesNotThrow(() -> shape.toGridString());
    }
}
