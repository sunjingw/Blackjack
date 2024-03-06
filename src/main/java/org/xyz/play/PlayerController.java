package org.xyz.play;

import org.xyz.model.Card;
import org.xyz.model.Player;


import java.util.*;

public class PlayerController {

    private Player player;
    private List<Player> players;

    public PlayerController(Player player) {
        this.player = player;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Card> getPlayerCards() {
        return player.getCards();
    }

    public int getPlayerCardValues() {
        return player.getCardValues();
    }

    public boolean doesPlayerAceExist() {
       return false;
    }
    public void hit() {

    }

    public void stand() {

    }
}
