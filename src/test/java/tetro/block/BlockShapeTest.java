package tetro.block;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tetro.offset.Offset;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BlockShapeTest {
    final int SIZE = BlockShape.SIZE;

    BlockShape oBlockShape;
    BlockShape sBlockShape;

    @BeforeEach
    public void beforeEach() {
        final int NOT_ROTATION = 0;
        BlockShapeData data = new BlockShapeData();
        Blocks blocks = new Blocks(data);
        oBlockShape = blocks.get(BlockType.O).shape(NOT_ROTATION);
        sBlockShape = blocks.get(BlockType.S).shape(NOT_ROTATION);
    }

    @Test
    public void equals_sameOffsets_returnsTrue() throws Exception {
        //given
        BlockShape shape1 = oBlockShape;
        BlockShape shape2 = new BlockShape(List.of(0, 0, 1, 0, 0, 1, 1, 1));

        //when
        boolean actual = shape1.equals(shape2);

        //then
        assertTrue(actual);
    }

    @Test
    public void equals_differentOffsets_returnsFalse() throws Exception {
        //given
        BlockShape shape1 = oBlockShape;
        BlockShape shape2 = sBlockShape;

        //when
        boolean actual = shape1.equals(shape2);

        //then
        assertFalse(actual);
    }

    @Test
    public void validate_lengthIsNotEight_throwsException() throws Exception {
        //given
        List lengthIsNotEight = List.of(0, 0, 1, 0, 0, 1);

        //then
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> new BlockShape(lengthIsNotEight));
    }

    @Test
    public void validate_duplicateValue_throwsException() throws Exception {
        //given
        Offset offset = Offset.ZERO;
        List lengthIsNotEight = List.of(offset.x, offset.y, 1, 0, 0, 1, offset.x, offset.y);

        //then
        assertThrows(IllegalArgumentException.class, () -> new BlockShape(lengthIsNotEight));
    }

    @Test
    public void validate_containSameNumberAsSize_throwsException() throws Exception {
        //given
        int sameNumberAsSize = SIZE;
        List list = List.of(0, 0, 1, 0, 0, 1, 1, sameNumberAsSize);

        //then
        assertThrows(IllegalArgumentException.class, () -> new BlockShape(list));
    }

    @Test
    public void validate_containNumberGreaterThanSize_throwsException() throws Exception {
        //given
        int numberGreaterThanZie = SIZE + 1;
        List list = List.of(0, 0, 1, 0, numberGreaterThanZie, 1, 1, 1);

        //then
        assertThrows(IllegalArgumentException.class, () -> new BlockShape(list));
    }

    @Test
    public void validate_containNegativeNumber_throwsException() throws Exception {
        //given
        int negativeNumber = -1;
        List list = List.of(negativeNumber, 0, 1, 0, 0, 1, 1, 1);

        //then
        assertThrows(IllegalArgumentException.class, () -> new BlockShape(list));
    }

    @Test
    public void toGridString_startedZeroOffset_doesNotThrowsException() throws Exception {
        //given
        BlockShape shape = oBlockShape;

        //then
        assertDoesNotThrow(() -> shape.toGridString());
    }

    @Test
    public void toGridString_notStartedZeroOffset_doesNotThrowsException() throws Exception {
        //given
        BlockShape shape = sBlockShape;

        //then
        assertDoesNotThrow(() -> shape.toGridString());
    }
}
