package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Player> Players = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        int service, prize, rounds, noOfTiles, noOfPlayers;
        String playerName;
        char tileType, playerType;

        rounds = sc.nextInt();
        noOfTiles = sc.nextInt();
        Board Game = new Board(noOfTiles);

        for(int i = 0; i < noOfTiles; i++){
            tileType = sc.next().charAt(0);
            switch(tileType){
                case 'P':
                    Game.setPropertyTile(i);
                    break;
                case 'S':
                    service = sc.nextInt();
                    Game.setServiceTile(i, service);
                    break;
                case 'L':
                    prize = sc.nextInt();
                    Game.setLuckTile(i, prize);
                    break;
                default:
                    System.out.println("Unrecognized character");
            }
        }

        noOfPlayers = sc.nextInt();
        
        for(int i = 0; i < noOfPlayers; i++){
            playerType = sc.next().charAt(0);
            playerName = sc.nextLine();
            switch(playerType){
                case 'G':
                    Players.add(new GreedyPlayer(playerName));
                    break;
                case 'R':
                    Players.add(new ReservedPlayer(playerName));
                    break;
                case 'T':
                    Players.add(new TacticalPlayer(playerName));
                    break;
                default:
                    System.out.println("Unrecognized character");
            }
        }

        Game.simulateGame(rounds, Players, noOfTiles - 1);
        Game.printPlayers();

    }
}
