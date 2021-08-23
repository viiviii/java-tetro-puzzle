import java.util.*;

// 1. 모든 element는 comparable 해야 함
// 2. ClassCastException를 던져선 안됨 -> e1.compareTo(e2) or comparator.compare(e1, e2)
// 3. 정확한 consistent with equals 구현
//      - e1.compareTo(e2) == 0와  e1.equals(e2)는 같은 boolean 값을 가져야 함
//      - e.equals(null) 값이 false여도 e.compareTo(null)은 NullPointerException을 던져야 함
// 4. 일반적인 SortedSet의 구현체는 4개의 standart 생성자를 제공해야 함
public class EmptySpace {
    private final SortedSet<Offset> offsets;
    public EmptySpace(Offset... offsets) {
        this.offsets = new TreeSet<>(Arrays.asList(offsets));
    }

    public int size() {
        return offsets.size();
    }
}
