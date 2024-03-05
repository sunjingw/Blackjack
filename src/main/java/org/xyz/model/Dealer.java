package org.xyz.model;

import java.util.*;

public class Dealer {
    private static Dealer instance;
    private List<Card> cards;
    private int cardValues;
    private Dealer(){
        cards = new ArrayList<>();
    }

    public static Dealer getDealer() {
        var result = instance;
        if (result == null) {
            result = new Dealer();
            instance = result;
            return result;
        }
        return result;
    }

    public int getCardValues() {
        if (cards.contains(CardValue.ACE)) {
            //TODO
        }

        return cards.stream().reduce(0,(x, y) -> x + y.getCardValue().getValue(), Integer::sum);

    }

    public void setCardValues(int cardValues) {
        this.cardValues = cardValues;
    }

    public void add(Card card) {
        cards.add(card);
    }
    public List<Card> getCards() {
        return cards;
    }
}
