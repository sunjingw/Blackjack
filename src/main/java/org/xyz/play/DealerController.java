package org.xyz.play;

import org.xyz.model.*;

import java.util.*;

public class DealerController {
    private Deck deck;
    private Dealer dealer;
    private Player player;

    public DealerController(Player player) {
        deck = Deck.getInstance();
        dealer = Dealer.getDealer();
        this.player = player;

    }

    public void shuffleDeck(int n) {
        deck.shuffleDeck(n);
    }

    public Card getCard() {
        return deck.getCard();
    }

    public Card dealToDealer() {
        Card card = deck.getCard();
        dealer.add(card);
        return card;
    }

    public Card dealToPlayer() {
        Card card = deck.getCard();
        player.add(card);
        return card;

    }

    public List<Card> getDealerCards() {
        return dealer.getCards();
    }

    public int getDealerCardValues() {
        return dealer.getCardValues();
    }

    public boolean doesDealerAceExist() {
        return dealer.containsAce();
    }

}
