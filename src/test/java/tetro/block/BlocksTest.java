package tetro.block;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tetro.data.BlockShapesData;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BlocksTest {
    Blocks blocks;

    @BeforeEach
    public void beforeEach() {
        BlockShapesData data = new BlockShapesData();
        blocks = new Blocks(data);
    }

    @DisplayName("블럭이 가진 BlockShape 총 갯수는 19개이다")
    @Test
    public void blockShapes_allTypesTotalNumber_returnsNumber19() throws Exception {
        //given
        final int TOTAL_NUMBER_OF_BLOCK_SHAPES = 19;

        //when
        int actual = Arrays.stream(BlockType.values())
                .mapToInt(type -> blocks.get(type).numberOfShapes())
                .sum();

        //then
        assertEquals(TOTAL_NUMBER_OF_BLOCK_SHAPES, actual);
    }

    @DisplayName("블럭이 가진 BlockShape 갯수는 최소 1개에서 최대 4개이다")
    @Test
    public void numberOfShapes_numberRangesFrom1To4_returnsTrue() throws Exception {
        //when
        boolean actual = Arrays.stream(BlockType.values()).allMatch(type -> {
            final int size = blocks.get(type).numberOfShapes();
            return size >= 1 && size <= 4;
        });

        //then
        assertTrue(actual);
    }

    @DisplayName("Blocks 갯수는 BlockType 갯수와 같다")
    @Test
    public void all_sizeEqualsNumberOfBlockType_returnsTrue() throws Exception {
        //given
        Set<Block> allBlocks = blocks.all();

        //when
        int actual = allBlocks.size();

        //then
        assertEquals(BlockType.values().length, actual);
    }
}