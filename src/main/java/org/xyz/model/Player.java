package org.xyz.model;

import java.util.*;

public class Player {
    private List<Card> cards;
    private int cardValues;
    private int playerNum;
    private boolean bust;
    private boolean win;

    public Player() {
        cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public int getPlayerNum() {
        return playerNum;
    }


    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
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
