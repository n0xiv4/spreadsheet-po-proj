package xxl.core;

public class None extends Content {
	public String toString() {
		return "";
	}

	Literal getValue() {
		return new LiteralString("");
	}
}
