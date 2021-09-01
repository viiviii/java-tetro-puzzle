package tetro.shape;

import org.junit.jupiter.api.Test;
import tetro.offset.Offset;
import tetro.block.BlockType;
import tetro.offset.Offsets;

import java.util.Set;

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
    public void validate_invalidOffset_throwsException() throws Exception {
        //given
        Offset negativeOffset = Offset.of(-1, -2);
        Offset outOfRangeOffset1 = Offset.of(4, 0);
        Offset outOfRangeOffset2 = Offset.of(0, 4);

        //when
        Offsets setWithInvalidOffset = Offsets.of(negativeOffset, outOfRangeOffset1, outOfRangeOffset2, Offset.ZERO);

        //then
        assertThrows(IllegalArgumentException.class, () -> BlockShape.from(setWithInvalidOffset));
    }
    
    @Test
    public void canContain_zeroOffset_returnsTrue() throws Exception {
        //when
        boolean actual = BlockShape.canContain(Offset.ZERO);

        //then
        assertTrue(actual);
    }

    @Test
    public void canContain_negativeOffset_returnsFalse() throws Exception {
        //given
        Offset negativeOffset = Offset.of(-1, -2);

        //when
        boolean actual = BlockShape.canContain(negativeOffset);

        //then
        assertFalse(actual);
    }

    @Test
    public void canContain_outOfRangeOffset_returnsFalse() throws Exception {
        //given
        Offset outOfRangeOffset = Offset.of(4, 0);

        //when
        boolean actual = BlockShape.canContain(outOfRangeOffset);

        //then
        assertFalse(actual);
    }
}
