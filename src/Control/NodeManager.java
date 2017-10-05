package Control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Model.Stairs;
import Model.Building;
import Model.Node;
import Model.Path;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Noah G
 */
public class NodeManager extends JFrame {
    
    ArrayList<Path> paths;
    ArrayList<Node> allNodes;
    JPanel panel, bottomPanel;
    Node start, finish;
    int x,y=0;
    ArrayList<Path> finalPath;
    Path toEntrance = null, toEnd=null;
    Building cmsc, hoh, nigh, music;
    JTextField text;
    JButton reset;


    public NodeManager() {
        Container contentPane = getContentPane();
        paths = new ArrayList<>();
        allNodes = new ArrayList<>();
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
        
        readXML();
        //Setup Entrances
        /*cmsc.addEntrance(new Node(310,400,"CMSC North Entrance")); //CMSC North
        cmsc.addEntrance(new Node(290,444,"CMSC West Entrance")); //CMSC West
        hoh.addEntrance(new Node(406,327,"HOH South East Entrance")); //HOH South East
        hoh.addEntrance(new Node(388,298,"HOH North East Entrance")); //HOH North East
        hoh.addEntrance(new Node(301,325,"HOH South West Entrance")); //HOH South West -- Stairs
        hoh.addEntrance(new Node(328,295,"HOH West Central Entrance")); //HOH West Central -- Stairs 
        hoh.addEntrance(new Node(365,256,"HOH  North Central Entrance")); //HOH  North Central
        hoh.addEntrance(new Node(297,259,"HOH North West Entrance")); //HOH North West
        //Setup Nodes
        allNodes.add(new Node(310,375,"a")); //Above CMSC North Entrance
        allNodes.add(new Node(280,375,"b")); //North of CMSC East and West of above intersection
        allNodes.add(new Node(280,320,"c")); //Above previous on the West side of HOH
        allNodes.add(new Node(280,232,"d")); //NW Corner of HOH
        allNodes.add(new Node(360,232,"e")); //North Central of HOH
        allNodes.add(new Node(375,232,"f")); //North Central of HOH, slightly to East of above
        allNodes.add(new Node(442,250,"g")); //NE Corner of HOH, between Nigh and HOH
        allNodes.add(new Node(442,375,"h")); //South East corner of HOH
        allNodes.add(new Node(440,322,"j")); //East Central between HOH and Nigh
        allNodes.add(new Node(418,325,"k")); //East Connection to HOH East Entrances
        allNodes.add(new Node(418,370,"l")); //South of above intersection
        allNodes.add(new Node(280,259,"m")); //West of HOH NW Entrance
        allNodes.add(new Node(301,322,"n")); //West side of HOH down stairs towards W HOH Entrances
        */
        //Setup Paths
        paths.add(new Path(allNodes.get(0),cmsc.getEntranceAt(0))); //Path from CMSC North Entrance up
        paths.add(new Path(allNodes.get(0),allNodes.get(1))); //E/W Path above CMSC
        paths.add(new Path(allNodes.get(1),cmsc.getEntranceAt(1))); //Path from CMSC East Entrance up
        paths.add(new Path(allNodes.get(1),allNodes.get(2))); //Path heading North along West side of HOH
        paths.add(new Path(allNodes.get(2),allNodes.get(11))); //Path heading North along West side of HOH
        paths.add(new Path(allNodes.get(3),allNodes.get(4))); //Path heading East along North side of HOH
        paths.add(new Path(allNodes.get(4),allNodes.get(5))); //Short path heading East along North Side of HOH
        paths.add(new Path(allNodes.get(4),hoh.getEntranceAt(4))); //Path from North side of HOH to NC entrance
        paths.add(new Path(allNodes.get(5),allNodes.get(6))); //Diaganol path on NE side of HOH
        paths.add(new Path(allNodes.get(6),allNodes.get(8))); //Path South between Nigh and HOH
        paths.add(new Path(allNodes.get(8),allNodes.get(7))); //Path South between Nigh and HOH
        paths.add(new Path(allNodes.get(8),allNodes.get(9))); //Path East towards East HOH Entrances
        paths.add(new Path(allNodes.get(9),allNodes.get(10))); //Path South from East HOH Entrances
        paths.add(new Path(allNodes.get(10),allNodes.get(0))); //Path East between HOH and CMSC
        paths.add(new Path(allNodes.get(9),hoh.getEntranceAt(0)));
        paths.add(new Path(allNodes.get(6),hoh.getEntranceAt(1)));
        paths.add(new Path(allNodes.get(11),allNodes.get(3))); //Path North along West side of HOH to NW corner
        paths.add(new Path(allNodes.get(11),hoh.getEntranceAt(5)));
        paths.add(new Path(allNodes.get(12),hoh.getEntranceAt(2)));
        paths.add(new Path(allNodes.get(12),hoh.getEntranceAt(3)));
        paths.add(new Stairs(allNodes.get(2),allNodes.get(12)));
        paths.add(new Path(cmsc.getEntranceAt(0),allNodes.get(0)));
        paths.add(new Path(cmsc.getEntranceAt(1),allNodes.get(1)));
        paths.add(new Path(hoh.getEntranceAt(0),allNodes.get(9)));
        paths.add(new Path(hoh.getEntranceAt(1),allNodes.get(6)));
        paths.add(new Path(hoh.getEntranceAt(5),allNodes.get(11)));
        paths.add(new Path(hoh.getEntranceAt(2),allNodes.get(12)));
        paths.add(new Path(hoh.getEntranceAt(3),allNodes.get(12)));
        paths.add(new Path(hoh.getEntranceAt(4),allNodes.get(4)));
        paths.add(new Path(allNodes.get(1),allNodes.get(0)));
        
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start=null;
                finish=null;
                text.setText("");
                repaint();
                toEntrance=null;
                toEnd=null;
                System.out.println("Reset");
            }
        });
        
    }
    
    public void setStart(){
        if(start.getX() <cmsc.getX()+cmsc.getW() && start.getX() >cmsc.getX() && start.getY()>cmsc.getY() && start.getY()<cmsc.getY()+cmsc.getH()){
            for(Node e : cmsc.entrances){
                Path temp = new Path(start, e);
                if(toEntrance == null || temp.getLength()<toEntrance.getLength()){
                    toEntrance = temp;
                }
            }
        } else if(start.getX() <hoh.getX()+hoh.getW() && start.getX() >hoh.getX()&& start.getY()>hoh.getY() && start.getY()<hoh.getY()+hoh.getH()){
            for(Node e : hoh.entrances){
                Path temp = new Path(start, e);
                if(toEntrance == null || temp.getLength()<toEntrance.getLength()){
                    toEntrance = temp;
                }
            }
        }
    }
    
    public void setEnd(){
        if(finish.getX() <cmsc.getX()+cmsc.getW() && finish.getX() >cmsc.getX() && finish.getY() >cmsc.getY() && finish.getY()<cmsc.getY()+cmsc.getH()){
            for(Node e : cmsc.entrances){
                Path temp = new Path(finish, e);
                if(toEnd == null || temp.getLength()<toEnd.getLength()){
                    toEnd = temp;
                }
            }
        } else if(finish.getX() <hoh.getX()+hoh.getW() && finish.getX() >hoh.getX() && finish.getY()>hoh.getY() && finish.getY()<hoh.getY()+hoh.getH()){
            for(Node e : hoh.entrances){
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
        ArrayList<Node> entrances = new ArrayList<>();
        for(Building b : buildings){
            entrances.addAll(b.entrances);
        }
        
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.addAll(entrances);
        nodes.addAll(allNodes);
        Navigator navi = new Navigator(nodes,paths);
        navi.execute(toEntrance.getDestination());
        LinkedList<Node> route = navi.getPath(toEnd.getDestination());
        
        if(route !=null){
            Graphics g = getGraphics();
            String r = "";
            for(int i=0; i<route.size();i++){
                g.setColor(Color.GREEN);
                r+=route.get(i).getID();
                if(i+1<route.size()){
                    r+=" > ";
                    g.drawLine(route.get(i).getX(), route.get(i).getY()+25, route.get(i+1).getX(), route.get(i+1).getY()+25);
                }
                //g.drawString(route.get(i).id, route.get(i).x, route.get(i).y+30);
                System.out.println(route.get(i).getID());
            }
            text.setText(r);
        } else{
            System.out.println("Route is null.");
        }
    }

    private void readXML(){
        try{        
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = new FileInputStream("node.xml");
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            Node node = null;
            
            while(eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();
                if(event.isStartElement()){
                    StartElement startElement = event.asStartElement();
                    if(startElement.getName().getLocalPart().equals("Node")){
                        node= new Node();
                        Iterator<Attribute> attributes = startElement
                                .getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            if (attribute.getName().toString().equals("name")) {
                                node.setID(attribute.getValue());
                            }
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals("posx")) {
                            event = eventReader.nextEvent();
                            node.setX(Integer.parseInt(event.asCharacters().getData()));
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart().equals("posy")) {
                        event = eventReader.nextEvent();
                        node.setY(Integer.parseInt(event.asCharacters().getData()));
                        continue;
                    }
                    
                    if(event.asStartElement().getName().getLocalPart().equals("type")){
                        event = eventReader.nextEvent();
                        node.setType(Integer.parseInt(event.asCharacters().getData()));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals("ada")) {
                        event = eventReader.nextEvent();
                        node.setADA(Boolean.parseBoolean(event.asCharacters().getData()));
                        
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart().equals("active")) {
                        event = eventReader.nextEvent();
                        node.setActive(Boolean.parseBoolean(event.asCharacters().getData()));
                        continue;
                    }
                }
                // If we reach the end of an item element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals("node")) {
                        allNodes.add(node);
                    }
                }
                        
            }
        } catch(FileNotFoundException | XMLStreamException e){
            e.printStackTrace();
        }
    }
        
    public void saveConfig() throws Exception {
        // create an XMLOutputFactory
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        // create XMLEventWriter
        XMLEventWriter eventWriter = outputFactory
                .createXMLEventWriter(new FileOutputStream("node.xml"));
        // create an EventFactory
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        // create and write Start Tag
        StartDocument startDocument = eventFactory.createStartDocument();
        eventWriter.add(startDocument);

        // create config open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", "config");
        eventWriter.add(configStartElement);
        eventWriter.add(end);
        // Write the different nodes
        createNode(eventWriter, "posx", "1");
        createNode(eventWriter, "posy", "901");
        createNode(eventWriter, "ada", "0");
        createNode(eventWriter, "active", "0");

        eventWriter.add(eventFactory.createEndElement("", "", "config"));
        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndDocument());
        eventWriter.close();
    }

    private void createNode(XMLEventWriter eventWriter, String name,
            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);

    }
    
    class MouseController implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("X: "+e.getX()+"\nY: "+e.getY());
        if(start == null) {
            //start = new Node(e.getX(), e.getY(),"Start");
            setStart();
        }
        else if(finish == null) {
            //finish = new Node(e.getX(), e.getY(),"End");
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
