package tetro.block;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

// TODO: DB에서 가져올래
final class BlockShapeData {
    public final EnumMap<BlockType, List<List<Integer>>> map = new EnumMap(BlockType.class);

    public BlockShapeData() {
        map.put(BlockType.O, oBlockShapes());
        map.put(BlockType.I, iBlockShapes());
        map.put(BlockType.S, sBlockShapes());
        map.put(BlockType.Z, zBlockShapes());
        map.put(BlockType.T, tBlockShapes());
        map.put(BlockType.J, jBlockShapes());
        map.put(BlockType.L, lBlockShapes());
    }

    /*
        ■ ■ □ □
        ■ ■ □ □
        □ □ □ □
        □ □ □ □  */
    private List<List<Integer>> oBlockShapes() {
        final List list = new ArrayList();
        list.add(List.of(0, 0, 1, 0, 0, 1, 1, 1));
        return list;
    }

    /*
        ■ □ □ □    □ □ □ □
        ■ □ □ □    □ □ □ □
        ■ □ □ □    □ □ □ □
        ■ □ □ □    ■ ■ ■ ■  */
    private List<List<Integer>> iBlockShapes() {
        final List list = new ArrayList();
        list.add(List.of(0, 0, 0, 1, 0, 2, 0, 3));
        list.add(List.of(0, 3, 1, 3, 2, 3, 3, 3));
        return list;
    }

    /*
        ■ □ □ □    □ □ □ □
        ■ ■ □ □    □ ■ ■ □
        □ ■ □ □    ■ ■ □ □
        □ □ □ □    □ □ □ □  */
    private List<List<Integer>> sBlockShapes() {
        final List list = new ArrayList();
        list.add(List.of(0, 0, 0, 1, 1, 1, 1, 2));
        list.add(List.of(1, 1, 2, 1, 0, 2, 1, 2));
        return list;
    }

    /*
        □ ■ □ □    □ □ □ □
        ■ ■ □ □    ■ ■ □ □
        ■ □ □ □    □ ■ ■ □
        □ □ □ □    □ □ □ □  */
    private List<List<Integer>> zBlockShapes() {
        final List list = new ArrayList();
        list.add(List.of(1, 0, 0, 1, 1, 1, 0, 2));
        list.add(List.of(0, 1, 1, 1, 1, 2, 2, 2));
        return list;
    }

    /*
        □ ■ □ □    □ □ □ □    ■ □ □ □    □ □ □ □
        ■ ■ □ □    □ ■ □ □    ■ ■ □ □    ■ ■ ■ □
        □ ■ □ □    ■ ■ ■ □    ■ □ □ □    □ ■ □ □
        □ □ □ □    □ □ □ □    □ □ □ □    □ □ □ □  */
    private List<List<Integer>> tBlockShapes() {
        final List list = new ArrayList();
        list.add(List.of(1, 0, 0, 1, 1, 1, 1, 2));
        list.add(List.of(1, 1, 0, 2, 1, 2, 2, 2));
        list.add(List.of(0, 0, 0, 1, 1, 1, 0, 2));
        list.add(List.of(0, 1, 1, 1, 2, 1, 1, 2));
        return list;
    }

    /*
        ■ ■ □ □    □ □ □ □    □ ■ □ □    □ □ □ □
        ■ □ □ □    ■ ■ ■ □    □ ■ □ □    ■ □ □ □
        ■ □ □ □    □ □ ■ □    ■ ■ □ □    ■ ■ ■ □
        □ □ □ □    □ □ □ □    □ □ □ □    □ □ □ □  */
    private List<List<Integer>> jBlockShapes() {
        final List list = new ArrayList();
        list.add(List.of(0, 0, 1, 0, 0, 1, 0, 2));
        list.add(List.of(0, 1, 1, 1, 2, 1, 2, 2));
        list.add(List.of(1, 0, 1, 1, 0, 2, 1, 2));
        list.add(List.of(0, 1, 0, 2, 1, 2, 2, 2));
        return list;
    }

    /*
        ■ ■ □ □    □ □ □ □    ■ □ □ □    □ □ □ □
        □ ■ □ □    □ □ ■ □    ■ □ □ □    ■ ■ ■ □
        □ ■ □ □    ■ ■ ■ □    ■ ■ □ □    ■ □ □ □
        □ □ □ □    □ □ □ □    □ □ □ □    □ □ □ □  */
    private List<List<Integer>> lBlockShapes() {
        final List list = new ArrayList();
        list.add(List.of(0, 0, 1, 0, 1, 1, 1, 2));
        list.add(List.of(2, 1, 0, 2, 1, 2, 2, 2));
        list.add(List.of(0, 0, 0, 1, 0, 2, 1, 2));
        list.add(List.of(0, 1, 1, 1, 2, 1, 0, 2));
        return list;
    }
}
