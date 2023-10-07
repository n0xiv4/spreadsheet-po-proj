package xxl.core;

public class Reference extends Content {
    private Position _referencePosition;
    private Spreadsheet _spreadsheet;

    Reference(Position position, Spreadsheet spreadsheet) {
        _referencePosition = position;
        _spreadsheet = spreadsheet;
    }

    public String toString() {
        // FIXME not certain of implementation (YET)
        return "";
    }

    Literal getValue() {
        return _spreadsheet.getValueInPosition(_referencePosition);
    }
}