package tetro.block;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockTypeTest {

    @DisplayName("테트리스 블록은 총 7종류이다")
    @Test
    public void blockType_valuesLength_returnNumber7() throws Exception {
        //when
        final int BLOCK_TYPES_LENGTH = 7;

        //then
        int actual = BlockType.values().length;

        //then
        assertEquals(BLOCK_TYPES_LENGTH, actual);
    }
}
