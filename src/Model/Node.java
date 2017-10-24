/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Noah
 */

public class Node {
    public static final int ENTRANCE=0, INTERSECTION=1;
    private int type;
    private double lat, lon;
    private String id;
    private Boolean ada;
    private Boolean active;
    
 
    public Node(){
        lon=0;
        lat=0;
        id="";
        ada=false;
        active=true;
        type=-1;
    }
    
    public Node(double lat, double lon, String id, int t, Boolean ada){
        this.lat=lat;
        this.lon=lon;
        this.id=id;
        type=t;
        this.ada=ada;
        active=true;
    }
    
    public Node getNode(){
        return this;
    }
    
 
    public int getType(){
        return type;
    }
    
    public void setType(int t){
        this.type=t;
    }
      
    public String getID(){
        return id;
    }
    
    public void setID(String i){
        this.id=i;
    }
    
    public void setActive(Boolean i){
        active=i;
    }
    
    public Boolean getADA(){
        return ada;
    }
    
    public void setADA(Boolean b){
        ada=b;
    }
    
    

    /**
     * @return the lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * @return the lon
     */
    public double getLon() {
        return lon;
    }

    /**
     * @param lon the lon to set
     */
    public void setLon(double lon) {
        this.lon = lon;
    }
}
