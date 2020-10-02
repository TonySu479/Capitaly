package com.company.Player;

import com.company.Tile.PropertyTile;

public class TacticalPlayer extends Player {
    private boolean hasPurchasedLast = false;

    public TacticalPlayer(String name){
        super(name);
    }

    public void interactWithPropertyTile(PropertyTile propertyTile){
        if(!propertyTile.hasOwner() && balance > propertyTile.getPlotCost() && !hasPurchasedLast){
            purchasePropertyTile(propertyTile);
        } else if(!propertyTile.hasBuilding() && balance > propertyTile.getBuildingCost() && !hasPurchasedLast){
            purchaseBuilding(propertyTile);
        }
        Purchased();

    }
    public void Purchased(){
        hasPurchasedLast = !hasPurchasedLast;
    }
}
