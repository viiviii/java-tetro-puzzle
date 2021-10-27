package tetro;

import org.junit.jupiter.api.Test;
import tetro.cell.Cell;
import tetro.cell.Offsets;

import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {
    
    @Test
    public void validate_notEqualsCellsSizeAsBlockSize_throwsException() throws Exception {
        //given
        Cell duplicate = Cell.of(2, 3);
        Offsets offsets = Offsets.of(
                Cell.of(0, 0), duplicate,
                Cell.of(4, 5), duplicate);

        //then
        assertNotEquals(Block.SIZE, offsets.size());
        assertThrows(IllegalArgumentException.class, () -> Block.of(offsets, null, 0));
    }
}
