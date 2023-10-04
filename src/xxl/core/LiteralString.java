package xxl.core;

public class LiteralString extends Literal {
    /** Content value, held as string */
    private String _value;

    /**
     * Grabs _value of the Literal (as Int)
     *
     * @returns the int value of the Literal
     */
    public int getIntValue() {
        return Integer.parseInt(_value);
    }

    /**
     * Grabs _value of the Literal (as String)
     *
     * @returns the String value of the Literal
     */
    public String getStringValue() {
        return _value;
    }
    
}