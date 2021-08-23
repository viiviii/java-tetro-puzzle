import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void isFull() throws Exception {
        //given
        EmptySpace emptySpace = new EmptySpace();
        Board board = new Board(emptySpace);

        //when
        boolean isFull = board.isFull();

        //then
        assertTrue(isFull);
    }
}
