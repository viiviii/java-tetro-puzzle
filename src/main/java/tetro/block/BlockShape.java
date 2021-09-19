package tetro.block;

import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.*;

public final class BlockShape {
    public static final int SIZE = 4;

    private final BlockType type;
    private final int rotation;
    private final Offsets offsets;

    private BlockShape(BlockType type, int rotation, Offsets offsets) {
        validate(offsets);
        this.type = type;
        this.rotation = rotation;
        this.offsets = offsets;
    }

    private BlockShape(BlockType type, int rotation, Offset o1, Offset o2, Offset o3, Offset o4) {
        this(type, rotation, Offsets.of(Set.of(o1, o2, o3, o4)));
    }

    // TODO x1, x2, ...
    public BlockShape(BlockType type, int rotation, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        this(type, rotation, Offset.of(x1, y1), Offset.of(x2, y2), Offset.of(x3, y3), Offset.of(x4, y4));
    }

    private static void validate(Offsets offsets) throws IllegalArgumentException {
        if (offsets.size() == SIZE) return;
        throw new IllegalArgumentException("'offsets.size()' is not equal to BlockShape size: " +
                "<offsets size> " + offsets.size() + ", " +
                "<shape size> " + SIZE);
    }

    public BlockType type() {
        return this.type;
    }

    public int rotation() {
        return this.rotation;
    }

    public Offsets offsets() {
        return this.offsets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockShape)) return false;
        BlockShape that = (BlockShape) o;
        return this.rotation == that.rotation
                && this.type == that.type
                && this.offsets.equals(that.offsets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, rotation, offsets);
    }

    @Override
    public String toString() {
        return "BlockShape{" +
                "type=" + type +
                ", rotation=" + rotation +
                ", offsets=" + offsets +
                '}';
    }
}