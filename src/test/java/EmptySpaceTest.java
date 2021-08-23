import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EmptySpaceTest {

    @Test
    public void offset은_중복을_허용하지_않는다() throws Exception {
        //given
        Offset offset1 = new Offset(1, 1);
        Offset offset2 = new Offset(1, 1);

        //when
        EmptySpace emptySpace = new EmptySpace(offset1, offset2);

        //then
        assertEquals(1, emptySpace.size());
    }
    
    @Test
    public void containsAll() throws Exception {
        //given
        Offset emptyOffset1 = new Offset(1, 1);
        Offset emptyOffset2 = new Offset(2, 2);
        Offset compareOffset1 = new Offset(1, 1);
        Offset compareOffset2 = new Offset(2, 2);
        Offset compareOffset3 = new Offset(3, 3);

        EmptySpace emptySpace = new EmptySpace(emptyOffset1, emptyOffset2);

        Set compare1 = Set.of(compareOffset1);
        Set compare2 = Set.of(compareOffset1, compareOffset2);
        Set compare3 = Set.of(compareOffset1, compareOffset3);

        //then
        assertTrue(emptySpace.containsAll(compare1));
        assertTrue(emptySpace.containsAll(compare2));
        assertFalse(emptySpace.containsAll(compare3));
    }

    @Test
    public void 가장_처음_비어있는_위치_구하기() throws Exception {
        //given
        Offset offset1 = new Offset(3, 3);
        Offset offset2 = new Offset(2, 2);
        Offset offset3 = new Offset(3, 2);
        EmptySpace emptySpace = new EmptySpace(offset1, offset2, offset3);

        //when
        Offset firstOffset = emptySpace.first();

        //then
        assertEquals(offset2, firstOffset);
    }
}
