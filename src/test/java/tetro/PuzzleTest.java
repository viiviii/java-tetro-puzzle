package tetro;

import org.junit.jupiter.api.Test;
import tetro.cell.Cell;
import tetro.cell.Offsets;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleTest {

    @Test
    void hasBlanks_returnsTrue() {
        //given
        Offsets offsets = Offsets.of(Cell.of(1, 3));
        Board board = new Board(offsets);
        Puzzle puzzle = new Puzzle(board);

        //when
        boolean actual = puzzle.hasBlanks();

        //then
        assertTrue(actual);
    }

    @Test
    void hasBlanks_returnsFalse() {
        //given
        Offsets offsets = Offsets.EMPTY;
        Board board = new Board(offsets);
        Puzzle puzzle = new Puzzle(board);

        //when
        boolean actual = puzzle.hasBlanks();

        //then
        assertFalse(actual);
    }

    @Test
    void put_returnsTrue() {
        //given
        Block block = Blocks.get(BlockType.O, 1);

        Offsets offsets = block.shapeOffsets();
        Board board = new Board(offsets);
        Puzzle puzzle = new Puzzle(board);

        FitBlock fitBlock = new FitBlock(block, puzzle.remainingBlankOffsets().first());

        //when
        boolean actual = puzzle.put(fitBlock);

        //then
        assertTrue(actual);
    }

    @Test
    void put_returnsFalse() {
        //given
        Block block = Blocks.get(BlockType.O, 1);

        Offsets offsets = Blocks.get(BlockType.S, 1).shapeOffsets();
        Board board = new Board(offsets);
        Puzzle puzzle = new Puzzle(board);

        FitBlock fitBlock = new FitBlock(block, puzzle.remainingBlankOffsets().first());

        //when
        boolean actual = puzzle.put(fitBlock);

        //then
        assertFalse(actual);
    }

    @Test
    void take_returnsTrue() {
        //given
        Block block = Blocks.get(BlockType.O, 1);

        Offsets offsets = block.shapeOffsets();
        Board board = new Board(offsets);
        Puzzle puzzle = new Puzzle(board);

        FitBlock fitBlock = new FitBlock(block, puzzle.remainingBlankOffsets().first());
        puzzle.put(fitBlock);

        //when

        boolean actual = puzzle.take(fitBlock);

        //then
        assertTrue(actual);
    }

    @Test
    void take_returnsFalse() {
        //given
        Block block = Blocks.get(BlockType.O, 1);

        Offsets offsets = Blocks.get(BlockType.S, 1).shapeOffsets();
        Board board = new Board(offsets);
        Puzzle puzzle = new Puzzle(board);

        FitBlock fitBlock = new FitBlock(block, puzzle.remainingBlankOffsets().first());

        //when
        puzzle.put(fitBlock);
        boolean actual = puzzle.take(fitBlock);

        //then
        assertFalse(actual);
    }

    @Test
    void fitBlockSet_returnsOneSizeSet() {
        //given
        Block block = Blocks.get(BlockType.O, 1);

        Offsets offsets = block.shapeOffsets();
        Board board = new Board(offsets);
        Puzzle puzzle = new Puzzle(board);

        FitBlock fitBlock = new FitBlock(block, puzzle.remainingBlankOffsets().first());
        puzzle.put(fitBlock);

        //when
        Set<FitBlock> fitBlocks = puzzle.fitBlockSet();

        //then
        assertEquals(1, fitBlocks.size());
        assertEquals(Set.of(fitBlock), fitBlocks);
    }

    @Test
    void fitBlockSet_returnsEmptySet() {
        //given
        Block block = Blocks.get(BlockType.O, 1);

        Offsets offsets = Blocks.get(BlockType.S, 1).shapeOffsets();
        Board board = new Board(offsets);
        Puzzle puzzle = new Puzzle(board);

        FitBlock fitBlock = new FitBlock(block, puzzle.remainingBlankOffsets().first());
        puzzle.put(fitBlock);

        //when
        Set<FitBlock> fitBlocks = puzzle.fitBlockSet();

        //then
        assertEquals(0, fitBlocks.size());
        assertEquals(Collections.EMPTY_SET, fitBlocks);
    }

    @Test
    void blanks() {
        //given
        Block block = Blocks.get(BlockType.O, 1);

        Offsets offsets = block.shapeOffsets();
        Board board = new Board(offsets);
        Puzzle puzzle = new Puzzle(board);

        //when
        Offsets actual = puzzle.remainingBlankOffsets();

        //then
        assertEquals(offsets, actual);
    }
}