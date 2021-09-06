package tetro;

import org.junit.jupiter.api.Test;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void isFull_offsetsSizeZero_returnsTrue() throws Exception {
        //given
        Offsets offsets = Offsets.of();
        Board board = Board.from(offsets);

        //when
        boolean actual = board.filled();
        //then
        assertTrue(actual);
    }

    @Test
    public void isFull_offsetsSizeNotZero_returnsFalse() throws Exception {
        //given
        Offsets offsets = Offsets.of(Offset.of(1, 1));
        Board board = Board.from(offsets);

        //when
        boolean actual = board.filled();

        //then
        assertFalse(actual);
    }
}
