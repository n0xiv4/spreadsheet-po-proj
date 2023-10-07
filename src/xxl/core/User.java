package xxl.core;

import java.util.Collection;
import java.io.Serializable;
import java.util.ArrayList;

import xxl.core.exception.UnavailableNameException;

// FIXME import classes

public class User implements Serializable {
    private String _name;
    private Collection<Spreadsheet> _spreadsheets; 

    User(String name) {
        // FIXME package or public constructor?
        // FIXME {@link UnavailableNameException}
        _name = name;
        _spreadsheets = new ArrayList<Spreadsheet>();
    }

    public boolean equals(Object obj) {
        // FIXME
        return true;
    }

    public int getHashCode() {
        return _name.hashCode();
    }

    void linkSpreadsheet(Spreadsheet spreadsheet) {
        _spreadsheets.add(spreadsheet);
    }
}