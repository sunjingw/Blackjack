package org.xyz.model;

import java.util.*;

public class Deck {
    private static Stack<Card> cards;
    private static Deck instance;
    private static int count;

    private Deck() {
        cards = new Stack<>();
    }

    public static void addCard(Card card) {
        cards.push(card);
        count++;
    }

    public Card getCard() {
        count--;
        return cards.pop();
    }
    public void printDeck() {
        cards.stream().forEach(System.out::println);
    }
    public void shuffleDeck(int n) {
        //shuffle n times
        for (int i = 0; i < n; i++) {
            Collections.shuffle(cards);
        }
    }

    public static void createDeck() {

        CardValue[] cardValues = CardValue.values();
        Suit[] suits = Suit.values();

        for (int i = 0; i < cardValues.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                Card card = new Card(cardValues[i], suits[j]);
                addCard(card);
            }
        }
    }

    public Stack<Card> getDeck() {
        return cards;
    }
    public static Deck getInstance() {
        var result = instance;
        if (result == null) {
            result = new Deck();
            instance = result;
            return result;
        }
        return result;
    }

    public void destroyInstance() {
        instance = null;
    }

    public int getCount() {
        return count;
    }

}
