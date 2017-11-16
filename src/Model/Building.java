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
    
    Double lon1, lat1, lon2, lat2; //Width and Height of the building in pixels, x0/y0 is the top-left corner of the building
    public ArrayList<Node> entrances;
    public ArrayList<Node> adaEntrances;
    
    public Building(Double lat1, Double lon, Double lon2, Double lat2){
        this.lon1 = lon1;
        this.lon2=lon2;
        this.lat1=lat1;
        this.lat2=lat2;
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
    
    public Double getLon1(){
        return lon1;
    }
    
    public Double getLat1(){
        return lat1;
    }
    
    public Double getLon2(){
        return lon2;
    }
    
    public Double getLat2(){
        return lat2;
    }
    
}
