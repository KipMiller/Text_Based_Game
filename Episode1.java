// Author: Chris Miller
// Date: 6/19/2020
// Episode 1 

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


// Episode 1 class
public class Episode1 {
      
      //public static Inventory playerInventory = new Inventory(2, 7, 4, 3, 2, 1);
      // the main function, called upon clicking the 'Episode 2' button at the main menu
      public static void main(String[] args){
            
            
            int choice = 0;// the players choice 
            String location = "Bedroom";// the current location of the player
            Inventory playerInventory = new Inventory();
            ArrayList<ArrayList<String>> items = new ArrayList<ArrayList<String>>();
            
            ArrayList<String> temp = new ArrayList<String>();
            temp.add("");
            
            items.add(temp);
            
            
            ArrayList<String> kitchen = new ArrayList<String>();
            kitchen.add("Garage Key");
            kitchen.add("Ammo");
            
            items.add(kitchen);
            
            
            
            Map theMap = new Map(items);
            Input input = new Input("Day 1", playerInventory, location, theMap); 
            
            
            String text = "You awake in a small bedroom which is scarcely furnished. You see around you a bed, a closet, and a nighstand. Atop the nighstand is a piece of paper with a black mechanical pencil atop it.\n You grab the paper and pencil and begin sketching a map of your surroundings. \n";
            input.makeNewFrame(text, "MAP_top_floor_F.png");// set the background image
      }
}// end of episode 1 