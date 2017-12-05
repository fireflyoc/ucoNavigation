package main;

import Control.JSONReader;
import Control.JSONWriter;
import Control.NodeManager;
import Model.Node;
import java.util.LinkedList;

/**
 *
 * @author Decker
 */
public class MockWebServer {

    private JSONReader reader;
    private JSONWriter writer;
    private NodeManager manager;
    LinkedList<Node> route;
    String routeString = "";

    public MockWebServer() {
        route = new LinkedList<>();
        writer = new JSONWriter();
        manager = new NodeManager();

        mockRecieveMessage();
    }

    private void mockRecieveMessage() {

        manager.setADA(false);
        //manager.setStart(35.654079,-97.472848,"cmsc"); 
        //manager.setEnd(35.654772,-97.472550,"hoh");
        route = manager.search();
        routeString = writer.convertListToJsonArray(route);
        System.out.println(routeString);
    }

}
