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
   public static String episodeNum;
   public static Inventory playerInventory;
   public static String location;
   public static JLabel label = new JLabel();// used for the background image
   public static JLabel player = new JLabel();// used for the player's location dot (sprite)
   public Map map;
   

   //Constructor 
   Input(String episodeNum, Inventory playerInventory, String location, Map theMap){
      this.episodeNum = episodeNum;
      this.playerInventory = playerInventory; 
      this.location = location;
      this.map = theMap;
   }
      
   public Input(){
      super("LayeredPane Example");
      JLayeredPane pane = getLayeredPane();
      setSize(1200,900);
 
     JLabel labelB = new JLabel();
     labelB.setBounds(1, 1, 1200, 900);
     ImageIcon background = new ImageIcon("map_complete.png");
     labelB.setIcon(background);
     
     
     
     JLabel player = new JLabel();
     player.setBounds(400, 500, 10, 10);
     ImageIcon playerIcon = new ImageIcon("playerDot.png");
     player.setIcon(playerIcon);


     pane.setPreferredSize(new Dimension(1200, 900));
     pane.add(labelB, new Integer(0));
     pane.add(player, new Integer(1));
   
      pane.setVisible(true);
   
   }
      
      
   public static void main(String[] args){
      Input test = new Input();
      test.setVisible(true);
      
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
         map.look(location, textArea);
      } else if(userInput.equals("Location")){
         textArea.append(location);
      } else if(userInput.equals("Open door")){
         // Open a door 
         // TODO: ? 
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
      }
      
      
      else // If the user enters something we don't recognize 
         textArea.append( "I can't \"" + userInput + "\"\n");
      
   }//end of check input 


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
      } else if(map.checkMove(location, userInput, textArea)){
         location = userInput;
         update(textArea);
      
      } else {// if they entered a bad location or something 
         textArea.append("I can't go there.\n");
      }
      map.mapLocations(location, player);// update the location of the player dot after moving somewhere
   }
   
   

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


