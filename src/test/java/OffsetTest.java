import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OffsetTest {

    @Test
    public void equals() throws Exception {
        //given
        Offset offset1 = new Offset(1, 1);
        Offset offset2 = new Offset(0, 2);

        //then
        assertNotEquals(offset1, offset2);
        assertEquals(new Offset(1, 1), offset1);
    }
    
    @Test
    public void y값이_더_작거나_만약_같은_경우_x값이_작은게_순서가_앞이다() throws Exception {
        //given
        final int LESS = -1;
        final int GREATER = 1;
        Offset offset1 = new Offset(3, 3);
        Offset offset2 = new Offset(2, 2);
        Offset offset3 = new Offset(3, 2);

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
        Offset offset1 = new Offset(1, 1);
        Offset offset2 = new Offset(1, 1);
        
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
        Offset offset = new Offset(1, 1);

        //then
        assertEquals(false, offset.equals(null));
        assertThrows(NullPointerException.class, () -> offset.compareTo(null));
    }
}
