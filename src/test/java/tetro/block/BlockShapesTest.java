package tetro.block;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BlockShapesTest {

    @DisplayName("블럭이 가진 BlockShape 총 갯수는 19개이다")
    @Test
    public void blockShapes_allTypesTotalNumber_returnsNumber19() throws Exception {
        //given
        final int TOTAL_NUMBER_OF_BLOCK_SHAPES = 19;

        //when
        int actual = BlockShapes.all().size();

        //then
        assertEquals(TOTAL_NUMBER_OF_BLOCK_SHAPES, actual);
    }

    @DisplayName("블럭이 가진 BlockShape 갯수는 최소 1개에서 최대 4개이다")
    @Test
    public void numberOfShapes_numberRangesFrom1To4_returnsTrue() throws Exception {
        //when
        boolean allMatch = Arrays.stream(BlockType.values()).allMatch(type -> {
            final int size = BlockShapes.get(type).size();
            return size >= 1 && size <= 4;
        });

        //then
        assertTrue(allMatch);
    }
}