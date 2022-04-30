package com.example.commonlogic.domain.operation;

public enum OperationType {
    ADD("add"),
    SUBTRACT("sub"),
    DIVIDE("div"),
    MULTIPLY("mult");

    private String name;

    OperationType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
