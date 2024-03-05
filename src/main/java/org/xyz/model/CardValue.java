package org.xyz.model;

public enum CardValue {

    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    final int value;
    private CardValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
