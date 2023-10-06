package xxl.core;

public class LiteralString extends Literal {
    /** Content value, held as string */
    private String _value;

    // FIXME add javadoc
    LiteralString(String value) {
        _value = value;
    }

    /**
     * Grabs _value of the Literal (as Int)
     *
     * @returns the int value of the Literal
     */
    @Override
    public int getIntValue() {
        return Integer.parseInt(_value);
    }

    /**
     * Grabs _value of the Literal (as String)
     *
     * @returns the String value of the Literal
     */
    @Override
    public String getStringValue() {
        return _value;
    }
    
}