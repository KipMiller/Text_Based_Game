// Chris Miller
// Date: 6/19/2020
//
// Location class
// Holds all locations of the map and keeps track of which are connected to which.
import java.lang.*;
import java.util.*;
import java.io.*;

public class Location{
   String name; 
   String[] neighbors;// which rooms this room is connected to 
   String description; 
   String picture;// the name of the jpeg
   ArrayList<String> items;// if the location has any items 
   boolean visited;// whether or not the player has visited this room yet (for filling out the map)
   boolean locked;// if the room is locked or not, true for locked, false unlocked
   
   public Location(String name, String[] neighbors,String description, boolean visited, boolean locked, String picture, ArrayList<String> items){
      this.name = name;
      this.neighbors = neighbors;   
      this.description = description;
      this.visited = visited;  
      this.locked = locked;
      this.picture = picture;
      this.items = items;
   }
   
   // Check to see if the target location is connected to the current location (IE a neighbor)
   public boolean isNeighbor(String target){
      //System.out.println("Location's neighbors: " + neighbors);
      for(int i = 0 ; i < neighbors.length; i++){
         if(neighbors[i].toLowerCase().equals(target)){
            return true;
         }
      
      }
      return false;
   }
   
   
   // Return true or false if the item the player wants to pick up is valid or not
   public boolean isItem(String item){
      for(int i = 0; i < items.size(); i++){
         if(items.get(i).equals(item)){
            items.remove(i);// get rid of that item from the room
            return true;
         }
      }
      return false;
   }

   // Getters
   public String getName(){
      return name;
   }
   public String[] getNeighbors(){
      return neighbors;
   }

   public boolean isVisited(){
      return visited;
   }
   public boolean isLocked(){
      return locked;
   }
   public String getDescription(){
      return description;
   }
   public String getPicture(){
      return picture;   
   }
   
   // Setters
   public void setVisited(){
      visited = true;
   }
   public void setLocked(){
      locked = false;
   }

}// End of the Map class