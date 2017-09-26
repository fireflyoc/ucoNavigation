/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;

/**
 *
 * @author Noah
 */
public class Intersection extends Node{
    int x,y;
    ArrayList<Path> routes;
    
    public Intersection(int x0, int y0){
        x=x0;
        y=y0;
    }
    
    public void addPath(Path p){
        routes.add(p);
    }
    
}
