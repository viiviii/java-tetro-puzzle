import java.util.*;

// 2. ClassCastException를 던져선 안됨 -> e1.compareTo(e2) or comparator.compare(e1, e2)
public class EmptySpace {
    private final SortedSet<Offset> offsets;

    public EmptySpace(Offset... offsets) {
        this.offsets = new TreeSet<>(Arrays.asList(offsets));
    }

    public int size() {
        return offsets.size();
    }
}
