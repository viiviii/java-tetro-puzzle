package tetro.offset;

import java.util.*;

public class Offsets {
    private final Set<Offset> offsets;

    private Offsets(Set<Offset> offsets) {
        this.offsets = offsets;
    }

    // TODO
    protected Offsets(Offsets offsets) {
        this(offsets.toImmutableSet());
    }

    public static final Offsets from(Set<Offset> offsets) {
        return new Offsets(Set.copyOf(offsets));
    }

    // TODO
    public static final Offsets of(Offset o1) {
        final Set set = Set.of(o1);
        return new Offsets(set);
    }

    public static final Offsets of(Offset o1, Offset o2) {
        final Set set = Set.of(o1, o2);
        return new Offsets(set);
    }

    public static final Offsets of(Offset o1, Offset o2, Offset o3) {
        final Set set = Set.of(o1, o2, o3);
        return new Offsets(set);
    }

    public static final Offsets of(Offset o1, Offset o2, Offset o3, Offset o4) {
        final Set set = Set.of(o1, o2, o3, o4);
        return new Offsets(set);
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
