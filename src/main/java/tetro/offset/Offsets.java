package tetro.offset;

import tetro.Rotatable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Offsets implements Rotatable<Offsets> {
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

    @Override
    public Offsets rotate() {
        final List<Offset> rotatedOffsets = this.offsets.stream()
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
