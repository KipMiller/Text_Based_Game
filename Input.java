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


public class Input{
   public static final int X = 1200;// constant resolution ints 
   public static final int Y = 800;
   public static String episodeNum;
   public static Inventory playerInventory;
   public static String location;
   public static JLabel label = new JLabel();// make new label
   public Map map;
   
   //Constructor 
   Input(String episodeNum, Inventory playerInventory, String location, Map theMap){
      this.episodeNum = episodeNum;
      this.playerInventory = playerInventory; 
      this.location = location;
      this.map = theMap;
   }
      

   // make new frame function, takes in the text to be displayed as a string, and the name of the background image JPEG that will be shown 
   public void makeNewFrame(String text, String backgroundImage){
         JFrame frame1 = new JFrame(episodeNum);// title at top of the window
         JPanel lowerPanel = new JPanel(new BorderLayout());// make a panel for the bottom half of the screen
         frame1.setSize(X,Y);// make the frame X by Y pixels
         frame1.getContentPane().add(lowerPanel,"South");// put the panel on the bottom
         frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame1.setLocationRelativeTo(null);
         
         JTextField textField = new JTextField(20);

                      
         JTextArea textArea = new JTextArea(5,100);// displays the text for the current frame, can be updated with setText
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
         // set up the background image and make the frame visible 
         ImageIcon background = new ImageIcon(backgroundImage);
         label.setIcon(background);// make the label the background image
         JPanel p = new JPanel();// make new panel
         p.add(label);// add the label to the panel
         frame1.add(p);// add the panel to frame1 
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
      
      
      // Day-specific interractions (control game flow essentially)
      /*
      if(episodeNum.equals("Day 1")){
      
      } else if (episodeNum.equals("Day 2")){
      
      } else if (episodeNum.equals("Day 3")){
         
      }
      */
      
      else // If the user enters something we don't recognize 
         textArea.append( "I can't \"" + userInput + "\"\n");
      
   }//end of check input 

   public void grab(JTextArea textArea, String userInput){
      if(map.getLocation(location).isItem(userInput)){
         textArea.append("You grab " + userInput + " and put it in your pocket.\n");
         playerInventory.addItem(userInput);
      } else {
         textArea.append("I Can't grab that.\n");
      }
   }
   
   
   
   public void unlock(JTextArea textArea, String userInput){
      if(location.equals("Living Room")){// The kithen door from the living room doesn't need a key to be unlocked
         map.getLocation(location).setLocked();
         textArea.append("You turn the doorknob and hear a click, the door to the kitchen is now unlocked.\n");
      } else if(location.equals("Master Bedroom")){
         map.getLocation(location).setLocked();
         textArea.append("You turn the doorknob and hear a click, the door leading to the upper hall is now unlocked.\n");
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

   public void go(JTextArea textArea, String userInput){
 
      if(userInput.contains("stairs")){
         stairs(textArea,userInput);
      } else if(userInput.contains("hole") && location.equals("Garage")){ 
         location = "Living Room";
         update(textArea);
      } else if(userInput.contains("Master Bedroom") && location.equals("Upper Landing")){
         location = "Master Bedroom";
         update(textArea);
         if(episodeNum.equals("Day 1")){
            Episode1.dayEnd();   
         }
      }else if(map.checkMove(location, userInput, textArea)){
         location = userInput;
         update(textArea);
      
      } else {// if they entered a bad location or something 
         textArea.append("I can't go there.\n");
      }
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


