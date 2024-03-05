package org.xyz.model;

import java.util.*;

public class Dealer {
    private static Dealer instance;
    private int cardValues;
    private Dealer(){}
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
        return cardValues;
    }

    public void setCardValues(int cardValues) {
        this.cardValues = cardValues;
    }
}
