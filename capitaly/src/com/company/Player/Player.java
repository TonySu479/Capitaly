package com.company.Player;

import com.company.Tile.LuckTile;
import com.company.Tile.PropertyTile;
import com.company.Tile.ServiceTile;
import com.company.Tile.Tile;

import java.util.ArrayList;
import java.util.Random;

public abstract class Player {
    protected int currentTileNum = 0;
    protected int balance = 10000;
    protected String name;
    protected int lastTileIndex;
    protected int numberOfPlots;
    protected int numberOfBuildings;
    protected boolean outOfGame = false;
    public ArrayList<Integer> ownedPlotTiles = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    //player interaction with game board tile
    public void interactWithTile(Tile tile){
        if(tile instanceof ServiceTile){
            balance = deductServiceCost(((ServiceTile) tile).getServiceCost());
        }
        else if(tile instanceof LuckTile){
            balance = addPrize(((LuckTile) tile).getPrize());
        }
    };

    //player has different interactions with property tile depending player type
    public abstract void interactWithPropertyTile(PropertyTile tile);

    //dice roll
    public void rollDice(){
        Random random = new Random();
        final int diceRoll = random.nextInt(6) + 1;
        currentTileNum = currentTileNum + diceRoll;
        while(currentTileNum > lastTileIndex){
            currentTileNum = currentTileNum - lastTileIndex - 1;
        }
    }

    //Purchase on empty plot/ purchasing Building on plot
    public void purchasePropertyTile(PropertyTile propertyTile){
        propertyTile.setOwner(this);
        balance = balance - propertyTile.getPlotCost();
        numberOfPlots++;
    }

    public void purchaseBuilding(PropertyTile propertyTile){
        propertyTile.setBuilding();
        balance = balance - propertyTile.getBuildingCost();
        numberOfBuildings++;
    }

    //Payment for plot fees for plot owned by other players
    public void payPlotFee(PropertyTile propertyTile){
        if(!propertyTile.hasBuilding()){
            payPropertyFee(propertyTile);
            checkIfOutOfGame();
        } else if(propertyTile.hasBuilding()){
            payBuildingFee(propertyTile);
            checkIfOutOfGame();
        }
    }

    public void payPropertyFee(PropertyTile tile){
        balance = balance - tile.getPlotFee();
        tile.getOwner().receiveFee(tile.getPlotFee());
    }

    public void payBuildingFee(PropertyTile tile){
        balance = balance - tile.getBuildingFee();
        tile.getOwner().receiveFee(tile.getBuildingFee());
    }

    public int deductServiceCost(int serviceCost){
        return balance - serviceCost;
    }

    public boolean checkIfOutOfGame(){
        outOfGame = balance < 0;
        return outOfGame;
    }

    public int addPrize(int prize){
        return balance + prize;
    }

    public String getName(){
        return name;
    }

    public void receiveFee(int fee){
        balance = balance + fee;
    }

    public int getCurrentTileNum() {
        return currentTileNum;
    }

    public int getBalance() {
        return balance;
    }

    public int getNumberOfBuildings() {
        return numberOfBuildings;
    }

    public int getNumberOfPlots() {
        return numberOfPlots;
    }

    public void setLastTileIndex(int lastTileIndex){
        this.lastTileIndex = lastTileIndex;
    }

    public void addPropertyTileOwnership(){
        ownedPlotTiles.add(currentTileNum);
    }

    public ArrayList<Integer> getOwnedPlotTiles() {
        return ownedPlotTiles;
    }
    
}