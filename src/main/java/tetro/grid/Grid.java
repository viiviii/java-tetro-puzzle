package tetro.grid;

public interface Grid<E extends Grid.Cells> {

    int length();

    E cells();

    interface Cells<EE> {
        enum CellState {empty, fill}

        CellState state();

        int size();

        EE first();
    }
}
