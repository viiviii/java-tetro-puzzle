package tetro.offset;

import java.util.*;

// TODO: AbstractSet 상속 YN?
public final class Offsets {
    private final Set<Offset> offsets;

    private Offsets(Set<Offset> offsets) {
        this.offsets = offsets;
    }

    public static final Offsets from(Set<Offset> set) {
        return new Offsets(Set.copyOf(set));
    }

    public static final Offsets of(Offset offset) {
        return new Offsets(Set.of(offset));
    }

    public static final Offsets of(Offset o1, Offset o2) {
        return new Offsets(Set.of(o1, o2));
    }

    public static final Offsets of(Offset o1, Offset o2, Offset o3) {
        return new Offsets(Set.of(o1, o2, o3));
    }

    public static final Offsets of(Offset o1, Offset o2, Offset o3, Offset o4) {
        return new Offsets(Set.of(o1, o2, o3, o4));
    }

    public final Set<Offset> toImmutableSet() {
        return Set.copyOf(this.offsets);
    }

    public int size() {
        return this.offsets.size();
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
