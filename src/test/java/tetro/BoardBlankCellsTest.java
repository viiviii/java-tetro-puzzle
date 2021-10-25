package tetro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tetro.grid.Cells;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BoardBlankCellsTest {
    Block.Shape oBlockShapeCells;

    @BeforeEach
    public void beforeEach() throws Exception {
        final Block oBlock = Blocks.basic(BlockType.O);
        oBlockShapeCells = oBlock.shape();
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
        Board.Blanks actual = cells.difference(oBlockShapeCells.offsets());

        //then
        Board.Blanks expect = new Board.Blanks(Offsets.of(remainOffset));
        assertEquals(expect, actual);
    }
}