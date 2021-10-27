package tetro.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tetro.Offset;

import static org.junit.jupiter.api.Assertions.*;

public class CellsTest {
    final int LESS = -1;

    @Test
    public void size_twoDifferentOffset_returnsNumberTwo() throws Exception {
        //given
        Cell c1 = Cell.of(0, 0);
        Cell c2 = Cell.of(1, 2);
        Cells cells = Cells.of(c1, c2);

        //when
        int actual = cells.size();

        //then
        assertEquals(2, actual);
    }

    @Test
    public void size_duplicateCells_returnsNumberOne() throws Exception {
        //given
        Cell c1 = Cell.of(4, 1);
        Cell c2 = Cell.of(4, 1);
        Cells cells = Cells.of(c1, c2);

        //when
        int actual = cells.size();

        //then
        assertEquals(1, actual);
    }

    @Test
    public void first_twoPositiveOffset_returnsOffsetWithLessForCompareTo() throws Exception {
        //given
        Cell c1 = Cell.of(5, 3);
        Cell c2 = Cell.of(1, 2);
        Cells cells = Cells.of(c1, c2);

        //when
        Offset actual = cells.first();
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
        Cells cells = Cells.of(c1, c2);

        //when
        Offset actual = cells.first();
        int compare = c1.compareTo(c2);

        //then
        assertEquals(c1, actual);
        assertEquals(LESS, compare);
    }

    @Test
    public void translateBy_positiveTranslateValue_returnsPlusValue() throws Exception {
        //given
        final int translateX = 1;
        final int translateY = 4;

        Cell cell = Cell.of(-3, -3);
        Cells origin = Cells.of(cell);

        //when
        Cells actual = origin.translateBy(translateX, translateY);

        //then
        assertEquals(cell.x + translateX, actual.first().x);
        assertEquals(cell.y + translateY, actual.first().y);
    }

    @Test
    public void translateBy_negativeTranslateValue_returnsPlusValue() throws Exception {
        //given
        final int translateX = -1;
        final int translateY = -4;

        Cell cell = Cell.of(-3, -3);
        Cells origin = Cells.of(cell);

        //when
        Cells actual = origin.translateBy(translateX, translateY);

        //then
        assertEquals(cell.x + translateX, actual.first().x);
        assertEquals(cell.y + translateY, actual.first().y);
    }

    @Test
    public void translateTo_offset_returnsCellsWithSameFirstOffset() throws Exception {
        //given
        Cell c1 = Cell.of(1, 3);
        Cell c2 = Cell.of(2, 4);
        Cells origin = Cells.of(c1, c2);
        Offset offset = new Offset(6, 3);

        //when
        Cells actual = origin.translateTo(offset);

        //then
        assertEquals(offset, actual.first());
    }

    @Test
    public void containsAll_containAllCells_returnsTrue() throws Exception {
        //given
        Cell c1 = Cell.of(1, 2);
        Cell c2 = Cell.of(3, 4);
        Cell c3 = Cell.of(5, 6);
        Cells cells = Cells.of(c1, c2, c3);
        Cells other = Cells.of(c2);

        //when
        boolean actual = cells.containsAll(other);

        //then
        assertTrue(actual);
    }

    @Test
    public void containsAll_notContainAllCells_returnsFalse() throws Exception {
        //given
        Cell c1 = Cell.of(1, 2);
        Cell c2 = Cell.of(3, 4);
        Cell c3 = Cell.of(5, 6);
        Cells cells = Cells.of(c2);
        Cells other = Cells.of(c1, c2, c3);

        //when
        boolean actual = cells.containsAll(other);

        //then
        assertFalse(actual);
    }

    @Test
    public void containsAll_notContainAnyCells_returnsFalse() throws Exception {
        //given
        Cell c1 = Cell.of(1, 2);
        Cell c2 = Cell.of(3, 4);
        Cell c3 = Cell.of(5, 6);
        Cells cells = Cells.of(c1, c2, c3);
        Cells other = Cells.of(Cell.of(99, 99));

        //when
        boolean actual = cells.containsAll(other);

        //then
        assertFalse(actual);
    }

    @Test
    public void difference_emptyCells_returnsEqualsValue() throws Exception {
        //given
        Cell c1 = Cell.of(1, 2);
        Cell c2 = Cell.of(3, 4);
        Cell c3 = Cell.of(5, 6);

        Cells origin = Cells.of(c1, c2, c3);
        Cells other = Cells.EMPTY;

        //when
        Cells actual = origin.difference(other);

        //then
        assertEquals(origin, actual);
    }

    @Test
    public void difference_emptyCells_returnNotSameInstance() throws Exception {
        //given
        Cell c1 = Cell.of(1, 2);
        Cell c2 = Cell.of(3, 4);
        Cell c3 = Cell.of(5, 6);

        Cells origin = Cells.of(c1, c2, c3);
        Cells other = Cells.EMPTY;

        //when
        Cells actual = origin.difference(other);

        //then
        assertNotSame(origin, actual);
    }

    @Test
    public void difference_otherCells_returnsDifferentCells() throws Exception {
        //given
        Cell c1 = Cell.of(1, 2);
        Cell c2 = Cell.of(3, 4);
        Cell c3 = Cell.of(5, 6);

        Cells origin = Cells.of(c1, c2, c3);
        Cells other = Cells.of(c2);

        //when
        Cells actual = origin.difference(other);

        //then
        assertEquals(Cells.of(c1, c3), actual);
    }
}