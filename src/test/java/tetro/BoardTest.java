package tetro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @DisplayName("빈 칸을 채울 수 없으면 빈 리스트를 반환한다")
    @Test
    public void put_unfitEmptyGrids_returnsEmptyList() throws Exception {
        //given
        Offsets unfitOffsets = Offsets.of(Offset.of(0, 0));
        Board board = new Board(unfitOffsets);

        //when
        Set<Set<Block>> allCombinations = board.allFitCombinations();
        int actual = allCombinations.size();

        //then
        assertEquals(0, actual);
    }

    @Test
    public void allFitCombinations_oBlockShapeEmptyGrid_returnsSizeOneSet() throws Exception {
        //given
        Offsets emptyOffsets = Blocks.get(BlockType.O, 0).cells().offsets();
        Board board = new Board(emptyOffsets);

        //when
        Set<Set<Block>> allCombinations = board.allFitCombinations();
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
    public void allFitCombinations_4x2RectangularEmptyGrid_returnsSizeFourSet() throws Exception {
        //given
        Offsets emptyOffsets = Offsets.of(
                Offset.of(3, 3), Offset.of(4, 3), Offset.of(5, 3), Offset.of(6, 3),
                Offset.of(3, 4), Offset.of(4, 4), Offset.of(5, 4), Offset.of(6, 4)
        );
        Board board = new Board(emptyOffsets);

        Set<Set<Block>> expect = new HashSet<>();
        Block oBlock1 = Blocks.get(BlockType.O, 0).translateTo(Offset.of(3, 3));
        Block oBlock2 = Blocks.get(BlockType.O, 0).translateTo(Offset.of(5, 3));
        Block iBlock1 = Blocks.get(BlockType.I, 1).translateTo(Offset.of(3, 3));
        Block iBlock2 = Blocks.get(BlockType.I, 1).translateTo(Offset.of(3, 4));
        Block lBlock1 = Blocks.get(BlockType.L, 3).translateTo(Offset.of(3, 3));
        Block lBlock2 = Blocks.get(BlockType.L, 1).translateTo(Offset.of(6, 3));
        Block jBlock1 = Blocks.get(BlockType.J, 3).translateTo(Offset.of(3, 3));
        Block jBlock2 = Blocks.get(BlockType.J, 1).translateTo(Offset.of(4, 3));
        expect.add(Set.of(oBlock1, oBlock2));
        expect.add(Set.of(iBlock1, iBlock2));
        expect.add(Set.of(lBlock1, lBlock2));
        expect.add(Set.of(jBlock1, jBlock2));

        //when
        Set<Set<Block>> allCombinations = board.allFitCombinations();

        //then
        assertEquals(expect, allCombinations);
    }
}
