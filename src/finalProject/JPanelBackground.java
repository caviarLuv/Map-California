package finalProject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author Ashley
 */
public class JPanelBackground extends JPanel{
    private Image backgroundImg = null;
   
    public JPanelBackground(String link){
        try{
           this.backgroundImg = ImageIO.read(new File(link));
          // this.backgroundImg = ImageIO.read(getClass().getResource("src/images/background/projectMap.png"));
          //this.backgroundImg = new ImageIcon("/Users/Ashley/NetBeansProjects/finalProject/images/background/projectMap.png").getImage();
        }catch(IOException ex){
           ex.printStackTrace();
            this.backgroundImg = null;
       }
        setOpaque(false);
    }
    
    public void paintComponent(Graphics g){
        g.drawImage(backgroundImg,0,0, this);
        super.paintComponent(g);
    }
}
