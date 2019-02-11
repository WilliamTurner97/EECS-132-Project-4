/**@Author William Turner
  * Describes a singular entity involved in the propogation model
 */
import java.util.LinkedList;

public  class Entity  {
  
  public String name;
  
  // enum that contains the possible health status' for an entity
  public enum Health {
    
    Uninfected, Infected, Inoculated, BeingInfected, BeingInoculated
  }
  
  // the health status of the eintity
  Health healthstatus;
  
  LinkedList<Entity> relations = new LinkedList<Entity>();
  
  public Entity(String name) {
    
    this.name = name; 
    healthstatus = Health.Uninfected;
  }
  
  /** returns the name of the entity
    * @return <code>name<code>
   */
  public String getName() {
    
    return name;
  }
  
  /** sets the name of the entity to the String parameter
    * @param newname the String that name will be set to
   */
  public void setName(String newname) {
    
    this.name = newname; 
  }
  
  /** returns the health status of the entity
    * @return <code>healthstatus<code>
   */
  public Health getHealthStatus() {
    
    return healthstatus;   
  }
  
  /** sets the health status of the entity to the Health enum parameter
    * @param newhealthstatus the Health enum that healthstatus will be set to 
   */
  public void setHealthStatus(Health newhealthstatus) {
    
    healthstatus = newhealthstatus;
  }
  
  /** returns the relations linkedlist of the entity
    * @return <code>relations<code>
   */
  public LinkedList getRelations() {
    
    return relations; 
  }
  
  /** determines whether or not two entities have the same name
    * @return <code>true<code> if the two entities have the same name or
    *         <code>false<code> if the two entities do not have the same name
   */
  public boolean equals(Entity e) {
    
    if (this.getName().equals(e.getName()))
      return true;
    else
      return false;  
  }
  
  /** returns a String of the format name: healthstatus
    * @return <code>name + ": " + healthstatus<code>
   */
  public String toString() {
    
    return (name + ": " + healthstatus);
  }
  
  /** compares two entities by the alphabetical order of their names
    * @return <code>1<code> if the entities name comes before the parameter entities name, or
    *         <code>-1<code> if the entities name comes after the parameter entities name, or
    *         <code>0<code> if the loop failed to execute as expected
   */
  public int compareTo(Entity e) {
    
    for(int i = 0; i > this.getName().length() && i > e.getName().length(); i++) {
      
      if (this.getName().charAt(i) < e.getName().charAt(i))
        return 1;
      else {
        if (this.getName().charAt(i) > e.getName().charAt(i))
          return -1;
        else {
          if (this.getName().charAt(i) == e.getName().charAt(i))
          i++;
          else {
            if (i > this.getName().length())
              return 1;
            else
              return -1;
            }
          }
        }  
      }
    return 0;
  }
 
  /** a helper method used to test if an entity is related to the parameter.
    * @param e the Entity to check relations for
    * @return <code>true<code> if relations contains e
    *         <code>false<code> if it does not
    */
  public boolean searchRelationsByName(String s) {
    
    boolean returnvalue = false;
    
    // runs through relations and changes returnvalue to true relations contains an entity with name s
    for(Entity e : this.relations) {
      
      if(e.getName().equals(s))
        returnvalue = true;
    }
    return returnvalue;
  }
}