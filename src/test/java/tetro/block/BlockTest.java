package tetro.block;

import org.junit.jupiter.api.Test;
import tetro.block.shape.BlockShape;
import tetro.block.shape.BlockShapes;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {

    @Test
    public void offsets() throws Exception {
        //given
        BlockShape sBlockShape = BlockShapes.get(BlockType.S, 0);
        Block block = new Block(sBlockShape, Offset.of(3, 4));

        Offsets expect = Offsets.of(
                Offset.of(3, 4), Offset.of(3, 5),
                Offset.of(4, 5), Offset.of(4, 6)
        );

        //when
        Offsets offsets = block.offsets();

        //then
        assertEquals(expect, offsets);
    }
}
