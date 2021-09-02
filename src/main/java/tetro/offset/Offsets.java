package tetro.offset;

import java.util.*;

public final class Offsets {
    private final SortedSet<Offset> offsets;

    private Offsets(List<Offset> offsets) {
        this.offsets = new TreeSet(offsets);
    }

    public static final Offsets from(Set<Offset> set) {
        return new Offsets(List.copyOf(set));
    }

    public static final Offsets of(Offset offset) {
        return new Offsets(List.of(offset));
    }

    public static final Offsets of(Offset o1, Offset o2) {
        return new Offsets(List.of(o1, o2));
    }

    public static final Offsets of(Offset o1, Offset o2, Offset o3) {
        return new Offsets(List.of(o1, o2, o3));
    }

    public static final Offsets of(Offset o1, Offset o2, Offset o3, Offset o4) {
        return new Offsets(List.of(o1, o2, o3, o4));
    }

    public int size() {
        return this.offsets.size();
    }

    public Offset first() {
        return this.offsets.first();
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
