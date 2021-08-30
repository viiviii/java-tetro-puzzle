package tetro.block;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlockTypeTest {
    private final BlockType[] BLOCK_TYPES = BlockType.values();

    @Test
    public void numberOfRotatedShapes_범위는_1_이상_4_이하이다() throws Exception {
        //when
        boolean allMatched = Arrays.stream(BLOCK_TYPES).allMatch(e -> {
            int num = e.numberOfRotatedShapes;
            return num >= 1 && num <= 4;
        });

        //then
        assertTrue(allMatched);
    }

    @Test
    public void blockType_offsets의_size는_4여야_한다() throws Exception {
        //when
        boolean allMatched = Arrays.stream(BLOCK_TYPES)
                .allMatch(e -> e.offsets.size() == 4);

        //then
        assertTrue(allMatched);
    }
}
