package tetro.offset;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class OffsetTest {
    final int LESS = -1;
    final int GREATER = 1;

    @Test
    public void of() throws Exception {
        //given
        Offset offset1 = Offset.of(0, 8);

        //when
        int actualX = offset1.x;
        int actualY = offset1.y;

        //then
        assertEquals(0, actualX);
        assertEquals(8, actualY);
    }

    @Test
    public void equals_twoSameOffset_returnsTrue() throws Exception {
        //given
        Offset offset1 = Offset.of(1, 1);
        Offset offset2 = Offset.of(1, 1);

        //when
        boolean actual = offset1.equals(offset2);

        //then
        assertTrue(actual);
    }

    @Test
    public void equals_twoDifferentOffset_returnsFalse() throws Exception {
        //given
        Offset offset1 = Offset.of(1, 1);
        Offset offset2 = Offset.of(0, 2);

        //when
        boolean actual = offset1.equals(offset2);

        //then
        assertFalse(actual);
    }


    /*
     * `ImmutableSet`의 equals() 테스트 시 꼭 요소가 3개 이상이어야 함
     * 갯수에 따라 `AbstractImmutableSet`의 contains()가 다르게 구현되어 있음
     *   - 2개 이하일 경우: equals() 사용
     *   -    이상일 경우: 내부적으로 hashCode 사용 -> Offset hashCode override 필요
     * */
    @Test
    public void equalsOfImmutableSet_equalsTrueButDifferentReference_returnsTrue() throws Exception {
        //given
        Offset offset1 = Offset.of(1, 1);
        Offset offset2 = Offset.of(0, 2);
        Offset offset3 = Offset.of(3, 5);
        Offset equalToOffset1 = Offset.of(1, 1);

        Set<Offset> set1 = Set.of(offset1, offset2, offset3);
        Set<Offset> set2 = Set.of(equalToOffset1, offset2, offset3);

        //when
        boolean actual = set1.equals(set2);

        //then
        assertTrue(actual);
    }

    @Test
    public void equalsOfTreeSet_equalsTrueButDifferentReference_returnsTrue() throws Exception {
        //given
        Offset offset1 = Offset.of(1, 1);
        Offset offset2 = Offset.of(0, 2);
        Offset equalToOffset1 = Offset.of(1, 1);

        Set<Offset> set1 = new TreeSet<>();
        set1.add(offset1);
        set1.add(offset2);

        Set<Offset> set2 = new TreeSet<>();
        set2.add(equalToOffset1);
        set2.add(offset2);

        //when
        boolean actual = set1.equals(set2);

        //then
        assertTrue(actual);
    }


    @Test
    public void equalsOfHashSet_equalsTrueButDifferentReference_returnsTrue() throws Exception {
        //given
        Offset offset1 = Offset.of(1, 1);
        Offset offset2 = Offset.of(0, 2);
        Offset equalToOffset1 = Offset.of(1, 1);

        Set<Offset> set1 = new HashSet<>();
        set1.add(offset1);
        set1.add(offset2);

        Set<Offset> set2 = new HashSet<>();
        set2.add(equalToOffset1);
        set2.add(offset2);

        //when
        boolean actual = set1.equals(set2);

        //then
        assertTrue(actual);
    }

    @Test
    public void sizeOfHashSet_addDuplicateOffsets_returns1() throws Exception {
        //given
        Offset offset1 = Offset.of(1, 1);
        Offset equalToOffset1 = Offset.of(1, 1);

        Set<Offset> hashSet = new HashSet<>();
        hashSet.add(offset1);
        hashSet.add(equalToOffset1);

        //when
        int actual = hashSet.size();

        //then
        assertEquals(1, actual);
    }

    @Test
    public void sizeOfTreeSet_addDuplicateOffsets_returns1() throws Exception {
        //given
        Offset offset1 = Offset.of(1, 1);
        Offset equalToOffset1 = Offset.of(1, 1);

        Set<Offset> treeSet = new TreeSet<>();
        treeSet.add(offset1);
        treeSet.add(equalToOffset1);

        //when
        int actual = treeSet.size();

        //then
        assertEquals(1, actual);
    }

    @Test
    public void equals_결과가_true이면_compareTo_결과는_0이어야한다() throws Exception {
        //given
        Offset offset1 = Offset.of(0, 8);
        Offset offset2 = Offset.of(0, 8);

        //when
        boolean equals = offset1.equals(offset2);
        int compareTo = offset1.compareTo(offset2);

        //then
        assertEquals(true, equals);
        assertEquals(0, compareTo);
    }

    @Test
    public void null이_인자일때_equals_결과가_false여도_coampreTo는_Exception을_던져야한다() throws Exception {
        //given
        Offset offset = Offset.of(0, 8);

        //then
        assertEquals(false, offset.equals(null));
        assertThrows(NullPointerException.class, () -> offset.compareTo(null));
    }

    @Test
    public void compareTo_otherWithGreaterX_returnsLess() throws Exception {
        //given
        Offset offset1 = Offset.of(1, 1);
        Offset offset2 = Offset.of(2, 1);

        //when
        int actual = offset1.compareTo(offset2);

        //then
        assertEquals(LESS, actual);
    }

    @Test
    public void compareTo_otherWithGreaterY_returnsLess() throws Exception {
        //given
        Offset offset1 = Offset.of(1, 1);
        Offset offset2 = Offset.of(1, 2);

        //when
        int actual = offset1.compareTo(offset2);

        //then
        assertEquals(LESS, actual);
    }

    @Test
    public void compareTo_otherWithGreaterXY_returnsLess() throws Exception {
        //given
        Offset offset1 = Offset.of(1, 1);
        Offset offset2 = Offset.of(2, 2);

        //when
        int actual = offset1.compareTo(offset2);

        //then
        assertEquals(LESS, actual);
    }

    @Test
    public void compareTo_otherWithLessXY_returnsGreater() throws Exception {
        //given
        Offset offset1 = Offset.of(2, 2);
        Offset offset2 = Offset.of(1, 1);

        //when
        int actual = offset1.compareTo(offset2);

        //then
        assertEquals(GREATER, actual);
    }

    @Test
    public void compareTo_otherWithLessXGreaterY_returnsLess() throws Exception {
        //given
        Offset offset1 = Offset.of(1, 1);
        Offset offset2 = Offset.of(0, 2);

        //when
        int actual = offset1.compareTo(offset2);

        //then
        assertEquals(LESS, actual);
    }

    @Test
    public void compareTo_otherWithGreaterXLessY_returnsGreater() throws Exception {
        //given
        Offset offset1 = Offset.of(1, 1);
        Offset offset2 = Offset.of(2, 0);

        //when
        int actual = offset1.compareTo(offset2);

        //then
        assertEquals(GREATER, actual);
    }

    @Test
    public void translate_positiveAxis_returnsSumOfXY() throws Exception {
        //given
        int translateX = 3;
        int translateY = 3;
        Offset offset = Offset.of(1, 1);

        //when
        Offset actual = offset.translate(translateX, translateY);

        //then
        assertEquals(4, actual.x);
        assertEquals(4, actual.y);
    }

    @Test
    public void translate_negativeAxis_returnsMinusOfXY() throws Exception {
        //given
        int translateX = -3;
        int translateY = -3;
        Offset offset = Offset.of(1, 1);

        //when
        Offset actual = offset.translate(translateX, translateY);

        //then
        assertEquals(-2, actual.x);
        assertEquals(-2, actual.y);
    }

    @Test
    public void difference_positiveOtherOffset_returnsOriginMinusOtherOffset() throws Exception {
        //given
        Offset offset = Offset.of(10, 6);
        Offset other = Offset.of(3, 1);

        //when
        Offset actual = offset.difference(other);

        //then
        assertEquals(Offset.of(7, 5), actual);
    }

    @Test
    public void difference_negativeOtherOffset_returnsOriginPlusOtherOffset() throws Exception {
        //given
        Offset offset = Offset.of(10, 6);
        Offset other = Offset.of(-3, -1);

        //when
        Offset actual = offset.difference(other);

        //then
        assertEquals(Offset.of(13, 7), actual);
    }
}
