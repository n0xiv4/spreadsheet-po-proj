package xxl.core;

public class Reference extends Content {
    private Cell _referencedCell;

    Reference(Cell cell) {
        _referencedCell = cell;
    }

    Reference(int row, int column) {
        _referencedCell = cell;
    }

    public String toString() {
        // FIXME not certain of implementation (YET)
        return _referencedCell.toString();
    }

    Literal getValue() {
        return _referencedCell.getValue();
    }
}
