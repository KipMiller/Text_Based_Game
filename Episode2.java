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

      public static void main(String[] args){
            String location = "Master Bedroom";// the current location of the player
            Inventory playerInventory = new Inventory();
            ArrayList<ArrayList<String>> items = new ArrayList<ArrayList<String>>();
            
            ArrayList<String> temp = new ArrayList<String>();
            temp.add("");
            
            items.add(temp);          

            ArrayList<String> kitchen = new ArrayList<String>();
            kitchen.add("");//add the garage key to the kitchen
            
            items.add(kitchen);

            boolean[] visits = {true,true,false,false,true,false,true,true,true,true,true,false,false,true,false,true,true};// which rooms have been visited
            boolean[] locks = {false,false,true,true,false,true,false,false,false,false,false,true,true,false,false,false,false};// which rooms are locked / unlock
               
            Map theMap = new Map(items,visits,locks);
            Input input = new Input("Day 2", playerInventory, location, theMap); 

            Episode2 test = new Episode2();
            //test.play();// start the background music
            
            String text = "You awake in the Master Bedroom, your head still aching from the fall. You feintly recall the growing sound of whispers the night before, but after quickly glancing around the room, you are sure you are alone for now. Looking out the window, you see the back end of the car jutting out of the garage below you, it appears to have gotten stuck halfway into the garage. \n";
            input.makeNewFrame(text, "map_12.png", 650, 220);// set the background image, text, and the location of the player dot
      }
      
      // The series of events caused upon finishing the second day.
      public static void dayEnd(){      
            JFrame inventory = new JFrame ("Day 2 End");
            JPanel panel1 = new JPanel(new BorderLayout());
            inventory.setSize(600,400);
            inventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inventory.setLocationRelativeTo(null);
            JButton close = new JButton("Day ?");
            
            JTextArea items = new JTextArea(30,30);// displays the text for the current frame, can be updated with setText
            items.setEditable(false);
 
            items.setLineWrap(true);
            items.setText("Walking into the bathroom from the pitch black hallway, you fumble along the walls for the lightswitch. You hear an accompanying click from the switches as you flick them up and down multiple times. Holding up your flashlight, you gaze around the bathroom, for anything that might help you out of this house; a house you have faint memories of living in...\nAs you open the cabinet underneath the sink and kneel down to look inside, the unmistakable sound of breathing. You bolt upright so fast that you hit your head on the edge of the sink; dizzy, you turn to your right and point the flashlight at the curtain surrounding the bath. As the light swpet across the plastic surface of the curtain, the sillhouette of a man became clear. He, or it, was standing perfectly still in the bath, letting a breath out at an unnatural pace. You slowly climb to your feet and debate pulling aside the curtain, instead you reach for the door handle and turn it as slow as possible. The breathing from behind the curtain stops and a rustling of the plastic indicates the man shaped being stepping out of the tub. Your drop the flashlight and use both hands to throw open the door as fast as possible, though as you pass through the threshold of the door, two strong hands grasp each of your shoulders and pull you back into the bathroom... \n");
          
            inventory.getContentPane().add(panel1,"Center");
            panel1.add(close, BorderLayout.PAGE_END);
            panel1.add(items, BorderLayout.CENTER);
            inventory.setVisible(true);
            
            close.addActionListener(new ActionListener(){// set up the close inventory button usage 
                  public void actionPerformed(ActionEvent e){
                        inventory.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        inventory.dispatchEvent(new WindowEvent(inventory, WindowEvent.WINDOW_CLOSING));
                        //System.exit(0);

                        String[] args = {"",""};
                        Episode3.main(args);// go into the episode 2 main function
                  }
            });
      }

    // Method that will call the sound alarm constructor and create the sound clip object that will be played.
    // After the sound clip has been created and started, when the user clicks the close button on the dialog
    // the sound will cease to play.
    public void play(){
        Clip bgm = this.playMusic();

    }
  
    /*
    Method that creates and starts the audio clip object, in this case an alarm sound effect will start
    when this method is done, also returns said clip object so that we can successfully stop the sound
    with a button press when we no longer need it to play.
    */
    public Clip playMusic(){
        try{
            File noise = new File("BGM2.wav");
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

}// end of episode 2