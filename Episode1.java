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
            
            Episode1 test = new Episode1();
            test.play();// start the background music
            
            String text = "You awake in a small bedroom which is scarcely furnished. You see around you a bed, a closet, and a nighstand. \n";
            input.makeNewFrame(text, "map_4.png", 860, 245);// set the background image
      }
      
      public static void dayEnd(){      
            JFrame inventory = new JFrame ("Day 1 End");
            JPanel panel1 = new JPanel(new BorderLayout());
            inventory.setSize(600,400);
            inventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inventory.setLocationRelativeTo(null);
            JButton close = new JButton("Main Menu");
            
            JTextArea items = new JTextArea(20,20);// displays the text for the current frame, can be updated with setText
            items.setEditable(false);
 
            items.setLineWrap(true);
            items.setText("Upon entering what appears to be the master bedroom, you pull aside the blinds and the dull grey light floods the room.\n"
             + " Outside the window you see rows and rows of trees flanking a long gravel driveway. The grey light,\n while seemingly coming from somewhere unatural,"
              + " grows black as feint whispers surround you. A pair of headlights appear at the end of the driveway,\n the car behind them careening down the path at breakneck speeds."
              + " You let out a gasp as the car draws closer,\n clearly aiming to collide with the garage below you.\n Before you can get out of the master bedroom the car slams into the double garage doors and the floor beneath you shakes.\n"
              + " You fall to the ground the faint sound of whispering grows louder. Your eyes close tight as night falls...\n");
            
            inventory.getContentPane().add(panel1,"Center");
            panel1.add(close, BorderLayout.PAGE_END);
            panel1.add(items, BorderLayout.CENTER);
            inventory.setVisible(true);
            
            close.addActionListener(new ActionListener(){// set up the close inventory button usage 
                  public void actionPerformed(ActionEvent e){
                        inventory.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        inventory.dispatchEvent(new WindowEvent(inventory, WindowEvent.WINDOW_CLOSING));
                        String[] args = {"","",""};
                        Game.main(args);
                        
                  }
            });
      }
      
      
      
    

    // Method that will call the sound alarm constructor and create the sound clip object that will be played.
    // After the sound clip has been created and started, when the user clicks the close button on the dialog
    // the sound will cease to play.
    public void play(){
        Clip bgm = this.playAlarm();

    }
  
    /*
    Method that creates and starts the audio clip object, in this case an alarm sound effect will start
    when this method is done, also returns said clip object so that we can successfully stop the sound
    with a button press when we no longer need it to play.
    */
    public Clip playAlarm(){
        try{

            File noise = new File("BGM.wav");
            Clip bgm = AudioSystem.getClip();
            AudioInputStream stream = AudioSystem.getAudioInputStream(noise);
            bgm.open(stream);
            bgm.start();

           return bgm;
        // Catch all of the potential errors that come from the clip class
        } catch(UnsupportedAudioFileException e){
            System.out.println(e);
            return null;
        } catch(IOException e){
            System.out.println(e);
            return null;
        } catch( LineUnavailableException e){
            System.out.println(e);
            return null;
        }

    }

      
      
}// end of episode 1 