/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Noah G
 */
public class Impass extends Node{
    int width, height, x0, y0; //Width and Height of the stairs in pixels, x0/y0 is the top-left corner of the stairs
    public Impass(){
        width=height=x0=y0=0;
    }
    public Impass(int w, int h, int x, int y){
        width=w;
        height=h;
        x0=x;
        y0=y;
    }
    
}
