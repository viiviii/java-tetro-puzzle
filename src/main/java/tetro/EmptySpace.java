package tetro;

import java.util.*;

public final class EmptySpace {
    private final SortedSet<Offset> offsets;

    public EmptySpace(Offset... offsets) {
        final List list = (offsets == null || offsets.length == 0
                ? List.of()
                : Arrays.asList(offsets));
        this.offsets = new TreeSet<>(list);
    }

    public int size() {
        return this.offsets.size();
    }

    public boolean containsAll(Collection<Offset> others) {
        return offsets.containsAll(others);
    }

    public Offset first() {
        return offsets.first();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmptySpace)) return false;
        EmptySpace that = (EmptySpace) o;
        return this.offsets.equals(that.offsets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offsets);
    }

    @Override
    public String toString() {
        return "EmptySpace{" + "offsets=" + offsets + '}';
    }
}
