package tetro.shape;

import org.junit.jupiter.api.Test;
import tetro.Offset;
import tetro.block.BlockType;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BlockShapeTest {

    @Test
    public void equals() throws Exception {
        //given
        Set sameOffsets = Set.of(Offset.ZERO, Offset.of(1, 0), Offset.of(0, 1), Offset.of(1, 1));
        Set otherOffsets = Set.of(Offset.of(0, 2), Offset.of(1, 0), Offset.of(0, 1), Offset.of(1, 1));

        //when
        BlockShape oShape = new BlockShape(BlockType.O.offsets);
        BlockShape sameShape = new BlockShape(sameOffsets);
        BlockShape otherShape = new BlockShape(otherOffsets);

        //then
        assertEquals(oShape, sameShape);
        assertNotEquals(oShape, otherShape);
        assertNotEquals(oShape, null);
    }

    @Test
    public void validate_notShapeSize_throwsException() throws Exception {
        //when
        Set setNotEqualToShapeSize = Set.of(Offset.ZERO);

        //then
        assertThrows(IllegalArgumentException.class, () -> new BlockShape(setNotEqualToShapeSize));
    }

    @Test
    public void validate_invalidOffset_throwsException() throws Exception {
        //given
        Offset negativeOffset = Offset.of(-1, -2);
        Offset outOfRangeOffset1 = Offset.of(4, 0);
        Offset outOfRangeOffset2 = Offset.of(0, 4);

        //when
        Set setWithInvalidOffset = Set.of(negativeOffset, outOfRangeOffset1, outOfRangeOffset2, Offset.ZERO);

        //then
        assertThrows(IllegalArgumentException.class, () -> new BlockShape(setWithInvalidOffset));
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
