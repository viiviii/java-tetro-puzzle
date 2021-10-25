package tetro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tetro.grid.cells.AbstractNonBlankCells;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BoardBlankCellsTest {
    AbstractNonBlankCells oBlockShapeCells;

    @BeforeEach
    public void beforeEach() throws Exception {
        final Block oBlock = Blocks.basic(BlockType.O);
        oBlockShapeCells = oBlock.shape();
    }

    @Test
    public void containsAll_fitBlock_returnsTrue() throws Exception {
        //given
        Board.Blanks cells = new Board.Blanks(oBlockShapeCells.offsets());

        //when
        boolean actual = cells.containsAll(oBlockShapeCells);

        //then
        assertTrue(actual);
    }

    @Test
    public void containsAll_unfitBlock_returnsFalse() throws Exception {
        //given
        Offsets unfitOffsets = Offsets.of(Offset.of(0, 0));
        Board.Blanks cells = new Board.Blanks(unfitOffsets);

        //when
        boolean actual = cells.containsAll(oBlockShapeCells);

        //then
        assertFalse(actual);
    }

    @Test
    public void difference_fitBlock_returnsRemainingCells() throws Exception {
        //given
        Offset remainOffset = Offset.of(6, 8);
        Set<Offset> set = oBlockShapeCells.offsets().stream().collect(Collectors.toSet());
        set.add(remainOffset);
        Offsets offsets = Offsets.of(set);
        Board.Blanks cells = new Board.Blanks(offsets);

        //when
        Board.Blanks actual = cells.difference(oBlockShapeCells);

        //then
        Board.Blanks expect = new Board.Blanks(Offsets.of(remainOffset));
        assertEquals(expect, actual);
    }
}