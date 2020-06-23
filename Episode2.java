// Author: Chris Miller
// Date: 6/19/2020
// Episode 2 class

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


public class Episode2{
      
      int choice = 0;// the players choice 
      public static String location = "TV";// the current location of the player
      public static Inventory playerInventory = new Inventory();
      //public static Inventory playerInventory = new Inventory(2, 7, 4, 3, 2, 1);
      // the main function, called upon clicking the 'Episode 2' button at the main menu
      public static void main(String[] args){
            Map theMap = new Map();
            Input input = new Input("Day 2", playerInventory, location, theMap); 
            String text = "You awaken in a dark and dirty room, atop a sofa before a TV. Behind you is an open door into a Kitchen. \n";
            input.makeNewFrame(text, "tv.jpg");
      }


}// end of episode 2