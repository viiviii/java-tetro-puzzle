package tetro;

import org.junit.jupiter.api.Test;
import tetro.grid.Cell;
import tetro.grid.Cells;

import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {
    
    @Test
    public void validate_notEqualsCellsSizeAsBlockSize_throwsException() throws Exception {
        //given
        Cell duplicate = Cell.of(2, 3);
        Cells cells = Cells.of(
                Cell.of(0, 0), duplicate,
                Cell.of(4, 5), duplicate);

        //then
        assertNotEquals(Block.SIZE, cells.size());
        assertThrows(IllegalArgumentException.class, () -> Block.of(cells, null, 0));
    }
}
