package com.company;

public class PropertyTile implements Tile{
    private int plotCost = 1000;
    private int buildingCost = 4000;
    private int plotFee = 500;
    private int buildingFee = 2000;
    private boolean hasBuilding = false;
    private boolean hasOwner = false;
    private Player owner;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        owner.addPropertyTileOwnership();
        hasOwner = true;
    }

    public void resetTile(){
        this.owner = null;
        hasOwner = false;
        hasBuilding = false;
    }

    public void setBuilding(){
        hasBuilding = true;
    }

    public boolean hasBuilding(){
        return hasBuilding;
    }

    public int getPlotCost() {
        return plotCost;
    }

    public int getBuildingCost() {
        return buildingCost;
    }

    public boolean hasOwner(){
        return hasOwner;
    }

    public int getBuildingFee() {
        return buildingFee;
    }

    public int getPlotFee() {
        return plotFee;
    }
}
