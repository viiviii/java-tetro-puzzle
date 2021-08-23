import java.util.*;

// TODO: ClassCastException를 던져선 안됨 -> e1.compareTo(e2) or comparator.compare(e1, e2)
public final class EmptySpace {
    private final SortedSet<Offset> offsets;

    public EmptySpace(Offset... offsets) {
        this.offsets = new TreeSet<>(Arrays.asList(offsets));
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
