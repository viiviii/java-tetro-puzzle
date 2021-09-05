package tetro.block;

import org.junit.jupiter.api.Test;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.Arrays;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlockTypeTest {
    private final int BLOCK_SIZE = 4; // TODO: 위치가 있을텐데

    /**
     * 테트리스 회전은 최대 4번까지 가능하므로 블럭 모양은 최소 1개에서 최대 4개이다.
     */
    @Test
    public void numberOfShapes_rangeIsBetween1And4_returnsTrue() throws Exception {
        //when
        boolean actual = blockTypesMatchAll(e -> e.numberOfShapes >= 1 && e.numberOfShapes <= 4);

        //then
        assertTrue(actual);
    }

    /**
     * 테트리스 블록은 4개의 칸으로 구성되어 있다.
     */
    @Test
    public void offsetsSize_equalsNumberFour_returnsTrue() throws Exception {
        //when
        boolean actual = blockTypesMatchAll(e -> e.offsets.size() == BLOCK_SIZE);

        //then
        assertTrue(actual);
    }

    /**
     * 테트리스 블록의 x, y 위치 값 범위는 0~3이다
     * - I 블럭이 한 행으로 4칸이니까
     */
    @Test
    public void offsets_validateOffsetRange_returnsTrue() throws Exception {
        //given
        final Predicate<Offset> validRange = (e) ->
                (e.x >= 0 && e.x < BLOCK_SIZE) && (e.y >= 0 && e.y < BLOCK_SIZE);

        //when
        boolean actual = blockTypesMatchAll(blockType -> blockType.offsets.stream()
                .allMatch(offset -> validRange.test(offset)));

        //then
        assertTrue(actual);
    }

    private boolean blockTypesMatchAll(Predicate<BlockType> predicate) {
        return Arrays.stream(BlockType.values()).allMatch(predicate);
    }



    /**
     * 이하 테스트는 enum의 상수 값 테스트
     * - 불운하게 변경되었지만 git add .에 섞여서 묻힐까봐 안전장치로 추가함
     */
    @Test
    public void blockTypeO_offsetsEqualsExpect_returnsTrue() throws Exception {
        //given
        Offsets expect = makeOffsets(0, 0, 1, 0, 0, 1, 1, 1);

        //when
        boolean actual = BlockType.O.offsets.equals(expect);

        //then
        assertTrue(actual);
    }

    @Test
    public void blockTypeI_offsetsEqualsExpect_returnsTrue() throws Exception {
        //given
        Offsets expect = makeOffsets(0, 0, 0, 1, 0, 2, 0, 3);

        //when
        boolean actual = BlockType.I.offsets.equals(expect);

        //then
        assertTrue(actual);
    }

    @Test
    public void blockTypeS_offsetsEqualsExpect_returnsTrue() throws Exception {
        //given
        Offsets expect = makeOffsets(1, 0, 2, 0, 0, 1, 1, 1);

        //when
        boolean actual = BlockType.S.offsets.equals(expect);

        //then
        assertTrue(actual);
    }

    @Test
    public void blockTypeZ_offsetsEqualsExpect_returnsTrue() throws Exception {
        //given
        Offsets expect = makeOffsets(0, 0, 1, 0, 1, 1, 2, 1);

        //when
        boolean actual = BlockType.Z.offsets.equals(expect);

        //then
        assertTrue(actual);
    }

    @Test
    public void blockTypeT_offsetsEqualsExpect_returnsTrue() throws Exception {
        //given
        Offsets expect = makeOffsets(0, 0, 1, 0, 2, 0, 1, 1);

        //when
        boolean actual = BlockType.T.offsets.equals(expect);

        //then
        assertTrue(actual);
    }

    @Test
    public void blockTypeJ_offsetsEqualsExpect_returnsTrue() throws Exception {
        //given
        Offsets expect = makeOffsets(1, 0, 1, 1, 0, 2, 1, 2);

        //when
        boolean actual = BlockType.J.offsets.equals(expect);

        //then
        assertTrue(actual);
    }

    @Test
    public void blockTypeL_offsetsEqualsExpect_returnsTrue() throws Exception {
        //given
        Offsets expect = makeOffsets(0, 0, 0, 1, 0, 2, 1, 2);

        //when
        boolean actual = BlockType.L.offsets.equals(expect);

        //then
        assertTrue(actual);
    }

    @Test
    public void blockTypeO_numberOfShapesEqualsExpect_returnsTrue() throws Exception {
        //given
        int expect = 1;

        //when
        int actual = BlockType.O.numberOfShapes;

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void blockTypeI_numberOfShapesEqualsExpect_returnsTrue() throws Exception {
        //given
        int expect = 2;

        //when
        int actual = BlockType.I.numberOfShapes;

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void blockTypeS_numberOfShapesEqualsExpect_returnsTrue() throws Exception {
        //given
        int expect = 2;

        //when
        int actual = BlockType.S.numberOfShapes;

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void blockTypeZ_numberOfShapesEqualsExpect_returnsTrue() throws Exception {
        //given
        int expect = 2;

        //when
        int actual = BlockType.Z.numberOfShapes;

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void blockTypeT_numberOfShapesEqualsExpect_returnsTrue() throws Exception {
        //given
        int expect = 4;

        //when
        int actual = BlockType.T.numberOfShapes;

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void blockTypeJ_numberOfShapesEqualsExpect_returnsTrue() throws Exception {
        //given
        int expect = 4;

        //when
        int actual = BlockType.J.numberOfShapes;

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void blockTypeL_numberOfShapesEqualsExpect_returnsTrue() throws Exception {
        //given
        int expect = 4;

        //when
        int actual = BlockType.L.numberOfShapes;

        //then
        assertEquals(expect, actual);
    }

    private Offsets makeOffsets(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        final Offset o1 = Offset.of(x1, y1);
        final Offset o2 = Offset.of(x2, y2);
        final Offset o3 = Offset.of(x3, y3);
        final Offset o4 = Offset.of(x4, y4);
        return Offsets.of(o1, o2, o3, o4);
    }
}
