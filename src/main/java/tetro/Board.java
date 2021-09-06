package tetro;

import tetro.offset.Offsets;

public final class Board {
    public static final int SIZE = 9;

    private final Offsets emptySpace;

    private Board(Offsets emptySpace) {
        this.emptySpace = emptySpace;
    }

    public static Board from(Offsets emptySpace) {
        // TODO
        return new Board(emptySpace);
    }

    public boolean isFull() {
        return emptySpace.size() == 0;
    }

    @Override
    public String toString() {
        return "Board{" +"emptySpace=" + emptySpace + '}';
    }
}
