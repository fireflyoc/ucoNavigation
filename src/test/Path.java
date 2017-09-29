/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import static java.lang.Math.pow;


/**
 *
 * @author Noah
 */
public class Path{
    private Node node1;
    private Node node2;
    private final double length;
    
    public Path(Node n1, Node n2){
        node1 = n1;
        node2 = n2;
        length = pow((pow((n1.x-n2.x),2) + pow((n1.y-n2.y),2)),0.5);
    }

    public Node getSource() {
        return node1;
    }

    public void setNode1(Node node1) {
        this.node1 = node1;
    }

    public Node getDestination() {
        return node2;
    }

    public void setNode2(Node node2) {
        this.node2 = node2;
    }
    
    public double getLength(){
        return length;
    }
}
