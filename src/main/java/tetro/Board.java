package tetro;

import tetro.offset.Offsets;

public final class Board {
    public static final int SIZE = 9;

    private final Offsets holes;

    private Board(Offsets holes) {
        this.holes = holes;
    }

    public static Board from(Offsets holes) {
        // TODO
        return new Board(holes);
    }

    public boolean filled() {
        return holes.size() == 0;
    }

    @Override
    public String toString() {
        return "Board{" +"holes=" + holes + '}';
    }
}
