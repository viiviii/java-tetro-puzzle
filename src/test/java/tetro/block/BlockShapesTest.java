package tetro.block;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tetro.data.BlockShapesData;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BlockShapesTest {
    BlockShapes blockShapes;

    @BeforeEach
    public void beforeEach() {
        BlockShapesData data = new BlockShapesData();
        blockShapes = BlockShapes.from(data);
    }

    @DisplayName("블럭이 가진 BlockShape 총 갯수는 19개이다")
    @Test
    public void blockShapes_allTypesTotalNumber_returnsNumber19() throws Exception {
        //given
        final int TOTAL_NUMBER_OF_BLOCK_SHAPES = 19;

        //when
        int actual = Arrays.stream(BlockType.values())
                .mapToInt(type -> blockShapes.get(type).size())
                .sum();

        //then
        assertEquals(TOTAL_NUMBER_OF_BLOCK_SHAPES, actual);
    }

    @DisplayName("블럭이 가진 BlockShape 갯수는 최소 1개에서 최대 4개이다")
    @Test
    public void numberOfShapes_numberRangesFrom1To4_returnsTrue() throws Exception {
        //when
        boolean actual = Arrays.stream(BlockType.values()).allMatch(type -> {
            final int size = blockShapes.get(type).size();
            return size >= 1 && size <= 4;
        });

        //then
        assertTrue(actual);
    }
}