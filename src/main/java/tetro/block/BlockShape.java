package tetro.block;

import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.*;

public final class BlockShape {
    public static final int SIZE = 4;

    private final BlockType type;
    private final int rotation;
    private final Offsets offsets;

    public BlockShape(BlockType type, int rotation, Offsets offsets) {
        validate(offsets);
        this.type = type;
        this.rotation = rotation;
        this.offsets = offsets;
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