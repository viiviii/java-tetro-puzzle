package tetro.block.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BlockShapesDataTest {
    BlockShapesData data;

    @BeforeEach
    public void beforeEach() throws Exception {
        data = new BlockShapesData();
    }

    @Test
    public void oBlock_shapes() throws Exception {
        //given
        int[][] expect = this.oBlockShapes();

        //when
        int[][] actual = data.oBlockShapes();

        //then
        assertArrayEquals(expect, actual);
    }

    @Test
    public void iBlock_shapes() throws Exception {
        //given
        int[][] expect = this.iBlockShapes();

        //when
        int[][] actual = data.iBlockShapes();

        //then
        assertArrayEquals(expect, actual);
    }

    @Test
    public void sBlock_shapes() throws Exception {
        //given
        int[][] expect = this.sBlockShapes();

        //when
        int[][] actual = data.sBlockShapes();

        //then
        assertArrayEquals(expect, actual);
    }

    @Test
    public void zBlock_shapes() throws Exception {
        //given
        int[][] expect = this.zBlockShapes();

        //when
        int[][] actual = data.zBlockShapes();

        //then
        assertArrayEquals(expect, actual);
    }

    @Test
    public void tBlock_shapes() throws Exception {
        //given
        int[][] expect = this.tBlockShapes();

        //when
        int[][] actual = data.tBlockShapes();

        //then
        assertArrayEquals(expect, actual);
    }

    @Test
    public void jBlock_shapes() throws Exception {
        //given
        int[][] expect = this.jBlockShapes();

        //when
        int[][] actual = data.jBlockShapes();

        //then
        assertArrayEquals(expect, actual);
    }

    @Test
    public void lBlock_shapes() throws Exception {
        //given
        int[][] expect = this.lBlockShapes();

        //when
        int[][] actual = data.lBlockShapes();

        //then
        assertArrayEquals(expect, actual);
    }

    //////////////////////////////////////////////////////////////


    private int[][] oBlockShapes() {
        return new int[][]{
                {0, 0, 1, 0, 0, 1, 1, 1}
        };
    }

    private int[][] iBlockShapes() {
        return new int[][]{
                {0, 0, 0, 1, 0, 2, 0, 3},
                {0, 3, 1, 3, 2, 3, 3, 3}
        };
    }

    private int[][] sBlockShapes() {
        return new int[][]{
                {0, 0, 0, 1, 1, 1, 1, 2},
                {1, 1, 2, 1, 0, 2, 1, 2}
        };
    }

    private int[][] zBlockShapes() {
        return new int[][]{
                {1, 0, 0, 1, 1, 1, 0, 2},
                {0, 1, 1, 1, 1, 2, 2, 2}
        };
    }

    private int[][] tBlockShapes() {
        return new int[][]{
                {1, 0, 0, 1, 1, 1, 1, 2},
                {1, 1, 0, 2, 1, 2, 2, 2},
                {0, 0, 0, 1, 1, 1, 0, 2},
                {0, 1, 1, 1, 2, 1, 1, 2}
        };
    }

    private int[][] jBlockShapes() {
        return new int[][]{
                {0, 0, 1, 0, 0, 1, 0, 2},
                {0, 1, 1, 1, 2, 1, 2, 2},
                {1, 0, 1, 1, 0, 2, 1, 2},
                {0, 1, 0, 2, 1, 2, 2, 2}
        };
    }

    private int[][] lBlockShapes() {
        return new int[][]{
                {0, 0, 1, 0, 1, 1, 1, 2},
                {2, 1, 0, 2, 1, 2, 2, 2},
                {0, 0, 0, 1, 0, 2, 1, 2},
                {0, 1, 1, 1, 2, 1, 0, 2}
        };
    }
}
