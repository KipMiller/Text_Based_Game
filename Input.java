// Chris Miller
// Date: 6/19/2020
//
// User input class 
// Used to collect user input from the keyboard and perform accordingly 

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

public class Input extends JFrame{
   public static final int X = 1200;// constant resolution ints 
   public static final int Y = 800;
   public static String episodeNum;// which day the player is on, to trigger certain events
   public static Inventory playerInventory;// used to keep track of what the player is holding
   public static String location;// keep track of the player's current location
   public static JLabel label = new JLabel();// used for the background image
   public static JLabel player = new JLabel();// used for the player's location dot (sprite)
   public Map map;// store the map variable of locations and connections
   
   //Constructor 
   Input(String episodeNum, Inventory playerInventory, String location, Map theMap){
      this.episodeNum = episodeNum;
      this.playerInventory = playerInventory; 
      this.location = location;
      this.map = theMap;
   }

   // make new frame function, takes in the text to be displayed as a string, and the name of the background image JPEG that will be shown 
   public void makeNewFrame(String text, String backgroundImage, int playerX, int playerY){
         JFrame frame1 = new JFrame(episodeNum);// title at top of the window
         JPanel lowerPanel = new JPanel(new BorderLayout());// make a panel for the bottom half of the screen
         frame1.setSize(X,Y);// make the frame X by Y pixels
         frame1.getContentPane().add(lowerPanel,"South");// put the panel on the bottom
         frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame1.setLocationRelativeTo(null);

         JTextField textField = new JTextField(40);
                      
         JTextArea textArea = new JTextArea(8,200);// displays the text for the current frame, can be updated with setText
         JScrollPane scrolling = new JScrollPane(textArea);
         scrolling.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
         
         textArea.setEditable(false);
         textArea.setLineWrap(true);
         textArea.setText(text);//set the text in the text area to the parameter passed into the makeNewFrame function  
         
         JTextArea prompt = new JTextArea(1,1);// displays the text for the current frame, can be updated with setText
         prompt.setEditable(false);
         prompt.setText("What do you do?");//set the text in the text area to the parameter passed into the makeNewFrame function  
         
        
         lowerPanel.add(scrolling, BorderLayout.PAGE_START);// add the textArea to the panel (our text)

         
         lowerPanel.add(textField, BorderLayout.CENTER);// add the textField to the panel (user input text)
         lowerPanel.add(prompt, BorderLayout.LINE_START);
         
         frame1.addWindowListener(new WindowAdapter(){
            public void windowOpened(WindowEvent e){
                  textField.requestFocus();// make the cursor focus on the text field upon making a new frame 
            }
         });
         
         textField.addActionListener(new ActionListener(){// set up the enter button usage 
               public void actionPerformed(ActionEvent e){
                     String userInput = textField.getText();//get the user input and store it in a string 
                     System.out.println("User input = " + userInput);
                     textField.setText("");// make the text field empty after someone hits 'enter'
                     textArea.append(">> " + userInput + "\n");
                     checkInput(userInput, frame1, textArea);
               }
         }); 
  
         // Using layered pane for the top panel so that there can be a player dot indicating location atop the map
         JLayeredPane pane = getLayeredPane();
         pane.setSize(1200,900);

         label.setBounds(200, -100, 1200, 900);
         ImageIcon background = new ImageIcon(backgroundImage);
         label.setIcon(background);
          
         if(episodeNum.equals("Day 2")){// Change the color of the background for which day the player is on 
            label.setOpaque(true);
            label.setBackground(Color.gray);  
            pane.setOpaque(true);
            pane.setBackground(Color.gray);
         }  
         if(episodeNum.equals("Day ?")){
            label.setOpaque(true);
            label.setBackground(Color.black);  
            pane.setOpaque(true);
            pane.setBackground(Color.black);
         }
           
         player.setBounds(playerX, playerY, 10, 10);// player X and player Y will determine the location of the player dot 
         ImageIcon playerIcon = new ImageIcon("playerDot.png");
         player.setIcon(playerIcon);
 
         pane.setPreferredSize(new Dimension(1200, 900));
         pane.add(label, new Integer(0));
         pane.add(player, new Integer(1));
      
         pane.setVisible(true);

         frame1.add(pane);
         frame1.setVisible(true);   
   }// end of the make new frame function

   // Changes the background and the text area of the window 
   public static void changeFrame(JTextArea textArea, String newBackground, String newText){
      ImageIcon background = new ImageIcon(newBackground);
      label.setIcon(background);
      textArea.setText(newText);
   }

   // Takes in user input from the keyboard and decides what to do with it. 
   public void checkInput(String userInput, JFrame frame1, JTextArea textArea){
      // BASIC FUNCTIONS
      if(userInput.equals("Quit") || userInput.equals("Close")){
         System.exit(0);//close the entire game 
      } else if(userInput.equals("Help") || userInput.equals("help")){
         textArea.append("Basic controls:\n'Inventory' - See what you are holding.\n'Look' - Examine a specific object closer.\n'Location' - Display your current location.\n'Open door' - Attempt to open a door.\n'Go' - Attempt to move from your current location.\n'Grab' - Place an item in your inventory.\n'Unlock' - Attempt to unlock a specified room with a key in your inventory.\n ");
      }
      else if(userInput.equals("Inventory") || userInput.equals("inventory")){
         playerInventory.checkInventory();
      }
      else if(userInput.equals("Look")){
         if(episodeNum.equals("Day ?")){// the final day has an alternate look command
            map.look2(location,textArea,episodeNum);
         } else {
            map.look(location, textArea, episodeNum);
            if(episodeNum.equals("Day 2") && location.equals("Garage")){
               playerInventory.addFlashlight();// give the player the flashlight from the crashed car
            }
            if(location.equals("Lower Hall") && playerInventory.hasFlashlight()){// if they look in the lower hall with the flashlight.
               textArea.append("Remembering your flashlight, you flick the switch and illuminate the pitch black hallway leading to the east. Along the left wall is a pale blue door, leading to the Lower Bathroom. Sitting at the end of the hallway, flanked by two large urns, is the maroon colored door leading to the Study. Gazing down the shadowy hall sends goosebumps up your arms, you feel uneasy as a slight ringing in your ears grows louder the longer you look.\n");
            }
            if(location.equals("Upper Bathroom")){
               playerInventory.addItem("Lower Bathroom Key");
            }
         }
      } else if(userInput.equals("Location")){
         textArea.append(location);
      } else if(userInput.contains("Go")){
         if(userInput.length() <= 2){
            textArea.append("I need to know where to go.\n");      
         } else {
            go(textArea, userInput.substring(3));
         }
      } else if(userInput.contains("Grab")){
         grab(textArea,userInput.substring(5));
      } else if (userInput.contains("Unlock")){
         unlock(textArea, userInput.substring(7));
      } else if(userInput.contains("Open Book") || userInput.contains("Read Book")){
         read(textArea);
      }
  
      else // If the user enters something we don't recognize 
         textArea.append( "I can't \"" + userInput + "\"\n");  
   }//end of check input 

   public void read(JTextArea textArea){
      if(location.equals("Master Bedroom")){
         textArea.append("Upon opening the dark leatherbound book, you realize it appears to be some sort of journal or diary; with each page dated. After flipping through a few pages, a brass key falls to the ground from an indent cut into several of the pages.\n You pick up the key and flip to the last pages of the journal; though some words are scribbled out you can still make out the last entry.\n 'M_ ___, it will ___ be ready soon enough, I will b_ ____ ____. Keep that in min_ and do__ feel ___ f_r __. If it wo_ks, I wi__ ____...'\n After that the writing becomes less legible, but you can barely make out the words 'I should get some food', scrawled over and over again for the next few pages.\n I should probably find out what door this key opens, or maybe I should investigate the crashed vehicle in the garage...\n");
         playerInventory.addItem("Upper Bathroom Key");
      } else if(location.equals("Study")){
         textArea.append("Leaning over the wooden-bound book, your eyes strain to read the faded ink on each page. Symbols and shapes of varying sizes cover each page from top to bottom, and as you flip through the stiff pages, you see a pattern slowly becoming clearer and clearer. Three squares with overlapping corners within a large oval, writing and shapes surrounding the main shape. This symbol, as you keep flipping through pages, grows larger and larger, clearly the author was drawing it larger and larger over time... You flip to the final page of this unnerving book and see plain writing. 'The ritual will take two of us, that much I'm sure of now. It will work with him, he has proven worthy time and time again; but what will become of me? I guess it won't really matter; but to those reading this after we are both gone. We are not dead, the boy and I, we have found a way between life and death. And to YOU, when are ready to leave (if you ever wish to), use the key...' Ringing in your ears grows louder as you read the final passage of this disturbing book, you are not sure who wrote it, what the ritual was, or how you got to this oddly familiar house, but you instinctively close the wooden-bound book. Beneath the final page and cover is a key, not a rusty metal key like all of the others; this key is a car key. Is it time to leave? Or perhaps..."); 
         // TODO: Add the car keys to the player's inventory so they can leave
         map.getLocation("???").setLocked();// unlock the hidden room after they have gotten to the study   
      } else if(location.equals("Library")){
         textArea.append("You kneel down and pick up the purple book, most of the pages are stuck together, but about halfway through you find a loose page. 'What happened with the boy, I know it was all my fault; but maybe its a sign? Maybe this was supposed to happen, I was never worthy of it, I know that for sure after all of the failed rituals... But he will be worthy.' Chills run down your spine as you read, and while you don't completely understand the context, the talk of rituals and 'the boy' lead you to believe the author was speaking about you... You don't know why, but you feel like the red door at the end of the lower hall is open, what awaits you?\n");
         map.getLocation("Study").setLocked();// unlock the study after they read the book in the library
      }
   }

   // Method used to grab a specified item based on their current location and input.
   public void grab(JTextArea textArea, String userInput){
      if(map.getLocation(location).isItem(userInput)){
         textArea.append("You grab " + userInput + " and put it in your pocket.\n");
         playerInventory.addItem(userInput);
      } else {
         textArea.append("I can't grab that.\n");
      }
   }
   
   // Unlock (open) one of several doors, some need keys and some are locked from one side.
   public void unlock(JTextArea textArea, String userInput){
      if(location.equals("Living Room")){// The kithen door from the living room doesn't need a key to be unlocked
         map.getLocation(location).setLocked();
         textArea.append("You turn the doorknob and hear a click, the door to the kitchen is now unlocked. Your push open the door and prop it open with a kitchen chair, so that it won't close.\n");
      } else if(location.equals("Master Bedroom")){
         map.getLocation(location).setLocked();
         textArea.append("You turn the doorknob and hear a click, the door leading to the Upper Hall is now unlocked.\n");
      } else if(map.getLocation(userInput).isLocked()){// if the room is indeed locked
         if(playerInventory.validKey(userInput)){
            map.getLocation(userInput).setLocked();
            textArea.append("You unlocked the " + userInput + " with your key.\n"); 
         } else {
            textArea.append("I don't have the key to unlock that door.\n");
         }
      } else {
         textArea.append("That room isn't locked.\n");
      }
   }

   // Checks to see if the player has been in this room before, if not update the map, if they have print a simple return message
   public void update(JTextArea textArea){
      Location temp = map.getLocation(location);
      if(!temp.isVisited()){// if they HAVEN'T been in this room yet
         temp.setVisited();// indicate they have now visited this room
         changeFrame(textArea, temp.getPicture(), temp.getDescription());// then change the picture and text 
      } else {
         textArea.append("You are now back in the " + location + "\n");// else if they have been here before, just say their location
      }
   }

   // Allows the player to go from one room to another, based on a rigid set of logic that connects each room and determines where 
   // and when the player can move.
   public void go(JTextArea textArea, String userInput){
      
      if(userInput.contains("stairs")){// if the player wants to go up / down one of the two staircases
         stairs(textArea,userInput);
      } else if(userInput.contains("hole") && location.equals("Garage")){ // if they want to go through the hole in the garage
         location = "Living Room";
         update(textArea);
      } else if(userInput.contains("Master Bedroom") && location.equals("Upper Landing")){// if they are on the upper landing and go into the master bedroom
         location = "Master Bedroom";
         update(textArea);
         if(episodeNum.equals("Day 1")){// if it is day 1, they have finished the first day by getting to the master bedroom
            Episode1.dayEnd();   
         }
      }else if(userInput.contains("Lower Hall") && !map.getLocation("Lower Hall").isVisited()){// lower hall with either formal living room visited or not 
         location = userInput;
         Location temp = map.getLocation(location);
         temp.setVisited();
         
         if(!map.getLocation("Formal Living Room").isVisited()){ // if they go into the hall without going in the formal living room 
            changeFrame(textArea, "map_7.png", temp.getDescription());
         } else {
            changeFrame(textArea, "map_7_A.png", temp.getDescription());
         }
      } else if(userInput.contains("Kitchen") && !map.getLocation("Kitchen").isVisited()){
         location = userInput;
         Location temp = map.getLocation(location);
         temp.setVisited();
         
         if(!map.getLocation("Formal Living Room").isVisited() && !map.getLocation("Dining Room").isVisited()){// no formal living room OR dining room
            changeFrame(textArea, "map_8_E.png", temp.getDescription());
         } else if(!map.getLocation("Formal Living Room").isVisited() && map.getLocation("Dining Room").isVisited()){// no formal living room AND dining room
            changeFrame(textArea, "map_8_F.png", temp.getDescription());
         } else if(map.getLocation("Formal Living Room").isVisited() && !map.getLocation("Dining Room").isVisited()){
            changeFrame(textArea, "map_8_D.png", temp.getDescription());
         } else{
            changeFrame(textArea, "map_8_A.png", temp.getDescription());
         }
      } else if(userInput.contains("Dining Room") && !map.getLocation("Dining Room").isVisited()){
         location = userInput;
         Location temp = map.getLocation(location);
         temp.setVisited();
         
         if(!map.getLocation("Formal Living Room").isVisited() && !map.getLocation("Kitchen").isVisited()){// no kitchen OR formal living room
            changeFrame(textArea, "map_8_G.png", temp.getDescription());
         } else if(!map.getLocation("Formal Living Room").isVisited() && map.getLocation("Kitchen").isVisited()){// no formal living room AND kitchen
            changeFrame(textArea, "map_8_F.png", temp.getDescription());
         } else if(map.getLocation("Formal Living Room").isVisited() && !map.getLocation("Kitchen").isVisited()){// formal living room WITH no kitchen
            changeFrame(textArea, "map_8_B.png", temp.getDescription());
         } else{
            changeFrame(textArea, "map_8_A.png", temp.getDescription());
         }
      } else if(userInput.contains("Formal Living Room") && !map.getLocation(userInput).isVisited()){
         location = userInput;
         Location temp = map.getLocation(location);
         temp.setVisited();
         if(map.getLocation("Lower Hall").isVisited()){
            if(!map.getLocation("Kitchen").isVisited() && !map.getLocation("Dining Room").isVisited()){// no kitchen no dining room
               changeFrame(textArea, "map_8_C.png", temp.getDescription());
            } else if(!map.getLocation("Dining Room").isVisited() && map.getLocation("Kitchen").isVisited()){// no dining room yes kitchen
               changeFrame(textArea, "map_8_D.png", temp.getDescription());
            } else if(map.getLocation("Dining Room").isVisited() && !map.getLocation("Kitchen").isVisited()){// yes dining room no kitchen
               changeFrame(textArea, "map_8_B.png", temp.getDescription());
            }
         } else {
            changeFrame(textArea, "map_7_B.png", temp.getDescription());
         }
      } else if(userInput.contains("Lower Bathroom") && episodeNum.equals("Day 2")){
         Episode2.dayEnd();
      } else if(map.checkMove(location, userInput, textArea)){
         location = userInput;
         update(textArea);
      
      } else {// if they entered a bad location or something 
         textArea.append("I can't go there.\n");
      }
      map.mapLocations(location, player);// update the location of the player dot after moving somewhere
   }// end of the 'go' method
   
   

   // Slightly different commands if the user wants to go down/up the two staircases in the map
   public void stairs(JTextArea textArea, String userInput){
      if(userInput.contains("down stairs") && location.equals("Upper Hall")){
         location = "Entrance";
      } else if(userInput.contains("down stairs") && location.equals("Landing")){
         location = "Living Room";
      } else if(userInput.contains("up stairs") && location.equals("Entrance")){
         location = "Upper Hall";
      } else if(userInput.contains("up stairs") && location.equals("Living Room")){
         location = "Upper Landing";
      } 
      update(textArea);
   }
   
}//end of input class 