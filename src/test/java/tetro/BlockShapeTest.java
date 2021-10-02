package tetro;

import org.junit.jupiter.api.Test;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import static org.junit.jupiter.api.Assertions.*;

public class BlockShapeTest {
    final Block oBlock = Blocks.basic(BlockType.O);
    final Block sBlock = Blocks.basic(BlockType.S);

    @Test
    public void equals_sameShape_returnsTrue() throws Exception {
        //given
        Offsets oBlockOffsets = oBlock.cells().offsets();
        Block.Shape shape1 = new Block.Shape(oBlockOffsets);
        Block.Shape shape2 = new Block.Shape(oBlockOffsets);

        //when
        boolean actual = shape1.equals(shape2);

        //then
        assertTrue(actual);
    }

    @Test
    public void equals_differentShape_returnsFalse() throws Exception {
        //given
        Block.Shape oShape = new Block.Shape(oBlock.cells().offsets());
        Block.Shape sShape = new Block.Shape(sBlock.cells().offsets());

        //when
        boolean actual = oShape.equals(sShape);

        //then
        assertFalse(actual);
    }

    @Test
    public void validate_duplicateOffset_throwsException() throws Exception {
        //given
        Offset duplicateOffset = Offset.of(2, 3);
        Offsets offsets = Offsets.of(
                Offset.of(0, 0), duplicateOffset,
                Offset.of(4, 5), duplicateOffset);

        //then
        assertThrows(IllegalArgumentException.class, () -> new Block.Shape(offsets));
    }
}
