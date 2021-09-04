package tetro.shape;

import org.junit.jupiter.api.Test;
import tetro.block.BlockType;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlockShapesTest {

    @Test
    public void blockShapes_size_returnsNumberOfAllBlockShapes() throws Exception {
        //given
        final int NUMBER_OF_ALL_BLOCK_SHAPES = numberOfAllBlockShapes();
        List<BlockShape> allShapes = BlockShapes.all();

        //when
        int actual = allShapes.size();

        //then
        assertEquals(NUMBER_OF_ALL_BLOCK_SHAPES, actual);
    }

    private int numberOfAllBlockShapes() {
        return Arrays.stream(BlockType.values())
                .mapToInt(e -> e.numberOfShapes)
                .sum();
    }
}