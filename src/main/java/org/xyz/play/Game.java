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
        System.out.println("--------------------");
        dealerController.getDealerCards().stream().forEach(System.out::println);
        System.out.println("--------------------");

        System.out.println("Deal to player: ");
        System.out.println("--------------------");
        playerController.getPlayerCards().stream().forEach(System.out::println);
        System.out.println("--------------------");

        hitOrStand();

    }

    public void hitOrStand() {

        int playerCardValues = playerController.getPlayerCardValues();
        int dealerCardValues = dealerController.getDealerCardValues();
        boolean playerAce = playerController.doesPlayerAceExist();
        boolean dealerAce = dealerController.doesDealerAceExist();

        //loop if game is not finished
        while (!gameFinished) {

            System.out.println("Player Total: " + (playerAce ? playerCardValues + " or " + (playerCardValues+10) : playerCardValues));
            System.out.println("Dealer Total: " + (dealerAce ? dealerCardValues + " or " + (dealerCardValues+10) : dealerCardValues));


            //player's turn
            if (turn < players-1) {

                while (playerCardValues < 21) {

                    System.out.println("Enter 1 to Hit or 2 to Stand");
                    int response = new java.util.Scanner(System.in).nextInt();
                    if (response == Choices.HIT.getChoice()) {

                        //hit will add another card to player's hand
                        System.out.println("Deal to player: " + dealerController.dealToPlayer());
                        playerCardValues = playerController.getPlayerCardValues();

                        if (playerCardValues > 21) {
                            System.out.println("Bust: " + playerCardValues);
                            turn++;
                            gameFinished = true;
                            break;

                        } else if (playerCardValues == 21) {
                            System.out.println("Winner 21");
                            turn++;
                        } else {

                            if (playerAce)
                                System.out.println("Total: " + playerCardValues + " or " + (playerCardValues+10));
                            else
                                System.out.println("Total: " + playerCardValues);
                        }

                    } else {
                        //next player's turn
                        turn++;
                        break;
                    }
                }

            } else {

                dealerCardValues = dealerController.getDealerCardValues();
                int val1 = dealerCardValues;
                int val2 = dealerCardValues + 10;

                if (dealerAce) {

                    if (val2 >=17 && val2 <= 21) {
                        dealerCardValues = val2;
                    }
                } else {
                    dealerCardValues = dealerController.getDealerCardValues();
                }


                if (dealerCardValues >= 17 && dealerCardValues < 21) {
                    System.out.println("Dealer stands at: " + dealerCardValues);
                    gameFinished = true;
                }

                while (dealerCardValues < 17) {
                    System.out.println("Deal to dealer: " + dealerController.dealToDealer());
                    dealerCardValues = dealerController.getDealerCardValues();

                    if (dealerCardValues > 21) {
                        System.out.println("Dealer bust: " + dealerCardValues);
                        gameFinished = true;
                        break;

                    } else if (dealerCardValues == 21) {
                        System.out.println("Dealer blackjack");
                        gameFinished = true;
                        break;

                    }
                }

            }
        }

        if (playerCardValues > 21)
            System.out.println("Dealer wins");
        else if (dealerCardValues > 21)
            System.out.println("Player wins");
        else
            determineWinner(playerCardValues, dealerCardValues);

    }

    public void determineWinner(int playerCardValues, int dealerCardValues) {

        System.out.println("Player: " + playerCardValues);
        System.out.println("Dealer: " + dealerCardValues);

        if (playerCardValues > dealerCardValues) {
            System.out.println("Player wins");
        } else if (playerCardValues == dealerCardValues) {
            System.out.println("Tie");
        } else {
            System.out.println("Dealer wins");
        }

    }
    public List<Card> getPlayerCards() {
        return playerController.getPlayerCards();
    }

    public List<Card> getDealerCards() {
        return dealerController.getDealerCards();
    }




}
