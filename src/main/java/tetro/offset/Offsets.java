package tetro.offset;

import tetro.Rotatable;
import tetro.Translatable;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Offsets implements Translatable<Offsets>, Rotatable<Offsets> {
    private final SortedSet<Offset> offsets;

    private Offsets(List<Offset> offsets) {
        this.offsets = new TreeSet(offsets);
    }

    public static final Offsets from(Set<Offset> set) {
        return new Offsets(List.copyOf(set));
    }

    public static final Offsets of(Offset... offset) {
        return new Offsets(Arrays.asList(offset));
    }

    public int size() {
        return this.offsets.size();
    }

    public Offset first() {
        return this.offsets.first();
    }

    public Stream<Offset> stream() {
        return this.offsets.stream();
    }

    public boolean linked() {
        final BiPredicate<Offset, Offset> linked = (a, b) -> a.x == b.x || a.y == b.y;
        final Offset offset = this.stream()
                .reduce((a, b) -> linked.test(a, b) ? b : a)
                .get();
        return offset.equals(this.offsets.last());
    }

    @Override
    public Offsets translate(int translateX, int translateY) {
        final List<Offset> translateOffsets = this.stream()
                .map(e -> e.translate(translateX, translateY))
                .collect(Collectors.toList());
        return new Offsets(translateOffsets);
    }

    public Offsets translate(Offset translate) {
        return translate(translate.x, translate.y);
    }

    // TODO: 메서드명
    public Offsets translateBy(Offset origin) {
        final Offset first = this.first();
        final int translateX = origin.x - first.x;
        final int translateY = origin.y - first.y;
        return translate(translateX, translateY);
    }

    public Offsets translatePositive() {
        final int minX = min(e -> e.x);
        final int minY = min(e -> e.y);
        return translate(-minX, -minY);
    }

    // TODO: 이거 이름 뭐라그래
    private int min(Function<Offset, Integer> mapper) {
        return this.stream().map(e -> mapper.apply(e)).min(Integer::compare).get();
    }

    @Override
    public Offsets rotate() {
        final List<Offset> rotatedOffsets = this.stream()
                .map(e -> quarterTurn(e))
                .collect(Collectors.toList());
        return new Offsets(rotatedOffsets);
    }

    private Offset quarterTurn(Offset offset) {
        return Offset.of(-offset.y, offset.x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offsets)) return false;
        Offsets that = (Offsets) o;
        return this.offsets.equals(that.offsets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offsets);
    }

    @Override
    public String toString() {
        return "Offsets{" + offsets + '}';
    }
}
