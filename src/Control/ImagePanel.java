/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Noah
 */
public class ImagePanel extends JPanel{
    private BufferedImage map;
    public ImagePanel(){
        try{
            map = ImageIO.read(getClass().getResource("campus.png"));
        } catch (IOException ex){
            System.out.println("Excpetion: " + ex);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(map, 0, 0, this);
    }
}
