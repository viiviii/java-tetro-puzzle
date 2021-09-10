package tetro.offset;

import tetro.Translatable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Offsets implements Translatable<Offsets> {
    private final SortedSet<Offset> offsets;

    private Offsets(List<Offset> offsets) {
        this.offsets = new TreeSet(offsets);
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

    public boolean containsAll(Offsets other) {
        return this.offsets.containsAll(other.offsets);
    }

    @Override
    public Offsets translate(int translateX, int translateY) {
        final List<Offset> translateOffsets = this.stream()
                .map(e -> e.translate(translateX, translateY))
                .collect(Collectors.toList());
        return new Offsets(translateOffsets);
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
