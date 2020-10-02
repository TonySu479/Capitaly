package com.company.Tile;

public class ServiceTile implements Tile {
    private int serviceCost;

    public ServiceTile(int serviceCost) {
        this.serviceCost = serviceCost;
    }

    public int getServiceCost(){
        return serviceCost;
    }
}
