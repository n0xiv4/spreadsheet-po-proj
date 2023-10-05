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

    // FIXME maybe toString here too ??
}