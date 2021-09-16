package tetro.block;

import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.*;

final class BlockShape {
    public static final int SIZE = 4;
    private final Offsets offsets;

    private BlockShape(Offsets offsets) {
        validate(offsets);
        this.offsets = offsets;
    }

    private BlockShape(Offset o1, Offset o2, Offset o3, Offset o4) {
        this(Offsets.of(Set.of(o1, o2, o3, o4)));
    }

    public BlockShape(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        this(Offset.of(x1, y1), Offset.of(x2, y2), Offset.of(x3, y3), Offset.of(x4, y4));
    }

    private static void validate(Offsets offsets) throws IllegalArgumentException {
        if (offsets.size() == SIZE) return;
        throw new IllegalArgumentException("'offsets.size()' is not equal to BlockShape size: " +
                "<offsets size> " + offsets.size() + ", " +
                "<shape size> " + SIZE);
    }

    public Offsets offsets() {
        return this.offsets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockShape)) return false;
        BlockShape that = (BlockShape) o;
        return this.offsets.equals(that.offsets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offsets);
    }

    @Override
    public String toString() {
        return "BlockShape{" + offsets + '}';
    }
}