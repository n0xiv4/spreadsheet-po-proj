package xxl.core;

public abstract class Function extends Content {
    protected abstract Literal computeValue();
    public abstract String toString();

    @Override
    public Literal getValue() {
        return computeValue();
    }
}
