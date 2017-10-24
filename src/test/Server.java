/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Control.NodeManager;

/**
 *
 * @author Noah G
 */
public class Server {
        private final static int PORT=4444;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        //NodeManager manager = new NodeManager();
        new WebSeverSocket().start();
        
    }
    
}
