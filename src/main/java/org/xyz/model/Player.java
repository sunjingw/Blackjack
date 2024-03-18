package org.xyz.model;

import java.util.*;

public class Player {
    private List<Card> cards;
    private String name;
    private boolean bust;
    private boolean win;

    public Player() {
        cards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        return cards.stream().reduce(0, (x, y) -> x + y.getCardValue().getValue(), Integer::sum);
    }

    public boolean doesPlayerAceExist() {
        return cards.stream().filter(e -> e.getCardValue() == CardValue.ACE).findAny().isPresent();
    }

}
