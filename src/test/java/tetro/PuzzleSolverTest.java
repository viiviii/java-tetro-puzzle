package tetro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tetro.cell.Cell;
import tetro.cell.Offsets;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleSolverTest {

    @DisplayName("빈 칸을 채울 수 없으면 빈 리스트를 반환한다")
    @Test
    public void allFitCombinations_unfitBoard_returnsEmptyList() throws Exception {
        //given
        Offsets unfitOffsets = Offsets.of(Cell.of(0, 0));
        Board board = new Board(unfitOffsets);
        Puzzle puzzle = new Puzzle(board);

        //when
        Set<Set<FitBlock>> allCombinations = PuzzleSolver.allFitCombinations(puzzle);
        int actual = allCombinations.size();

        //then
        assertEquals(0, actual);
    }

    @Test
    public void allFitCombinations_oBlockShapeBoard_returnsOneSizeSet() throws Exception {
        //given
        Offsets offsets = Blocks.get(BlockType.O, 0).shapeOffsets();
        Board board = new Board(offsets);
        Puzzle puzzle = new Puzzle(board);

        //when
        Set<Set<FitBlock>> allCombinations = PuzzleSolver.allFitCombinations(puzzle);
        int actual = allCombinations.size();

        //then
        assertEquals(1, actual);
    }

    /*
     * 비어있는 그리드가 아래와 같은 경우(4x2)
     * ㅁㅁㅁㅁ
     * ㅁㅁㅁㅁ
     * */
    @Test
    public void allFitCombinations_4x2RectangularBoard_returnsFourSizeSet() throws Exception {
        //given
        Offsets offsets = Offsets.of(
                Cell.of(3, 3), Cell.of(4, 3), Cell.of(5, 3), Cell.of(6, 3),
                Cell.of(3, 4), Cell.of(4, 4), Cell.of(5, 4), Cell.of(6, 4)
        );
        Board board = new Board(offsets);
        Puzzle puzzle = new Puzzle(board);

        Set<Set<FitBlock>> expect = new HashSet<>();
        FitBlock oBlock1 = new FitBlock(Blocks.get(BlockType.O, 0), Offset.of(3, 3));
        FitBlock oBlock2 = new FitBlock(Blocks.get(BlockType.O, 0), Offset.of(5, 3));
        FitBlock iBlock1 = new FitBlock(Blocks.get(BlockType.I, 1), Offset.of(3, 3));
        FitBlock iBlock2 = new FitBlock(Blocks.get(BlockType.I, 1), Offset.of(3, 4));
        FitBlock lBlock1 = new FitBlock(Blocks.get(BlockType.L, 3), Offset.of(3, 3));
        FitBlock lBlock2 = new FitBlock(Blocks.get(BlockType.L, 1), Offset.of(6, 3));
        FitBlock jBlock1 = new FitBlock(Blocks.get(BlockType.J, 3), Offset.of(3, 3));
        FitBlock jBlock2 = new FitBlock(Blocks.get(BlockType.J, 1), Offset.of(4, 3));
        expect.add(Set.of(oBlock1, oBlock2));
        expect.add(Set.of(iBlock1, iBlock2));
        expect.add(Set.of(lBlock1, lBlock2));
        expect.add(Set.of(jBlock1, jBlock2));

        //when
        Set<Set<FitBlock>> actual = PuzzleSolver.allFitCombinations(puzzle);

        //then
        assertEquals(expect, actual);
        assertEquals(4, actual.size());
    }
}