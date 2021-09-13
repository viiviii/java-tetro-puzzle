package tetro;

import tetro.block.FitBlock;
import tetro.offset.Offset;
import tetro.offset.Offsets;

import java.util.*;

// TODO: validate
public class EmptyGrids {
    public static final int CAPACITY = Board.LENGTH * Board.LENGTH;
    private final Offsets emptyGrids;

    private EmptyGrids(Offsets emptyGrids) {
        this.emptyGrids = emptyGrids;
    }

    public static EmptyGrids of(Collection<Offset> offsets) {
        return new EmptyGrids(Offsets.of(offsets));
    }

    public boolean isFull() {
        return emptyGrids.size() == 0;
    }

    public Offset first() {
        return emptyGrids.first();
    }

    public boolean canFit(FitBlock fitBlock) {
        return emptyGrids.containsAll(fitBlock.offsets());
    }

    public EmptyGrids fit(FitBlock fitBlock) {
        final Offsets remainingEmptyGrids = emptyGrids.difference(fitBlock.offsets());
        return new EmptyGrids(remainingEmptyGrids);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmptyGrids)) return false;
        EmptyGrids that = (EmptyGrids) o;
        return this.emptyGrids.equals(that.emptyGrids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emptyGrids);
    }

    @Override
    public String toString() {
        return "EmptyGrids{" + emptyGrids + '}';
    }
}
