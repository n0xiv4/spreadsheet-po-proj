package xxl.core;

public abstract class Literal extends Content {
    /**
     * Abstract getValue for child classes
     *
     * @returns a Literal version of the current
     * literal type (Integer or String)
     */
    @Override
    Literal getValue() {
        return this;
    }

    abstract public int getIntValue();
    abstract public String getStringValue();

    // FIXME maybe toString here too ??

    // OPERATIONS WITH LITERALS (MAKING LITERAL A USABLE TYPE FOR
    // ARITHMETIC OPERATIONS)

    static Literal add(Literal a, Literal b) {
        return new LiteralInteger(a.getIntValue() + b.getIntValue());
    }
}