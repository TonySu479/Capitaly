package com.company.Player;

import com.company.Tile.PropertyTile;

public class GreedyPlayer extends Player {
    public GreedyPlayer(String name){
        super(name);
    }

    public void interactWithPropertyTile(PropertyTile propertyTile){
        if(!propertyTile.hasOwner() && balance > propertyTile.getPlotCost()){
            purchasePropertyTile(propertyTile);
        } else if(!propertyTile.hasBuilding() && balance > propertyTile.getBuildingCost()){
            purchaseBuilding(propertyTile);
        }
    }
}
