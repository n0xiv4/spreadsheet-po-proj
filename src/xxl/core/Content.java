package xxl.core;

import java.io.Serializable;

public abstract class Content implements Serializable {

    abstract Literal getValue();

    abstract public String toString();
    // FIXME other implementations. Decide how to implement
    // toString, if to use getInt and getString or else
}
