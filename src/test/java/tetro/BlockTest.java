package tetro;

import org.junit.jupiter.api.Test;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import static org.junit.jupiter.api.Assertions.*;

public class BlockTest {
    
    @Test
    public void validate_notEqualsOffsetSizeAsBlockSize_throwsException() throws Exception {
        //given
        Offset duplicateOffset = Offset.of(2, 3);
        Offsets offsets = Offsets.of(
                Offset.of(0, 0), duplicateOffset,
                Offset.of(4, 5), duplicateOffset);

        //then
        assertNotEquals(Block.SIZE, offsets.size());
        assertThrows(IllegalArgumentException.class, () -> Block.of(offsets, null, 0));
    }
}
