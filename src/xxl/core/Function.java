package xxl.core;

public abstract class Function extends Content {
    // FIXME name attribute ?????????????
    // FIXME still not getting dif between "getValue" and "compute"... what are both for?

    protected abstract Literal computeValue();

    public abstract String toString();
}
