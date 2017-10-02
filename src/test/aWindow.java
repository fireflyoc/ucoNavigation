/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Noah G
 */
class aWindow extends JFrame {
    
    ArrayList<Path> paths;
    ArrayList<Intersection> intersections;
    JPanel panel, bottomPanel;
    Node start, finish;
    int x,y=0;
    ArrayList<Path> finalPath;
    Path toEntrance = null, toEnd=null;
    Building cmsc, hoh, nigh, music;
    JTextField text;
    JButton reset;


    public aWindow() {
        Container contentPane = getContentPane();
        paths = new ArrayList<>();
        intersections = new ArrayList<>();
        panel = new ImagePanel();
        contentPane.add(panel, "Center");
        panel.addMouseListener(new MouseController());
        text = new JTextField();
        text.setPreferredSize(new Dimension(500,25));
        text.setEditable(false);
        bottomPanel = new JPanel();
        reset = new JButton("Reset");
        bottomPanel.add(text);
        bottomPanel.add(reset);
        contentPane.add(bottomPanel, "South");
        //Setup buildings
        cmsc = new Building(65,90,285,400);
        hoh = new Building(120,110,290,240);
        nigh = new Building(75,230,460,140);
        music = new Building(100,80,260,140);
        //Setup Entrances
        cmsc.addEntrance(new Entrance(310,400,"CMSC North")); //CMSC North
        cmsc.addEntrance(new Entrance(290,444,"CMSC West")); //CMSC West
        hoh.addEntrance(new Entrance(406,327,"HOH South East")); //HOH South East
        hoh.addEntrance(new Entrance(388,298,"HOH North East")); //HOH North East
        hoh.addEntrance(new Entrance(301,325,"HOH South West")); //HOH South West -- Stairs
        hoh.addEntrance(new Entrance(328,295,"HOH West Central")); //HOH West Central -- Stairs 
        hoh.addEntrance(new Entrance(365,256,"HOH  North Central")); //HOH  North Central
        hoh.addEntrance(new Entrance(297,259,"HOH North West")); //HOH North West
        //Setup Intersections
        intersections.add(new Intersection(310,375,"a")); //Above CMSC North Entrance
        intersections.add(new Intersection(280,375,"b")); //North of CMSC East and West of above intersection
        intersections.add(new Intersection(280,320,"c")); //Above previous on the West side of HOH
        intersections.add(new Intersection(280,232,"d")); //NW Corner of HOH
        intersections.add(new Intersection(360,232,"e")); //North Central of HOH
        intersections.add(new Intersection(375,232,"f")); //North Central of HOH, slightly to East of above
        intersections.add(new Intersection(442,250,"g")); //NE Corner of HOH, between Nigh and HOH
        intersections.add(new Intersection(442,375,"h")); //South East corner of HOH
        intersections.add(new Intersection(440,322,"j")); //East Central between HOH and Nigh
        intersections.add(new Intersection(418,325,"k")); //East Connection to HOH East Entrances
        intersections.add(new Intersection(418,370,"l")); //South of above intersection
        intersections.add(new Intersection(280,259,"m")); //West of HOH NW Entrance
        intersections.add(new Intersection(301,322,"n")); //West side of HOH down stairs towards W HOH Entrances
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
        paths.add(new Path(cmsc.entrances.get(0),intersections.get(0)));
        paths.add(new Path(cmsc.entrances.get(1),intersections.get(1)));
        paths.add(new Path(hoh.entrances.get(0),intersections.get(9)));
        paths.add(new Path(hoh.entrances.get(1),intersections.get(6)));
        paths.add(new Path(hoh.entrances.get(5),intersections.get(11)));
        paths.add(new Path(hoh.entrances.get(2),intersections.get(12)));
        paths.add(new Path(hoh.entrances.get(3),intersections.get(12)));
        paths.add(new Path(hoh.entrances.get(4),intersections.get(4)));
        
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start=finish=null;
                text.setText("");
                repaint();
            }
        });
        
    }
    
    public void setStart(){
        if(start.x <cmsc.x+cmsc.width && start.x >cmsc.x && start.y>cmsc.y && start.y<cmsc.y+cmsc.height){
            for(Entrance e : cmsc.entrances){
                Path temp = new Path(start, e);
                if(toEntrance == null || temp.getLength()<toEntrance.getLength()){
                    toEntrance = temp;
                }
            }
        } else if(start.x <hoh.x+hoh.width && start.x >hoh.x && start.y>hoh.y && start.y<hoh.y+hoh.height){
            for(Entrance e : hoh.entrances){
                Path temp = new Path(start, e);
                if(toEntrance == null || temp.getLength()<toEntrance.getLength()){
                    toEntrance = temp;
                }
            }
        }
    }
    
    public void setEnd(){
        if(finish.x <cmsc.x+cmsc.width && finish.x >cmsc.x && finish.y>cmsc.y && finish.y<cmsc.y+cmsc.height){
            for(Entrance e : cmsc.entrances){
                Path temp = new Path(finish, e);
                if(toEnd == null || temp.getLength()<toEnd.getLength()){
                    toEnd = temp;
                }
            }
        } else if(finish.x <hoh.x+hoh.width && finish.x >hoh.x && finish.y>hoh.y && finish.y<hoh.y+hoh.height){
            for(Entrance e : hoh.entrances){
                Path temp = new Path(finish, e);
                if(toEnd == null || temp.getLength()<toEnd.getLength()){
                    toEnd = temp;
                }
            }
        }
    }
    
    public void search(){
        ArrayList<Building> buildings = new ArrayList<>();
        buildings.add(cmsc);
        buildings.add(hoh);
        ArrayList<Entrance> entrances = new ArrayList<>();
        for(Building b : buildings){
            entrances.addAll(b.entrances);
        }
        
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.addAll(entrances);
        nodes.addAll(intersections);
        Navigator navi = new Navigator(nodes,paths);
        navi.execute(toEntrance.getDestination());
        LinkedList<Node> route = navi.getPath(toEnd.getDestination());
        
        if(route !=null){
            Graphics g = getGraphics();
            String r = "";
            for(Node n: route){
                r+=n.id+" > ";
                g.setColor(Color.BLUE);
                g.drawString(n.id, n.x, n.y+30);
                System.out.println(n.id);
            }
            text.setText(r);
        } else{
            System.out.println("Route is null.");
        }
    }
        
    
    
    class MouseController implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("X: "+e.getX()+"\nY: "+e.getY());
        if(start == null) {
            start = new Intersection(e.getX(), e.getY(),"Start");
            setStart();
        }
        else if(finish == null) {
            finish = new Intersection(e.getX(), e.getY(),"End");
            setEnd();
            search();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {    }

    @Override
    public void mouseReleased(MouseEvent e) {    }

    @Override
    public void mouseEntered(MouseEvent e) {    }

    @Override
    public void mouseExited(MouseEvent e) {    }
}
    /*
    Determine which pixels encompass stairs, buildings, entrances, or impasses
    stairs next to nigh center on west side
    road between HOH and CMS buildings is an impass due to construction
    
    */
    
}
