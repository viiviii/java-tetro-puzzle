package tetro.block;

import org.junit.jupiter.api.Test;
import tetro.Offset;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BlockShapeTest {

    @Test
    public void equals() throws Exception {
        //given
        BlockShape oShape = BlockType.O.shape;

        //when
        BlockShape sameShape = new BlockShape(Offset.of(0, 0), Offset.of(1, 0), Offset.of(0, 1), Offset.of(1, 1));
        BlockShape otherShape = new BlockShape(Offset.of(0, 2), Offset.of(1, 0), Offset.of(0, 1), Offset.of(1, 1));

        //then
        assertEquals(oShape, sameShape);
        assertNotEquals(oShape, otherShape);
        assertNotEquals(oShape, null);
    }

    @Test
    public void validateArgumentSize() throws Exception {
        //given
        Set not4ArgumentSet = Set.of(Offset.of(0, 0));

        //then
        assertThrows(IllegalArgumentException.class, () -> new BlockShape(not4ArgumentSet));
    }
}
