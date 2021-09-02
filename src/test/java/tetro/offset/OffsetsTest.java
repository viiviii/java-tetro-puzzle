package tetro.offset;

import org.junit.jupiter.api.Test;
import tetro.block.BlockType;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Offset o2 = Offset.of(1, 1);
        Offsets offsets = Offsets.of(o1, o2);

        //when
        int actual = offsets.size();

        //then
        assertEquals(2, actual);
    }

    @Test
    public void size_duplicateOffsets_returnsNumberOne() throws Exception {
        //given
        Offset o1 = Offset.ZERO;
        Offset o2 = Offset.of(0, 0);
        Offsets offsets = Offsets.of(o1, o2);

        //when
        int actual = offsets.size();

        //then
        assertEquals(1, actual);
    }

    @Test
    public void first_twoPositiveOffset_returnsOffsetWithLessForCompareTo() throws Exception {
        //given
        Offset o1 = Offset.of(3, 3);
        Offset o2 = Offset.of(2, 2);
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
}
