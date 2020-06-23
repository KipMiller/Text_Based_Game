// Chris Miller
// Date: 6/19/2020
//
// Map class
// Holds all locations of the map 
import java.lang.*;
import java.util.*;
import java.io.*;

public class Map{
   List<Location> map;
   
   public Map(){
       map = new ArrayList<Location>();

       String[] descriptions = {"A small bedroom with a bed, closet, window and a nightstand.\n","A long hallway lined with four doors, a window and a staircase going downwards.\n",
       "A dingy bathroom dimly lit by a skylight.\n","A cluttered library, each wall is lined with shelving, the floor is covered with books.\n","The master bedroom, the windows are covered with dark curtains making it impossible to see.\n", 
       "????\n","The upper landing, a small room consisting of a staircase leading downwards and a door.\n", 
       // Downstairs
       "The entrance of the house, at the bottom of the stairs lies the front door, to your left is a large room.\n","The formal living room, being a couple sofas and decorated bookshelves.\n","The lower hall is dimly lit and consists of several doors, and a path leading to what looks like a dinging room.\n",
       "The garage.\n","The Study, BOO.\n", "The lower bathroom.\n", "The dining room.\n","The Pantry.\n","The Kitchen.\n", "The living room.\n"};
   
      // top floor 
      String[] n1 ={"Upper Hall"};
      Location loc1 = new Location("Bedroom",n1,descriptions[0],true,false,"MAP_top_floor_F.png");
      String[] n2 ={"Bedroom", "Library", "Upper Bathroom", "Master Bedroom", "?", "Entrance"};
      Location loc2 = new Location("Upper Hall",n2,descriptions[1],false,false,"MAP_top_floor_E.png");
      String[] n3 ={"Upper Hall"};
      Location loc3 = new Location("Upper Bathroom",n3,descriptions[2],false,true,"MAP_top_floor_D.png");
      String[] n4 ={"Upper Hall"};
      Location loc4 = new Location("Library",n4,descriptions[3],false,true,"MAP_top_floor_C.png");
      String[] n5 ={"Upper Hall Upper Landing"};
      Location loc5 = new Location("Master Bedroom",n5,descriptions[4],false, true, "MAP_top_floor_A.png");
      String[] n6 ={"Upper Hall"};
      Location loc6 = new Location("?",n6,descriptions[5],false,true, "MAP.png");
      String[] n7 ={"Master Bedroom"};
      Location loc7 = new Location("Upper Landing",n7,descriptions[6],false,true, "MAP.png");
      
      
      // bottom floor 
      String[] n8 ={"Formal Living Room", "Lower Hall"};
      Location loc8 = new Location("Entrance",n8,descriptions[7],false,false, "MAP.png");
      String[] n9 ={"Entrance", "Lower Hall"};
      Location loc9 = new Location("Formal Living Room",n9,descriptions[8],false,false, "MAP.png");
      String[] n10 ={"Formal Living Room","Entrance", "Garage", "Study", "Lower Bathroom", "Dining Room"};
      Location loc10 = new Location("Lower Hall",n10,descriptions[9],false,false, "MAP.png");
      String[] n11 ={"Lower Hall"};
      Location loc11 = new Location("Garage",n11,descriptions[10],false,true, "MAP.png");
      String[] n12 ={"Lower Hall"};
      Location loc12 = new Location("Study",n12,descriptions[11],false,true, "MAP.png");
      String[] n13 ={"Lower Hall"};
      Location loc13 = new Location("Lower Bathroom",n13,descriptions[12],false,true, "MAP.png");
      String[] n14 ={"Lower Hall", "Kitchen", "Pantry"};
      Location loc14 = new Location("Dining Room",n14,descriptions[13],false,false, "MAP.png");
      String[] n15 ={"Dining Room"};
      Location loc15 = new Location("Pantry",n15,descriptions[14],false,true, "MAP.png");
      String[] n16 ={"Dining Room" ,"Living Room"};
      Location loc16 = new Location("Kitchen",n16,descriptions[15],false,false, "MAP.png");
      String[] n17 ={"Kitchen"};
      Location loc17 = new Location("Living Room",n17,descriptions[16],false,true, "MAP.png");
      
      
      // Add all of the location variables to the map arrayList
      map.add(loc1);map.add(loc2);map.add(loc3);map.add(loc4);
      map.add(loc5);map.add(loc6);map.add(loc7);map.add(loc8);
      map.add(loc9);map.add(loc10);map.add(loc11);map.add(loc12);
      map.add(loc13);map.add(loc14);map.add(loc15);map.add(loc16);
      map.add(loc17);
   }
   
   
   public Location getLocation(String currLocation){
         for(int i = 0; i < map.size(); i++){
            if(map.get(i).getName().equals(currLocation)){
               return map.get(i);
            }
         }
         return null;
   }


   public boolean checkMove(String currLocation, String target){
      for(int i = 0; i < map.size(); i++){
         if(map.get(i).getName().equals(currLocation)){
            if(map.get(i).isNeighbor(target)){// TODO: Need to check if this room is locked
               //map.get(i).setVisited();// indicate this room has now been visited 
               return true;
            }
         }
      }
      return false;
   }





}// End of the Map class
