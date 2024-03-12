package org.xyz.play;

import org.xyz.model.Card;
import org.xyz.model.Player;


import java.util.*;

public class PlayerController {

    private Player player;
    private List<Player> players;

    public PlayerController(List<Player> player) {
        this.players = player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Card> getPlayerCards() {
        return player.getCards();
    }

    public List<Player> getPlayers() {
        return players;
    }

}
