package tetro.block;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlockTypeTest {

    /**
     * 테트리스 회전은 최대 4번까지 가능하므로 블럭 모양은 최소 1개에서 최대 4개이다.
     */
    @Test
    public void numberOfShapes_rangeIsBetween1And4_returnsTrue() throws Exception {
        //when
        boolean actual = blockTypesMatchAll(e -> e.numberOfShapes >=1 && e.numberOfShapes <= 4);

        //then
        assertTrue(actual);
    }

    /**
     * 테트리스 블록은 4개의 칸으로 구성되어 있다.
     */
    @Test
    public void offsetsSize_equalsNumberFour_returnsTrue() throws Exception {
        //when
        boolean actual = blockTypesMatchAll(e -> e.offsets.size() == 4);

        //then
        assertTrue(actual);
    }

    private boolean blockTypesMatchAll(Predicate<BlockType> predicate) {
        return Arrays.stream(BlockType.values()).allMatch(predicate);
    }
}
