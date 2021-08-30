package tetro.block;

import org.junit.jupiter.api.Test;
import tetro.Offset;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BlockShapeTest {

    @Test
    public void equals() throws Exception {
        //given
        Set sameOffsets = Set.of(Offset.of(0, 0), Offset.of(1, 0), Offset.of(0, 1), Offset.of(1, 1));
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
    public void validateSize() throws Exception {
        //given
        Set not4ArgumentSet = Set.of(Offset.of(0, 0));

        //then
        assertThrows(IllegalArgumentException.class, () -> new BlockShape(not4ArgumentSet));
    }

    @Test
    public void validateRange() throws Exception {
        //given
        Set invalidRageOffset = Set.of(Offset.of(-1, 0), Offset.of(1, 0), Offset.of(0, 1), Offset.of(1, 1));

        //then
        assertThrows(IllegalArgumentException.class, () -> new BlockShape(invalidRageOffset));
    }
}
