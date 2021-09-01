package tetro;

import tetro.offset.Offset;

public final class Board {
    public static final int LENGTH = 9;
    public static final Board UNFIT = new Board(new EmptySpace(Offset.INVALID));

    private final EmptySpace emptySpace;

    private Board(EmptySpace emptySpace) {
        this.emptySpace = emptySpace;
    }

    public static Board from(EmptySpace emptySpace) {
        if (emptySpace == null) return UNFIT;
        return new Board(emptySpace);
    }

    public boolean isFull() {
        return emptySpace.size() == 0;
    }

    @Override
    public String toString() {
        return "Board{" +
                "emptySpace=" + emptySpace +
                '}';
    }
}
