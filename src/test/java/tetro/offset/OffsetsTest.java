package tetro.offset;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OffsetsTest {
    final int LESS = -1;

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
    public void translate_oneSpace_returnsPlusNumberOne() throws Exception {
        //given
        final int translateX = 1;
        final int translateY = 4;

        Offset o1 = Offset.of(-3, -3);
        Offset o2 = Offset.of(-2, -2);
        Offsets origin = Offsets.of(o1, o2);

        //when
        Offsets actual = origin.translate(translateX, translateY);

        //then
        assertEquals(origin.first().x + translateX, actual.first().x);
        assertEquals(origin.first().y + translateY, actual.first().y);
    }

    @Test
    public void containsAll_containAllOffsets_returnsTrue() throws Exception {
        //given
        Offset o1 = Offset.of(1, 2);
        Offset o2 = Offset.of(3, 4);
        Offset o3 = Offset.of(5, 6);
        Offsets offsets = Offsets.of(o1, o2, o3);
        Offsets other = Offsets.of(o2);

        //when
        boolean actual = offsets.containsAll(other);

        //then
        assertTrue(actual);
    }

    @Test
    public void containsAll_notContainAllOffsets_returnsFalse() throws Exception {
        //given
        Offset o1 = Offset.of(1, 2);
        Offset o2 = Offset.of(3, 4);
        Offset o3 = Offset.of(5, 6);
        Offsets offsets = Offsets.of(o2);
        Offsets other = Offsets.of(o1, o2, o3);

        //when
        boolean actual = offsets.containsAll(other);

        //then
        assertFalse(actual);
    }

    @Test
    public void containsAll_notContainAnyOffsets_returnsFalse() throws Exception {
        //given
        Offset o1 = Offset.of(1, 2);
        Offset o2 = Offset.of(3, 4);
        Offset o3 = Offset.of(5, 6);
        Offsets offsets = Offsets.of(o1, o2, o3);
        Offsets other = Offsets.of(Offset.INVALID);

        //when
        boolean actual = offsets.containsAll(other);

        //then
        assertFalse(actual);
    }

    @Test
    public void difference_emptyOffsets_returnsEqualsValue() throws Exception {
        //given
        Offset o1 = Offset.of(1, 2);
        Offset o2 = Offset.of(3, 4);
        Offset o3 = Offset.of(5, 6);

        Offsets origin = Offsets.of(o1, o2, o3);
        Offsets other = Offsets.of();

        //when
        Offsets actual = origin.difference(other);

        //then
        assertEquals(origin, actual);
    }

    @Test
    public void difference_emptyOffsets_returnNotSameInstance() throws Exception {
        //given
        Offset o1 = Offset.of(1, 2);
        Offset o2 = Offset.of(3, 4);
        Offset o3 = Offset.of(5, 6);

        Offsets origin = Offsets.of(o1, o2, o3);
        Offsets other = Offsets.of();

        //when
        Offsets actual = origin.difference(other);

        //then
        assertNotSame(origin, actual);
    }

    @Test
    public void difference_otherOffsets_returnsDifferentOffsets() throws Exception {
        //given
        Offset o1 = Offset.of(1, 2);
        Offset o2 = Offset.of(3, 4);
        Offset o3 = Offset.of(5, 6);

        Offsets origin = Offsets.of(o1, o2, o3);
        Offsets other = Offsets.of(o2);

        //when
        Offsets actual = origin.difference(other);

        //then
        assertEquals(Offsets.of(o1, o3), actual);
    }
}