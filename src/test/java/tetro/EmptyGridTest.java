package tetro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tetro.block.Block;
import tetro.block.BlockShape;
import tetro.block.BlockType;
import tetro.block.BlockShapes;
import tetro.offset.Offset;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmptyGridTest {
    Block oBlock;

    @BeforeEach
    public void beforeEach() throws Exception {
        BlockShape oBlockShape = BlockShapes.get(BlockType.O, 0);
        oBlock = new Block(oBlockShape, Offset.of(0, 0));
    }

    @Test
    public void isFull() throws Exception {
        //given
        EmptyGrid emptyGrid = EmptyGrid.of(Collections.EMPTY_LIST);

        //when
        boolean actual = emptyGrid.isFull();

        //then
        assertTrue(actual);
    }

    @Test
    public void first() throws Exception {
        //given
        Offset o1 = Offset.of(2, 9);
        Offset o2 = Offset.of(0, 2);
        EmptyGrid emptyGrid = EmptyGrid.of(Set.of(o1, o2));

        //when
        Offset actual = emptyGrid.first();

        //then
        assertEquals(o2, actual);
    }

    @Test
    public void canFit_fitBlock_returnsTrue() throws Exception {
        //given
        Offset o1 = Offset.of(0, 0);
        Offset o2 = Offset.of(1, 0);
        Offset o3 = Offset.of(0, 1);
        Offset o4 = Offset.of(1, 1);
        EmptyGrid oBlockShapeEmptyGrid = EmptyGrid.of(Set.of(o1, o2, o3, o4));

        //when
        boolean actual = oBlockShapeEmptyGrid.canFit(oBlock);

        //then
        assertTrue(actual);
    }

    @Test
    public void canFit_unfitBlock_returnsFalse() throws Exception {
        //given
        EmptyGrid emptyGrid = EmptyGrid.of(Set.of(Offset.of(0, 0)));

        //when
        boolean actual = emptyGrid.canFit(oBlock);

        //then
        assertFalse(actual);
    }

    @Test
    public void fit_fitBlock_returnsRemainingEmptyGrids() throws Exception {
        //given
        Offset o1 = Offset.of(0, 0);
        Offset o2 = Offset.of(1, 0);
        Offset o3 = Offset.of(0, 1);
        Offset o4 = Offset.of(1, 1);
        Offset remainOffset = Offset.of(6, 8);

        EmptyGrid oBlockShapeEmptyGrid = EmptyGrid.of(Set.of(o1, o2, o3, o4, remainOffset));
        EmptyGrid expect = EmptyGrid.of(Set.of(remainOffset));

        //when
        EmptyGrid actual = oBlockShapeEmptyGrid.fit(oBlock);

        //then
        assertEquals(expect, actual);
    }

}