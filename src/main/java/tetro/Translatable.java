package tetro;

public interface Translatable<T> {

    /**
     * x, y만큼 이동
     */
    T translateBy(int translateX, int translateY);
}
