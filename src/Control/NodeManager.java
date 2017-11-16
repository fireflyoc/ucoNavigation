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
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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
public class NodeManager{
    ArrayList<Path> paths;
    ArrayList<Node> allNodes;
    Node start, finish;
    int x,y=0;
    ArrayList<Path> finalPath;
    Path toEntrance = null, toEnd=null;
    Building cmsc, hoh, nigh, chs;
    JTextField text;
    Boolean ada;


    public NodeManager() {
        paths = new ArrayList<>();
        allNodes = new ArrayList<>();
        text = new JTextField();
        text.setPreferredSize(new Dimension(500,25));
        text.setEditable(false);
        //Setup buildings
        cmsc = new Building(35.653762, -97.473034,35.654233,-97.472557);
        hoh = new Building(35.654517,-97.473029,35.655175, -97.472015);
        nigh = new Building(35.654508,-97.471900, 35.655881, -97.471164);
        chs = new Building(35.653253, -97.473780,35.653637, -97.473286);
        
        readXML();
        //Setup Entrances
        for(Node n: allNodes){
            if(n.getType() == 0){ //Node is an entrance
                if(n.getLat()>cmsc.getX1() && n.getLat()<cmsc.getX2() && Math.abs(n.getLon())>Math.abs(cmsc.getY1()) && Math.abs(n.getLon()) < Math.abs(cmsc.getY2())){
                    cmsc.addEntrance(n);
                }
                if(n.getLat()>hoh.getX1() && n.getLat()<hoh.getX2() && Math.abs(n.getLon())>Math.abs(hoh.getY1()) && Math.abs(n.getLon()) < Math.abs(hoh.getY2())){
                    hoh.addEntrance(n);
                }
                if(n.getLat()>chs.getX1() && n.getLat()<chs.getX2() && Math.abs(n.getLon())>Math.abs(chs.getY1()) && Math.abs(n.getLon()) < Math.abs(chs.getY2())){
                    chs.addEntrance(n);
                }
                if(n.getLat()>nigh.getX1() && n.getLat()<nigh.getX2() && Math.abs(n.getLon())>Math.abs(nigh.getY1()) && Math.abs(n.getLon()) < Math.abs(nigh.getY2())){
                    nigh.addEntrance(n);
                }
            }
        }
        //Setup Paths
        paths.add(new Path(allNodes.get(0),allNodes.get(2))); //Path from CMSC North Entrance up
        paths.add(new Path(allNodes.get(1),allNodes.get(2))); //E/W Path above CMSC
        paths.add(new Path(allNodes.get(2),allNodes.get(3))); //Path from CMSC East Entrance up
        paths.add(new Path(allNodes.get(3),allNodes.get(4))); //Path heading North along West side of HOH
        paths.add(new Path(allNodes.get(3),allNodes.get(5))); //Path heading North along West side of HOH
        paths.add(new Path(allNodes.get(5),allNodes.get(6))); //Path heading East along North side of HOH
        paths.add(new Path(allNodes.get(6),allNodes.get(7))); //Short path heading East along North Side of HOH
        paths.add(new Path(allNodes.get(7),allNodes.get(8))); //Path from North side of HOH to NC entrance
        paths.add(new Path(allNodes.get(8),allNodes.get(9))); //Diaganol path on NE side of HOH
        paths.add(new Path(allNodes.get(9),allNodes.get(10))); //Path South between Nigh and HOH
        paths.add(new Path(allNodes.get(10),allNodes.get(11))); //Path South between Nigh and HOH
        paths.add(new Path(allNodes.get(11),allNodes.get(12))); //Path East towards East HOH Entrances
        paths.add(new Path(allNodes.get(12),allNodes.get(13))); //Path South from East HOH Entrances
        paths.add(new Path(allNodes.get(11),allNodes.get(14))); //Path East between HOH and CMSC
        paths.add(new Path(allNodes.get(13),allNodes.get(14)));
        paths.add(new Path(allNodes.get(13),allNodes.get(0)));
        paths.add(new Path(allNodes.get(10),allNodes.get(15))); //Path North along West side of HOH to NW corner
        paths.add(new Path(allNodes.get(10),allNodes.get(16)));
        paths.add(new Path(allNodes.get(16),allNodes.get(18)));
        paths.add(new Path(allNodes.get(10),allNodes.get(17)));
        paths.add(new Path(allNodes.get(1),allNodes.get(19)));
        paths.add(new Path(allNodes.get(19),allNodes.get(20)));
        paths.add(new Path(allNodes.get(20),allNodes.get(21)));
        paths.add(new Path(allNodes.get(20),allNodes.get(22)));
        paths.add(new Path(allNodes.get(22),allNodes.get(23)));
        //Adding Symmetrical Paths
        paths.add(new Path(allNodes.get(2),allNodes.get(0))); //Path from CMSC North Entrance up
        paths.add(new Path(allNodes.get(2),allNodes.get(1))); //E/W Path above CMSC
        paths.add(new Path(allNodes.get(3),allNodes.get(2))); //Path from CMSC East Entrance up
        paths.add(new Path(allNodes.get(4),allNodes.get(3))); //Path heading North along West side of HOH
        paths.add(new Path(allNodes.get(5),allNodes.get(3))); //Path heading North along West side of HOH
        paths.add(new Path(allNodes.get(6),allNodes.get(5))); //Path heading East along North side of HOH
        paths.add(new Path(allNodes.get(7),allNodes.get(6))); //Short path heading East along North Side of HOH
        paths.add(new Path(allNodes.get(8),allNodes.get(7))); //Path from North side of HOH to NC entrance
        paths.add(new Path(allNodes.get(9),allNodes.get(8))); //Diaganol path on NE side of HOH
        paths.add(new Path(allNodes.get(10),allNodes.get(9))); //Path South between Nigh and HOH
        paths.add(new Path(allNodes.get(11),allNodes.get(10))); //Path South between Nigh and HOH
        paths.add(new Path(allNodes.get(12),allNodes.get(11))); //Path East towards East HOH Entrances
        paths.add(new Path(allNodes.get(13),allNodes.get(12))); //Path South from East HOH Entrances
        paths.add(new Path(allNodes.get(14),allNodes.get(11))); //Path East between HOH and CMSC
        paths.add(new Path(allNodes.get(14),allNodes.get(13)));
        paths.add(new Path(allNodes.get(0),allNodes.get(13)));
        paths.add(new Path(allNodes.get(15),allNodes.get(10))); //Path North along West side of HOH to NW corner
        paths.add(new Path(allNodes.get(16),allNodes.get(10)));
        paths.add(new Path(allNodes.get(18),allNodes.get(16)));
        paths.add(new Path(allNodes.get(17),allNodes.get(10)));
        paths.add(new Path(allNodes.get(19),allNodes.get(1)));
        paths.add(new Path(allNodes.get(20),allNodes.get(19)));
        paths.add(new Path(allNodes.get(21),allNodes.get(20)));
        paths.add(new Path(allNodes.get(22),allNodes.get(20)));
        paths.add(new Path(allNodes.get(23),allNodes.get(22)));
        //Adding Entrances
        paths.add(new Path(cmsc.getEntranceAt(0),allNodes.get(0)));
        paths.add(new Path(allNodes.get(0),cmsc.getEntranceAt(0)));
        paths.add(new Path(cmsc.getEntranceAt(1),allNodes.get(1)));
        paths.add(new Path(allNodes.get(1),cmsc.getEntranceAt(1)));
        paths.add(new Path(allNodes.get(21),chs.getEntranceAt(1)));
        paths.add(new Path(allNodes.get(23),chs.getEntranceAt(0)));
        paths.add(new Path(chs.getEntranceAt(0),allNodes.get(23)));
        paths.add(new Path(chs.getEntranceAt(1),allNodes.get(21)));
        paths.add(new Path(hoh.getEntranceAt(0),allNodes.get(4)));
        paths.add(new Path(allNodes.get(4),hoh.getEntranceAt(0)));
        paths.add(new Path(hoh.getEntranceAt(1),allNodes.get(12)));
        paths.add(new Path(allNodes.get(12),hoh.getEntranceAt(1)));
        paths.add(new Path(hoh.getEntranceAt(2),allNodes.get(4)));
        paths.add(new Path(allNodes.get(4),hoh.getEntranceAt(2)));
        paths.add(new Path(hoh.getEntranceAt(3),allNodes.get(12)));
        paths.add(new Path(allNodes.get(12),hoh.getEntranceAt(3)));
        paths.add(new Path(hoh.getEntranceAt(4),allNodes.get(12)));
        paths.add(new Path(allNodes.get(12),hoh.getEntranceAt(4)));
        paths.add(new Path(hoh.getEntranceAt(5),allNodes.get(10)));
        paths.add(new Path(allNodes.get(10),hoh.getEntranceAt(5)));
        paths.add(new Path(allNodes.get(5),hoh.getEntranceAt(6)));
        paths.add(new Path(hoh.getEntranceAt(6),allNodes.get(5)));
        paths.add(new Path(allNodes.get(11),nigh.getEntranceAt(1)));
        paths.add(new Path(nigh.getEntranceAt(1),allNodes.get(11)));
        paths.add(new Path(allNodes.get(15),nigh.getEntranceAt(3)));
        paths.add(new Path(allNodes.get(17),nigh.getEntranceAt(3)));
        paths.add(new Path(nigh.getEntranceAt(3),allNodes.get(15)));
        paths.add(new Path(nigh.getEntranceAt(3),allNodes.get(17)));
        paths.add(new Path(nigh.getEntranceAt(9),allNodes.get(16)));
        paths.add(new Path(allNodes.get(16),nigh.getEntranceAt(9)));
        paths.add(new Path(allNodes.get(18),nigh.getEntranceAt(8)));
        paths.add(new Path(nigh.getEntranceAt(8),allNodes.get(18)));
        
        
        
    }
    
    public void setStart(double lat, double lon, String buildingName){
        start = new Node(lat,lon,"ent",0,true);
        switch (buildingName){
            case "HoH":
                if(ada){
                    for(Node e: hoh.adaEntrances){
                        Path temp = new Path(start, e);
                        if(toEntrance == null || temp.getLength()<toEntrance.getLength()){
                            toEntrance = temp;
                        }
                    }
                }
                else{
                    for(Node e: hoh.entrances){
                        Path temp = new Path(start, e);
                        if(toEntrance == null || temp.getLength()<toEntrance.getLength()){
                            toEntrance = temp;
                        }
                    }
                }
                break;
            case "cmsc":
                if(ada){
                    for(Node e: cmsc.adaEntrances){
                        Path temp = new Path(start, e);
                        if(toEntrance == null || temp.getLength()<toEntrance.getLength()){
                            toEntrance = temp;
                        }
                    }
                }
                else{
                    for(Node e: cmsc.entrances){
                        Path temp = new Path(start, e);
                        if(toEntrance == null || temp.getLength()<toEntrance.getLength()){
                            toEntrance = temp;
                        }
                    }
                }
                break;
        }
    }
    
    public void setEnd(double lat, double lon, String buildingName){
        finish = new Node(lat,lon,"ent",0,true);
        switch (buildingName){
            case "HoH":
                if(ada){
                    for(Node e: hoh.adaEntrances){
                        Path temp = new Path(finish, e);
                        if(toEnd == null || temp.getLength()<toEnd.getLength()){
                            toEnd = temp;
                        }
                    }
                }
                else{
                    for(Node e: hoh.entrances){
                        Path temp = new Path(finish, e);
                        if(toEnd == null || temp.getLength()<toEnd.getLength()){
                            toEnd = temp;
                        }
                    }
                }
                break;
            case "cmsc":
                if(ada){
                    for(Node e: cmsc.adaEntrances){
                        Path temp = new Path(finish, e);
                        if(toEnd == null || temp.getLength()<toEnd.getLength()){
                            toEnd = temp;
                        }
                    }
                }
                else{
                    for(Node e: cmsc.entrances){
                        Path temp = new Path(finish, e);
                        if(toEnd == null || temp.getLength()<toEnd.getLength()){
                            toEnd = temp;
                        }
                    }
                }
                break;
        }
    }
    
    public LinkedList<Node> search(){
        ArrayList<Building> buildings = new ArrayList<>();
        buildings.add(cmsc);
        buildings.add(hoh);
        buildings.add(chs);
        buildings.add(nigh);
        ArrayList<Node> entrances = new ArrayList<>();
        ArrayList<Node> nodes = new ArrayList<>();

        if(ada){
            for(Building b : buildings){
                entrances.addAll(b.adaEntrances);
            }
            for(Node n: allNodes){
                if(n.getADA()){
                    nodes.add(n);
                }
            }
        } else{
            for(Building b: buildings){
                entrances.addAll(b.entrances);
            }
            nodes.addAll(allNodes);
        }
        
        nodes.addAll(entrances);
        Navigator navi = new Navigator(nodes,paths);
        navi.execute(toEntrance.getDestination());
        LinkedList<Node> route = navi.getPath(toEnd.getDestination());
        return route;
    }

    private void readXML(){
        try{        
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            //Must update nodeXMLPath to the absolute path once moved to CS Server
            String nodeXMLPath = "/home/gq/gq027/public_html/ucoNavigation/src/Control/node.xml";
            InputStream in = new FileInputStream(nodeXMLPath);
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
                        if (event.asStartElement().getName().getLocalPart().equals("lat")) {
                            event = eventReader.nextEvent();
                            node.setLat(Integer.parseInt(event.asCharacters().getData()));
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart().equals("lon")) {
                        event = eventReader.nextEvent();
                        node.setLon(Integer.parseInt(event.asCharacters().getData()));
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

    public void setADA(Boolean a){
        ada=a;
    }
    
    
}//end NodeManager
