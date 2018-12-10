package finalProject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tsz Ting (Ashley) Yu
 */

import javax.swing.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileSystemView;

public class MapCal extends JFrame{

    protected ImageIcon[] bannerIcon = {new ImageIcon("images/banner/Napa.png"),
    new ImageIcon("images/banner/Alcatraz.png"),
    new ImageIcon("images/banner/GGBridge.png"),
    new ImageIcon("images/banner/Hollywood.png"),
    new ImageIcon("images/banner/Salvation.png")};
    protected DataReader data;
    private JButton[] locations = new JButton[5];
    private Rectangle[] positions = {new Rectangle(191,161,20,20),
    new Rectangle(169,246,20,20),
    new Rectangle(151,325,20,20),
    new Rectangle(341,523,20,20),
    new Rectangle(498,580,20,20)};
    int currentLocation = -1;
    private String[] names = {"Napa","Alcatraz","Golden Gate Bridge",
        "HollyWood Sign","Salvation Mountain"};
    JButton addPic;
    JButton back;
    JButton changePic;
    public MapCal(){
        this.setTitle("Map Album");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,695);
        this.setResizable(false);
        
        init();
        
        this.setVisible(true);
    }
    public void init(){  //read data from file            
        try{
           data = new DataReader();
           
        }
        catch(Exception e){
            e.printStackTrace();
        }
        drawMap();
    }
    public void drawMap(){
        
        this.getContentPane().removeAll();
         //-------------------------------------------------
        JPanelBackground panel = new JPanelBackground("images/background/projectMap.png");
        this.setContentPane(panel);
        this.getContentPane().setLayout(null);
       //-----------------------------------------
        //button setting
        
        for(int i = 0; i < 5; i++){
            locations[i] = new JButton();
            locations[i].setBounds(positions[i]);
            locations[i].setOpaque(false);
            locations[i].setContentAreaFilled(false);
            locations[i].setBorderPainted(false);
            locations[i].addMouseListener(new CustomMouseListener());
           panel.add(locations[i]);
        }
        
        //this.add(panel);
        panel.addMouseListener(new CustomMouseListener());       
        this.revalidate();
        this.repaint(); 
    }
    
    //display the photo if exist, if not, show no photo added yet
   public void display(int num){ 
       if(data.photoAvailable[num] == 0){        
       this.getContentPane().removeAll();
       JPanelBackground panel = new JPanelBackground("images/background/bg_01.png");     
       this.setContentPane(panel);
       this.getContentPane().setLayout(null);
       addPic = new JButton("Add a picture");
       addPic.setBounds(22,606,100,50);
       addPic.addMouseListener(new CustomMouseListener());
       back = new JButton("Go Back");
       back.setBounds(460,606,100,50);
       back.addMouseListener(new CustomMouseListener());
       panel.add(addPic);
       panel.add(back);
       
       JLabel bannerL = new JLabel(bannerIcon[num],JLabel.LEFT);
       bannerL.setBounds(0, 10, 600, 92);
       panel.add(bannerL);
         
       this.revalidate();
       this.repaint();
       }
       else{
           this.getContentPane().removeAll();
           JPanelBackground panel = new JPanelBackground("images/background/bg_02.png");
           JPanel background = new JPanel();
           background.setBackground(Color.WHITE);
           this.setContentPane(background);
           //this.setContentPane(panel);
           this.getContentPane().setLayout(null);
           panel.setBounds(0, 0,600, 600);
           this.getContentPane().add(panel);
           //display picture
           ImageIcon photo = new ImageIcon(data.direct[num]);
           JLabel photoL = new JLabel(photo,JLabel.CENTER);
           photoL.setBounds(72,197,450,323);
           background.add(photoL);      
           changePic = new JButton("Change");
           changePic.setBounds(22,606,100,50);
           changePic.addMouseListener(new CustomMouseListener());
           back = new JButton("Go Back");
           back.setBounds(460,606,100,50);
           back.addMouseListener(new CustomMouseListener());
           background.add(changePic);
           background.add(back);
       
           JLabel bannerL = new JLabel(bannerIcon[num],JLabel.LEFT);
           bannerL.setBounds(0, 10, 600, 92);
           panel.add(bannerL);
           
           this.revalidate();
           this.repaint();
       }
   }
   
    public class CustomMouseListener implements MouseListener{
       public void mouseClicked(MouseEvent e) {
    }
    
    public void mousePressed(MouseEvent e) {
       if(e.getSource().equals(locations[0])){
          currentLocation = 0;
          display(currentLocation);
       }
       else if(e.getSource().equals(locations[1])){
          currentLocation = 1;
          display(currentLocation);
       }
       else if(e.getSource().equals(locations[2])){
          currentLocation = 2;
          display(currentLocation);    
       }
       else if(e.getSource().equals(locations[3])){
          currentLocation = 3;
          display(currentLocation);
       }
       else if(e.getSource().equals(locations[4])){
          currentLocation = 4;
          display(currentLocation);
       }
       else if(e.getSource().equals(addPic)){
           String directory = fileChooser();
           if(directory != null){
               data.update(currentLocation, directory);
               display(currentLocation);
           }
           else{
           }
       }
       else if(e.getSource().equals(back)){
          drawMap();
       } 
       else if(e.getSource().equals(changePic)){
           String directory = fileChooser();
           if(directory != null){
               data.update(currentLocation, directory);  
               display(currentLocation);
           }
           else{
           }
       }
    }

    public void mouseReleased(MouseEvent e) {
       
    }
    
    public void mouseEntered(MouseEvent e) {
        
    }
    
    public void mouseExited(MouseEvent e) {
        
    }
}
    public String fileChooser(){
        String directory = null;
        JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
        int returnValue = jfc.showOpenDialog(null);
        
        if(returnValue == JFileChooser.APPROVE_OPTION){
            directory = jfc.getSelectedFile().getAbsolutePath();
        }
        return directory;
    }
        public static void main(String[] args){
           MapCal window = new MapCal();
    }
}
