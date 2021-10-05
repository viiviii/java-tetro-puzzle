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
        oBlockShapeCells = new Block.Shape(oBlock.cells().offsets());
    }

    @Test
    public void isNone() throws Exception {
        //given
        Board.BlankCells cells = new Board.BlankCells(Offsets.EMPTY);

        //when
        boolean actual = cells.isNone();

        //then
        assertTrue(actual);
    }

    @Test
    public void first() throws Exception {
        //given
        Offset o1 = Offset.of(2, 9);
        Offset o2 = Offset.of(0, 2);
        Board.BlankCells cells = new Board.BlankCells(Offsets.of(o1, o2));

        //when
        Offset actual = cells.first();

        //then
        assertEquals(o2, actual);
    }

    @Test
    public void canFit_fitBlock_returnsTrue() throws Exception {
        //given
        Board.BlankCells cells = new Board.BlankCells(oBlockShapeCells.offsets());

        //when
        boolean actual = cells.canFit(oBlockShapeCells);

        //then
        assertTrue(actual);
    }

    @Test
    public void canFit_unfitBlock_returnsFalse() throws Exception {
        //given
        Offsets unfitOffsets = Offsets.of(Offset.of(0, 0));
        Board.BlankCells cells = new Board.BlankCells(unfitOffsets);

        //when
        boolean actual = cells.canFit(oBlockShapeCells);

        //then
        assertFalse(actual);
    }

    @Test
    public void fit_fitBlock_returnsRemainingCells() throws Exception {
        //given
        Offset remainOffset = Offset.of(6, 8);
        Set<Offset> set = oBlockShapeCells.offsets().stream().collect(Collectors.toSet());
        set.add(remainOffset);
        Offsets offsets = Offsets.of(set);
        Board.BlankCells cells = new Board.BlankCells(offsets);

        //when
        Board.BlankCells actual = cells.fit(oBlockShapeCells);

        //then
        Board.BlankCells expect = new Board.BlankCells(Offsets.of(remainOffset));
        assertEquals(expect, actual);
    }
}