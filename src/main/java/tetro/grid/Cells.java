package tetro.grid;

import tetro.Offset;
import tetro.Translatable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Cells implements Translatable<Cells> {
    public static final Cells EMPTY = Cells.of(Collections.EMPTY_LIST);

    private final SortedSet<Cell> cells;

    private Cells(Collection<Cell> cells) {
        this.cells = new TreeSet(cells);
    }

    public static final Cells of(Collection<Cell> cells) {
        return new Cells(Collections.unmodifiableCollection(cells));
    }

    public static final Cells of(Cell cell) {
        return new Cells(Set.of(cell));
    }

    public static final Cells of(Cell... cells) {
        return new Cells(Arrays.asList(cells));
    }

    public Cells union(Cells other) {
        final Set<Cell> unionCells = Stream.concat(this.stream(), other.stream())
                .collect(Collectors.toUnmodifiableSet());
        return Cells.of(unionCells);
    }

    public Cells difference(Cells other) {
        final Set<Cell> differenceCells = this.stream()
                .filter(cell -> !other.contains(cell))
                .collect(Collectors.toUnmodifiableSet());
        return Cells.of(differenceCells);
    }

    public int size() {
        return this.cells.size();
    }

    public Offset first() {
        final Cell first = this.cells.first();
        return new Offset(first.x, first.y);
    }

    // TODO: 제거YN
    public Stream<Cell> stream() {
        return this.cells.stream();
    }

    public boolean contains(Cell cell) {
        return this.cells.contains(cell);
    }

    public boolean containsAll(Cells other) {
        return this.cells.containsAll(other.cells);
    }

    @Override
    public Cells translateBy(int translateX, int translateY) {
        final List<Cell> translateCells = this.stream()
                .map(cell -> cell.translateBy(translateX, translateY))
                .collect(Collectors.toList());
        return new Cells(translateCells);
    }

    /**
     * 첫번째 셀을 기준으로 Cell 집합을 offset 위치로 이동
     */
    public Cells translateTo(Offset offset) {
        final Offset distance = offset.minus(this.first());
        return translateBy(distance.x, distance.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cells)) return false;
        Cells that = (Cells) o;
        return this.cells.equals(that.cells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cells);
    }

    @Override
    public String toString() {
        return "Cells{" + cells + '}';
    }
}
