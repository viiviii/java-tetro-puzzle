package tetro.shape;

import org.junit.jupiter.api.Test;
import tetro.offset.Offset;
import tetro.block.BlockType;
import tetro.offset.Offsets;

import static org.junit.jupiter.api.Assertions.*;

public class BlockShapeTest {

    @Test
    public void equals() throws Exception {
        //given
        Offsets sameOffsets = Offsets.of(Offset.ZERO, Offset.of(1, 0), Offset.of(0, 1), Offset.of(1, 1));
        Offsets otherOffsets = Offsets.of(Offset.of(0, 2), Offset.of(1, 0), Offset.of(0, 1), Offset.of(1, 1));

        //when
        BlockShape oShape = BlockShape.from(BlockType.O.offsets);
        BlockShape sameShape = BlockShape.from(sameOffsets);
        BlockShape otherShape = BlockShape.from(otherOffsets);

        //then
        assertEquals(oShape, sameShape);
        assertNotEquals(oShape, otherShape);
        assertNotEquals(oShape, null);
    }

    @Test
    public void validate_notShapeSize_throwsException() throws Exception {
        //when
        Offsets setNotEqualToShapeSize = Offsets.of(Offset.ZERO);

        //then
        assertThrows(IllegalArgumentException.class, () -> BlockShape.from(setNotEqualToShapeSize));
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

    @Test
    public void toGridString_negativeOffset_doesNotThrowsException() throws Exception {
        //given
        Offsets offsets = BlockType.S.offsets;
        Offsets negativeOffsets = offsets.translate(-3, 0);

        //when
        BlockShape shape = BlockShape.from(negativeOffsets);

        //then
        assertDoesNotThrow(() -> shape.toGridString());
    }
}
