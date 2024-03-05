package org.xyz.play;

import org.xyz.model.*;

import java.util.List;
import java.util.Stack;

public class Game {

    private DealerController dealerController;
    private PlayerController playerController;
    private Player player;
    private boolean gameFinished;
    private int turn;
    private final int players = 2;

    public Game() {
        this.player = new Player();
        dealerController = new DealerController(player);
        playerController = new PlayerController(player);

    }
    public void shuffleDeck(int n) {
        dealerController.shuffleDeck(n);
    }

    public void start() {

        for (int i = 0; i < players*2; i++) {

            if (i % 2 == 0) {

                dealerController.dealToPlayer();
            } else {

                dealerController.dealToDealer();
            }
        }


        System.out.println("Deal to dealer: ");
        dealerController.getDealerCards().stream().forEach(System.out::println);


        System.out.println("Deal to player: ");
        playerController.getPlayerCards().stream().forEach(System.out::println);

        hitOrStand();

    }

    public void hitOrStand() {

        //loop if game is not finished
        while (!gameFinished) {

            //player's turn
            if (turn < players-1) {

                //fetch list of card values from player's hand
                int cardValues = playerController.getPlayerCardValues();

                while (cardValues < 21) {

                    System.out.println("Enter 1 to Hit or 2 to Stand");
                    int response = new java.util.Scanner(System.in).nextInt();
                    if (response == Choices.HIT.getChoice()) {

                        //hit will add another card to player's hand
                        System.out.println("Deal to player: " + dealerController.dealToPlayer());
                        cardValues = playerController.getPlayerCardValues();

                        if (cardValues > 21) {
                            System.out.println("Bust: " + cardValues);
                            turn++;
                            gameFinished = true;
                            break;

                        } else if (cardValues == 21) {
                            System.out.println("Winner 21");
                            turn++;
                        } else {
                            System.out.println("Total: " + cardValues);
                        }

                    } else {
                        //next player's turn
                        turn++;
                        break;
                    }
                }

            } else {

                int cardValues = dealerController.getDealerCardValues();

                while (cardValues < 17) {
                    System.out.println("Deal to dealer: " + dealerController.dealToDealer());
                    cardValues = dealerController.getDealerCardValues();

                    if (cardValues > 21) {
                        System.out.println("Dealer bust: " + cardValues);
                        gameFinished = true;
                        break;

                    } else if (cardValues == 21) {
                        System.out.println("Dealer blackjack");
                        gameFinished = true;
                        break;

                    } else if (cardValues >= 17) {
                        System.out.println("Dealer stands at: " + cardValues);
                        gameFinished = true;
                    }

                }
            }
        }

    }

    public void determineWinner() {

        int playerCardValues = playerController.getPlayerCardValues();
        int dealerCardValues = dealerController.getDealerCardValues();
        System.out.println("Player: " + playerCardValues);
        System.out.println("Dealer: " + dealerCardValues);

        if ((playerCardValues <= 21) && (playerCardValues > dealerCardValues)) {
            System.out.println("Player wins");
        } else if (playerCardValues == dealerCardValues && (playerCardValues <= 21)) {
            System.out.println("Tie");
        }

    }
    public List<Card> getPlayerCards() {
        return playerController.getPlayerCards();
    }

    public List<Card> getDealerCards() {
        return dealerController.getDealerCards();
    }




}
