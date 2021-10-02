package tetro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tetro.grid.cells.AbstractFillCells;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BoardEmptyCellsTest {
    AbstractFillCells oBlockShapeCells;

    @BeforeEach
    public void beforeEach() throws Exception {
        final Block oBlock = Blocks.basic(BlockType.O);
        oBlockShapeCells =  new Block.Shape(oBlock.cells().offsets());
    }

    @Test
    public void isNone() throws Exception {
        //given
        Board.EmptyCells emptyCells = new Board.EmptyCells(Offsets.EMPTY);

        //when
        boolean actual = emptyCells.isNone();

        //then
        assertTrue(actual);
    }

    @Test
    public void first() throws Exception {
        //given
        Offset o1 = Offset.of(2, 9);
        Offset o2 = Offset.of(0, 2);
        Board.EmptyCells emptyCells = new Board.EmptyCells(Offsets.of(o1, o2));

        //when
        Offset actual = emptyCells.first();

        //then
        assertEquals(o2, actual);
    }

    @Test
    public void canFit_fitBlock_returnsTrue() throws Exception {
        //given
        Board.EmptyCells emptyCells = new Board.EmptyCells(oBlockShapeCells.offsets());

        //when
        boolean actual = emptyCells.canFit(oBlockShapeCells);

        //then
        assertTrue(actual);
    }

    @Test
    public void canFit_unfitBlock_returnsFalse() throws Exception {
        //given
        Offsets unfitOffsets = Offsets.of(Offset.of(0, 0));
        Board.EmptyCells emptyCells = new Board.EmptyCells(unfitOffsets);

        //when
        boolean actual = emptyCells.canFit(oBlockShapeCells);

        //then
        assertFalse(actual);
    }

    @Test
    public void fit_fitBlock_returnsRemainingEmptyCells() throws Exception {
        //given
        Offset remainOffset = Offset.of(6, 8);
        Set<Offset> set = oBlockShapeCells.offsets().stream().collect(Collectors.toSet());
        set.add(remainOffset);
        Offsets emptyOffsets = Offsets.of(set);
        Board.EmptyCells emptyCells = new Board.EmptyCells(emptyOffsets);

        //when
        Board.EmptyCells actual = emptyCells.fit(oBlockShapeCells);

        //then
        Board.EmptyCells expect = new Board.EmptyCells(Offsets.of(remainOffset));
        assertEquals(expect, actual);
    }

}