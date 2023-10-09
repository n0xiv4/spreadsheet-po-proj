package xxl.core;

public abstract class BinaryFunction extends Function {
    protected Content[] _contents;

    BinaryFunction(Content a, Content b) {
        _contents = new Content[2];
        _contents[0] = a;
        _contents[1] = b;
    }

    // FIXME maybe toString implementation ?? otherwise its empty
}
