package tetro;

public final class Board {
    public static final Board UNFIT = new Board(new EmptySpace(new Offset(-99999, -99999)));
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
}
