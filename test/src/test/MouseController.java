/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Noah
 */
public class MouseController implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("X: "+e.getX()+"\nY: "+e.getY());
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
