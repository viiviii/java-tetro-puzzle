package tetro.offset;

import tetro.Rotatable;
import tetro.Translatable;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Offsets implements Translatable<Offsets>, Rotatable<Offsets> {
    private final SortedSet<Offset> offsets;

    private Offsets(List<Offset> offsets) {
        this.offsets = new TreeSet(offsets);
    }

    public static final Offsets from(Set<Offset> set) {
        return new Offsets(List.copyOf(set));
    }

    public static final Offsets of(Offset... offset) {
        return new Offsets(Arrays.asList(offset));
    }

    public int size() {
        return this.offsets.size();
    }

    public Offset first() {
        return this.offsets.first();
    }

    public Stream<Offset> stream() {
        return this.offsets.stream();
    }

    @Override
    public Offsets translate(int translateX, int translateY) {
        final List<Offset> translateOffsets = this.stream()
                .map(e -> e.translate(translateX, translateY))
                .collect(Collectors.toList());
        return new Offsets(translateOffsets);
    }

    public Offsets translate(Offset translate) {
        return translate(translate.x, translate.y);
    }

    public Offsets translateBy(Offset origin) {
        final Offset first = this.first();
        final int translateX = origin.x - first.x;
        final int translateY = origin.y - first.y;
        return translate(translateX, translateY);
    }

    /**
     * 제1사분면의 원점(0,0)에 가깝게 이동
     */
    public Offsets translateToZeroOffset() {
        final int minX = min(e -> e.x);
        final int minY = min(e -> e.y);
        return translate(-minX, -minY);
    }

    // TODO: 이거 이름 뭐라그래
    private int min(Function<Offset, Integer> mapper) {
        return this.stream().map(e -> mapper.apply(e)).min(Integer::compare).get();
    }

    @Override
    public Offsets rotate(int rotation) {
        final BiFunction<Integer, Integer, Offset> fn = createRotateOffset(rotation);
        final List<Offset> rotatedOffsets = this.stream()
                .map(e -> fn.apply(e.x, e.y))
                .collect(Collectors.toList());
        return new Offsets(rotatedOffsets);
    }

    public Offsets rotate() {
        final int ONCE = 1;
        return rotate(ONCE);
    }

    // TODO: 얘네 이름 뭐라고 지어ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ나어퓨ㅓㄴ유팡뉴파ㅓㄴ유퍼ㅏㄴ유
    // 0: (x, y), 1: (-y, x), 2: (-x, -y), 3: (y, -x), 4(0): (x, y)
    private BiFunction<Integer, Integer, Offset> createRotateOffset(int rotation) {
        return curry(rotation, (x, y) -> Offset.of(x, y));
    }

    private BiFunction curry(int rotation, BiFunction<Integer, Integer, Offset> createOffset) {
        if (rotation == 0) return createOffset;
        return curry(rotation - 1, (x, y) -> createOffset.apply(-y, x));
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
