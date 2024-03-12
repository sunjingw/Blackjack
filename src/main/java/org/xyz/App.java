package org.xyz;

import org.xyz.model.*;
import org.xyz.play.Game;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {



        Game game = new Game();
        Deck.createDeck();

        game.shuffleDeck(3);
        game.setPlayers(3);
        game.start();



    }

}
