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


        System.out.println("--------------------");
        System.out.println("Deal to dealer: ");
        dealerController.getDealerCards().stream().forEach(System.out::println);

        System.out.println("--------------------");
        System.out.println("Deal to player: ");
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

            System.out.println();
            System.out.println("Player Total: " + (playerAce ? playerCardValues + " or " + (playerCardValues+10) : playerCardValues));
            System.out.println("Dealer Total: " + (dealerAce ? dealerCardValues + " or " + (dealerCardValues+10) : dealerCardValues));
            System.out.println("--------------------");
            //player's turn
            if (turn < players-1) {

                while (playerCardValues < 21) {

                    System.out.println("Enter 1 to Hit or 2 to Stand");
                    int response = new java.util.Scanner(System.in).nextInt();

                    if (response == Choices.HIT.getChoice()) {

                        //hit will add another card to player's hand
                        System.out.println("Deal to player: " + dealerController.dealToPlayer());
                        playerCardValues = playerController.getPlayerCardValues();

                        System.out.println("Player Total: " + (playerAce ? playerCardValues + " or " + (playerCardValues+10) : playerCardValues));

                        if (playerCardValues > 21) {
                            System.out.println("BUST");
                            turn++;
                            gameFinished = true;
                            break;

                        } else {
                            //equal to or under 21
                            //account for Ace

                            if (playerAce) {

                                if (playerCardValues+10 == 21) {
                                    System.out.println("Player 21");
                                    turn++;
                                }

                            } else {

                                if (playerCardValues == 21) {
                                    System.out.println("Player 21");
                                    turn++;
                                }
                            }

                        }

                    } else {
                        //current player stand then next player's turn
                        turn++;
                        break;
                    }
                }

            } else {

                dealerCardValues = dealerController.getDealerCardValues();
                int val2 = dealerAce ? dealerCardValues+10 : dealerCardValues;

                //if over 21
                if (dealerCardValues > 21) {

                    System.out.println("Dealer BUST");
                    gameFinished = true;
                    break;

                } else {

                    //dealer under 17
                    while (dealerCardValues < 17 || val2 < 17) {

                        if (val2 == 21) {
                            System.out.println("Dealer Natural 21");
                            gameFinished = true;
                            break;
                        }

                        System.out.println("Deal to Dealer: " + dealerController.dealToDealer());
                        if (dealerController.doesDealerAceExist()) {
                            dealerAce = true;
                        }

                        dealerCardValues = dealerController.getDealerCardValues();
                        val2 = dealerAce ? dealerCardValues+10 : dealerCardValues;

                        System.out.println("Dealer Total: " + (dealerAce ? dealerCardValues + " or " + val2 : dealerCardValues));

                        //dealer stands if 17 or over
                        if ((dealerCardValues >= 17 && dealerCardValues <= 21) || (val2 >= 17 && val2 <= 21)) {
                            gameFinished = true;
                            break;
                        }

                    }

                    if (dealerCardValues == 21 || val2 == 21) {
                        System.out.println("Dealer 21");
                        gameFinished = true;
                        break;
                    } else {
                        System.out.println("Dealer stands");
                        gameFinished = true;
                        break;
                    }

                }

            }
        }

        System.out.println("------GAME OVER------");

        determineWinner(playerCardValues, dealerCardValues);

        System.out.println("------GAME OVER------");
    }

    public void determineWinner(int playerCardValues, int dealerCardValues) {

        System.out.println("Player: " + playerCardValues);
        System.out.println("Dealer: " + dealerCardValues);

        if (dealerCardValues > 21 && playerCardValues <= 21) {
            System.out.println("Player wins");
        } else if (playerCardValues > 21 && dealerCardValues <= 21) {
            System.out.println("Dealer wins");
        } else {

            if (playerCardValues == dealerCardValues) {
                System.out.println("Tied");
            } else if (dealerCardValues > playerCardValues) {
                System.out.println("Dealer wins");
            } else {
                System.out.println("Player wins");
            }
        }


    }
    public List<Card> getPlayerCards() {
        return playerController.getPlayerCards();
    }

    public List<Card> getDealerCards() {
        return dealerController.getDealerCards();
    }




}
