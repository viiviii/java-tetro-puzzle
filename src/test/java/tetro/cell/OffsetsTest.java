package tetro.cell;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OffsetsTest {
    final int LESS = -1;

    @Test
    public void size_twoDifferentOffset_returnsNumberTwo() throws Exception {
        //given
        Cell c1 = Cell.of(0, 0);
        Cell c2 = Cell.of(1, 2);
        Offsets offsets = Offsets.of(c1, c2);

        //when
        int actual = offsets.size();

        //then
        assertEquals(2, actual);
    }

    @Test
    public void size_duplicateOffsets_returnsNumberOne() throws Exception {
        //given
        Cell c1 = Cell.of(4, 1);
        Cell c2 = Cell.of(4, 1);
        Offsets offsets = Offsets.of(c1, c2);

        //when
        int actual = offsets.size();

        //then
        assertEquals(1, actual);
    }

    @Test
    public void first_twoPositiveOffset_returnsOffsetWithLessForCompareTo() throws Exception {
        //given
        Cell c1 = Cell.of(5, 3);
        Cell c2 = Cell.of(1, 2);
        Offsets offsets = Offsets.of(c1, c2);

        //when
        Cell actual = offsets.first();
        int compare = c2.compareTo(c1);

        //then
        assertEquals(c2, actual);
        assertEquals(LESS, compare);
    }

    @Test
    public void first_twoNegativeOffset_returnsOffsetWithLessForCompareTo() throws Exception {
        //given
        Cell c1 = Cell.of(-3, -3);
        Cell c2 = Cell.of(-2, -2);
        Offsets offsets = Offsets.of(c1, c2);

        //when
        Cell actual = offsets.first();
        int compare = c1.compareTo(c2);

        //then
        assertEquals(c1, actual);
        assertEquals(LESS, compare);
    }

    @Test
    public void translate_oneSpace_returnsPlusNumberOne() throws Exception {
        //given
        final int translateX = 1;
        final int translateY = 4;

        Cell c1 = Cell.of(-3, -3);
        Cell c2 = Cell.of(-2, -2);
        Offsets origin = Offsets.of(c1, c2);

        //when
        Offsets actual = origin.translate(translateX, translateY);

        //then
        assertEquals(origin.first().x + translateX, actual.first().x);
        assertEquals(origin.first().y + translateY, actual.first().y);
    }
    
    @Test
    public void translateTo_otherOffset_returnsOffsetsWithSameFirstOffset() throws Exception {
        //given
        Cell c1 = Cell.of(1, 3);
        Cell c2 = Cell.of(2, 4);
        Offsets origin = Offsets.of(c1, c2);
        Cell offset = Cell.of(6, 3);
        
        //when
        Offsets actual = origin.translateTo(offset);

        //then
        assertEquals(offset, actual.first());
    }

    @Test
    public void containsAll_containAllOffsets_returnsTrue() throws Exception {
        //given
        Cell c1 = Cell.of(1, 2);
        Cell c2 = Cell.of(3, 4);
        Cell c3 = Cell.of(5, 6);
        Offsets offsets = Offsets.of(c1, c2, c3);
        Offsets other = Offsets.of(c2);

        //when
        boolean actual = offsets.containsAll(other);

        //then
        assertTrue(actual);
    }

    @Test
    public void containsAll_notContainAllOffsets_returnsFalse() throws Exception {
        //given
        Cell c1 = Cell.of(1, 2);
        Cell c2 = Cell.of(3, 4);
        Cell c3 = Cell.of(5, 6);
        Offsets offsets = Offsets.of(c2);
        Offsets other = Offsets.of(c1, c2, c3);

        //when
        boolean actual = offsets.containsAll(other);

        //then
        assertFalse(actual);
    }

    @Test
    public void containsAll_notContainAnyOffsets_returnsFalse() throws Exception {
        //given
        Cell c1 = Cell.of(1, 2);
        Cell c2 = Cell.of(3, 4);
        Cell c3 = Cell.of(5, 6);
        Offsets offsets = Offsets.of(c1, c2, c3);
        Offsets other = Offsets.of(Cell.of(99, 99));

        //when
        boolean actual = offsets.containsAll(other);

        //then
        assertFalse(actual);
    }

    @Test
    public void difference_emptyOffsets_returnsEqualsValue() throws Exception {
        //given
        Cell c1 = Cell.of(1, 2);
        Cell c2 = Cell.of(3, 4);
        Cell c3 = Cell.of(5, 6);

        Offsets origin = Offsets.of(c1, c2, c3);
        Offsets other = Offsets.EMPTY;

        //when
        Offsets actual = origin.difference(other);

        //then
        assertEquals(origin, actual);
    }

    @Test
    public void difference_emptyOffsets_returnNotSameInstance() throws Exception {
        //given
        Cell c1 = Cell.of(1, 2);
        Cell c2 = Cell.of(3, 4);
        Cell c3 = Cell.of(5, 6);

        Offsets origin = Offsets.of(c1, c2, c3);
        Offsets other = Offsets.EMPTY;

        //when
        Offsets actual = origin.difference(other);

        //then
        assertNotSame(origin, actual);
    }

    @Test
    public void difference_otherOffsets_returnsDifferentOffsets() throws Exception {
        //given
        Cell c1 = Cell.of(1, 2);
        Cell c2 = Cell.of(3, 4);
        Cell c3 = Cell.of(5, 6);

        Offsets origin = Offsets.of(c1, c2, c3);
        Offsets other = Offsets.of(c2);

        //when
        Offsets actual = origin.difference(other);

        //then
        assertEquals(Offsets.of(c1, c3), actual);
    }
}