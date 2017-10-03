/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Control.NodeManager;
import javax.swing.JFrame;

/**
 *
 * @author Noah G
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame window = new NodeManager();
        window.setSize(860,600);
        window.setLocation(100,100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setTitle("");
    }
    
}
