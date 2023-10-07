package xxl.core;

public class LiteralInteger extends Literal {
    /** Content value, held as int */
    private int _value;

    // FIXME add javadoc
    LiteralInteger(int value) {
        _value = value;
    }

    /**
     * Grabs _value of the Literal (as Int)
     *
     * @returns the int value of the Literal
     */
    @Override
    public int getIntValue() {
        return _value;
    }

    /**
     * Grabs _value of the Literal (as String)
     *
     * @returns the String value of the Literal
     */
    @Override
    public String getStringValue() {
        return Integer.toString(_value);
    }

    @Override
    public String toString() {
        return "" + _value;
    }
    
}