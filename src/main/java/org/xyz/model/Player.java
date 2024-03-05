package org.xyz.model;

import java.util.*;

public class Player {
    private List<Card> cards;
    private int cardValues;
    private boolean bust;

    public Player() {
        cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isBust() {
        return bust;
    }

    public void setBust(boolean bust) {
        this.bust = bust;
    }

    public int getCardValues() {
        if (cards.contains(CardValue.ACE)) {
            //TODO
        }
        return cards.stream().reduce(0, (x, y) -> x + y.getCardValue().getValue(), Integer::sum);
    }

}
