package tetro;

import tetro.block.Block;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.*;

// TODO: validate
public class EmptyGrid {
    public static final int CAPACITY = Board.LENGTH * Board.LENGTH;
    private final Offsets offsets;

    private EmptyGrid(Offsets offsets) {
        this.offsets = offsets;
    }

    public static EmptyGrid of(Collection<Offset> offsets) {
        return new EmptyGrid(Offsets.of(offsets));
    }

    public boolean isFull() {
        return offsets.size() == 0;
    }

    public Offset first() {
        return offsets.first();
    }

    public boolean canFit(Block block) {
        return offsets.containsAll(block.offsets());
    }

    public EmptyGrid fit(Block block) {
        final Offsets remainingEmptyGrids = offsets.difference(block.offsets());
        return new EmptyGrid(remainingEmptyGrids);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmptyGrid)) return false;
        EmptyGrid that = (EmptyGrid) o;
        return this.offsets.equals(that.offsets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offsets);
    }

    @Override
    public String toString() {
        return "EmptyGrids{" + offsets + '}';
    }
}
