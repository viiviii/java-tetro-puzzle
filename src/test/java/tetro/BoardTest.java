package tetro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @DisplayName("빈 칸을 채울 수 없으면 빈 리스트를 반환한다")
    @Test
    public void put_unfitBoard_returnsEmptyList() throws Exception {
        //given
        Offsets unfitOffsets = Offsets.of(Offset.of(0, 0));
        Board board = new Board(unfitOffsets);
        Puzzle puzzle = new Puzzle(board);

        //when
        Set<Puzzle.FitCells> allCombinations = PuzzleSolver.allFitCombinations(puzzle);
        int actual = allCombinations.size();

        //then
        assertEquals(0, actual);
    }

    @Test
    public void allFitCombinations_oBlockShapeBoard_returnsSizeOneSet() throws Exception {
        //given
        Offsets offsets = Blocks.get(BlockType.O, 0).shape().offsets();
        Board board = new Board(offsets);
        Puzzle puzzle = new Puzzle(board);

        //when
        Set<Puzzle.FitCells> allCombinations = PuzzleSolver.allFitCombinations(puzzle);
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
    public void allFitCombinations_4x2RectangularBoard_returnsSizeFourSet() throws Exception {
        //given
        Offsets offsets = Offsets.of(
                Offset.of(3, 3), Offset.of(4, 3), Offset.of(5, 3), Offset.of(6, 3),
                Offset.of(3, 4), Offset.of(4, 4), Offset.of(5, 4), Offset.of(6, 4)
        );
        Board board = new Board(offsets);
        Puzzle puzzle = new Puzzle(board);

        Set<Set<FitBlock.State>> expect = new HashSet<>();
        FitBlock.State oBlock1 = new FitBlock.State(BlockType.O, 0, Offset.of(3, 3));
        FitBlock.State oBlock2 = new FitBlock.State(BlockType.O, 0, Offset.of(5, 3));
        FitBlock.State iBlock1 = new FitBlock.State(BlockType.I, 1, Offset.of(3, 3));
        FitBlock.State iBlock2 = new FitBlock.State(BlockType.I, 1, Offset.of(3, 4));
        FitBlock.State lBlock1 = new FitBlock.State(BlockType.L, 3, Offset.of(3, 3));
        FitBlock.State lBlock2 = new FitBlock.State(BlockType.L, 1, Offset.of(6, 3));
        FitBlock.State jBlock1 = new FitBlock.State(BlockType.J, 3, Offset.of(3, 3));
        FitBlock.State jBlock2 = new FitBlock.State(BlockType.J, 1, Offset.of(4, 3));
        expect.add(Set.of(oBlock1, oBlock2));
        expect.add(Set.of(iBlock1, iBlock2));
        expect.add(Set.of(lBlock1, lBlock2));
        expect.add(Set.of(jBlock1, jBlock2));

        //when
        Set<Puzzle.FitCells> allCombinations = PuzzleSolver.allFitCombinations(puzzle);
        Set<Set<FitBlock.State>> actual = allCombinations
                .stream().map(e -> e.blockStates())
                .collect(Collectors.toSet());

        //then
        assertEquals(expect, actual);
        assertEquals(4, actual.size());
    }
}
