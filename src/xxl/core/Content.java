package xxl.core;

import java.io.Serializable;

public abstract class Content implements Serializable {
    abstract Literal getValue();
    abstract public String toString();
}
