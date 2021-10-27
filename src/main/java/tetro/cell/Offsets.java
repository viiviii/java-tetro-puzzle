package tetro.cell;

import tetro.Offset;
import tetro.Translatable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Offsets implements Translatable<Offsets> {
    public static final Offsets EMPTY = Offsets.of(Collections.EMPTY_LIST);

    private final SortedSet<Cell> cells;

    private Offsets(Collection<Cell> cells) {
        this.cells = new TreeSet(cells);
    }

    public static final Offsets of(Collection<Cell> cells) {
        return new Offsets(Collections.unmodifiableCollection(cells));
    }

    public static final Offsets of(Cell cell) {
        return new Offsets(Set.of(cell));
    }

    public static final Offsets of(Cell... cells) {
        return new Offsets(Arrays.asList(cells));
    }

    public Offsets union(Offsets other) {
        final Set<Cell> unionCells = Stream.concat(this.stream(), other.stream())
                .collect(Collectors.toUnmodifiableSet());
        return Offsets.of(unionCells);
    }

    public Offsets difference(Offsets other) {
        final Set<Cell> differenceCells = this.stream()
                .filter(cell -> !other.contains(cell))
                .collect(Collectors.toUnmodifiableSet());
        return Offsets.of(differenceCells);
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

    public boolean containsAll(Offsets other) {
        return this.cells.containsAll(other.cells);
    }

    @Override
    public Offsets translateBy(int translateX, int translateY) {
        final List<Cell> translateCells = this.stream()
                .map(cell -> cell.translateBy(translateX, translateY))
                .collect(Collectors.toList());
        return new Offsets(translateCells);
    }

    /**
     * 첫번째 셀을 기준으로 Cell 집합을 offset 위치로 이동
     */
    public Offsets translateTo(Offset offset) {
        final Offset distance = offset.minus(this.first());
        return translateBy(distance.x, distance.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offsets)) return false;
        Offsets that = (Offsets) o;
        return this.cells.equals(that.cells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cells);
    }

    @Override
    public String toString() {
        return "Offsets{" + cells + '}';
    }
}
