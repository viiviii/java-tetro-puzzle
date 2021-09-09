package tetro.shape;

import org.junit.jupiter.api.Test;
import tetro.block.BlockType;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BlockShapesTest {

    @Test
    public void blockShapes_allShapesSize_returnsNumberOfAllBlockShapes() throws Exception {
        //given
        final int NUMBER_OF_ALL_BLOCK_SHAPES = 19;
        BlockShapeData data = new BlockShapeData();
        BlockShapes blockShapes = new BlockShapes(data);

        //when
        int actual = Arrays.stream(BlockType.values())
                .mapToInt(type -> blockShapes.get(type).size())
                .sum();

        //then
        assertEquals(NUMBER_OF_ALL_BLOCK_SHAPES, actual);
    }
}