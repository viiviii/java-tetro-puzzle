package tetro.block;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {
    Blocks blocks;

    @BeforeEach
    public void beforeEach() {
        BlockShapeData data = new BlockShapeData();
        blocks = new Blocks(data);
    }

    @Test
    public void equals_sameType_returnsTrue() throws Exception {
        //given
        Block block1 = blocks.get(BlockType.I);
        Block block2 = blocks.get(BlockType.I);

        //when
        boolean actual = block1.equals(block2);

        //then
        assertTrue(actual);
    }

    @Test
    public void equals_differentType_returnsFalse() throws Exception {
        //given
        Block block1 = blocks.get(BlockType.S);
        Block block2 = blocks.get(BlockType.Z);

        //when
        boolean actual = block1.equals(block2);

        //then
        assertFalse(actual);
    }

    //////////////////////////////////////////////////////////////

    @Test
    public void oBlock_shapes() throws Exception {
        //given
        Block block = blocks.get(BlockType.O);
        BlockShape expect = toBlockShape(0, 0, 1, 0, 0, 1, 1, 1);

        //when
        BlockShape actual = block.shape(0);

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void iBlock_shapes() throws Exception {
        //given
        Block block = blocks.get(BlockType.I);
        BlockShape expect1 = toBlockShape(0, 0, 0, 1, 0, 2, 0, 3);
        BlockShape expect2 = toBlockShape(0, 3, 1, 3, 2, 3, 3, 3);

        //when
        BlockShape actual1 = block.shape(0);
        BlockShape actual2 = block.shape(1);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
    }

    @Test
    public void sBlock_shapes() throws Exception {
        //given
        Block block = blocks.get(BlockType.S);
        BlockShape expect1 = toBlockShape(0, 0, 0, 1, 1, 1, 1, 2);
        BlockShape expect2 = toBlockShape(1, 1, 2, 1, 0, 2, 1, 2);

        //when
        BlockShape actual1 = block.shape(0);
        BlockShape actual2 = block.shape(1);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
    }

    @Test
    public void zBlock_shapes() throws Exception {
        //given
        Block block = blocks.get(BlockType.Z);
        BlockShape expect1 = toBlockShape(1, 0, 0, 1, 1, 1, 0, 2);
        BlockShape expect2 = toBlockShape(0, 1, 1, 1, 1, 2, 2, 2);

        //when
        BlockShape actual1 = block.shape(0);
        BlockShape actual2 = block.shape(1);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
    }

    @Test
    public void tBlock_shapes() throws Exception {
        //given
        Block block = blocks.get(BlockType.T);
        BlockShape expect1 = toBlockShape(1, 0, 0, 1, 1, 1, 1, 2);
        BlockShape expect2 = toBlockShape(1, 1, 0, 2, 1, 2, 2, 2);
        BlockShape expect3 = toBlockShape(0, 0, 0, 1, 1, 1, 0, 2);
        BlockShape expect4 = toBlockShape(0, 1, 1, 1, 2, 1, 1, 2);

        //when
        BlockShape actual1 = block.shape(0);
        BlockShape actual2 = block.shape(1);
        BlockShape actual3 = block.shape(2);
        BlockShape actual4 = block.shape(3);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
        assertEquals(expect3, actual3);
        assertEquals(expect4, actual4);
    }

    @Test
    public void jBlock_shapes() throws Exception {
        //given
        Block block = blocks.get(BlockType.J);
        BlockShape expect1 = toBlockShape(0, 0, 1, 0, 0, 1, 0, 2);
        BlockShape expect2 = toBlockShape(0, 1, 1, 1, 2, 1, 2, 2);
        BlockShape expect3 = toBlockShape(1, 0, 1, 1, 0, 2, 1, 2);
        BlockShape expect4 = toBlockShape(0, 1, 0, 2, 1, 2, 2, 2);

        //when
        BlockShape actual1 = block.shape(0);
        BlockShape actual2 = block.shape(1);
        BlockShape actual3 = block.shape(2);
        BlockShape actual4 = block.shape(3);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
        assertEquals(expect3, actual3);
        assertEquals(expect4, actual4);
    }

    @Test
    public void lBlock_shapes() throws Exception {
        //given
        Block block = blocks.get(BlockType.L);
        BlockShape expect1 = toBlockShape(0, 0, 1, 0, 1, 1, 1, 2);
        BlockShape expect2 = toBlockShape(2, 1, 0, 2, 1, 2, 2, 2);
        BlockShape expect3 = toBlockShape(0, 0, 0, 1, 0, 2, 1, 2);
        BlockShape expect4 = toBlockShape(0, 1, 1, 1, 2, 1, 0, 2);

        //when
        BlockShape actual1 = block.shape(0);
        BlockShape actual2 = block.shape(1);
        BlockShape actual3 = block.shape(2);
        BlockShape actual4 = block.shape(3);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
        assertEquals(expect3, actual3);
        assertEquals(expect4, actual4);
    }

    @Test
    public void oBlock_numberOfShapes() throws Exception {
        //given
        Block block = blocks.get(BlockType.O);
        int expect = 1;

        //when
        int actual = block.numberOfShapes();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void iBlock_numberOfShapes() throws Exception {
        //given
        Block block = blocks.get(BlockType.I);
        int expect = 2;

        //when
        int actual = block.numberOfShapes();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void sBlock_numberOfShapes() throws Exception {
        //given
        Block block = blocks.get(BlockType.S);
        int expect = 2;

        //when
        int actual = block.numberOfShapes();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void zBlock_numberOfShapes() throws Exception {
        //given
        Block block = blocks.get(BlockType.Z);
        int expect = 2;

        //when
        int actual = block.numberOfShapes();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void tBlock_numberOfShapes() throws Exception {
        //given
        Block block = blocks.get(BlockType.T);
        int expect = 4;

        //when
        int actual = block.numberOfShapes();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void jBlock_numberOfShapes() throws Exception {
        //given
        Block block = blocks.get(BlockType.J);
        int expect = 4;

        //when
        int actual = block.numberOfShapes();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void lBlock_numberOfShapes() throws Exception {
        //given
        Block block = blocks.get(BlockType.L);
        int expect = 4;

        //when
        int actual = block.numberOfShapes();

        //then
        assertEquals(expect, actual);
    }

    private BlockShape toBlockShape(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        return new BlockShape(x1, y1, x2, y2, x3, y3, x4, y4);
    }
}
