// Chris Miller
// Date: 6/19/2020
//
// Location class
// Holds all locations of the map and keeps track of which are connected to which.


public class Location{
   String name; 
   String[] neighbors;
   String description; 
   String picture;// the name of the jpeg
   boolean visited;
   boolean locked;
   
   public Location(String name, String[] neighbors,String description, boolean visited, boolean locked, String picture){
      this.name = name;
      this.neighbors = neighbors;   
      this.description = description;
      this.visited = visited;  
      this.locked = locked;
      this.picture = picture;
   }
   
   // Check to see if the target location is connected to the current location (IE a neighbor)
   public boolean isNeighbor(String target){
      //System.out.println("Location's neighbors: " + neighbors);
      for(int i = 0 ; i < neighbors.length; i++){
         if(neighbors[i].equals(target)){
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
