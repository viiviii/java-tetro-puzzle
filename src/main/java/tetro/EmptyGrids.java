package tetro;

import tetro.block.FitBlock;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.*;

// TODO: validate
public class EmptyGrids {
    public static final int CAPACITY = Board.LENGTH * Board.LENGTH;
    private final Offsets offsets;

    private EmptyGrids(Offsets offsets) {
        this.offsets = offsets;
    }

    public static EmptyGrids of(Collection<Offset> offsets) {
        return new EmptyGrids(Offsets.of(offsets));
    }

    public boolean isFull() {
        return offsets.size() == 0;
    }

    public Offset first() {
        return offsets.first();
    }

    public boolean canFit(FitBlock fitBlock) {
        return offsets.containsAll(fitBlock.offsets());
    }

    public EmptyGrids fit(FitBlock fitBlock) {
        final Offsets remainingEmptyGrids = offsets.difference(fitBlock.offsets());
        return new EmptyGrids(remainingEmptyGrids);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmptyGrids)) return false;
        EmptyGrids that = (EmptyGrids) o;
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
