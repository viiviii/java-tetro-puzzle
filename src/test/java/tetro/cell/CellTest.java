package tetro.cell;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    final int LESS = -1;
    final int GREATER = 1;

    @Test
    public void of() throws Exception {
        //given
        Cell cell = Cell.of(0, 8);

        //when
        int x = cell.x;
        int y = cell.y;

        //then
        assertEquals(0, x);
        assertEquals(8, y);
    }

    @Test
    public void equals_same_returnsTrue() throws Exception {
        //given
        Cell cell = Cell.of(1, 1);
        Cell other = Cell.of(1, 1);

        //when
        boolean actual = cell.equals(other);

        //then
        assertTrue(actual);
    }

    @Test
    public void equals_different_returnsFalse() throws Exception {
        //given
        Cell cell = Cell.of(1, 1);
        Cell other = Cell.of(0, 2);

        //when
        boolean actual = cell.equals(other);

        //then
        assertFalse(actual);
    }


    /*
     * `ImmutableSet`의 equals() 테스트 시 꼭 요소가 3개 이상이어야 함
     * 갯수에 따라 `AbstractImmutableSet`의 contains()가 다르게 구현되어 있음
     *   - 2개 이하일 경우: equals() 사용
     *   -    이상일 경우: 내부적으로 hashCode 사용 -> Cell hashCode override 필요
     * */
    @Test
    public void equalsOfImmutableSet_equalsTrueButDifferentReference_returnsTrue() throws Exception {
        //given
        Cell cell = Cell.of(1, 1);
        Cell other1 = Cell.of(0, 2);
        Cell other2 = Cell.of(3, 5);
        Cell equalToCell = Cell.of(1, 1);

        Set<Cell> set1 = Set.of(cell, other1, other2);
        Set<Cell> set2 = Set.of(equalToCell, other1, other2);

        //when
        boolean actual = set1.equals(set2);

        //then
        assertTrue(actual);
    }

    @Test
    public void equalsOfTreeSet_equalsTrueButDifferentReference_returnsTrue() throws Exception {
        //given
        Cell cell = Cell.of(1, 1);
        Cell equalToCell = Cell.of(1, 1);
        Cell other = Cell.of(0, 2);

        Set<Cell> set1 = new TreeSet<>();
        set1.add(cell);
        set1.add(other);

        Set<Cell> set2 = new TreeSet<>();
        set2.add(equalToCell);
        set2.add(other);

        //when
        boolean actual = set1.equals(set2);

        //then
        assertTrue(actual);
    }


    @Test
    public void equalsOfHashSet_equalsTrueButDifferentReference_returnsTrue() throws Exception {
        //given
        Cell cell = Cell.of(1, 1);
        Cell equalToCell = Cell.of(1, 1);
        Cell other = Cell.of(0, 2);

        Set<Cell> set1 = new HashSet<>();
        set1.add(cell);
        set1.add(other);

        Set<Cell> set2 = new HashSet<>();
        set2.add(equalToCell);
        set2.add(other);

        //when
        boolean actual = set1.equals(set2);

        //then
        assertTrue(actual);
    }

    @Test
    public void sizeOfHashSet_addDuplicateOffsets_returns1() throws Exception {
        //given
        Cell cell = Cell.of(1, 1);
        Cell equalToCell = Cell.of(1, 1);

        Set<Cell> hashSet = new HashSet<>();
        hashSet.add(cell);
        hashSet.add(equalToCell);

        //when
        int actual = hashSet.size();

        //then
        assertEquals(1, actual);
    }

    @Test
    public void sizeOfTreeSet_addDuplicateOffsets_returns1() throws Exception {
        //given
        Cell cell = Cell.of(1, 1);
        Cell equalToCell = Cell.of(1, 1);

        Set<Cell> treeSet = new TreeSet<>();
        treeSet.add(cell);
        treeSet.add(equalToCell);

        //when
        int actual = treeSet.size();

        //then
        assertEquals(1, actual);
    }

    @Test
    public void equals_결과가_true이면_compareTo_결과는_0이어야한다() throws Exception {
        //given
        Cell cell = Cell.of(0, 8);
        Cell other = Cell.of(0, 8);

        //when
        boolean equals = cell.equals(other);
        int compareTo = cell.compareTo(other);

        //then
        assertEquals(true, equals);
        assertEquals(0, compareTo);
    }

    @Test
    public void null이_인자일때_equals_결과가_false여도_coampreTo는_Exception을_던져야한다() throws Exception {
        //given
        Cell cell = Cell.of(0, 8);

        //then
        assertEquals(false, cell.equals(null));
        assertThrows(NullPointerException.class, () -> cell.compareTo(null));
    }

    @Test
    public void compareTo_otherWithGreaterX_returnsLess() throws Exception {
        //given
        Cell cell = Cell.of(1, 1);
        Cell other = Cell.of(2, 1);

        //when
        int actual = cell.compareTo(other);

        //then
        assertEquals(LESS, actual);
    }

    @Test
    public void compareTo_otherWithGreaterY_returnsLess() throws Exception {
        //given
        Cell cell = Cell.of(1, 1);
        Cell other = Cell.of(1, 2);

        //when
        int actual = cell.compareTo(other);

        //then
        assertEquals(LESS, actual);
    }

    @Test
    public void compareTo_otherWithGreaterXY_returnsLess() throws Exception {
        //given
        Cell cell = Cell.of(1, 1);
        Cell other = Cell.of(2, 2);

        //when
        int actual = cell.compareTo(other);

        //then
        assertEquals(LESS, actual);
    }

    @Test
    public void compareTo_otherWithLessXY_returnsGreater() throws Exception {
        //given
        Cell cell = Cell.of(2, 2);
        Cell other = Cell.of(1, 1);

        //when
        int actual = cell.compareTo(other);

        //then
        assertEquals(GREATER, actual);
    }

    @Test
    public void compareTo_otherWithLessXGreaterY_returnsLess() throws Exception {
        //given
        Cell cell = Cell.of(1, 1);
        Cell other = Cell.of(0, 2);

        //when
        int actual = cell.compareTo(other);

        //then
        assertEquals(LESS, actual);
    }

    @Test
    public void compareTo_otherWithGreaterXLessY_returnsGreater() throws Exception {
        //given
        Cell cell = Cell.of(1, 1);
        Cell other = Cell.of(2, 0);

        //when
        int actual = cell.compareTo(other);

        //then
        assertEquals(GREATER, actual);
    }

    @Test
    public void translate_positiveAxis_returnsSumOfXY() throws Exception {
        //given
        int translateX = 3;
        int translateY = 3;
        Cell cell = Cell.of(1, 1);

        //when
        Cell actual = cell.translate(translateX, translateY);

        //then
        assertEquals(4, actual.x);
        assertEquals(4, actual.y);
    }

    @Test
    public void translate_negativeAxis_returnsMinusOfXY() throws Exception {
        //given
        int translateX = -3;
        int translateY = -3;
        Cell cell = Cell.of(1, 1);

        //when
        Cell actual = cell.translate(translateX, translateY);

        //then
        assertEquals(-2, actual.x);
        assertEquals(-2, actual.y);
    }


}
