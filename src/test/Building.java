/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;

/**
 *
 * @author Noah G
 */
public class Building extends Node{
    
    int width, height; //Width and Height of the building in pixels, x0/y0 is the top-left corner of the building
    ArrayList<Entrance> entrances;
    
    public Building(){
        width=height=x=y=0;
    }
    public Building(int w, int h, int x, int y){
        width=w;
        height=h;
        this.x=x;
        this.y=y;
        entrances = new ArrayList<>();
    }
    
    public void addEntrance(Entrance e){
        entrances.add(e);
    }
    
}
