package org.xyz.model;

import java.util.*;

public class Deck {
    private Stack<Card> cards;

    public Deck() {
        cards = new Stack<>();
    }

    public void generateDeck() {
        CardValue[] cardValues = CardValue.values();
        Suit[] suits = Suit.values();

        for (int i = 0; i <cardValues.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                Card card = new Card(cardValues[i], suits[j]);
                addCard(card);
            }
        }
    }
    public void addCard(Card card) {
        cards.push(card);
    }

    public Card getCard() {
        return cards.pop();
    }

    public void shuffle(int n) {
        //shuffle n times
        for (int i = 0; i < n; i++) {
            Collections.shuffle(cards);
        }
    }
    public Stack<Card> getDeck() {
        return cards;
    }

    public void printDeck() {
        cards.stream().forEach(System.out::println);
    }


}
