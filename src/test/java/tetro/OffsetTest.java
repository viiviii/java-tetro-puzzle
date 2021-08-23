package tetro;

import org.junit.jupiter.api.Test;
import tetro.exception.OffsetOutOfBoundsException;

import static org.junit.jupiter.api.Assertions.*;

public class OffsetTest {

    @Test
    public void of() throws Exception {
        //given
        Offset offset1 = Offset.of(0, 8);

        //then
        assertEquals(0, offset1.x);
        assertEquals(8, offset1.y);
    }

    @Test
    public void equals() throws Exception {
        //given
        Offset offset1 = Offset.of(1, 1);
        Offset offset2 = Offset.of(0, 2);

        //then
        assertNotEquals(offset1, offset2);
        assertEquals(Offset.of(1, 1), offset1);
    }

    @Test
    public void y값이_더_작거나_만약_같은_경우_x값이_작은게_순서가_앞이다() throws Exception {
        //given
        final int LESS = -1;
        final int GREATER = 1;
        Offset offset1 = Offset.of(8, 8);
        Offset offset2 = Offset.of(0, 0);
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
    public void 유효하지_않은_좌표일_경우_exception을_던진다() throws Exception {
        //given
        final int MAX_LENGTH = Offset.LENGTH;

        //then
        assertThrows(OffsetOutOfBoundsException.class,
                () -> Offset.of(MAX_LENGTH, 0));
        assertThrows(OffsetOutOfBoundsException.class,
                () -> Offset.of(-1, -10));
    }
}
