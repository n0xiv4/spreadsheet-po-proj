package xxl.core;

public abstract class Literal implements Content {
    /** Holder for different type literals */
    Literal _literal;

    /**
     * Abstract getValue for child classes
     *
     * @returns a Literal version of the current
     * literal type (Integer or String)
     */
    Literal getValue() {
        return _literal;
    }

    // FIXME maybe toString here too ??
}