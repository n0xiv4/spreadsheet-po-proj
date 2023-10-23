package xxl.core;

import java.util.Comparator;

/**
 * Comparator for sorting cells based on their content type.
 * Used mainly for comparing cells with functions.
 */
public class ContentSort implements Comparator<Cell> {
    
    /**
     * Compares two cells based on their content type.
     *
     * @param c1 The first cell to compare.
     * @param c2 The second cell to compare.
     * @return a negative integer, zero, or a positive integer as the first cell is less than, equal to, or greater
     *         than the second cell based on content type.
     */
    @Override
    public int compare(Cell c1, Cell c2) {
        return c1.getContentType().compareTo(c2.getContentType());
    }
}
