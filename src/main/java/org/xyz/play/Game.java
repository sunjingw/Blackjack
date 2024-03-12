package org.xyz.play;

import org.xyz.model.Card;
import org.xyz.model.Choices;
import org.xyz.model.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private DealerController dealerController;
    private PlayerController playerController;
    private boolean gameFinished;
    private int turn;
    private List<Player> players;

    public Game() {
        players = new ArrayList<>();
        dealerController = new DealerController(players);
        playerController = new PlayerController(players);

    }

    public void setPlayers(int players) {
        for (int i = 0; i < players; i++) {
            Player player = new Player();
            player.setPlayerNum(i);
            player.setBust(false);
            this.players.add(player);
        }
    }

    public void shuffleDeck(int n) {
        dealerController.shuffleDeck(n);
    }

    public void start() {

        for (int i = 0; i < 2; i++) {

            for (int k = 0; k <= players.size(); k++) {

                if (k != players.size()) {

                    dealerController.setPlayer(players.get(k));
                    dealerController.dealToPlayer();

                } else {
                    dealerController.dealToDealer();
                }
            }
        }

        System.out.println("--------------------");
        System.out.println("Dealer Cards: ");
        dealerController.getDealerCards().stream().forEach(System.out::println);

        System.out.println("--------------------");
        System.out.println("Player cards: ");
        playerController.getPlayers().stream().forEach(e -> System.out.println(e.getCards()));
        System.out.println("--------------------");

        hitOrStand();

    }

    public void hitOrStand() {
        turn = 0;

        int playerCardValues = 0;
        int dealerCardValues = 0;
        boolean playerAce = false;
        boolean dealerAce = false;

        //loop if game is not finished
        while (!gameFinished) {


            dealerCardValues = dealerController.getDealerCardValues();
            dealerAce = dealerController.doesDealerAceExist();

            System.out.println();
            System.out.println("Total Score");
            for (Player p : playerController.getPlayers()) {
                System.out.println("Player " + p.getPlayerNum() + " " + (p.doesPlayerAceExist() ? (p.getCardValues() + " or " + (p.getCardValues()+10)) : p.getCardValues()));
            }
            System.out.println("Dealer Total: " + (dealerAce ? dealerCardValues + " or " + (dealerCardValues + 10) : dealerCardValues));
            System.out.println();

            if (turn < players.size()) {

                playerCardValues = playerController.getPlayers().get(turn).getCardValues();
                playerAce = playerController.getPlayers().get(turn).doesPlayerAceExist();
//                if (playerController.getPlayers().get(turn).isBust()) {
//                    break;
//                }

                dealerController.setPlayer(playerController.getPlayers().get(turn));

                while (playerCardValues <= 21) {

                    System.out.println("Player " + turn + " enter 1 to Hit or 2 to Stand");
                    int response = new java.util.Scanner(System.in).nextInt();

                    if (response == Choices.HIT.getChoice()) {

                        //hit will add another card to player's hand
                        System.out.println("Deal to player " + turn + ": \n" + dealerController.dealToPlayer());
                        playerCardValues = playerController.getPlayers().get(turn).getCardValues();

                        System.out.println("Player " + turn + " Total: " + (playerAce ? playerCardValues + " or " + (playerCardValues + 10) : playerCardValues));

                        if (playerCardValues > 21) {
                            System.out.println("Player " + turn + " BUST");
                            playerController.getPlayers().get(turn).setBust(true);
                            turn++;
                            break;

                        } else {
                            //equal to or under 21
                            //account for Ace

                            if (playerAce) {

                                if (playerCardValues + 10 == 21) {
                                    System.out.println("Player " + turn + " Natural 21");
                                    turn++;
                                }

                            }
//                            else {
//
//                                if (playerCardValues == 21) {
//                                    System.out.println("Player " + turn + " 21");
//                                    turn++;
//                                }
//                            }

                        }

                    } else {
                        //current player stand then next player's turn
                        turn++;
                        break;
                    }
                }

            } else {

                dealerCardValues = dealerController.getDealerCardValues();
                int val2 = dealerAce ? dealerCardValues + 10 : dealerCardValues;

                //if all players are busted
                boolean allPlayersBust = playerController.getPlayers().stream()
                        .filter(e -> e.isBust()).count() == players.size();

                if (allPlayersBust) {
                    gameFinished = true;
                    break;
                }

                    //dealer under 17
                    while (dealerCardValues < 17 || val2 < 17) {

                        if (val2 == 21) {
                            System.out.println("Dealer Natural 21");
                            gameFinished = true;
                            break;

                        } else if ((dealerCardValues >= 17 && dealerCardValues <= 21) || (val2 >= 17 && val2 <= 21)) {
                            gameFinished = true;
                            break;
                        }

                        System.out.println("Deal to Dealer: " + dealerController.dealToDealer());

                        dealerAce = dealerController.doesDealerAceExist();
                        dealerCardValues = dealerController.getDealerCardValues();
                        val2 = dealerAce ? dealerCardValues + 10 : dealerCardValues;

                        System.out.println("Dealer Total: " + (dealerAce ? dealerCardValues + " or " + val2 : dealerCardValues));

                        System.out.println();
                    }

                    if (dealerCardValues == 21 || val2 == 21) {
                        System.out.println("Dealer 21");

                    } else if ((dealerCardValues >= 17 && dealerCardValues <= 21) || (val2 >= 17 && val2 <= 21)){
                        System.out.println("Dealer stands");

                    } else {
                        dealerController.setDealerBust(true);
                        System.out.println("Dealer bust");

                    }
                    gameFinished = true;
                    break;

                }

        }

        System.out.println("------GAME OVER------");

        determineWinner(dealerCardValues);

        System.out.println("------GAME OVER------");
    }

    public void determineWinner(int dealerCardValues) {

        playerController.getPlayers().stream()
                .forEach(e ->
                {
                    if (e.isBust()) {
                        System.out.println("Player " + e.getPlayerNum() + " lost");

                    } else if (dealerController.isDealerBust()) {
                        System.out.println("Player " + e.getPlayerNum() + " wins");

                    } else {
                        if (e.doesPlayerAceExist()) {

                            int aceValue = e.getCardValues() + 10;
                            if (aceValue <= 21 && aceValue > dealerCardValues) {
                                System.out.println("Player " + e.getPlayerNum() + " wins");
                            } else if (aceValue == dealerCardValues) {
                                System.out.println("Player " + e.getPlayerNum() + " tied");
                            } else {
                                System.out.println("Player " + e.getPlayerNum() + " lost");
                            }
                        } else {
                            if ((e.getCardValues() > dealerCardValues &&
                                    e.getCardValues() <= 21 && dealerCardValues <= 21)) {
                                System.out.println("Player " + e.getPlayerNum() + " wins");

                            } else if (e.getCardValues() == dealerCardValues && dealerCardValues <= 21) {
                                System.out.println("Player " + e.getPlayerNum() + " tied");
                            } else {
                                System.out.println("Player " + e.getPlayerNum() + " lost");
                            }
                        }
                    }
                });


    }


}
