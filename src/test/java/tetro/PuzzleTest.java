package tetro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tetro.block.*;
import tetro.block.shape.BlockShapes;
import tetro.offset.Offset;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PuzzleTest {
    Puzzle puzzle;

    @BeforeEach
    public void beforeEach() throws Exception {
        puzzle = new Puzzle();
    }

    @DisplayName("빈 칸을 채울 수 없으면 빈 리스트를 반환한다")
    @Test
    public void put_unfitEmptyGrids_returnsEmptyList() throws Exception {
        //given
        EmptyGrid emptyGrid = EmptyGrid.of(List.of(Offset.of(0, 0)));

        //when
        Set combinations = puzzle.put(emptyGrid);
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
        EmptyGrid emptyGrid = EmptyGrid.of(oBlockShapeOffsets);

        //when
        Set combinations = puzzle.put(emptyGrid);
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
        EmptyGrid emptyGrid = EmptyGrid.of(rectangularOffsets);

        Set<Set<Block>> expect = new HashSet<>();
        Block oBlock1 = new Block(BlockShapes.get(BlockType.O, 0), Offset.of(3, 3));
        Block oBlock2 = new Block(BlockShapes.get(BlockType.O, 0), Offset.of(5, 3));
        Block iBlock1 = new Block(BlockShapes.get(BlockType.I, 1), Offset.of(3, 3));
        Block iBlock2 = new Block(BlockShapes.get(BlockType.I, 1), Offset.of(3, 4));
        Block lBlock1 = new Block(BlockShapes.get(BlockType.L, 3), Offset.of(3, 3));
        Block lBlock2 = new Block(BlockShapes.get(BlockType.L, 1), Offset.of(6, 3));
        Block jBlock1 = new Block(BlockShapes.get(BlockType.J, 3), Offset.of(3, 3));
        Block jBlock2 = new Block(BlockShapes.get(BlockType.J, 1), Offset.of(4, 3));
        expect.add(Set.of(oBlock1, oBlock2));
        expect.add(Set.of(iBlock1, iBlock2));
        expect.add(Set.of(lBlock1, lBlock2));
        expect.add(Set.of(jBlock1, jBlock2));

        //when
        Set<Set<Block>> combinations = puzzle.put(emptyGrid);

        //then
        assertEquals(expect, combinations);
    }
}
