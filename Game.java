// Author: Chris Miller 
// Date Started: 6/19/2020
// Last Updated: 6/19/2020
//
// Game: Working Title 
// Main driver class for the text based adventure-style game. Allows the 
// selection of a chapter and the choosing of choices per chapter.

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

public class Game {

   public static boolean advance = false; 

   public static void main(String[] args){
   
      JFrame mainMenu = new JFrame ("Game");// name of the frame 
      JButton day1 = new JButton ("Day 1");
      JButton day2 = new JButton ("Day 2");
      JButton day3 = new JButton ("Day ?");
      JButton exit = new JButton ("Exit");
      JPanel layout = new JPanel();
      JPanel startGamePanel = new JPanel();
      JPanel exitPanel = new JPanel();
      JPanel titlePanel = new JPanel();
      JLabel title = new JLabel("Working Title");
      
      title.setFont(new	Font("Serif",	Font.BOLD, 70));
      title.setForeground(Color.RED);
      titlePanel.add(title, BorderLayout.NORTH);
      
      startGamePanel.add(day1);
      startGamePanel.add(day2);
      startGamePanel.add(day3);
      exitPanel.add(exit);

      layout.setLayout(new BoxLayout(layout, BoxLayout.Y_AXIS));
      layout.add(Box.createRigidArea(new Dimension(0, 2)));
      layout.add(startGamePanel);
      layout.add(exitPanel);

      //make everything visible 
      startGamePanel.setOpaque(false);
      exitPanel.setOpaque(false);
      layout.setOpaque(false);
      titlePanel.setOpaque(false);

      mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainMenu.setTitle("Working Title");
      mainMenu.setSize(500, 400);
      mainMenu.getContentPane().setBackground(Color.BLACK);
      mainMenu.add(layout, BorderLayout.PAGE_END);
      mainMenu.add(titlePanel, BorderLayout.CENTER);
      
      mainMenu.setResizable(false);
      mainMenu.setLocationRelativeTo(null);
      mainMenu.setVisible(true);
  
      exit.addActionListener(new ActionListener(){// main menu exit button 
         @Override
         public void actionPerformed(ActionEvent e){
            mainMenu.dispatchEvent(new WindowEvent(mainMenu, WindowEvent.WINDOW_CLOSING));
         }
      });
        
      day1.addActionListener(new ActionListener(){// action button for 'Day 1' 
            @Override
            public void actionPerformed(ActionEvent e){
                  mainMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                  mainMenu.dispatchEvent(new WindowEvent(mainMenu, WindowEvent.WINDOW_CLOSING));
                  Episode1.main(args);// go into the episode 1 main function
            }
      });           
          
      day2.addActionListener(new ActionListener(){// action button for 'Day 2' 
            @Override
            public void actionPerformed(ActionEvent e){
                  mainMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                  mainMenu.dispatchEvent(new WindowEvent(mainMenu, WindowEvent.WINDOW_CLOSING));
                  Episode2.main(args);// go into the episode 2 main function
            }
      });         
      
      day3.addActionListener(new ActionListener(){// action button for 'Day 3' 
            @Override
            public void actionPerformed(ActionEvent e){
                  mainMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                  mainMenu.dispatchEvent(new WindowEvent(mainMenu, WindowEvent.WINDOW_CLOSING));
                  //Episode2.main(args);// go into the episode 2 main function
            }
      }); 
                 
      while(advance != true){
            try{
               Thread.sleep(200);
            } catch(InterruptedException e){                    
            }
      }

   }// end of main 


}// end of class game 


