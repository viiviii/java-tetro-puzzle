package tetro;

import org.junit.jupiter.api.Test;

import tetro.offset.Offset;
import tetro.offset.Offsets;

import static org.junit.jupiter.api.Assertions.*;

public class GridStringTest {
    private final int LENGTH = 4;

    @Test
    public void valueOf() throws Exception {
        //given
        Offsets oBlockShapeOffsets = Offsets.of(
                Offset.of(0, 0), Offset.of(1, 0), Offset.of(0, 1), Offset.of(1, 1)
        );
        String expect = "\n" +
                "■ ■ □ □ \n" +
                "■ ■ □ □ \n" +
                "□ □ □ □ \n" +
                "□ □ □ □ ";

        //when
        String actual = GridString.valueOf(LENGTH, oBlockShapeOffsets);

        //then
        assertEquals(expect, actual);
    }

    @Test
    public void validate_containSameNumberAsLength_throwsException() throws Exception {
        //given
        Offset offset = Offset.of(0, LENGTH);
        Offsets offsets = Offsets.of(offset);

        //then
        assertThrows(IllegalArgumentException.class, () -> GridString.valueOf(LENGTH, offsets));
    }

    @Test
    public void validate_containNumberGreaterThanLength_throwsException() throws Exception {
        //given
        Offset offset = Offset.of(0, LENGTH + 1);
        Offsets offsets = Offsets.of(offset);

        //then
        assertThrows(IllegalArgumentException.class, () -> GridString.valueOf(LENGTH, offsets));
    }

    @Test
    public void validate_containNegativeNumber_throwsException() throws Exception {
        //given
        Offset offset = Offset.of(0, -LENGTH);
        Offsets offsets = Offsets.of(offset);

        //then
        assertThrows(IllegalArgumentException.class, () -> GridString.valueOf(LENGTH, offsets));
    }
}
