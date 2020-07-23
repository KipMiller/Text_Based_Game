// Chris Miller
// Date: 6/19/2020
//
// Map class
// Holds all locations of the map 
import java.lang.*;
import java.util.*;
import java.io.*;
import javax.swing.*;



public class Map{
   List<Location> map;
   
   public Map(ArrayList<ArrayList<String>> items){
       map = new ArrayList<Location>();

       String[] descriptions = {"A small bedroom with a bed, closet, window and a nightstand.\n","A long hallway lined with four doors, a window and a staircase going downwards.\n",
       "A dingy bathroom dimly lit by a skylight.\n","A cluttered library, each wall is lined with shelving, the floor is covered with books.\n","The master bedroom, the windows are covered with dark curtains making it impossible to see.\n", 
       "????\n","The upper landing, a small room consisting of a staircase leading downwards and a door.\n", 
       // Downstairs
       "The entrance of the house, at the bottom of the stairs lies the front door, to your left is a large room.\n","The formal living room, being a couple sofas and decorated bookshelves.\n","The lower hall is dimly lit and consists of several doors, and a path leading to what looks like a dinging room.\n",
       "You enter the garage, a small window across the width of the garage gives off a small amount of grey light.\nThe two car garage is empty, but boxes are stacked up high in the corner opposite you.\nTo your right is a wall lined with tools and to the left of the tool rack is what appears to be a large hole seemingly carved into the wall.\n","The Study, BOO.\n", "The lower bathroom.\n", "The dining room.\n","The Pantry.\n","The Kitchen, you see a large bronze colored key sitting on the table, it's on a piece of paper that reads 'Garage Key' written in sharpie.\n", 
       "You crawl through the hole and see what appears to be the living room, there is a large television beside the hole you crawled through on the wall. \nA staircase going upwards sits to your left beside a window filling the room with a pale grey light. \nA door stands across the room from you adjacent to a sofa.\n"};
   
      // top floor 
      
      // Bedroom
      String[] n1 ={"Upper Hall"};
      Location loc1 = new Location("Bedroom",n1,descriptions[0],true,false,"map_4.png", items.get(0));
      
      // Upper Hall
      String[] n2 ={"Bedroom", "Library", "Upper Bathroom", "Master Bedroom", "?", "Entrance"};
      Location loc2 = new Location("Upper Hall",n2,descriptions[1],false,false,"map_5.png", items.get(0));
      
      // Upper Bathroom
      String[] n3 ={"Upper Hall"};
      Location loc3 = new Location("Upper Bathroom",n3,descriptions[2],false,false,"map_13.png", items.get(0));
      
      // Library
      String[] n4 ={"Upper Hall"};
      Location loc4 = new Location("Library",n4,descriptions[3],false,false,"map_16.png", items.get(0));
      
      // Master Bedroom
      String[] n5 ={"Upper Hall", "Upper Landing"};
      Location loc5 = new Location("Master Bedroom",n5,descriptions[4],false, false, "map_12.png", items.get(0));
      
      // ???
      String[] n6 ={"Upper Hall"};
      Location loc6 = new Location("?",n6,descriptions[5],false,true, "map_17.png", items.get(0));
      
      // Upper Landing
      String[] n7 ={"Master Bedroom", "Living Room"};
      Location loc7 = new Location("Upper Landing",n7,descriptions[6],false, false, "map_11.png", items.get(0));
      
      
      // bottom floor 
      
      // Entrance
      String[] n8 ={"Formal Living Room", "Lower Hall"};
      Location loc8 = new Location("Entrance",n8,descriptions[7],false,false, "map_6.png", items.get(0));
      
      // Formal Living Room
      String[] n9 ={"Entrance", "Lower Hall"};
      Location loc9 = new Location("Formal Living Room",n9,descriptions[8],false,false, "map_7_B.png", items.get(0));
      
      // Lower Hall
      String[] n10 ={"Formal Living Room","Entrance", "Garage", "Study", "Lower Bathroom", "Dining Room", "Kitchen"};
      Location loc10 = new Location("Lower Hall",n10,descriptions[9],false,false, "map_8_C.png", items.get(0));
      
      // Garage
      String[] n11 ={"Lower Hall", "Living Room"};
      Location loc11 = new Location("Garage",n11,descriptions[10],false,false, "map_9.png", items.get(0));
      
      //Study
      String[] n12 ={"Lower Hall"};
      Location loc12 = new Location("Study",n12,descriptions[11],false,false, "map_complete.png", items.get(0));
      
      // Lower Bathroom
      String[] n13 ={"Lower Hall"};
      Location loc13 = new Location("Lower Bathroom",n13,descriptions[12],false,false, "map_15.png", items.get(0));
      
      // Dining Room
      String[] n14 ={"Lower Hall", "Kitchen", "Pantry"};
      Location loc14 = new Location("Dining Room",n14,descriptions[13],false,false, "map_8_G.png", items.get(0));
      
      // Pantry
      String[] n15 ={"Dining Room"};
      Location loc15 = new Location("Pantry",n15,descriptions[14],false,false, "map_14.png", items.get(0));
      
      // Kitchen
      String[] n16 ={"Dining Room" ,"Living Room", "Lower Hall"};
      Location loc16 = new Location("Kitchen",n16,descriptions[15],false,false, "map_8_E.png", items.get(1));
      
      // Living Room
      String[] n17 ={"Kitchen", "Garage", "Upper Landing"};
      Location loc17 = new Location("Living Room",n17,descriptions[16],false,false, "map_10.png", items.get(0));
      
      
      // Add all of the location variables to the map arrayList
      map.add(loc1);map.add(loc2);map.add(loc3);map.add(loc4);
      map.add(loc5);map.add(loc6);map.add(loc7);map.add(loc8);
      map.add(loc9);map.add(loc10);map.add(loc11);map.add(loc12);
      map.add(loc13);map.add(loc14);map.add(loc15);map.add(loc16);
      map.add(loc17);
   }
   
   // Returns the location variable based on the player's current location
   public Location getLocation(String currLocation){
         for(int i = 0; i < map.size(); i++){
            if(map.get(i).getName().equals(currLocation)){
               return map.get(i);
            }
         }
         return null;
   }

   // Based on the player's current location and the location they want to move to, see if it is a valid move
   public boolean checkMove(String currLocation, String target, JTextArea textArea){
      for(int i = 0; i < map.size(); i++){
         if(map.get(i).getName().equals(currLocation)){// get the location variable for our current location
            
            if(map.get(i).isNeighbor(target)){// check if the target IS a neighbor to the current location
               Location destination = getLocation(target);  
               if(destination.isLocked()){// if it IS locked (IE true) 
                  textArea.append("The door is locked, ");// if it is a valid move location, but the door is locked
                  return false; 
               }
               return true;
            }
         }
      }
      return false;
   }


   
   // look command, used to further inspect a location, can give more details related to a room than an initial
   public static void look(String currLocation, JTextArea textArea){
      switch(currLocation){
         case "Bedroom": 
            textArea.append("Pale beige walls sit uncovered, with window to the south.\nWhite wooden doors conceal a closet to the west, across from a small bed to the east.\nA nighstand with no light atop it is next to the bed.\n");
            break;
         case "Upper Hall": 
            textArea.append("A long hallway on the second floor of the house, it stretches from the east to the west, with a window ending the hall to the east.\nThe bedroom sits on the south side of the hall, with three doors lining the wall opposite the bedroom door.\nAt the end of the west end of the hall is a large landing with a staircase and one final door on the west wall.\n");
            break;
         case "Upper Bathroom": 
            textArea.append("The cramped bathroom has a robin egg blue color to it, but the dull grey light coming in through the skylight fills you with unease as you look around.\nYou turn the knob of the faucet and no water comes out.\nThe lights, surprisingly, work in the bathroom, but a quick look through the drawers proves there are no useful items for now.\n");
            break;
         case "Library": 
            textArea.append("The library is wide and the north wall is lined with three windows. The lights don't work, but you can see the floor is littered with books, each of the east, west and south walls are lined with half empty bookshelves, most of their contents lining the hardwood floor.\n");
            break;
         case "Master Bedroom": 
            textArea.append("The modern looking master bedroom is sparsely decorate, save for a number of abstract paintings on the east and west walls.\nTo the south are two long windows providing a good view of the outside area.\nA long cabinet sits by the east wall next to the door to the upper hall, atop it are a number of photo frames all devoid of pictures save for one.\n");
            break;
         case "Upper Landing": 
            textArea.append("The small landing is illuminated by a skylight directly above the staircase leading back down to the living room.\nGazing down the stairs into the dimly lit living room sends a shiver down your spine, though you can clearly see nobody at the bottom of the stairs, you can't escape the sensation of being watched from below.\nA small love seat lies along the west wall adjacent to a bookshelf, across from the chair is a dark wooden door with several marks seemingly carved into it by an animal.\n");
            break;
         case "Entrance": 
            textArea.append("A thick wooden door with two large glass panes sits at the bottom of the staircase on the south wall. The front door won't budge and the lightswitches on the wall beside the door serve no visible purpose.\nTo the east is an opening to a room with several large windows that can be seen from the front door.\nTo the north, past the stairs, is a hallway forking off in several directions.\n"); 
            break;
         case "Formal Living Room": 
            textArea.append("The formal living has three floort-to-cieling sized windows lining the south wall, giving a very modern look to the room.\nA small glass coffee table sits bare in the center of the room, flanked by unused sofas and a dormant fireplace on the east wall.\nTo the north is a path joining up with the lower hallway, to the west is the path back to the entrance of the house.\n");
            break;
         case "Lower Hall": 
            textArea.append("The lower hallway has hardly any natural light illuminating it, but you can see the path leading north ends in a window and what looks like a dining room table.\nTo the east are two doors concealed in shadow, it's too dark to try and head down this way.\nTo the west, right behind the base of the stairs is a door taller than the others, made of a solid white piece of wood covered in what looks like black splotches.\n");
            break;
         case "Lower Bathroom": 
            textArea.append("???");
            break;
         case "Dining Room": 
            textArea.append("The dining room has a long wooden table in the center, with a handful of chairs toppled over onto their side; save for two on either end of the table.\nAside from the disheveled nature of the scene, the north and south walls have several pictures all perfectly aligned.\nThe east wall alone has a light brown wooden door.\n");
            break;
         case "Pantry": 
            textArea.append("The pantry is lined with shelving covered in sealed boxes. A single lightbulb hangs low from the cieling, and after fumbling along the wall you flip the switch to illuminate the room better.\nOther than several dozen boxes and a number of empty water gallons, the pantry seems to hold nothing of consequence as far as you can see.\n");
            break;
         case "Kitchen": 
            textArea.append("The kitchen has cabinets and black granite countertops lining the north and west walls.\nA light wooden door sits to the south with no door handle visible.\nA rounded table is at the center of the kitchen with several pieces of paper atop it.\nAfter rummaging through the drawers, cabinets, and the fridge, you find nothing of use save for a handfull of spoons that hardly seem worthwhile in taking.\n");
            break;
         case "Living Room": 
            textArea.append("The dimly lit living room has a large flat screen TV on the south wall, to the right of the TV is a gaping hole leading into the garage, the edges of the hole seem smoothed down, as if from wear and tear of someone passing through it multiple times.\nAcross from the TV lies an extra long sofa with a coffee table in front of it.\nNext to the sofa is a staircase heading upwards to a dull grey light.\n");
            break;
         case "Garage": 
            textArea.append("The two car garage is seemingly empty save for what looks like several oil stains in the center of the concrete.\nTo the east is a window and a door surrounded by large sealed up boxes.\nThe east wall has several light switches that don't work and the door leading back to the lower hallway.\nThe south wall has two garage doors that don't appear to be hooked up to the garage door openers, judging by the chains hanging from the lifts.\nThe north wall has numerous hooks and panels that should have been holding power tools and equipment; but sadly this wall is completely bare of tools.\nTo the left of the tool racks is a large hole in the wall, seemingly leading to another room of the house.\n"); 
            break;
         case "Study": 
            textArea.append("???");
            break;
      
      }
   }
   
   
    // method that takes the players current location and updates the player dot on the map via X / Y coordinates for each room
   public static void mapLocations(String currLocation, JLabel player ){
      switch(currLocation){
         case "Bedroom": 
            player.setBounds(860, 245, 10,10);// good 
            break;
         case "Upper Hall": 
            player.setBounds(860, 220, 10,10);// good
            break;
         case "Upper Bathroom": 
            player.setBounds(875, 190, 10,10);// good
            break;
         case "Library": 
            player.setBounds(845, 190, 10,10);// good            
            break;
         case "Master Bedroom": 
            player.setBounds(650, 220, 10,10); // good          
            break;
         case "Upper Landing": 
            player.setBounds(600, 190, 10,10);// good            
            break;
         case "Entrance": 
            player.setBounds(570, 520, 10,10); // good           
            break;
         case "Formal Living Room": 
            player.setBounds(660, 530, 10,10); // good           
            break;
         case "Lower Hall": 
            player.setBounds(600, 400, 10,10); // good           
            break;
         case "Lower Bathroom": 
            player.setBounds(660, 450, 10,10);            
            break;
         case "Dining Room": 
            player.setBounds(860, 245, 10,10);            
            break;
         case "Pantry": 
            player.setBounds(860, 245, 10,10);            
            break;
         case "Kitchen": 
            player.setBounds(860, 245, 10,10);            
            break;
         case "Living Room": 
            player.setBounds(860, 245, 10,10);            
            break;
         case "Garage": 
            player.setBounds(860, 245, 10,10);            
            break;
         case "Study": 
            player.setBounds(860, 245, 10,10);            
            break;
      
      }
   }


}// End of the Map class
