package tetro;

import tetro.data.BlockShapesData;
import tetro.cell.Cell;
import tetro.cell.Cells;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Blocks {

    private Blocks() {
    }

    private static final Map<BlockType, Set<Block>> m = new EnumMap(BlockType.class);

    static {
        final BlockShapesData data = new BlockShapesData();
        data.forEach((type, value) -> m.put(type, blocks(type, value)));
    }

    private static Set<Block> blocks(BlockType type, int[][] data) {
        final int NUMBER_OF_SHAPES = data.length;
        return IntStream.range(0, NUMBER_OF_SHAPES)
                .mapToObj(rotation -> Block.of(cellsBy(data[rotation]), type, rotation))
                .collect(Collectors.toUnmodifiableSet());
    }

    private static Cells cellsBy(int[] data) {
        final int NUMBER_OF_CELL_ATTRIBUTE = 2;
        final List<Cell> list = new ArrayList();
        for (int i = 0; i < data.length; i += NUMBER_OF_CELL_ATTRIBUTE) {
            final int x = data[i];
            final int y = data[i + 1];
            list.add(Cell.of(x, y));
        }
        return Cells.of(list);
    }

    public static Set<Block> all() {
        return m.values().stream()
                .flatMap(e -> e.stream())
                .collect(Collectors.toUnmodifiableSet());
    }

    // todo
    public static Block get(BlockType blockType, int rotation) {
        final Set<Block> blocks = m.get(blockType);
        final int NUMBER_OF_ROTATION = blocks.size();
        final int COLLECT_ROTATION = rotation % NUMBER_OF_ROTATION;
        return blocks.stream()
                .filter(block -> block.rotation() == COLLECT_ROTATION)
                .findAny().get();
    }

    public static Set<Block> get(BlockType blockType) {
        return m.get(blockType);
    }

    // todo: 메서드명
    public static Block basic(BlockType blockType) {
        return get(blockType, 0);
    }
}
