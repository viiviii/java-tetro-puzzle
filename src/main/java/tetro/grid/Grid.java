package tetro.grid;

public interface Grid<E extends Grid.Cells> {

    int length();

    E cells();

    interface Cells<EE> {
        enum CellState {BLANK, NON_BLANK}

        CellState state();

        int size();

        EE first();
    }
}
