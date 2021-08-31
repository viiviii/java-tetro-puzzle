package tetro.shape;

import tetro.Offset;

import java.util.Objects;
import java.util.Set;

public abstract class Shape {
    private final Set<Offset> offsets;

    protected Shape(Set<Offset> offsets) {
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
        if (!(o instanceof Shape)) return false;
        Shape that = (Shape) o;
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
