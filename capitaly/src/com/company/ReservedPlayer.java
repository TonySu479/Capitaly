package com.company;

public class ReservedPlayer extends Player{
    private int halfOfRoundBal;

    public ReservedPlayer(String name){
        super(name);
    }

    public void interactWithPropertyTile(PropertyTile propertyTile){
        if(!propertyTile.hasOwner() && halfOfRoundBal > propertyTile.getPlotCost()){
            halfOfRoundBal = halfOfRoundBal - propertyTile.getPlotCost();
            purchasePropertyTile(propertyTile);
        } else if(!propertyTile.hasBuilding() && halfOfRoundBal > propertyTile.getBuildingCost()){
            halfOfRoundBal = halfOfRoundBal - propertyTile.getBuildingCost();
            purchaseBuilding(propertyTile);
        }
    }
    public void calculateHalfOfRoundBal(){
        halfOfRoundBal = balance / 2;
    }

}
