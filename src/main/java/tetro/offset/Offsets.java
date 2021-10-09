package tetro.offset;

import tetro.Translatable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Offsets implements Translatable<Offsets> {
    public static final Offsets EMPTY = Offsets.of(Collections.EMPTY_LIST);

    private final SortedSet<Offset> offsets;

    private Offsets(Collection<Offset> offsets) {
        this.offsets = new TreeSet(offsets);
    }

    public static final Offsets of(Collection<Offset> offsets) {
        return new Offsets(Collections.unmodifiableCollection(offsets));
    }

    public static final Offsets of(Offset offset) {
        return new Offsets(Set.of(offset));
    }

    public static final Offsets of(Offset... offsets) {
        return new Offsets(Arrays.asList(offsets));
    }

    public int size() {
        return this.offsets.size();
    }

    public Offset first() {
        return this.offsets.first();
    }

    // TODO: 제거YN
    public Stream<Offset> stream() {
        return this.offsets.stream();
    }

    public boolean containsAll(Offsets other) {
        return this.offsets.containsAll(other.offsets);
    }

    public Offsets add(Offsets other) {
        final Offsets newInstance = Offsets.of(this.offsets);
        newInstance.offsets.addAll(other.offsets);
        return newInstance;
    }

    public Offsets difference(Offsets other) {
        final Offsets newInstance = Offsets.of(this.offsets);
        newInstance.offsets.removeAll(other.offsets);
        return newInstance;
    }

    // TODO: 리팩토링
    @Override
    public Offsets translate(int translateX, int translateY) {
        final List<Offset> translateOffsets = this.stream()
                .map(e -> e.translate(translateX, translateY))
                .collect(Collectors.toList());
        return new Offsets(translateOffsets);
    }

    /**
     * offset 위치로 이동
     */
    public Offsets translateTo(Offset offset) {
        final Offset difference = offset.difference(this.first());
        return translate(difference.x, difference.y);
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
