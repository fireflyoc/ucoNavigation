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
    private int x,y, type;
    private String id;
    private Boolean ada;
    private Boolean active;
    
    public int getX(){
        return x;
    }
    
    public void setX(int x){
        this.x=x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getType(){
        return type;
    }
    
    public void setType(int t){
        this.type=t;
    }
    
    public void setY(int y ){
        this.y=y;
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
    
    public Node(){
        x=0;
        y=0;
        id="";
        ada=false;
        active=true;
        type=-1;
    }
    public Node(int x, int y, String id, int t, Boolean ada){
        this.x=x;
        this.y=y;
        this.id=id;
        type=t;
        this.ada=ada;
        active=true;
    }
    
    public Node getNode(){
        return this;
    }
}
