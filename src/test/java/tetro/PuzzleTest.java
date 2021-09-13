package tetro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tetro.block.*;
import tetro.offset.Offset;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PuzzleTest {
    Blocks blocks;
    Puzzle puzzle;

    @BeforeEach
    public void beforeEach() throws Exception {
        BlockShapeData data = new BlockShapeData();
        blocks = new Blocks(data);
        puzzle = new Puzzle(blocks);
    }

    @DisplayName("빈 칸을 채울 수 없으면 빈 리스트를 반환한다")
    @Test
    public void put_unfitEmptyGrids_returnsEmptyList() throws Exception {
        //given
        EmptyGrids emptyGrids = EmptyGrids.of(List.of(Offset.of(0, 0)));

        //when
        Set combinations = puzzle.put(emptyGrids);
        int actual = combinations.size();

        //then
        assertEquals(0, actual);
    }

    // TODO: oBlockShapeOffsets 이쪽 리팩토링
    @Test
    public void put_oBlockShapeEmptyGrid_returnsSizeOneSet() throws Exception {
        //given
        List oBlockShapeOffsets = List.of(
                Offset.of(0, 0), Offset.of(1, 0),
                Offset.of(0, 1), Offset.of(1, 1)
        );
        EmptyGrids emptyGrids = EmptyGrids.of(oBlockShapeOffsets);

        //when
        Set combinations = puzzle.put(emptyGrids);
        int actual = combinations.size();

        //then
        assertEquals(1, actual);
    }

    /*
     * 비어있는 그리드가 아래와 같은 경우(4x2)
     * ㅁㅁㅁㅁ
     * ㅁㅁㅁㅁ
     * */
    @Test
    public void put_4x2RectangularEmptyGrid_returnsSizeFourSet() throws Exception {
        //given
        List rectangularOffsets = List.of(
                Offset.of(3, 3), Offset.of(4, 3), Offset.of(5, 3), Offset.of(6, 3),
                Offset.of(3, 4), Offset.of(4, 4), Offset.of(5, 4), Offset.of(6, 4)
        );
        EmptyGrids emptyGrids = EmptyGrids.of(rectangularOffsets);

        Set<Set<FitBlock>> expect = new HashSet<>();
        FitBlock oBlock1 = new FitBlock(blocks.get(BlockType.O), 0, Offset.of(3, 3));
        FitBlock oBlock2 = new FitBlock(blocks.get(BlockType.O), 0, Offset.of(5, 3));
        FitBlock iBlock1 = new FitBlock(blocks.get(BlockType.I), 1, Offset.of(3, 3));
        FitBlock iBlock2 = new FitBlock(blocks.get(BlockType.I), 1, Offset.of(3, 4));
        FitBlock lBlock1 = new FitBlock(blocks.get(BlockType.L), 3, Offset.of(3, 3));
        FitBlock lBlock2 = new FitBlock(blocks.get(BlockType.L), 1, Offset.of(6, 3));
        FitBlock jBlock1 = new FitBlock(blocks.get(BlockType.J), 3, Offset.of(3, 3));
        FitBlock jBlock2 = new FitBlock(blocks.get(BlockType.J), 1, Offset.of(4, 3));
        expect.add(Set.of(oBlock1, oBlock2));
        expect.add(Set.of(iBlock1, iBlock2));
        expect.add(Set.of(lBlock1, lBlock2));
        expect.add(Set.of(jBlock1, jBlock2));

        //when
        Set<Set<FitBlock>> combinations = puzzle.put(emptyGrids);

        //then
        assertEquals(expect, combinations);
    }
}
