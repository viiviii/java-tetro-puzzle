package tetro;

import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.function.Predicate;

// TODO: 클래스명, 메서드명
public final class GridString {
    private GridString() {
    }

    /**
     * 디버깅용으로 {@link Offsets}를 시각적으로 보기 위해 만든 메서드 <br>
     * <p>
     * For example, <br>
     * - length: 4, offsets: (0, 0), (1, 0), (0, 1), (1, 1) <br>
     * ■ ■ □ □ <br>
     * ■ ■ □ □ <br>
     * □ □ □ □ <br>
     * □ □ □ □ <br>
     *
     * @param length  정사각형 그리드의 한쪽 길이
     * @param offsets 채운 네모로 표시할 offsets
     * @return 비어있거나 채워진 네모로 이루어진 문자열
     */
    public static String of(int length, Offsets offsets) {
        validate(length, offsets);
        final boolean[][] grid = grid(length, offsets);
        return toStringBy(grid);
    }

    private static void validate(int length, Offsets offsets) throws IllegalArgumentException {
        final int capacity = length * length;
        if (offsets.size() > capacity) {
            throw new IllegalArgumentException("'offsets.size()' is greater than capacity: " +
                    "<offsets size> " + offsets.size() + ", " +
                    "<capacity> " + capacity);
        }

        if (invalidOffsets(length, offsets)) {
            throw new IllegalArgumentException("Has invalid Offset in 'offsets': " +
                    "<offsets> " + offsets);
        }
    }

    private static boolean invalidOffsets(int length, Offsets offsets) {
        final Predicate<Offset> validRange = (e) -> (e.x >= 0 && e.x < length) && (e.y >= 0 && e.y < length);
        return offsets.stream().anyMatch(offset -> validRange.negate().test(offset));
    }

    private static boolean[][] grid(int length, Offsets offsets) {
        final boolean FILL = true;
        final boolean[][] grid = new boolean[length][length];
        offsets.stream().forEach(offset -> grid[offset.y][offset.x] = FILL);
        return grid;
    }

    private static String toStringBy(boolean[][] grid) {
        final StringBuilder sb = new StringBuilder();
        for (boolean[] row : grid) {
            sb.append(System.lineSeparator());
            for (boolean filled : row) {
                sb.append(String.format("%-2s", filled ? "■" : "□"));
            }
        }
        return sb.toString();
    }
}
