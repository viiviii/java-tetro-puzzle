package tetro;

import java.util.*;

// TODO: ClassCastException를 던져선 안됨 -> e1.compareTo(e2) or comparator.compare(e1, e2)
public final class EmptySpace {
    private final SortedSet<Offset> offsets;

    public EmptySpace(Offset... offsets) {
        final List list = (offsets == null || offsets.length == 0
                ? List.of()
                : Arrays.asList(offsets));
        this.offsets = new TreeSet<>(list);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmptySpace)) return false;
        EmptySpace that = (EmptySpace) o;
        return this.offsets.equals(that.offsets);
    }

    @Override
    public String toString() {
        return "EmptySpace{" + "offsets=" + offsets + '}';
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
}
