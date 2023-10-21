package xxl.core;

import java.io.Serializable;
import java.util.List;

public class CutBuffer implements Serializable {
    
    private List<Cell> _storedCells;

    void setCutBuffer(List<Cell> copiedCells) {
        _storedCells = copiedCells;
    }

    List<Cell> getCells() {
        return _storedCells;
    }
}
