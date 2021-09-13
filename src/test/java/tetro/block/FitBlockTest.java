package tetro.block;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import static org.junit.jupiter.api.Assertions.*;

class FitBlockTest {
    Block sBlock;

    @BeforeEach
    public void beforeEach() {
        BlockShapeData data = new BlockShapeData();
        Blocks blocks = new Blocks(data);
        sBlock = blocks.get(BlockType.S);
    }

    @Test
    public void offsets() throws Exception {
        //given
        FitBlock fitBlock = new FitBlock(sBlock, 0, Offset.of(3, 4));
        Offsets expect = Offsets.of(
                Offset.of(3, 4), Offset.of(3, 5),
                Offset.of(4, 5), Offset.of(4, 6)
        );

        //when
        Offsets offsets = fitBlock.offsets();

        //then
        assertEquals(expect, offsets);
    }
}