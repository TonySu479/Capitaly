package com.company;

public class LuckTile implements Tile {
    private int prize;

    public LuckTile(int prize){
        this.prize = prize;
    }

    public int getPrize(){
        return prize;
    }

}
