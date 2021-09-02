package tetro.offset;

import org.junit.jupiter.api.Test;
import tetro.block.BlockType;
import tetro.shape.BlockShape;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class OffsetsTest {
    final int LESS = -1;

    @Test
    public void size_emptySet_returnsNumberZero() throws Exception {
        //given
        Offsets offsets = Offsets.from(Collections.EMPTY_SET);

        //when
        int actual = offsets.size();

        //then
        assertEquals(0, actual);
    }

    @Test
    public void size_twoDifferentOffset_returnsNumberTwo() throws Exception {
        //given
        Offset o1 = Offset.ZERO;
        Offset o2 = Offset.of(1, 2);
        Offsets offsets = Offsets.of(o1, o2);

        //when
        int actual = offsets.size();

        //then
        assertEquals(2, actual);
    }

    @Test
    public void size_duplicateOffsets_returnsNumberOne() throws Exception {
        //given
        Offset o1 = Offset.of(4, 1);
        Offset o2 = Offset.of(4, 1);
        Offsets offsets = Offsets.of(o1, o2);

        //when
        int actual = offsets.size();

        //then
        assertEquals(1, actual);
    }

    @Test
    public void first_twoPositiveOffset_returnsOffsetWithLessForCompareTo() throws Exception {
        //given
        Offset o1 = Offset.of(5, 3);
        Offset o2 = Offset.of(1, 2);
        Offsets offsets = Offsets.of(o1, o2);

        //when
        Offset actual = offsets.first();
        int compare = o2.compareTo(o1);

        //then
        assertEquals(o2, actual);
        assertEquals(LESS, compare);
    }

    @Test
    public void first_twoNegativeOffset_returnsOffsetWithLessForCompareTo() throws Exception {
        //given
        Offset o1 = Offset.of(-3, -3);
        Offset o2 = Offset.of(-2, -2);
        Offsets offsets = Offsets.of(o1, o2);

        //when
        Offset actual = offsets.first();
        int compare = o1.compareTo(o2);

        //then
        assertEquals(o1, actual);
        assertEquals(LESS, compare);
    }

    @Test
    public void rotate_4Times_returnsSameOffsets() throws Exception {
        //given
        Offsets offsets = BlockType.I.offsets;

        //when
        Offsets rotate1 = offsets.rotate();
        Offsets rotate2 = rotate1.rotate();
        Offsets rotate3 = rotate2.rotate();
        Offsets rotate4 = rotate3.rotate();

        //then
        assertEquals(offsets, rotate4);
    }

    @Test
    public void translate_oneSpace_returnsPlusNumberOne() throws Exception {
        //given
        final int translateX = 1;
        final int translateY = 4;
        Offsets origin = BlockType.Z.offsets;

        //when
        Offsets actual = origin.translate(translateX, translateY);

        //then
        assertEquals(origin.first().x + translateX, actual.first().x);
        assertEquals(origin.first().y + translateY, actual.first().y);
    }

    @Test
    public void translateBy_positiveX10Y10_returnsFirstOffsetSame() throws Exception {
        //given
        Offset origin = Offset.of(10, 11);
        Offsets offsets = BlockType.J.offsets;

        //when
        Offsets actual = offsets.translateBy(origin);

        //then
        assertEquals(origin.x, actual.first().x);
        assertEquals(origin.y, actual.first().y);
    }

    @Test
    public void translatePositive_negativeOffsets_returnsPositiveOffsets() throws Exception {
        //given
        Offsets positive = BlockType.O.offsets;
        Offsets negative = positive.translate(-3, -5);

        //when
        Offsets actual = negative.translatePositive();

        //then
        assertEquals(positive, actual);
    }


    @Test
    public void linked_linkedOffsets_returnsTrue() throws Exception {
        //given
        Offsets offsets = BlockType.S.offsets;

        // TODO: test하고 지우기
        BlockShape shape = BlockShape.from(offsets);
        System.out.println(shape.toGridString());

        //when
        boolean actual = offsets.linked();

        //then
        assertTrue(actual);
    }

    @Test
    public void linked_unlinkedOffsets_returnsFalse() throws Exception {
        //given
        Offset o1 = Offset.of(1, 0);
        Offset o2 = Offset.of(2, 0);
        Offset o3 = Offset.of(0, 1);
        Offset o4 = Offset.of(1, 2);
        Offsets offsets = Offsets.of(o1, o2, o3, o4);

        // TODO: test하고 지우기
        BlockShape shape = BlockShape.from(offsets);
        System.out.println(shape.toGridString());

        //when
        boolean actual = offsets.linked();

        //then
        assertFalse(actual);
    }
}
