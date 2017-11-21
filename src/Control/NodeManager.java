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
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
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
public class NodeManager {

    ArrayList<Path> paths;
    ArrayList<Node> allNodes;
    ArrayList<Building> buildings;

    Node start, finish;
    int x, y = 0;
    ArrayList<Path> finalPath;
    Path toEntrance = null, toEnd = null;
    
    JTextField text;
    Boolean ada;

    public NodeManager() {
        paths = new ArrayList<>();
        allNodes = new ArrayList<>();
        buildings = new ArrayList<>();
        text = new JTextField();
        text.setPreferredSize(new Dimension(500, 25));
        text.setEditable(false);
        //Setup buildings
        //building(SW - NE)

        buildings.add(new Building("cmsc", new Point2D.Double(-97.473048, 35.653745), new Point2D.Double(-97.472527, 35.654258)));
        buildings.add(new Building("hoh", new Point2D.Double(-97.472978, 35.654491), new Point2D.Double(-97.472075, 35.655192)));
        buildings.add(new Building("nigh", new Point2D.Double(-97.471831, 35.654403), new Point2D.Double(-97.470953, 35.655913)));
        buildings.add(new Building("chs", new Point2D.Double(-97.473915, 35.653193), new Point2D.Double(-97.473219, 35.653703)));

        readXML();
        //Setup Entrances
        setUpEntrances();
        //set up paths
        setUpPaths();
     
    } //end constuctor
//
//    public void setStart(double lat, double lon, String buildingName) {
//        start = new Node(lat, lon, "ent", 0, true);
//        switch (buildingName) {
//            case "HoH":
//                if (ada) {
//                    for (Node e : hoh.adaEntrances) {
//                        Path temp = new Path(start, e);
//                        if (toEntrance == null || temp.getLength() < toEntrance.getLength()) {
//                            toEntrance = temp;
//                        }
//                    }
//                } else {
//                    for (Node e : hoh.entrances) {
//                        Path temp = new Path(start, e);
//                        if (toEntrance == null || temp.getLength() < toEntrance.getLength()) {
//                            toEntrance = temp;
//                        }
//                    }
//                }
//                break;
//            case "cmsc":
//                if (ada) {
//                    for (Node e : cmsc.adaEntrances) {
//                        Path temp = new Path(start, e);
//                        if (toEntrance == null || temp.getLength() < toEntrance.getLength()) {
//                            toEntrance = temp;
//                        }
//                    }
//                } else {
//                    for (Node e : cmsc.entrances) {
//                        Path temp = new Path(start, e);
//                        if (toEntrance == null || temp.getLength() < toEntrance.getLength()) {
//                            toEntrance = temp;
//                        }
//                    }
//                }
//                break;
//        }
//    }
//
//    public void setEnd(double lat, double lon, String buildingName) {
//        finish = new Node(lat, lon, "ent", 0, true);
//        switch (buildingName) {
//            case "HoH":
//                if (ada) {
//                    for (Node e : hoh.adaEntrances) {
//                        Path temp = new Path(finish, e);
//                        if (toEnd == null || temp.getLength() < toEnd.getLength()) {
//                            toEnd = temp;
//                        }
//                    }
//                } else {
//                    for (Node e : hoh.entrances) {
//                        Path temp = new Path(finish, e);
//                        if (toEnd == null || temp.getLength() < toEnd.getLength()) {
//                            toEnd = temp;
//                        }
//                    }
//                }
//                break;
//            case "cmsc":
//                if (ada) {
//                    for (Node e : cmsc.adaEntrances) {
//                        Path temp = new Path(finish, e);
//                        if (toEnd == null || temp.getLength() < toEnd.getLength()) {
//                            toEnd = temp;
//                        }
//                    }
//                } else {
//                    for (Node e : cmsc.entrances) {
//                        Path temp = new Path(finish, e);
//                        if (toEnd == null || temp.getLength() < toEnd.getLength()) {
//                            toEnd = temp;
//                        }
//                    }
//                }
//                break;
//        }
//    }

    public LinkedList<Node> search() {
        
        ArrayList<Node> entrances = new ArrayList<>();
        ArrayList<Node> nodes = new ArrayList<>();

        if (ada) {
            for (Building b : buildings) {
                entrances.addAll(b.adaEntrances);
            }
            for (Node n : allNodes) {
                if (n.getADA()) {
                    nodes.add(n);
                }
            }
        } else {
            for (Building b : buildings) {
                entrances.addAll(b.entrances);
            }
            nodes.addAll(allNodes);
        }

        nodes.addAll(entrances);
        Navigator navi = new Navigator(nodes, paths);
        navi.execute(toEntrance.getDestination());
        LinkedList<Node> route = navi.getPath(toEnd.getDestination());
        return route;
    }

    private void readXML() {
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            //Must update nodeXMLPath to the absolute path once moved to CS Server
            String nodeXMLPath = "C:\\Users\\Decker\\Documents\\NetBeansProjects\\ucoNavigation\\src\\Control\\node.xml";
            //String nodeXMLPath = "/home/gq/gq027/public_html/ucoNavigation/src/Control/node.xml";
            InputStream in = new FileInputStream(nodeXMLPath);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            Node node = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equalsIgnoreCase("Node")) {
                        node = new Node();
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equalsIgnoreCase("name")) {
                            event = eventReader.nextEvent();
                            node.setID(event.asCharacters().toString());
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equalsIgnoreCase("lat")) {
                            event = eventReader.nextEvent();
                            node.setLat(Double.parseDouble(event.asCharacters().getData()));
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart().equalsIgnoreCase("lon")) {
                        event = eventReader.nextEvent();
                        node.setLon(Double.parseDouble(event.asCharacters().getData()));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart().equalsIgnoreCase("type")) {
                        event = eventReader.nextEvent();
                        node.setType(Integer.parseInt(event.asCharacters().getData()));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equalsIgnoreCase("ada")) {
                        event = eventReader.nextEvent();
                        node.setADA(Boolean.parseBoolean(event.asCharacters().getData()));

                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart().equalsIgnoreCase("active")) {
                        event = eventReader.nextEvent();
                        node.setActive(Boolean.parseBoolean(event.asCharacters().getData()));
                        continue;
                    }
                }
                // If we reach the end of an item element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equalsIgnoreCase("node")) {
                        allNodes.add(node);
                    }
                }

            }
        } catch (FileNotFoundException | XMLStreamException e) {
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

    public void setADA(Boolean a) {
        ada = a;
    }

    private void setUpEntrances() {
       for (Node n : allNodes) {
            if (n.getType() == 0) { //Node is an entrance
                for (Building b : buildings) {
                    //if it is in building
                    if (n.isInBuilding(b.getSWCorner(), b.getNECorner()) ) {
                        b.addEntrance(n);
                    }//end inf

                }//end building for each

            } //end node is entrance
        }// end node n foreach
        System.out.println("Entrnaces done");
    }

    private void setUpPaths() {
        System.out.println("Starting paths");
        /*  //Setup Paths
        paths.add(new Path(allNodes.get(0), allNodes.get(2))); //Path from CMSC North Entrance up
        paths.add(new Path(allNodes.get(1), allNodes.get(2))); //E/W Path above CMSC
        paths.add(new Path(allNodes.get(2), allNodes.get(3))); //Path from CMSC East Entrance up
        paths.add(new Path(allNodes.get(3), allNodes.get(4))); //Path heading North along West side of HOH
        paths.add(new Path(allNodes.get(3), allNodes.get(5))); //Path heading North along West side of HOH
        paths.add(new Path(allNodes.get(5), allNodes.get(6))); //Path heading East along North side of HOH
        paths.add(new Path(allNodes.get(6), allNodes.get(7))); //Short path heading East along North Side of HOH
        paths.add(new Path(allNodes.get(7), allNodes.get(8))); //Path from North side of HOH to NC entrance
        paths.add(new Path(allNodes.get(8), allNodes.get(9))); //Diaganol path on NE side of HOH
        paths.add(new Path(allNodes.get(9), allNodes.get(10))); //Path South between Nigh and HOH
        paths.add(new Path(allNodes.get(10), allNodes.get(11))); //Path South between Nigh and HOH
        paths.add(new Path(allNodes.get(11), allNodes.get(12))); //Path East towards East HOH Entrances
        paths.add(new Path(allNodes.get(12), allNodes.get(13))); //Path South from East HOH Entrances
        paths.add(new Path(allNodes.get(11), allNodes.get(14))); //Path East between HOH and CMSC
        paths.add(new Path(allNodes.get(13), allNodes.get(14)));
        paths.add(new Path(allNodes.get(13), allNodes.get(0)));
        paths.add(new Path(allNodes.get(10), allNodes.get(15))); //Path North along West side of HOH to NW corner
        paths.add(new Path(allNodes.get(10), allNodes.get(16)));
        paths.add(new Path(allNodes.get(16), allNodes.get(18)));
        paths.add(new Path(allNodes.get(10), allNodes.get(17)));
        paths.add(new Path(allNodes.get(1), allNodes.get(19)));
        paths.add(new Path(allNodes.get(19), allNodes.get(20)));
        paths.add(new Path(allNodes.get(20), allNodes.get(21)));
        paths.add(new Path(allNodes.get(20), allNodes.get(22)));
        paths.add(new Path(allNodes.get(22), allNodes.get(23)));
        //Adding Symmetrical Paths
        paths.add(new Path(allNodes.get(2), allNodes.get(0))); //Path from CMSC North Entrance up
        paths.add(new Path(allNodes.get(2), allNodes.get(1))); //E/W Path above CMSC
        paths.add(new Path(allNodes.get(3), allNodes.get(2))); //Path from CMSC East Entrance up
        paths.add(new Path(allNodes.get(4), allNodes.get(3))); //Path heading North along West side of HOH
        paths.add(new Path(allNodes.get(5), allNodes.get(3))); //Path heading North along West side of HOH
        paths.add(new Path(allNodes.get(6), allNodes.get(5))); //Path heading East along North side of HOH
        paths.add(new Path(allNodes.get(7), allNodes.get(6))); //Short path heading East along North Side of HOH
        paths.add(new Path(allNodes.get(8), allNodes.get(7))); //Path from North side of HOH to NC entrance
        paths.add(new Path(allNodes.get(9), allNodes.get(8))); //Diaganol path on NE side of HOH
        paths.add(new Path(allNodes.get(10), allNodes.get(9))); //Path South between Nigh and HOH
        paths.add(new Path(allNodes.get(11), allNodes.get(10))); //Path South between Nigh and HOH
        paths.add(new Path(allNodes.get(12), allNodes.get(11))); //Path East towards East HOH Entrances
        paths.add(new Path(allNodes.get(13), allNodes.get(12))); //Path South from East HOH Entrances
        paths.add(new Path(allNodes.get(14), allNodes.get(11))); //Path East between HOH and CMSC
        paths.add(new Path(allNodes.get(14), allNodes.get(13)));
        paths.add(new Path(allNodes.get(0), allNodes.get(13)));
        paths.add(new Path(allNodes.get(15), allNodes.get(10))); //Path North along West side of HOH to NW corner
        paths.add(new Path(allNodes.get(16), allNodes.get(10)));
        paths.add(new Path(allNodes.get(18), allNodes.get(16)));
        paths.add(new Path(allNodes.get(17), allNodes.get(10)));
        paths.add(new Path(allNodes.get(19), allNodes.get(1)));
        paths.add(new Path(allNodes.get(20), allNodes.get(19)));
        paths.add(new Path(allNodes.get(21), allNodes.get(20)));
        paths.add(new Path(allNodes.get(22), allNodes.get(20)));
        paths.add(new Path(allNodes.get(23), allNodes.get(22)));
      */
        //Adding Paths to Entrances
        Node n = buildings.get(0).getEntranceAt(0);
        System.out.println("N: "+n.getID());
        paths.add(new Path( buildings.get(0).getEntranceAt(0), allNodes.get(0)));
        paths.add(new Path(allNodes.get(0),  buildings.get(0).getEntranceAt(0)));
        paths.add(new Path( buildings.get(0).getEntranceAt(1), allNodes.get(1)));
        paths.add(new Path(allNodes.get(1),  buildings.get(0).getEntranceAt(1)));
       /* paths.add(new Path(allNodes.get(21), chs.getEntranceAt(1)));
        paths.add(new Path(allNodes.get(23), chs.getEntranceAt(0)));
        paths.add(new Path(chs.getEntranceAt(0), allNodes.get(23)));
        paths.add(new Path(chs.getEntranceAt(1), allNodes.get(21)));
        paths.add(new Path(hoh.getEntranceAt(0), allNodes.get(4)));
        paths.add(new Path(allNodes.get(4), hoh.getEntranceAt(0)));
        paths.add(new Path(hoh.getEntranceAt(1), allNodes.get(12)));
        paths.add(new Path(allNodes.get(12), hoh.getEntranceAt(1)));
        paths.add(new Path(hoh.getEntranceAt(2), allNodes.get(4)));
        paths.add(new Path(allNodes.get(4), hoh.getEntranceAt(2)));
        paths.add(new Path(hoh.getEntranceAt(3), allNodes.get(12)));
        paths.add(new Path(allNodes.get(12), hoh.getEntranceAt(3)));
        paths.add(new Path(hoh.getEntranceAt(4), allNodes.get(12)));
        paths.add(new Path(allNodes.get(12), hoh.getEntranceAt(4)));
        paths.add(new Path(hoh.getEntranceAt(5), allNodes.get(10)));
        paths.add(new Path(allNodes.get(10), hoh.getEntranceAt(5)));
        paths.add(new Path(allNodes.get(5), hoh.getEntranceAt(6)));
        paths.add(new Path(hoh.getEntranceAt(6), allNodes.get(5)));
        paths.add(new Path(allNodes.get(11), nigh.getEntranceAt(1)));
        paths.add(new Path(nigh.getEntranceAt(1), allNodes.get(11)));
        paths.add(new Path(allNodes.get(15), nigh.getEntranceAt(3)));
        paths.add(new Path(allNodes.get(17), nigh.getEntranceAt(3)));
        paths.add(new Path(nigh.getEntranceAt(3), allNodes.get(15)));
        paths.add(new Path(nigh.getEntranceAt(3), allNodes.get(17)));
        paths.add(new Path(nigh.getEntranceAt(9), allNodes.get(16)));
        paths.add(new Path(allNodes.get(16), nigh.getEntranceAt(9)));
        paths.add(new Path(allNodes.get(18), nigh.getEntranceAt(8)));
        paths.add(new Path(nigh.getEntranceAt(8), allNodes.get(18)));*/
    }

}//end NodeManager
