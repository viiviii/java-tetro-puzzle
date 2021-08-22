import org.junit.jupiter.api.Test;

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
        assertTrue(emptySpace.size() == 1);
    }

}
