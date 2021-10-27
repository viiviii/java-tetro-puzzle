package tetro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OffsetTest {
    @Test
    public void difference_positiveOtherOffset_returnsOriginMinusOtherOffset() throws Exception {
        //given
        Offset offset = new Offset(10, 6);
        Offset other = new Offset(3, 1);

        //when
        Offset actual = offset.difference(other);

        //then
        assertEquals(new Offset(7, 5), actual);
    }

    @Test
    public void difference_negativeOtherOffset_returnsOriginPlusOtherOffset() throws Exception {
        //given
        Offset offset = new Offset(10, 6);
        Offset other = new Offset(-3, -1);

        //when
        Offset actual = offset.difference(other);

        //then
        assertEquals(new Offset(13, 7), actual);
    }
}
