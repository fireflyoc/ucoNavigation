/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Control.JSONReader;
import Control.NodeManager;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

/**
 *
 * @author Noah
 */
class WebSeverSocket extends WebSocketServer{

    private static int TCP_PORT = 4444;

    private Set<WebSocket> conns;
    
    private JSONReader reader;
    
    private NodeManager manager;

    public WebSeverSocket() {
        super(new InetSocketAddress(TCP_PORT));
        conns = new HashSet<>();
        manager = new NodeManager();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conns.add(conn);
        System.out.println("New connection from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        conns.remove(conn);
        System.out.println("Closed connection to " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        reader = new JSONReader();
        HashMap<String,String> map = reader.parseUserInput(message);
        manager.setADA(Boolean.parseBoolean(map.get("ada")));
        manager.setStart(Double.parseDouble(map.get("StartLat")),Double.parseDouble(map.get("StartLon")),map.get("StartNode"));
        manager.setEnd(Double.parseDouble(map.get("EndLat")),Double.parseDouble(map.get("EndLon")),map.get("EndNode"));
        manager.search();
        System.out.println("Message from client: " + message);
        for (WebSocket sock : conns) {
            sock.send(message);
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        //ex.printStackTrace();
        if (conn != null) {
            conns.remove(conn);
            // do some thing if required
        }
        System.out.println("ERROR from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }
    
}
