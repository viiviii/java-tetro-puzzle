package tetro;

import java.util.Objects;
import java.util.Set;

public abstract class Offsets {
    private final Set<Offset> offsets;

    protected Offsets(Set<Offset> offsets) {
        this.offsets = immutableSet(offsets);
        validate(offsets);
    }

    protected abstract void validate(Set<Offset> offsets) throws IllegalArgumentException;

    public final Set<Offset> offsets() {
        return immutableSet(this.offsets);
    }

    private Set<Offset> immutableSet(Set<Offset> set) {
        return Set.copyOf(set);
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
        return "Shape{" + "offsets=" + offsets + '}';
    }
}
