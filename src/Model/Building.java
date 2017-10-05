/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Noah G
 */
public class Building {
    
    int width, height,x,y; //Width and Height of the building in pixels, x0/y0 is the top-left corner of the building
    public ArrayList<Node> entrances;
    public ArrayList<Node> adaEntrances;
    
    public Building(int w, int h, int x, int y){
        width=w;
        height=h;
        this.x=x;
        this.y=y;
        entrances = new ArrayList<>();
        adaEntrances=new ArrayList<>();
    }
    
    public void addEntrance(Node e){
        entrances.add(e);
        if(e.getADA()){
            adaEntrances.add(e);
        }
    }
    
    public Node getEntranceAt(int i){
        return entrances.get(i);
    }
    
    public int getW(){
        return width;
    }
    
    public int getH(){
        return height;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
}
