import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void isFull() throws Exception {
        //given
        EmptySpace emptySpace1 = new EmptySpace();
        EmptySpace emptySpace2 = new EmptySpace(new Offset(1, 1));
        Board board1 = Board.from(emptySpace1);
        Board board2 = Board.from(emptySpace2);

        //then
        assertTrue(board1.isFull());
        assertFalse(board2.isFull());
    }
    
    @Test
    public void 인자로_null이_올_경우_UNFIT을_리턴한다() throws Exception {
        //given
        Board board = Board.from(null);
        
        //then
        assertEquals(board, Board.UNFIT);
    }
}
