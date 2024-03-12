package org.xyz.model;

public class Card {
    private final CardValue cardValue;
    private final int value1;
    private int value2;
    private final Suit suit;

    public Card(CardValue value, Suit suit) {
        this.cardValue = value;
        this.suit = suit;

        if (value == CardValue.ACE) {
            value1 = 1;
            value2 = 11;
        } else
            value1 = value.getValue();

    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        if (value2 != 0) {
            return cardValue + "(" + value1 + " or " + value2 + ") of " + suit;
        }

        return cardValue + "(" + value1 + ") of " + suit;
    }
}
