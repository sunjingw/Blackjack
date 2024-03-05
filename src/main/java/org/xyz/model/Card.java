package org.xyz.model;

import java.util.*;

public class Card {
    private CardValue cardValue;
    private int value;
    private Suit suit;

    public Card(CardValue value, Suit suit) {
        this.cardValue = value;
        this.suit = suit;
        this.value = value.getValue();
    }

    public CardValue getCardValue() {
        return cardValue;
    }
    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return cardValue + "(" + value + ") of " + suit;
    }
}
