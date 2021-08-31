package tetro;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class OffsetTest {

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
    public void equals_sameOffsets_returnsTrue() throws Exception {
        //given
        Offset offset1 = Offset.of(1, 1);
        Offset offset2 = Offset.of(1, 1);

        //when
        boolean actual = offset1.equals(offset2);

        //then
        assertTrue(actual);
    }

    @Test
    public void equals_differentOffsets_returnsFalse() throws Exception {
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
    public void y값이_더_작거나_만약_같은_경우_x값이_작은게_순서가_앞이다() throws Exception {
        //given
        final int LESS = -1;
        final int GREATER = 1;
        Offset offset1 = Offset.of(8, 8);
        Offset offset2 = Offset.ZERO;
        Offset offset3 = Offset.of(0, 8);

        //when
        int compare1With2 = offset1.compareTo(offset2);
        int compare2With3 = offset2.compareTo(offset3);
        int compare3With1 = offset3.compareTo(offset1);

        //then
        assertEquals(GREATER, compare1With2);
        assertEquals(LESS, compare2With3);
        assertEquals(LESS, compare3With1);
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
    public void rotate_zeroOffset_returnsSameOffset() throws Exception {
        //given
        Offset zero = Offset.ZERO;

        //when
        Offset actual = zero.rotate();

        //then
        assertEquals(zero, actual);
    }
    
    @Test
    public void rotate_1QuadrantOffset_returns2QuadrantOffset() throws Exception {
        //given
        Offset offset1Quadrant1 = Offset.of(2, 4);
        
        //when
        Offset actual = offset1Quadrant1.rotate();

        //then
        assertEquals(Offset.of(-4, 2),actual);
    }
    
    @Test
    public void rotate_4Times_returnsSameOffset() throws Exception {
        //given
        Offset originalOffset = Offset.of(1, -3);
        
        //when
        Offset rotate1TimeOffset = originalOffset.rotate();
        Offset rotate2TimesOffset = rotate1TimeOffset.rotate();
        Offset rotate3TimesOffset = rotate2TimesOffset.rotate();
        Offset rotate4TimesOffset = rotate3TimesOffset.rotate();

        //then
        assertEquals(originalOffset, rotate4TimesOffset);
    }
}
