package tetro.block;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tetro.data.BlockShapesData;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FitBlockTest {
    Block sBlock;

    @BeforeEach
    public void beforeEach() {
        BlockShapesData data = new BlockShapesData();
        Blocks blocks = new Blocks(data);
        sBlock = blocks.get(BlockType.S);
    }

    @Test
    public void offsets() throws Exception {
        //given
        FitBlock fitBlock = new FitBlock(sBlock, 0, Offset.of(3, 4));
        Offsets expect = toOffsets(
                Offset.of(3, 4), Offset.of(3, 5),
                Offset.of(4, 5), Offset.of(4, 6)
        );

        //when
        Offsets offsets = fitBlock.offsets();

        //then
        assertEquals(expect, offsets);
    }

    private Offsets toOffsets(Offset... offsets) {
        return Offsets.of(Arrays.asList(offsets));
    }
}