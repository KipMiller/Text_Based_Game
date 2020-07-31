// Author : Chris Miller
// 5/19/2020
// Inventory class, used to keep track of items encountered by the player

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.*;
import javax.sound.sampled.*;
import java.net.*;
import javax.sound.sampled.*;


public class Inventory {
      // items of the players inventory 
      public static int ammo; 
      public static int health;
      public static ArrayList<String> keys;
      public static int flashlight;
      public static int batteries;
      public static int gun; 
      public static String inventoryString;
   
      Inventory(){// default constructor for the inventory class 
            ammo = 0;
            health = 10;
            flashlight = 0;
            batteries = 0;
            gun = 0;
            keys = new ArrayList<String>();
      }
      
      Inventory(int ammo,int health ,int flashlight,int batteries,int gun){// constructor used to carry over inventory between chapters/set inventory numbers
            this.ammo = ammo;
            this.health = health;
            this.flashlight = flashlight;
            this.batteries = batteries; 
            this.gun = gun; 
            keys = new ArrayList<String>();
      }

      public void reset(){// function to reset inventory to starting values 
            ammo = 0;
            health = 10;
            flashlight = 0;
            batteries = 0;
            gun = 0;
      }
      
      public void addItem(String item){
         keys.add(item);
      
      }

      public void addFlashlight(){
         flashlight = 1;
      }
      
      public boolean hasFlashlight(){
         return (flashlight == 1);
      }

      // given our targeted room, if we have a key that can open that door, return true and remove the key
      public boolean validKey(String destination){
         for(int i = 0; i < keys.size(); i++){
            if(keys.get(i).contains(destination)){
               keys.remove(i);
               return true;
               
            }
         }
         return false; 
      }
      
      
      
      public void makeString(){
            inventoryString = ("\t" + "Health: " + health + "\n");
            if(ammo != 0){
                  inventoryString += ("\t" + "Ammo: " + ammo + "\n");
            }
            if(keys.size() != 0){
                  inventoryString += ("\t" + "Keys: " + keys + "\n");
            }
            if(flashlight != 0){
                  inventoryString += ("\t" + "Flashlight: " + flashlight + "\n");
            }
            if(batteries != 0){
                  inventoryString += ("\t" + "Batteries: " + batteries + "\n");
            }
            if(gun != 0){
                  inventoryString += ("\t" + "Gun: " + gun + "\n");
            }
      }
  
      // function to pull up a new window displaying the players inventory count of items 
      public void checkInventory(){
            
            JFrame inventory = new JFrame ("Inventory");
            JPanel panel1 = new JPanel(new BorderLayout());
            inventory.setSize(400,400);
            inventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inventory.setLocationRelativeTo(null);
            JButton close = new JButton("Close Inventory");
            
            JTextArea items = new JTextArea(20,20);// displays the text for the current frame, can be updated with setText
            items.setEditable(false);
            makeString();
            
            System.out.println("inventoryString = " + inventoryString);
            items.setText(inventoryString);
            
            inventory.getContentPane().add(panel1,"Center");
            panel1.add(close, BorderLayout.PAGE_END);
            panel1.add(items, BorderLayout.CENTER);
            inventory.setVisible(true);
            
            close.addActionListener(new ActionListener(){// set up the close inventory button usage 
                  public void actionPerformed(ActionEvent e){
                        inventory.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        inventory.dispatchEvent(new WindowEvent(inventory, WindowEvent.WINDOW_CLOSING));
                  }
            });
      }
  
      
}// end of inventory class 