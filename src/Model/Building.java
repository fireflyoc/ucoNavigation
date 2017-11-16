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
    
    Double x1, y1, x2, y2; //Width and Height of the building in pixels, x0/y0 is the top-left corner of the building
    public ArrayList<Node> entrances;
    public ArrayList<Node> adaEntrances;
    
    public Building(Double x1, Double y1, Double x2, Double y2){
        this.x1 = x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
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
    
    public Double getX1(){
        return x1;
    }
    
    public Double getY1(){
        return y1;
    }
    
    public Double getX2(){
        return x2;
    }
    
    public Double getY2(){
        return y2;
    }
    
}
