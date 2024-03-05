package org.xyz.play;

import org.xyz.model.*;

import java.util.*;

public class DealerController {
    private Deck deck;
    private Dealer dealer;

    public DealerController() {
        deck = Deck.getInstance();
        dealer = Dealer.getDealer();
    }

    public void shuffleDeck(int n) {
        Deck.shuffleDeck(n);
    }

    public Card getCard() {
        return Deck.getCard();
    }




}
