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
            kitchen.add("");//add the garage key to the kitchen
            
            items.add(kitchen);
            
            boolean[] visits = {true,true,true,false,true,false,true,true,true,true,true,false,true,true,true,true,true};// which rooms have been visited
            boolean[] locks = {false,false,false,false,false,true,false,false,false,false,false,true,false,false,false,false,false};// which rooms are locked/unlocked
            
            Map theMap = new Map(items,visits,locks);
            Input input = new Input("Day ?", playerInventory, location, theMap); 
       
            Episode3 test = new Episode3();
          //  test.play();// start the background music
            
            String text = "You awake in a large vehicle, behind the wheel; seatbelt wrapped tightly across your chest. As a dull pain in your head fades away, you recognize the vehicle as the one that collided with the house earlier. \nA faint glint of gray light flashes in through the window of the car and you realize that someone dragged you from the bathroom back to the garage and placed you in the car. \nYou rub your eyes and unbuckle yourself, turning around to see if the trunk is still sealed shut; it is. You climb out of the car and both feet splash into a small puddle.\nThe dull gray light, growing darker by the minute, reflects off of multiple puddles throughout the garage. In order for this much water to have accumulated in the garage, you know that some time has passed,\n something feels off... \n";
            input.makeNewFrame(text, "map_15.png",480, 480);// set the background image, text, and the location of the player dot
      }
      
      // The different endings for the game
      public static void dayEnd(String ending){      
            JFrame inventory = new JFrame ("End");
            JPanel panel1 = new JPanel(new BorderLayout());
            inventory.setSize(700,500);
            inventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inventory.setLocationRelativeTo(null);
            JButton close = new JButton("Exit");
            
            JTextArea items = new JTextArea(30,30);// displays the text for the current frame, can be updated with setText
            items.setEditable(false);
 
            items.setLineWrap(true);
            if(ending.equals("BAD")){// bad ending if they leave too early 
               items.setText("You put the SUV into reverse and back it up slowly; the crumpled remains of the garage door peel backwards as you reverse. Finally, a few feet away from the house, the SUV's engine shudders and stops, the lights cut out. You hop out of the vehicle and look around, breathing in the almost fresh air. The nightmare house sits before you, its paint chipped and peeling away, moss climbs the walls and all of the windows have faded beyond transparency. The dull grey light that has followed you since the start of this nightmare still clings to the sky above. The trees surrounding the house, while green, have an almost black-grey tinge to them. Wanting to get far from the house, you walk down the road and around the corner, the same path the careening vehicle took some time earlier. At the end of the road, just before the house went out of view, you turn around and and swallow hard as you stare at the mysterious house that seemingly trapped you for so long. The front door appears to have been left wide open; a figure standing in the doorway seems to be staring at you. You turn and run around the corner as quickly as you can down the long stretch of road. A thin grey fog permeates the air, making it hard to see, but you press onwards. The road seemingly has no end, but you keep running and running, is that the faint glimmer of sunlight around the bend?");            
            } else if(ending.equals("GOOD")){// good ending for going into the ritual room
               items.setText("The mysterious room, of which you can't seem to recall any details of, is pitch black. Slowly you raise your flashlight to illuminate the room and see it is completely devoid of any furniture or wall decorations. Nearly a perfect square, the room doesn't even have windows. What does attract your attention, however, is what appears to be chalk or dry paint all over the floor in the shape of the symbols you recall from the wooden-bound book in the study. Goosebumps cover your arms as an overwhelming feeling of sadness, or regret, washes over you. These symbols on the floor were a part of some ritual, you know that much from the journals around the house. The sound of a sniffle, followed by sobbing shocks you and causes you to drop your flashlight. There, in the corner of this square room, stands a man. Devoid of any clothing and facing the corner, this tall skinny man has a dull grey complexion; he continues to sob. 'Hello?' You nervously call out, with no response from the man. 'Who are you?' You call out, a little louder this time. The man's arm bends backwards at an awkward angle, pointing his index finger to the symbols on the ground. You inch forward and kneel down at the center of the room, placing your hand on the floor to trace some of the lines with your finger. A voice rings out quietly from nowhere in particular, is if it were whispered into your head directly. 'I had tried the ritual so many times, with no avail. Little did I know, it needed a sacrifice to work. I had become so obsessed with my work, that I foolishly scorned my family constantly. My son left in anger one night and collided with another driver head-on. He was killed, and the other driver was left in a coma. I felt so guilty, I took that driver, you, into my home. I payed for your surgeries and vowed to find a way to wake you up. So I performed the ritual on you, with me as the catalyst. This wasn't what I wanted, not at all, but I was just trying to help.' The man in the corner collapses to the floor, seemingly devoid of movement or life. 'Can you ever forgive me?' The voice in your head asks. 'I can, I forgive you.' The words slip from your mouth with no hesitation, all the while a flood of emotions and memories takes hold of you. You open your eyes and look around, the room is glowing with a golden light, the sun peeks in through a new window alongside the wall. A body sits in the corner, covered in markings and dried blood. You slowly stand up and leave the room, heading down the stairs and out through the front door. You inhale and gaze upwards at the sun, for the first time in an eternity."); 
            }
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