package org.xyz.model;

import java.util.*;

public class Dealer {
    private static Dealer instance;
    private List<Card> cards;

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

        if (2 > 0) {


            //represent Ace as 1

            //represent Ace as 11
        }

        return cards.stream().reduce(0,(x, y) -> x + y.getCardValue().getValue(), Integer::sum);

    }

    public boolean containsAce() {

        if (cards.stream().filter(e -> e.getCardValue() == CardValue.ACE).count() > 0)
            return true;
        return false;
    }

    public void add(Card card) {
        cards.add(card);
    }
    public List<Card> getCards() {
        return cards;
    }
}
