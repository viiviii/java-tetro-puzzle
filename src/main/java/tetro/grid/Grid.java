package tetro.grid;

public interface Grid<E extends Grid.Cells> {

    int length();

    E cells();

    interface Cells {

        int size();
    }
}
