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
    
    public int getY(){
        return y;
    }
    
    public String getID(){
        return id;
    }
    
    public void setActive(Boolean i){
        active=i;
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
