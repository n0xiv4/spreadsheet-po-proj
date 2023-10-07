package xxl.core;

public abstract class Content {

    abstract Literal getValue();

    abstract public String toString();
    // FIXME other implementations. Decide how to implement
    // toString, if to use getInt and getString or else
}
