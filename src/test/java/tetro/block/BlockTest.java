package tetro.block;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tetro.data.BlockShapesData;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {
    BlockShapes blockShapes;

    @BeforeEach
    public void beforeEach() {
        BlockShapesData data = new BlockShapesData();
        blockShapes = BlockShapes.from(data);
    }

    @Test
    public void offsets() throws Exception {
        //given
        BlockShape sBlockShape = blockShapes.get(BlockType.S, 0);
        Block block = new Block(sBlockShape, Offset.of(3, 4));

        Offsets expect = Offsets.of(
                Offset.of(3, 4), Offset.of(3, 5),
                Offset.of(4, 5), Offset.of(4, 6)
        );

        //when
        Offsets offsets = block.offsets();

        //then
        assertEquals(expect, offsets);
    }

    //////////////////////////////////////////////////////////////

    @Test
    public void oBlock_shapes() throws Exception {
        //given
        BlockType type = BlockType.O;
        BlockShape expect = toBlockShape(type, 0, 0, 0, 1, 0, 0, 1, 1, 1);

        //when
        BlockShape actual = blockShapes.get(type, 0);

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void iBlock_shapes() throws Exception {
        //given
        BlockType type = BlockType.I;
        BlockShape expect1 = toBlockShape(type, 0, 0, 0, 0, 1, 0, 2, 0, 3);
        BlockShape expect2 = toBlockShape(type, 1, 0, 3, 1, 3, 2, 3, 3, 3);

        //when
        BlockShape actual1 = blockShapes.get(type, 0);
        BlockShape actual2 = blockShapes.get(type, 1);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
    }

    @Test
    public void sBlock_shapes() throws Exception {
        //given
        BlockType type = BlockType.S;
        BlockShape expect1 = toBlockShape(type, 0, 0, 0, 0, 1, 1, 1, 1, 2);
        BlockShape expect2 = toBlockShape(type, 1, 1, 1, 2, 1, 0, 2, 1, 2);

        //when
        BlockShape actual1 = blockShapes.get(type, 0);
        BlockShape actual2 = blockShapes.get(type, 1);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
    }

    @Test
    public void zBlock_shapes() throws Exception {
        //given
        BlockType type = BlockType.Z;
        BlockShape expect1 = toBlockShape(type, 0, 1, 0, 0, 1, 1, 1, 0, 2);
        BlockShape expect2 = toBlockShape(type, 1, 0, 1, 1, 1, 1, 2, 2, 2);

        //when
        BlockShape actual1 = blockShapes.get(type, 0);
        BlockShape actual2 = blockShapes.get(type, 1);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
    }

    @Test
    public void tBlock_shapes() throws Exception {
        //given
        BlockType type = BlockType.T;
        BlockShape expect1 = toBlockShape(type, 0, 1, 0, 0, 1, 1, 1, 1, 2);
        BlockShape expect2 = toBlockShape(type, 1, 1, 1, 0, 2, 1, 2, 2, 2);
        BlockShape expect3 = toBlockShape(type, 2, 0, 0, 0, 1, 1, 1, 0, 2);
        BlockShape expect4 = toBlockShape(type, 3, 0, 1, 1, 1, 2, 1, 1, 2);

        //when
        BlockShape actual1 = blockShapes.get(type, 0);
        BlockShape actual2 = blockShapes.get(type, 1);
        BlockShape actual3 = blockShapes.get(type, 2);
        BlockShape actual4 = blockShapes.get(type, 3);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
        assertEquals(expect3, actual3);
        assertEquals(expect4, actual4);
    }

    @Test
    public void jBlock_shapes() throws Exception {
        //given
        BlockType type = BlockType.J;
        BlockShape expect1 = toBlockShape(type, 0, 0, 0, 1, 0, 0, 1, 0, 2);
        BlockShape expect2 = toBlockShape(type, 1, 0, 1, 1, 1, 2, 1, 2, 2);
        BlockShape expect3 = toBlockShape(type, 2, 1, 0, 1, 1, 0, 2, 1, 2);
        BlockShape expect4 = toBlockShape(type, 3, 0, 1, 0, 2, 1, 2, 2, 2);

        //when
        BlockShape actual1 = blockShapes.get(type, 0);
        BlockShape actual2 = blockShapes.get(type, 1);
        BlockShape actual3 = blockShapes.get(type, 2);
        BlockShape actual4 = blockShapes.get(type, 3);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
        assertEquals(expect3, actual3);
        assertEquals(expect4, actual4);
    }

    @Test
    public void lBlock_shapes() throws Exception {
        //given
        BlockType type = BlockType.L;
        BlockShape expect1 = toBlockShape(type, 0, 0, 0, 1, 0, 1, 1, 1, 2);
        BlockShape expect2 = toBlockShape(type, 1, 2, 1, 0, 2, 1, 2, 2, 2);
        BlockShape expect3 = toBlockShape(type, 2, 0, 0, 0, 1, 0, 2, 1, 2);
        BlockShape expect4 = toBlockShape(type, 3, 0, 1, 1, 1, 2, 1, 0, 2);

        //when
        BlockShape actual1 = blockShapes.get(type, 0);
        BlockShape actual2 = blockShapes.get(type, 1);
        BlockShape actual3 = blockShapes.get(type, 2);
        BlockShape actual4 = blockShapes.get(type, 3);

        //then
        assertEquals(expect1, actual1);
        assertEquals(expect2, actual2);
        assertEquals(expect3, actual3);
        assertEquals(expect4, actual4);
    }

    @Test
    public void oBlock_numberOfShapes() throws Exception {
        //given
        Set<BlockShape> shapes = blockShapes.get(BlockType.O);
        int expect = 1;

        //when
        int actual = shapes.size();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void iBlock_numberOfShapes() throws Exception {
        //given
        Set<BlockShape> shapes = blockShapes.get(BlockType.I);
        int expect = 2;

        //when
        int actual = shapes.size();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void sBlock_numberOfShapes() throws Exception {
        //given
        Set<BlockShape> shapes = blockShapes.get(BlockType.S);
        int expect = 2;

        //when
        int actual = shapes.size();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void zBlock_numberOfShapes() throws Exception {
        //given
        Set<BlockShape> shapes = blockShapes.get(BlockType.Z);
        int expect = 2;

        //when
        int actual = shapes.size();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void tBlock_numberOfShapes() throws Exception {
        //given
        Set<BlockShape> shapes = blockShapes.get(BlockType.T);
        int expect = 4;

        //when
        int actual = shapes.size();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void jBlock_numberOfShapes() throws Exception {
        //given
        Set<BlockShape> shapes = blockShapes.get(BlockType.J);
        int expect = 4;

        //when
        int actual = shapes.size();

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void lBlock_numberOfShapes() throws Exception {
        //given
        Set<BlockShape> shapes = blockShapes.get(BlockType.L);
        int expect = 4;

        //when
        int actual = shapes.size();

        //then
        assertEquals(expect, actual);
    }

    private BlockShape toBlockShape(BlockType type, int rotation, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        return new BlockShape(type, rotation, x1, y1, x2, y2, x3, y3, x4, y4);
    }
}
