package tetro;

import tetro.cell.Cell;
import tetro.cell.Cells;

import java.util.function.Consumer;
import java.util.function.Predicate;

// TODO: 클래스명, 메서드명
public final class GridString {
    private GridString() {
    }

    /**
     * 디버깅용으로 {@link Cells}를 시각적으로 보기 위해 만든 메서드 <br>
     * <p>
     * For example, <br>
     * - length: 4, cells: (0, 0), (1, 0), (0, 1), (1, 1) <br>
     * ■ ■ □ □ <br>
     * ■ ■ □ □ <br>
     * □ □ □ □ <br>
     * □ □ □ □ <br>
     *
     * @param length      정사각형 그리드의 한쪽 길이
     * @param filledCells 채운 네모로 표시할 cells
     * @return 비어있거나 채워진 네모로 이루어진 문자열
     */
    public static String valueOf(int length, Cells filledCells) {
        validate(length, filledCells);
        final boolean[][] grid = grid(length, filledCells);
        return toStringBy(grid);
    }

    private static void validate(int length, Cells cells) throws IllegalArgumentException {
        final int capacity = length * length;
        if (cells.size() > capacity) {
            throw new IllegalArgumentException("'cells.size()' is greater than capacity: " +
                    "<cells size> " + cells.size() + ", " +
                    "<capacity> " + capacity);
        }

        if (invalidCells(length, cells)) {
            throw new IllegalArgumentException("Has invalid Cell in 'cells': " +
                    "<cells> " + cells);
        }
    }

    private static boolean invalidCells(int length, Cells cells) {
        final Predicate<Cell> validRange = cell -> (cell.x >= 0 && cell.x < length) && (cell.y >= 0 && cell.y < length);
        return cells.stream().anyMatch(cell -> validRange.negate().test(cell));
    }

    private static boolean[][] grid(int length, Cells filledCells) {
        final boolean[][] grid = new boolean[length][length];
        final Consumer<Cell> fillGrid = fillInCellOf(grid);
        filledCells.stream().forEach(cell -> fillGrid.accept(cell));
        return grid;
    }

    private static Consumer<Cell> fillInCellOf(boolean[][] grid) {
        final boolean FILL = true;
        return cell -> grid[cell.y][cell.x] = FILL;
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
