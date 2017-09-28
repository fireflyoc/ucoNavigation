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
    
    ArrayList<Path> paths;
    ArrayList<Intersection> intersections;
    JPanel panel;
    int x,y=0;

    public aWindow() {
        Container contentPane = getContentPane();
        paths = new ArrayList<>();
        intersections = new ArrayList<>();
        panel = new ImagePanel();
        contentPane.add(panel, "Center");
        panel.addMouseListener(new MouseController());
        //Setup buildings
        Building cmsc = new Building(65,90,285,400);
        Building hoh = new Building(120,110,290,240);
        Building nigh = new Building(75,230,460,140);
        Building music = new Building(100,80,260,140);
        //Setup Entrances
        cmsc.addEntrance(new Entrance(310,400)); //CMSC North
        cmsc.addEntrance(new Entrance(290,444)); //CMSC West
        hoh.addEntrance(new Entrance(406,327)); //HOH South East
        hoh.addEntrance(new Entrance(388,298)); //HOH North East
        hoh.addEntrance(new Entrance(301,325)); //HOH South West -- Stairs
        hoh.addEntrance(new Entrance(328,295)); //HOH West Central -- Stairs 
        hoh.addEntrance(new Entrance(365,256)); //HOH  North Central
        hoh.addEntrance(new Entrance(297,259)); //HOH North West
        //Setup Intersections
        intersections.add(new Intersection(310,375)); //Above CMSC North Entrance
        intersections.add(new Intersection(280,375)); //North of CMSC East and West of above intersection
        intersections.add(new Intersection(280,320)); //Above previous on the West side of HOH
        intersections.add(new Intersection(280,232)); //NW Corner of HOH
        intersections.add(new Intersection(360,232)); //North Central of HOH
        intersections.add(new Intersection(375,232)); //North Central of HOH, slightly to East of above
        intersections.add(new Intersection(442,250)); //NE Corner of HOH, between Nigh and HOH
        intersections.add(new Intersection(442,375)); //South East corner of HOH
        intersections.add(new Intersection(440,322)); //East Central between HOH and Nigh
        intersections.add(new Intersection(418,325)); //East Connection to HOH East Entrances
        intersections.add(new Intersection(418,370)); //South of above intersection
        intersections.add(new Intersection(280,259)); //West of HOH NW Entrance
        intersections.add(new Intersection(301,322)); //West side of HOH down stairs towards W HOH Entrances
        //Setup Paths
        paths.add(new Path(intersections.get(0),cmsc.entrances.get(0))); //Path from CMSC North Entrance up
        paths.add(new Path(intersections.get(0),intersections.get(1))); //E/W Path above CMSC
        paths.add(new Path(intersections.get(1),cmsc.entrances.get(1))); //Path from CMSC East Entrance up
        paths.add(new Path(intersections.get(1),intersections.get(2))); //Path heading North along West side of HOH
        paths.add(new Path(intersections.get(2),intersections.get(11))); //Path heading North along West side of HOH
        paths.add(new Path(intersections.get(3),intersections.get(4))); //Path heading East along North side of HOH
        paths.add(new Path(intersections.get(4),intersections.get(5))); //Short path heading East along North Side of HOH
        paths.add(new Path(intersections.get(4),hoh.entrances.get(4))); //Path from North side of HOH to NC entrance
        paths.add(new Path(intersections.get(5),intersections.get(6))); //Diaganol path on NE side of HOH
        paths.add(new Path(intersections.get(6),intersections.get(8))); //Path South between Nigh and HOH
        paths.add(new Path(intersections.get(8),intersections.get(7))); //Path South between Nigh and HOH
        paths.add(new Path(intersections.get(8),intersections.get(9))); //Path East towards East HOH Entrances
        paths.add(new Path(intersections.get(9),intersections.get(10))); //Path South from East HOH Entrances
        paths.add(new Path(intersections.get(10),intersections.get(0))); //Path East between HOH and CMSC
        paths.add(new Path(intersections.get(9),hoh.entrances.get(0)));
        paths.add(new Path(intersections.get(6),hoh.entrances.get(1)));
        paths.add(new Path(intersections.get(11),intersections.get(3))); //Path North along West side of HOH to NW corner
        paths.add(new Path(intersections.get(11),hoh.entrances.get(5)));
        paths.add(new Path(intersections.get(12),hoh.entrances.get(2)));
        paths.add(new Path(intersections.get(12),hoh.entrances.get(3)));
        paths.add(new Stairs(intersections.get(2),intersections.get(12)));
    }
        
    /*
    Determine which pixels encompass stairs, buildings, entrances, or impasses
    stairs next to nigh center on west side
    road between HOH and CMS buildings is an impass due to construction
    
    */
    
}
