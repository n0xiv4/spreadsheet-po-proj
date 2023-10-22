package xxl.core;

import java.io.Serial;
import java.io.Serializable;

import java.util.List;

public class CutBuffer implements Serializable {
    
    /** FIXME */
    private List<Cell> _storedCells;

    /** The serial version UID for object serialization. */
	@Serial
	private static final long serialVersionUID = 202310211241L;

    void setCutBuffer(List<Cell> copiedCells) {
        _storedCells = copiedCells;
    }

    List<Cell> getCells() {
        return _storedCells;
    }
}
