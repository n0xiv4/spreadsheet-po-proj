package xxl.core;

import java.io.Serial;
import java.io.Serializable;

import java.util.List;

/**
 * The {@code CutBuffer} class represents a data structure for storing a collection of cells.
 * It is serializable, allowing its contents to be saved and restored.
 */
public class CutBuffer implements Serializable {
    
    /** The list of cells stored in the cut buffer. */
    private List<Cell> _storedCells;

    /** The serial version UID for object serialization. */
	@Serial
	private static final long serialVersionUID = 202310211241L;

    /**
     * Sets the contents of the cut buffer with the given list of cells.
     *
     * @param copiedCells The list of cells to be stored in the cut buffer.
     */
    void setCutBuffer(List<Cell> copiedCells) {
        _storedCells = copiedCells;
    }

    /**
     * Retrieves the list of cells stored in the cut buffer.
     *
     * @return The list of cells in the cut buffer.
     */
    List<Cell> getCells() {
        return _storedCells;
    }
    
}
