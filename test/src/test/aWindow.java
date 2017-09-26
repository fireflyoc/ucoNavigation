/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Noah G
 */
class aWindow extends JFrame {
    
    ArrayList<Building> buildings; //List of buildings on the campus map
    ArrayList<Stairs> stair;
    ArrayList<Path> paths;
    ArrayList<Intersection> intersections;
    JPanel panel;
    int x,y=0;

    public aWindow() {
        Container contentPane = getContentPane();
        buildings=new ArrayList<>();
        stair = new ArrayList<>();
        paths = new ArrayList<>();
        intersections = new ArrayList<>();
        panel = new ImagePanel();
        contentPane.add(panel, "Center");
        panel.addMouseListener(new MouseController());
        //Setup buildings
        buildings.add(new Building(65,90,285,400,"CMSC"));
        buildings.add(new Building(120,110,290,240,"HOH"));
        buildings.add(new Building(75,230,460,140,"Nigh"));
        buildings.add(new Building(100,80,260,140,"Music"));
        //Setup Entrances
        buildings.get(0).addEntrance(new Entrance(310,400)); //CMSC North
        buildings.get(0).addEntrance(new Entrance(290,444)); //CMSC West
        buildings.get(1).addEntrance(new Entrance(406,327)); //HOH South East
        buildings.get(1).addEntrance(new Entrance(388,298)); //HOH North East
        buildings.get(1).addEntrance(new Entrance(301,325)); //HOH South West -- Stairs
        buildings.get(1).addEntrance(new Entrance(328,295)); //HOH West Central -- Stairs 
        buildings.get(1).addEntrance(new Entrance(365,256)); //HOH  North Central
        buildings.get(1).addEntrance(new Entrance(297,259)); //HOH North West
        //Setup Intersections
        intersections.add(new Intersection(310,375)); //Above CMSC North Entrance
        intersections.add(new Intersection(280,375)); //North of CMSC East and West of above intersection
        intersections.add(new Intersection(280,320));
        intersections.add(new Intersection(280,232));
        intersections.add(new Intersection(360,232));
        intersections.add(new Intersection(375,232));
        intersections.add(new Intersection(442,250));
        intersections.add(new Intersection(442,375));
        intersections.add(new Intersection(440,322));
        intersections.add(new Intersection(418,325));
        intersections.add(new Intersection(418,370));
        //Setup Paths
        paths.add(new Path(intersections.get(0),buildings.get(0).entrances.get(0))); //Path from CMSC North Entrance up
        paths.add(new Path(intersections.get(0),intersections.get(1))); //E/W Path above CMSC
        paths.add(new Path(intersections.get(1),buildings.get(0).entrances.get(1))); //Path from CMSC East Entrance up
        
    }
        
    /*
    Determine which pixels encompass stairs, buildings, entrances, or impasses
    stairs next to nigh center on west side
    road between HOH and CMS buildings is an impass due to construction
    
    */
    
}
