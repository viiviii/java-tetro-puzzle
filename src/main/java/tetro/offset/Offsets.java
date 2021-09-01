package tetro.offset;

import java.util.Objects;
import java.util.Set;

public class Offsets {
    private final Set<Offset> offsets;

    private Offsets(Set<Offset> offsets) {
        this.offsets = offsets;
    }

    public static final Offsets from(Set<Offset> offsets) {
        return new Offsets(Set.copyOf(offsets));
    }

    public final Set<Offset> toImmutableSet() {
        return Set.copyOf(this.offsets);
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
