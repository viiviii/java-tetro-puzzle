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
}
