package tetro.block;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RotatedBlockTest {

    @Test
    public void rotate_oBlock_returnsSameBlock() throws Exception {
        //given
        RotatedBlock oBlock = RotatedBlock.from(BlockType.O);

        //when
        RotatedBlock rotatedOBlock = oBlock.rotate();

        //then
        assertEquals(oBlock, rotatedOBlock);
    }
}
