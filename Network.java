/** @Author William Turner
  * Describes a network of entites
 */
import java.util.LinkedList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.ListIterator;

public class Network implements Iterable<Entity>{
  
  LinkedList<Entity> entitieslist = new LinkedList<Entity>();
  
  public Network() {  
  }
  
  /**Adds two entities to the network (will not add an entity if it is already in the network) and sets the two
    * entities to be related to each other
   */
  public void addRelation(Entity e1, Entity e2) {
    
    if (entitieslist.contains(e1) == false)
      entitieslist.add(e1);
    
    if (entitieslist.contains(e2) == false)
      entitieslist.add(e2);
    
    e1.relations.add(e2);
    e2.relations.add(e1);
  }
  
  /** removes two entities from the network, and sets them to be not related to each other
   */
  public void removeRelation(Entity e1, Entity e2) {
    
    if(entitieslist.contains(e1))
      entitieslist.remove(e1);
    
    if(entitieslist.contains(e2))
      entitieslist.remove(e2);
    
    if(e1.relations.contains(e2))
      e1.relations.remove(e2);
    
    if(e2.relations.contains(e1))
      e2.relations.remove(e1);
  }
  
  /** adds multiple entities to the network. Takes a .txt file with entity pairs on lines seprated by commas. 
   */
  public void addRelations(String s) throws IllegalArgumentException {
    
    try (BufferedReader br = new BufferedReader(new FileReader(new File(s)))) {
      String line;
      
      while ((line = br.readLine()) != null) {
        
        String[] splitLine = line.split(", ");
        String leftEntity = splitLine[0];
        String rightEntity = splitLine[1];
        
        if (leftEntity != null && rightEntity != null) {
          
        Entity left = new Entity(leftEntity);
        Entity right = new Entity(rightEntity);
        addRelation(left, right);
        left.relations.add(right);
        right.relations.add(left);
        }
        
        else 
          throw new IllegalArgumentException("File is not correctly formatted");
      }
    }
    
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /** overrides iterator() to return a listIterator starting at index 0
    * @return <code>listIterator(0)<code>
   */
  public Iterator<Entity> iterator() {
    
    return entitieslist.listIterator(0);
  }
 
  /** helper method for testing, searches entitieslist by the name of the entity
    * @return <code>true<code> if entitieslist contains an entity with name s
    *         <code>false<code> if it does not
   */ 
  public boolean searchELByName(String s) {
    
    boolean returnvalue = false;
    
    for(Entity e : entitieslist) {
      
      if(e.getName().equals(s))
        returnvalue = true;
    }
    return returnvalue;
  }
}