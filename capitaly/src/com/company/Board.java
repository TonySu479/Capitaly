package com.company;


import com.company.Player.Player;
import com.company.Player.ReservedPlayer;
import com.company.Tile.LuckTile;
import com.company.Tile.PropertyTile;
import com.company.Tile.ServiceTile;
import com.company.Tile.Tile;

import java.util.ArrayList;

public class Board {
    private Tile[] gameBoard;
    private int boardSize = 0;
    private ArrayList<Player> Players;
    private ArrayList<Player> LostPlayers = new ArrayList<>();
    private Tile currentTile;
    private PropertyTile propertyTile;
    private int currentRound = 0;
    private int rounds;
    private int playersOut = 0;

    public Board(int boardSize){
        this.boardSize = boardSize;
        this.gameBoard = new Tile[boardSize];
    }

    public void setPropertyTile(int Tile){
        gameBoard[Tile] = new PropertyTile();
    }

    public void setServiceTile(int tile, int service){
        gameBoard[tile] = new ServiceTile(service);
    }

    public void setLuckTile(int tile, int prize){
        gameBoard[tile] = new LuckTile(prize);
    }

    public Tile getTile(int tile){
        return gameBoard[tile];
    }

    public void setLastTileIndexForPlayers(int totalTiles){
        for(Player player : Players){
            player.setLastTileIndex(totalTiles);
        }
    }

    public void removePlotTilesFromLostPlayer(Player player){
        player.getOwnedPlotTiles();
        for(int tileNum : player.getOwnedPlotTiles()){
            PropertyTile propTile = (PropertyTile) gameBoard[tileNum];
            propTile.resetTile();
        }
    }

    public void simulateGame(int rounds, ArrayList<Player> players, int lastTileIndex){
        this.rounds = rounds;
        this.Players = players;
        setLastTileIndexForPlayers(lastTileIndex);
        while(currentRound != rounds && playersOut != Players.size()){
            playersOut = 0;
            for(Player player : Players){
                if(!player.checkIfOutOfGame()){
                    if(player instanceof ReservedPlayer){
                        ((ReservedPlayer) player).calculateHalfOfRoundBal();
                    }
                    player.rollDice();
                    currentTile = getTile(player.getCurrentTileNum());
                    if(currentTile instanceof PropertyTile){
                        propertyTile = (PropertyTile) currentTile;
                        if(propertyTile.hasOwner() && propertyTile.getOwner() != player){
                            player.payPlotFee(propertyTile);
                        } else{
                            player.interactWithPropertyTile(propertyTile);
                        }
                    }
                    else{
                        player.interactWithTile(currentTile);
                    }
                }
                else{
                    playersOut++;
                    removePlotTilesFromLostPlayer(player);
                    if(!LostPlayers.contains(player)){
                        LostPlayers.add(player);
                    }
                }
            }
            currentRound++;
        }
    }

    public void printPlayers(){
        for(Player player : Players){
            if(!player.checkIfOutOfGame()){
                System.out.println("Player name: " + player.getName() + " Balance: " + player.getBalance() + " Buildings " + player.getNumberOfBuildings() + " Plots " + player.getNumberOfPlots());
            }
        }
        for(Player player : LostPlayers){
            System.out.println("Player name: " + player.getName() + " is out");
        }
    }

}
