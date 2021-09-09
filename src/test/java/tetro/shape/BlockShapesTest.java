package tetro.shape;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tetro.block.BlockType;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlockShapesTest {
    BlockShapes blockShapes;

    @BeforeEach
    public void beforeEach() {
        BlockShapeData data = new BlockShapeData();
        blockShapes = new BlockShapes(data);
    }

    @DisplayName("블럭 모양의 총 갯수는 19개이다")
    @Test
    public void blockShapes_allTypesTotalSize_returnsNumber19() throws Exception {
        //given
        final int TOTAL_NUMBER_OF_BLOCK_SHAPES = 19;
        BlockShapeData data = new BlockShapeData();
        BlockShapes blockShapes = new BlockShapes(data);

        //when
        int actual = Arrays.stream(BlockType.values())
                .mapToInt(type -> blockShapes.get(type).size())
                .sum();

        //then
        assertEquals(TOTAL_NUMBER_OF_BLOCK_SHAPES, actual);
    }

    @DisplayName("BlockShape 갯수는 최소 1개에서 최대 4개이다")
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

    //////////////////////////////////////////////////////////////

    @Test
    public void blockTypeO() throws Exception {
        //given
        BlockType blockType = BlockType.O;
        BlockShape expect = makeBlockShape(0, 0, 1, 0, 0, 1, 1, 1);

        //when
        BlockShape actual = this.blockShapes.get(blockType).get(0);

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void blockTypeI() throws Exception {
        //given
        BlockType blockType = BlockType.I;
        BlockShape expect1 = makeBlockShape(0, 0, 0, 1, 0, 2, 0, 3);
        BlockShape expect2 = makeBlockShape(0, 3, 1, 3, 2, 3, 3, 3);

        //when
        BlockShape actual1 = this.blockShapes.get(blockType).get(0);
        BlockShape actual2 = this.blockShapes.get(blockType).get(1);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
    }

    @Test
    public void blockTypeS() throws Exception {
        //given
        BlockType blockType = BlockType.S;
        BlockShape expect1 = makeBlockShape(0, 0, 0, 1, 1, 1, 1, 2);
        BlockShape expect2 = makeBlockShape(1, 1, 2, 1, 0, 2, 1, 2);

        //when
        BlockShape actual1 = this.blockShapes.get(blockType).get(0);
        BlockShape actual2 = this.blockShapes.get(blockType).get(1);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
    }

    @Test
    public void blockTypeZ() throws Exception {
        //given
        BlockType blockType = BlockType.Z;
        BlockShape expect1 = makeBlockShape(1, 0, 0, 1, 1, 1, 0, 2);
        BlockShape expect2 = makeBlockShape(0, 1, 1, 1, 1, 2, 2, 2);

        //when
        BlockShape actual1 = this.blockShapes.get(blockType).get(0);
        BlockShape actual2 = this.blockShapes.get(blockType).get(1);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
    }

    @Test
    public void blockTypeT() throws Exception {
        //given
        BlockType blockType = BlockType.T;
        BlockShape expect1 = makeBlockShape(1, 0, 0, 1, 1, 1, 1, 2);
        BlockShape expect2 = makeBlockShape(1, 1, 0, 2, 1, 2, 2, 2);
        BlockShape expect3 = makeBlockShape(0, 0, 0, 1, 1, 1, 0, 2);
        BlockShape expect4 = makeBlockShape(0, 1, 1, 1, 2, 1, 1, 2);

        //when
        BlockShape actual1 = this.blockShapes.get(blockType).get(0);
        BlockShape actual2 = this.blockShapes.get(blockType).get(1);
        BlockShape actual3 = this.blockShapes.get(blockType).get(2);
        BlockShape actual4 = this.blockShapes.get(blockType).get(3);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
        assertEquals(expect3, actual3);
        assertEquals(expect4, actual4);
    }

    @Test
    public void blockTypeJ() throws Exception {
        //given
        BlockType blockType = BlockType.J;
        BlockShape expect1 = makeBlockShape(0, 0, 1, 0, 0, 1, 0, 2);
        BlockShape expect2 = makeBlockShape(0, 1, 1, 1, 2, 1, 2, 2);
        BlockShape expect3 = makeBlockShape(1, 0, 1, 1, 0, 2, 1, 2);
        BlockShape expect4 = makeBlockShape(0, 1, 0, 2, 1, 2, 2, 2);

        //when
        BlockShape actual1 = this.blockShapes.get(blockType).get(0);
        BlockShape actual2 = this.blockShapes.get(blockType).get(1);
        BlockShape actual3 = this.blockShapes.get(blockType).get(2);
        BlockShape actual4 = this.blockShapes.get(blockType).get(3);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
        assertEquals(expect3, actual3);
        assertEquals(expect4, actual4);
    }

    @Test
    public void blockTypeL() throws Exception {
        //given
        BlockType blockType = BlockType.L;
        BlockShape expect1 = makeBlockShape(0, 0, 1, 0, 1, 1, 1, 2);
        BlockShape expect2 = makeBlockShape(2, 1, 0, 2, 1, 2, 2, 2);
        BlockShape expect3 = makeBlockShape(0, 0, 0, 1, 0, 2, 1, 2);
        BlockShape expect4 = makeBlockShape(0, 1, 1, 1, 2, 1, 0, 2);

        //when
        BlockShape actual1 = this.blockShapes.get(blockType).get(0);
        BlockShape actual2 = this.blockShapes.get(blockType).get(1);
        BlockShape actual3 = this.blockShapes.get(blockType).get(2);
        BlockShape actual4 = this.blockShapes.get(blockType).get(3);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
        assertEquals(expect3, actual3);
        assertEquals(expect4, actual4);
    }

    @Test
    public void blockTypeO_numberOfShapesEqualsExpect_returnsTrue() throws Exception {
        //given
        BlockType blockType = BlockType.O;
        List<BlockShape> blockShapes = this.blockShapes.get(blockType);
        int expect = 1;

        //when
        int actual = blockShapes.size();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void blockTypeI_numberOfShapesEqualsExpect_returnsTrue() throws Exception {
        //given
        BlockType blockType = BlockType.I;
        List<BlockShape> blockShapes = this.blockShapes.get(blockType);
        int expect = 2;

        //when
        int actual = blockShapes.size();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void blockTypeS_numberOfShapesEqualsExpect_returnsTrue() throws Exception {
        //given
        BlockType blockType = BlockType.S;
        List<BlockShape> blockShapes = this.blockShapes.get(blockType);
        int expect = 2;

        //when
        int actual = blockShapes.size();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void blockTypeZ_numberOfShapesEqualsExpect_returnsTrue() throws Exception {
        //given
        BlockType blockType = BlockType.Z;
        List<BlockShape> blockShapes = this.blockShapes.get(blockType);
        int expect = 2;

        //when
        int actual = blockShapes.size();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void blockTypeT_numberOfShapesEqualsExpect_returnsTrue() throws Exception {
        //given
        BlockType blockType = BlockType.T;
        List<BlockShape> blockShapes = this.blockShapes.get(blockType);
        int expect = 4;

        //when
        int actual = blockShapes.size();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void blockTypeJ_numberOfShapesEqualsExpect_returnsTrue() throws Exception {
        //given
        BlockType blockType = BlockType.J;
        List<BlockShape> blockShapes = this.blockShapes.get(blockType);
        int expect = 4;

        //when
        int actual = blockShapes.size();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void blockTypeL_numberOfShapesEqualsExpect_returnsTrue() throws Exception {
        //given
        BlockType blockType = BlockType.L;
        List<BlockShape> blockShapes = this.blockShapes.get(blockType);
        int expect = 4;

        //when
        int actual = blockShapes.size();

        //then
        assertEquals(expect, actual);
    }

    private BlockShape makeBlockShape(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        List list = List.of(x1, y1, x2, y2, x3, y3, x4, y4);
        return new BlockShape(list);
    }
}