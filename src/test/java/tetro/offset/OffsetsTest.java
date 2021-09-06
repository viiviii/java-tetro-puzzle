package tetro.offset;

import org.junit.jupiter.api.Test;
import tetro.block.BlockType;

import java.util.Collections;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public void rotate_1TimesXAndY_returnsMinusYAndX() throws Exception {
        //given
        Offsets offsets = BlockType.J.offsets;
        Offsets expect = map(offsets, (e) -> Offset.of(-e.y, e.x));

        //when
        Offsets actual = offsets.rotate(1);

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void rotate_2TimesXAndY_returnsMinusXAndMinusY() throws Exception {
        //given
        Offsets offsets = BlockType.S.offsets;
        Offsets expect = map(offsets, (e) -> Offset.of(-e.x, -e.y));

        //when
        Offsets actual = offsets.rotate(2);

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void rotate_3TimesXAndY_returnsYAndMinusX() throws Exception {
        //given
        Offsets offsets = BlockType.T.offsets;
        Offsets expect = map(offsets, (e) -> Offset.of(e.y, -e.x));

        //when
        Offsets actual = offsets.rotate(3);

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void rotate_4Times_returnsSameOffsets() throws Exception {
        //given
        Offsets offsets = BlockType.I.offsets;

        //when
        Offsets actual = offsets.rotate(4);

        //then
        assertEquals(offsets, actual);
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
    public void translatePositive_negativeOffsets_returnsPositiveOffsets() throws Exception {
        //given
        Offsets positive = BlockType.O.offsets;
        Offsets negative = positive.translate(-3, -5);

        //when
        Offsets actual = negative.translateToZeroOffset();

        //then
        assertEquals(positive, actual);
    }

    private Offsets map(Offsets offsets, Function<Offset, Offset> mapper) {
        final Set<Offset> set = offsets.stream().map(mapper).collect(Collectors.toSet());
        return Offsets.from(set);
    }
}
