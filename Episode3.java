// Author: Chris Miller
// Date: 8/3/2020
// Episode 3 class

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

public class Episode3{

      public static void main(String[] args){
            String location = "Garage";// the current location of the player
            Inventory playerInventory = new Inventory();
            ArrayList<ArrayList<String>> items = new ArrayList<ArrayList<String>>();
            
            ArrayList<String> temp = new ArrayList<String>();
            temp.add("");
            
            items.add(temp);  
            ArrayList<String> kitchen = new ArrayList<String>();
            
            items.add(kitchen);
            
            boolean[] visits = {true,true,true,false,true,false,true,true,true,true,true,false,true,true,true,true,true};
            boolean[] locks = {false,false,false,false,false,true,false,false,false,false,false,true,false,false,false,false,false};
            
            
            Map theMap = new Map(items,visits,locks);
            Input input = new Input("Day ?", playerInventory, location, theMap); 
       
            Episode3 test = new Episode3();
            test.play();// start the background music
            
            String text = "You awake in a large vehicle, behind the wheel; seatbelt wrapped tightly across your chest. As a dull pain in your head fades away, you recognize the vehicle as the one that collided with the house earlier. \nA faint glint of gray light flashes in through the window of the car and you realize that someone dragged you from the bathroom back to the garage and placed you in the car. \nYou rub your eyes and unbuckle yourself, turning around to see if the trunk is still sealed shut; it is. You climb out of the car and both feet splash into a small puddle.\nThe dull gray light, growing darker by the minute, reflects off of multiple puddles throughout the garage. In order for this much water to have accumulated in the garage, you know that some time has passed,\n something feels off... \n";
            input.makeNewFrame(text, "map_15.png",480, 480);// set the background image, text, and the location of the player dot
      }
      
      // The series of events caused upon finishing the second day.
      public static void dayEnd(){      
            JFrame inventory = new JFrame ("End");
            JPanel panel1 = new JPanel(new BorderLayout());
            inventory.setSize(600,400);
            inventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inventory.setLocationRelativeTo(null);
            JButton close = new JButton("Exit");
            
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
                        System.exit(0);
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

            File noise = new File("BGM3.wav");
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