package com.github.bohnman.squiggly.parser;

public class ParseContext {

    private final int line;
    private final int column;

    public ParseContext() {
        this(1, 1);
    }

    public ParseContext(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
