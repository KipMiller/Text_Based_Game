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
   
   public Map(ArrayList<ArrayList<String>> items, boolean[] visits, boolean[] locks){
       map = new ArrayList<Location>();

       String[] descriptions = {"A small bedroom with a bed, closet, window and a nightstand.\n","A long hallway lined with four doors, a window and a staircase going downwards.\n",
       "A dingy bathroom dimly lit by a skylight.\n","A cluttered library, each wall is lined with shelving, the floor is covered with books.\n","The master bedroom, the windows are covered with dark curtains making it impossible to see.\n", 
       "????\n","The upper landing, a small room consisting of a staircase leading downwards and a door.\n", 
       // Downstairs
       "The entrance of the house, at the bottom of the stairs lies the front door, to your left is a large room.\n","The formal living room, being a couple sofas and decorated bookshelves.\n","The lower hall is dimly lit and consists of several doors, and a path leading to what looks like a dinging room.\n",
       "You enter the garage, a small window across the width of the garage gives off a small amount of grey light.\nThe two car garage is empty, but boxes are stacked up high in the corner opposite you.\nTo your right is a wall lined with tools and to the left of the tool rack is what appears to be a large hole seemingly carved into the wall.\n","The Study...\n", "The lower bathroom.\n", "The dining room.\n","The Pantry.\n","The Kitchen, you see a large bronze colored key sitting on the table, it's on a piece of paper that reads 'Garage Key' written in sharpie.\n", 
       "You crawl through the hole and see what appears to be the living room, there is a large television beside the hole you crawled through on the wall. \nA staircase going upwards sits to your left beside a window filling the room with a pale grey light. \nA door stands across the room from you adjacent to a sofa.\n"};
   
      // top floor 
 
      // Bedroom 
      String[] n1 ={"Upper Hall"};
      Location loc1 = new Location("Bedroom",n1,descriptions[0],visits[0],locks[0],"map_4.png", items.get(0));
      
      // Upper Hall
      String[] n2 ={"Bedroom", "Library", "Upper Bathroom", "Master Bedroom", "Ritual Room", "Entrance"};
      Location loc2 = new Location("Upper Hall",n2,descriptions[1],visits[1],locks[1],"map_5.png", items.get(0));
      
      // Upper Bathroom
      String[] n3 ={"Upper Hall"};
      Location loc3 = new Location("Upper Bathroom",n3,descriptions[2],visits[2],locks[2],"map_13.png", items.get(0));
      
      // Library
      String[] n4 ={"Upper Hall"};
      Location loc4 = new Location("Library",n4,descriptions[3],visits[3],locks[3],"map_16.png", items.get(0));
      
      // Master Bedroom
      String[] n5 ={"Upper Hall", "Upper Landing"};
      Location loc5 = new Location("Master Bedroom",n5,descriptions[4],visits[4],locks[4], "map_12.png", items.get(0));
      
      // Ritual Room
      String[] n6 ={"Upper Hall"};
      Location loc6 = new Location("Ritual Room",n6,descriptions[5],visits[5],locks[5], "map_complete.png", items.get(0));
      
      // Upper Landing
      String[] n7 ={"Master Bedroom", "Living Room"};
      Location loc7 = new Location("Upper Landing",n7,descriptions[6],visits[6],locks[6], "map_11.png", items.get(0));
      
      
      // bottom floor 
      
      // Entrance
      String[] n8 ={"Formal Living Room", "Lower Hall"};
      Location loc8 = new Location("Entrance",n8,descriptions[7],visits[7],locks[7], "map_6.png", items.get(0));
      
      // Formal Living Room
      String[] n9 ={"Entrance", "Lower Hall"};
      Location loc9 = new Location("Formal Living Room",n9,descriptions[8],visits[8],locks[8], "map_7_B.png", items.get(0));
      
      // Lower Hall
      String[] n10 ={"Formal Living Room","Entrance", "Garage", "Study", "Lower Bathroom", "Dining Room", "Kitchen"};
      Location loc10 = new Location("Lower Hall",n10,descriptions[9],visits[9],locks[9], "map_8_C.png", items.get(0));
      
      // Garage
      String[] n11 ={"Lower Hall", "Living Room"};
      Location loc11 = new Location("Garage",n11,descriptions[10],visits[10],locks[10], "map_9.png", items.get(0));
      
      //Study
      String[] n12 ={"Lower Hall"};
      Location loc12 = new Location("Study",n12,descriptions[11],visits[11],locks[11], "map_complete.png", items.get(0));
      
      // Lower Bathroom
      String[] n13 ={"Lower Hall"};
      Location loc13 = new Location("Lower Bathroom",n13,descriptions[12],visits[12],locks[12], "map_15.png", items.get(0));
      
      // Dining Room
      String[] n14 ={"Lower Hall", "Kitchen", "Pantry"};
      Location loc14 = new Location("Dining Room",n14,descriptions[13],visits[13],locks[13], "map_8_G.png", items.get(0));
      
      // Pantry
      String[] n15 ={"Dining Room"};
      Location loc15 = new Location("Pantry",n15,descriptions[14],visits[14],locks[14], "map_14.png", items.get(0));
      
      // Kitchen
      String[] n16 ={"Dining Room" ,"Living Room", "Lower Hall"};
      Location loc16 = new Location("Kitchen",n16,descriptions[15],visits[15],locks[15], "map_8_E.png", items.get(1));
      
      // Living Room
      String[] n17 ={"Kitchen", "Garage", "Upper Landing"};
      Location loc17 = new Location("Living Room",n17,descriptions[16],visits[16],locks[16], "map_10.png", items.get(0));
      
      
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
            if(map.get(i).getName().toLowerCase().equals(currLocation.toLowerCase())){
               return map.get(i);
            }
         }
         return null;
   }
   
   // used to get the proper capitilized version of a locations name
   public String locationName(String currLocation){
         for(int i = 0; i < map.size(); i++){
            if(map.get(i).getName().toLowerCase().equals(currLocation.toLowerCase())){
               return map.get(i).getName();
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
   public static void look(String currLocation, JTextArea textArea, String episodeNum){
      switch(currLocation){
         case "Bedroom": 
            textArea.append("Pale beige walls sit uncovered, with window to the south.\nWhite wooden doors conceal a closet, across from a small bed.\nA nighstand with no light atop it is next to the bed. The only door out of the room leads to the Upper Hall\n");
            break;
         case "Upper Hall": 
            textArea.append("A long hallway on the second floor of the house, it stretches from the east to the west, with a window ending the hall to the east.\nThe bedroom sits on the south side of the hall, with three doors lining the wall opposite the bedroom door; the Upper Bathroom, Library, and a room you cannot recall, respectively.\nAt the end of the west end of the hall is a large landing with a staircase and one final door on the west wall, the door to the master bedroom.\n");
            break;
         case "Upper Bathroom": 
            textArea.append("The cramped bathroom has a robin egg blue color to it, but the dull grey light coming in through the skylight fills you with unease as you look around.\nA puddle of still water has pooled around the toilet, leaking out of the sink which looks like it has been slowly dripping water for a very long time. Trying not to step in the pool of water, you stretch your arm out and turn the knob of the sink, ensuring the water is turned off. You also pull up on a level beside the sink's knob, draining the full basin of water. 'How long was this dripping?' As the stagnant water drains from the sink, you spot another key; putting it into your pocket as soon as you recognize the shape.\n");
            break;
         case "Library": 
            textArea.append("The library is wide and the north wall is lined with three windows. The lights don't work, but you can see the floor is littered with books, each of the east, west and south walls are lined with half empty bookshelves, most of their contents lining the hardwood floor.\n");
            break;
         case "Master Bedroom": 
            textArea.append("The modern looking master bedroom is sparsely decorate, save for a number of abstract paintings on the east and west walls.\nTo the south are two long windows providing a good view of the outside area.\nA long cabinet sits by the east wall next to the door to the upper hall, atop it are a number of photo frames all devoid of pictures save for one; it looks like a family photo, but the two children have their eyes closed, and the parents are frowning. Behind the photo frame appears to be a black, leather bound Book.\n");
            break;
         case "Upper Landing": 
            textArea.append("The small landing is illuminated by a skylight directly above the staircase leading back down to the Living Room.\nGazing down the stairs into the dimly lit Living Room sends a shiver down your spine, though you can clearly see nobody at the bottom of the stairs, you can't escape the sensation of being watched from below.\nA small love seat lies along the west wall adjacent to a bookshelf, across from the chair is a dark wooden door with several marks seemingly carved into it by an animal.\n");
            break;
         case "Entrance": 
            textArea.append("A thick wooden door with two large glass panes sits at the bottom of the staircase on the south wall. The front door won't budge and the light switches on the wall beside the door serve no visible purpose.\nTo the east is an opening to a room with several large windows that can be seen from the front door, the Formal Living Room.\nTo the north, past the stairs, is the Lower Hall forking off in several directions.\n"); 
            break;
         case "Formal Living Room": 
            textArea.append("The formal living has three floor-to-ceiling sized windows lining the south wall, giving a very modern look to the room.\nA small glass coffee table sits bare in the center of the room, flanked by unused sofas and a dormant fireplace on the east wall.\nTo the north is a path joining up with the Lower Hall, to the west is the path back to the Entrance of the house.\n");
            break;
         case "Lower Hall": 
            textArea.append("The lower hallway has hardly any natural light illuminating it, but you can see the path leading north ends in a window and what looks like a Dining Room table, opposite the black and white tiles of the Kitchen.\nTo the east are two doors concealed in shadow, it's too dark to try and head down this way.\nTo the west, right behind the base of the stairs is a door taller than the others, made of a solid white piece of wood covered in what looks like black splotches, the Garage.\n");
            break;
         case "Lower Bathroom": 
            textArea.append("With no natural lighting, you fumble around the pitch black bathroom in search of a light switch. You hold your breath and flip what feels like the light, was that the sound of another person breathing? The lower bathroom is empty save for a dark purple leatherbound book on the counter by the sink.");
            break;
         case "Dining Room": 
            textArea.append("The Dining Room has a long wooden table in the center, with a handful of chairs toppled over onto their side; save for two on either end of the table.\nAside from the disheveled nature of the scene, the north and south walls have several pictures all perfectly aligned.\nThe east wall alone has a light brown wooden door, the Pantry. Across from the Pantry, back the way you came, sits the Kitchen.\n");
            break;
         case "Pantry": 
            textArea.append("The Pantry is lined with shelving covered in sealed boxes. A single lightbulb hangs low from the cieling, and after fumbling along the wall you flip the switch to illuminate the room better.\nOther than several dozen boxes and a number of empty water gallons, the pantry seems to hold nothing of consequence as far as you can see. You feel a drip of water land on your head, and you quickly look up to the cieling. There above you is a damp black spot, occasionally driping water; it's clear there's some kind of water leak on the second floor...\n");
            break;
         case "Kitchen": 
            textArea.append("The kitchen has cabinets and black granite countertops lining the north and west walls.\nA light wooden door sits to the south with no door handle visible, it was the door to the Living Room.\nA rounded table is at the center of the kitchen with several pieces of paper atop it.\nAfter rummaging through the drawers, cabinets, and the fridge, you find nothing of use save for a handfull of spoons that hardly seem worthwhile in taking.\n");
            break;
         case "Living Room": 
            textArea.append("The dimly lit Living Room has a large flat screen TV on the south wall, to the right of the TV is a gaping hole leading into the Garage, the edges of the hole seem smoothed down, as if from wear and tear of someone passing through it multiple times.\nAcross from the TV lies an extra long sofa with a coffee table in front of it.\nNext to the sofa is a staircase heading upwards to a dull grey light.\n");
            break;
         case "Garage": 
            if(episodeNum.equals("Day 1")){
               textArea.append("The two car Garage is seemingly empty save for what looks like several oil stains in the center of the concrete.\nTo the east is a window and a door surrounded by large sealed up boxes.\nThe east wall has several light switches that don't work and the door leading back to the Lower Hall.\nThe south wall has two garage doors that don't appear to be hooked up to the garage door openers, judging by the chains hanging from the lifts.\nThe north wall has numerous hooks and panels that should have been holding power tools and equipment; but sadly this wall is completely bare of tools.\nTo the left of the tool racks is a large Hole in the wall, seemingly leading to another room of the house.\n"); 
            } else if(episodeNum.equals("Day 2")){
               textArea.append("There, jutting out of the garage door, which had exploded inwards from the colision the night before, was an SUV. Its front window was severely cracked, along with some minor dents to the front half of the body. You approach the car slowly, and as your eyes adjust to the poorly lit garage, you see the driver side door ajar. Quickly you go over and open the door to find the car empty, the only signs of life being the residue of spilled coffee all over the dash.\n You crawl into the car and try to start it to no avail, after letting out a sigh you crawl in the back and try to open the trunk, which would finally allow you to leave the nightmare-like house. Alas, with all the kicking and pushing you had the energy for, the trunk wouldn't budge. Climbing back up to the front of the SUV, you spot a silver tube on the floor of the passenger side. Picking up said tube, you realize it is a flashlight, and with the press of a button it illuminates the car! Knowing you can explore the house further with the help of a light, you hop out of the crashed vehicle. \n");
            }
            break;
         case "Study": 
            textArea.append("???");
            break;
      }
   }
   
     // look2, alternate descriptions for each room, for the final day
   public static void look2(String currLocation, JTextArea textArea, String episodeNum){
      switch(currLocation){
         case "Bedroom": 
            textArea.append("The once pale beige walls now lay stained, gray and black streaks drip down from the cieling; most likely mold and water damage. The closet door is knocked off the hinge and leaning haphazardly against the wall. The nighstand lies tipped over on its side adjacent to what used to resemble a bed. Puddles of water and some broken glass line the floor.\n");
            //Pale beige walls sit uncovered, with window to the south.\nWhite wooden doors conceal a closet, across from a small bed.\nA nighstand with no light atop it is next to the bed. The only door out of the room leads to the Upper Hall\n");
            break;
         case "Upper Hall": 
            textArea.append("The long hallway on the second floor, its carpet damp with water and reeking of soggy wood and drywall. The walls are covered in new scratches and dents, as if an animal got loose and lashed out at its surroundings. The door to the Library sits ajar for the first time in an eternity...\n");
            //A long hallway on the second floor of the house, it stretches from the east to the west, with a window ending the hall to the east.\nThe bedroom sits on the south side of the hall, with three doors lining the wall opposite the bedroom door; the Upper Bathroom, Library, and a room you cannot recall, respectively.\nAt the end of the west end of the hall is a large landing with a staircase and one final door on the west wall, the door to the master bedroom.\n");
            break;
         case "Upper Bathroom": 
            textArea.append("The cramped bathroom doesn't appear to be the cause of the water damage, as the puddles around the sink and toilet seem to be just as big as that last time you were here. However, a strong smell of vinegar, or maybe bleach, permeates the air and makes it hard to breath. Staying in this bathroom for too long wouldn't be a good idea.\n");
            //The cramped bathroom has a robin egg blue color to it, but the dull grey light coming in through the skylight fills you with unease as you look around.\nA puddle of still water has pooled around the toilet, leaking out of the sink which looks like it has been slowly dripping water for a very long time. Trying not to step in the pool of water, you stretch your arm out and turn the knob of the sink, ensuring the water is turned off. You also pull up on a level beside the sink's knob, draining the full basin of water. 'How long was this dripping?' As the stagnant water drains from the sink, you spot another key; putting it into your pocket as soon as you recognize the shape.\n");
            break;
         case "Library": 
            textArea.append("The library is wide and the north wall is lined with two long windows. The lights don't work, but you can see the floor is littered with books, each of the east, west and south walls are lined with half empty bookshelves, most of their contents lining the hardwood floor; which, unlike the rest of the house, is devoid of puddles or mold stains. A bright purple book sits leaning against the wall lined with windows...\n");
            break;
         case "Master Bedroom": 
            textArea.append("The once modern looking master bedroom looks as if a tornado had blown through; the sheets of the bed lie scattered and torn on the floor. Water damage and mold cover the walls and cieling. The long cabinet where you found the journal and several photo frames has been toppled over. Unlike the rest of the house, you feel a strange calmness eminating from the room, as if you were safe for the moment, but why? Of all the books littering the floor, one with a light purple cover catches your eye...\n");
            //The modern looking master bedroom is sparsely decorate, save for a number of abstract paintings on the east and west walls.\nTo the south are two long windows providing a good view of the outside area.\nA long cabinet sits by the east wall next to the door to the upper hall, atop it are a number of photo frames all devoid of pictures save for one; it looks like a family photo, but the two children have their eyes closed, and the parents are frowning. Behind the photo frame appears to be a black, leather bound Book.\n");
            break;
         case "Upper Landing": 
            textArea.append("The small landing has been relatively untouched by the forces of time, or the animal that seems to have ravaged most of the house. Though with the grey light fading, it has grown harder to look around the room and down the stairs.\n");
            //The small landing is illuminated by a skylight directly above the staircase leading back down to the Living Room.\nGazing down the stairs into the dimly lit Living Room sends a shiver down your spine, though you can clearly see nobody at the bottom of the stairs, you can't escape the sensation of being watched from below.\nA small love seat lies along the west wall adjacent to a bookshelf, across from the chair is a dark wooden door with several marks seemingly carved into it by an animal.\n");
            break;
         case "Entrance": 
            textArea.append("The thick wooden door at the front of the house has had several large chunks of wood taken out of it; with a plethora of cuts and dents in the glass. Something has tried repeatedly to smash the door it seems. A large puddle sits at the bottom of the stairs, causing the wooden staircase's last step to bow downwards at a sharp angle, water damage most likely deteriorating the stair's integrity.\n");
            //"A thick wooden door with two large glass panes sits at the bottom of the staircase on the south wall. The front door won't budge and the light switches on the wall beside the door serve no visible purpose.\nTo the east is an opening to a room with several large windows that can be seen from the front door, the Formal Living Room.\nTo the north, past the stairs, is the Lower Hall forking off in several directions.\n"); 
            break;
         case "Formal Living Room": 
            textArea.append("The three large windows in the formal living room appear to have been completely covered up by a black material, just a small amount of grey light pouring in through the windows indicates that its not entirely opaque. The coffee table is smashed to pieces and strewn about the floor, you watch your step for glass. A large pile of black ash lies in the fireplace, as if someone had lit dozens of fires and never cleaned it out.\n");
            //The formal living has three floor-to-ceiling sized windows lining the south wall, giving a very modern look to the room.\nA small glass coffee table sits bare in the center of the room, flanked by unused sofas and a dormant fireplace on the east wall.\nTo the north is a path joining up with the Lower Hall, to the west is the path back to the Entrance of the house.\n");
            break;
         case "Lower Hall": 
            textArea.append("All the walls of the lower hallway are streaked with dark black and grey stains of water damage, with one deep, long cut in the wall stretching from the corner all the way down to the ominous red door. With flashlight in hand you can see puddles and water damage abounding down the dark hall leading up to that door.\n");
            //The lower hallway has hardly any natural light illuminating it, but you can see the path leading north ends in a window and what looks like a Dining Room table, opposite the black and white tiles of the Kitchen.\nTo the east are two doors concealed in shadow, it's too dark to try and head down this way.\nTo the west, right behind the base of the stairs is a door taller than the others, made of a solid white piece of wood covered in what looks like black splotches, the Garage.\n");
            break;
         case "Lower Bathroom": 
            textArea.append("Unsure of what you would find in the lower bathroom, you are pleased to discover that the bath curtain has been torn to shreds and that no human, or creature, is hiding in there again. A quick glance through all of the drawers and cabinets proves fruitless, but as you look about, a loud sloshing sound jolts you upright. Shining your flashlight at the toilet, you see a splash of water shoot out of the bowl and onto the floor, where a small puddle had already begun forming. Staying in this room for too long wouldn't be a good idea...\n");
            //With no natural lighting, you fumble around the pitch black bathroom in search of a light switch. You hold your breath and flip what feels like the light, was that the sound of another person breathing? The lower bathroom is empty save for a dark purple leatherbound book on the counter by the sink.");
            break;
         case "Dining Room": 
            textArea.append("All of the furniture, chairs, tables, everything, is gone from the dining room. The carpet is soaking wet and a rotten smell from the kitchen permeates the air.\n");
            //The Dining Room has a long wooden table in the center, with a handful of chairs toppled over onto their side; save for two on either end of the table.\nAside from the disheveled nature of the scene, the north and south walls have several pictures all perfectly aligned.\nThe east wall alone has a light brown wooden door, the Pantry. Across from the Pantry, back the way you came, sits the Kitchen.\n");
            break;
         case "Pantry": 
            textArea.append("The pantry, surprisingly, is completely devoid of boxes this time around. Using your flashlight, you see that the smal lightbulb that once hung from the cieling has been smashed, and that the water damage leaking down the walls seems to have created what looks like graffiti or art depicting arrows. These 'arrows' all seem to be pointing towards the south wall of the pantry, where you spot a small square indent in the wall. Kneeling down, you press your hands against the surface of the wall and push, the square folds inwards to reveal what looks like a very small doorway.\n");
            //The Pantry is lined with shelving covered in sealed boxes. A single lightbulb hangs low from the cieling, and after fumbling along the wall you flip the switch to illuminate the room better.\nOther than several dozen boxes and a number of empty water gallons, the pantry seems to hold nothing of consequence as far as you can see. You feel a drip of water land on your head, and you quickly look up to the cieling. There above you is a damp black spot, occasionally driping water; it's clear there's some kind of water leak on the second floor...\n");
            break;
         case "Kitchen": 
            textArea.append("The smell of rotting food overpowers your senses, the cabinets and drawers of the kitchen have all been strewn about and smashed. The smell is soo strong you don't feel like looking around the room for much longer.\n");
            //The kitchen has cabinets and black granite countertops lining the north and west walls.\nA light wooden door sits to the south with no door handle visible, it was the door to the Living Room.\nA rounded table is at the center of the kitchen with several pieces of paper atop it.\nAfter rummaging through the drawers, cabinets, and the fridge, you find nothing of use save for a handfull of spoons that hardly seem worthwhile in taking.\n");
            break;
         case "Living Room": 
            textArea.append("The living room, now with the grey light nearly all gone, is almost impossible to look around without the aid of your flashlight. Everything has been torn to shreds, the carpet, the sofas, and bannister of the staircase leading to the upper landing. The one thing that seems normal is the large TV on the south wall...\n");
            //The dimly lit Living Room has a large flat screen TV on the south wall, to the right of the TV is a gaping hole leading into the Garage, the edges of the hole seem smoothed down, as if from wear and tear of someone passing through it multiple times.\nAcross from the TV lies an extra long sofa with a coffee table in front of it.\nNext to the sofa is a staircase heading upwards to a dull grey light.\n");
            break;
         case "Garage": 
            textArea.append("The puddles scattered all around the garage's concrete floor give off a stagnant smell. The faint grey light from the one window in the garage seems to be fading by the minute, you flick your flashlight on and off instinctively, making sure it still works.\n");
            break;
         case "Study": 
            textArea.append("The small, square study is surprisingly intact with little to no water damage seeping in. A wooden desk sits squarely in the middle of the room, a leather chair pushed snugly up against the desk. Behind the desk is a window with deep red curtains pulled across; mixed with the dark gray light coming in through the window, the curtains lit up the room in a blend of deep red and gray. Paintings of abstract art adorn each of the walls, mostly shapes, right angles, and symbols of differing colors. Near the cieling, by the four corners of the room, hung black candles via silver platters jutting from the wall. A large book with what appears to be a wooden cover and spine sits wide open atop the desk...\n");
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
            player.setBounds(660, 430, 10,10);// good             
            break;
         case "Dining Room": 
            player.setBounds(660, 380, 10,10);// good            
            break;
         case "Pantry": 
            player.setBounds(726, 380, 10,10);// good            
            break;
         case "Kitchen": 
            player.setBounds(500, 370, 10,10); // good           
            break;
         case "Living Room": 
            player.setBounds(485, 415, 10,10);// good            
            break;
         case "Garage": 
            player.setBounds(480, 480, 10,10);// good            
            break;
         case "Study": 
            player.setBounds(720, 430, 10,10);// good            
            break;
         case "Ritual Room":
            player.setBounds(750, 190, 10,10);// good            
            break;
      }
   }

}// End of the Map class