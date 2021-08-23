package tetro.exception;

import tetro.Offset;

public class OffsetOutOfBoundsException extends RuntimeException {

    public OffsetOutOfBoundsException(int x, int y) {
        super("Offset 범위를 벗어남. " +
                "input: x = " + x + ", y = " + y + ", " +
                "Bounds: 0 ~ " + (Offset.LENGTH - 1));
    }
}
