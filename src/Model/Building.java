/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author Noah G
 */
public class Building {

    //  Double eastBoundry, westBoundry, lon2, lat2; //Width and Height of the building in pixels, x0/y0 is the top-left corner of the building
    private Point2D.Double SWCorner, NECorner;
    private String name;
    public ArrayList<Node> entrances;
    public ArrayList<Node> adaEntrances;

    public Building(String name, Point2D.Double SWCorner, Point2D.Double NECorner) {
        this.SWCorner = SWCorner;
        this.NECorner = NECorner;
        this.name = name;
        entrances = new ArrayList<>();
        adaEntrances = new ArrayList<>();
    }

    public void addEntrance(Node e) {
        System.out.println(getName()+" adding Entrace: " + e.getID());
        entrances.add(e);
        if (e.getADA()) {
            adaEntrances.add(e);
        }
    }

    public Node getEntranceAt(int i) {
        System.out.println("B: "+getName()+" s: " + entrances.size());
        return entrances.get(i);
    }

    public String getName() {
        return this.name;
    }

    public Point2D.Double getSWCorner() {
        return this.SWCorner;
    }

    public Point2D.Double getNECorner() {
        return this.NECorner;
    }
}//end class
