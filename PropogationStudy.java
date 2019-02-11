/** @author William Turner
  * Describes a propogation study on a network of entities
 */
import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PropogationStudy {
  
  // the network for the propogation study
  Network network;
  // the infection rate for the study
  double infectionrate;
  // the heal rate for the study
  double healrate;
  // the wear rate for the study (the rate at which inoculated entities become uninfected)
  double wearrate;
  
  public PropogationStudy(Network network, double infectionrate, double healrate, double wearrate) {
    
    this.network = network;
    this.infectionrate = infectionrate;
    this.healrate = healrate;
    this.wearrate = wearrate;
  }
  
  public static void main(String[] args) {
    
    Network n1 = new Network();
    n1.addRelations(args[0]);
    PropogationStudy ps = new PropogationStudy(n1, Double.parseDouble(args[1]), Double.parseDouble(args[2]),
                                            Double.parseDouble(args[3]) );

    try (BufferedReader br = new BufferedReader(new FileReader(new File(args[5])))) {
      String line;
      
      while ((line = br.readLine()) != null) {
        
        for (Entity e : ps.getNetwork() ) {
          
          if (e.getName().equals(line) )
            e.setHealthStatus(Entity.Health.Infected);
        }
      }
    }
    
    catch (IOException e) {
      e.printStackTrace();
    }
    
    if (args [6] != null) {
    
      try (BufferedReader br = new BufferedReader(new FileReader(new File(args[6])))) {
        String line;
      
        while ((line = br.readLine()) != null) {
        
          for (Entity e : ps.getNetwork() ) {
          
            if (e.getName().equals(line) )
              e.setHealthStatus(Entity.Health.Uninfected);
          }
        }
      }
      catch (IOException e) {
        e.printStackTrace();
      } 
    } 
    ps.runPropogation(Integer.parseInt(args[4]));
  } 
  
  /** returns the network for the study
    * @return <code>network<code>
   */
  public Network getNetwork() {
    
    return network;
  }
  
  /** sets the network for the study to the network parameter
    * @param n Network that network will be set to
   */
  public void setNetwork(Network n) {
    
    network = n;
  }
  
  /** returns the infection rate for the study
    * @return <code>infectionrate<code>
   */
  public double getInfectionRate() {
   
    return infectionrate;
  }
  
  /** sets the infection rate to the double input
    * @param ir the double that infection
   */
  public void setInfectionRate(double ir) {
    
    infectionrate = ir;
  }
  
  /** returns the heal rate for the study
    * @return <code>healrate<code>
   */
  public double getHealRate() {
    
    return healrate; 
  }
  
  /** sets the heal rate to the double input
    * @param hr the double that healrate will be set to
   */
  public void setHealRate(double hr) {
    
    healrate = hr; 
  }
  
  /** returns the wear rate for the study
    * @return <code>wearrate<code>
   */
  public double getWearRate() {
    
    return wearrate; 
  }
  
  /** sets the wear rate to the double input
    * @param wr the double that wearrate will be set to
   */
  public void setWearRate(double wr) {
    
    wearrate = wr; 
  }
  
  /** sets an entity's health status to infected if it is in the network
    * @param e the enity that will be infected
   */
  public void infect(Entity e) {
    
    if(network.entitieslist.contains(e))
      e.setHealthStatus(Entity.Health.Infected); 
    else
      throw new NoSuchElementException();
  }
  
  /** infects entities in the network at a determined rate
    * @param d the double that determines the probability of each entity being infected
   */
  public void infect(double d) {
    
    // runs through the network and has probability d of infecting each entity
    for(Entity e : network) {
      
      if (Math.random() < d)
        e.setHealthStatus(Entity.Health.Infected);
    }
  }
  
  /** sets an entity's health status to inoculated
    * @param e the entity that will be inoculated
   */
  public void inoculate(Entity e) {
    
    if(network.entitieslist.contains(e))
      e.setHealthStatus(Entity.Health.Inoculated); 
    else
      throw new NoSuchElementException();
  }
  
  /** inoculates entities in a network at a determined rate
    * @param d the double that determines the probability of each entity being innoculated
   */
  public void inoculate(double d) {
    
    // runs through the network and has a probability d of inoculating each entity
    for(Entity e : network) {
      
      if (Math.random() < d)
        e.setHealthStatus(Entity.Health.Inoculated);
    }
  }
  
  /** returns the number of entities in the network that are Uninfected
    * @return <code>returnvalue<code>
   */
  public int getNumUninfected() {
    
    int returnvalue = 0;
   
    // runs through the network and adds 1 to returnvalue for each uninfected entity
    for(Entity e : network) {
      
      if (e.getHealthStatus() == Entity.Health.Uninfected)
        returnvalue++;
    }
    return returnvalue;
  }
  
  /** returns the number of entities in the network that are Inoculated
    * @return <code>returnvalue<code>
   */
  public int getNumInoculated() {
    
    int returnvalue = 0;
   
    // runs through the network and adds 1 to returnvalue for each inoculated entity
    for(Entity e : network) {
      
      if (e.getHealthStatus() == Entity.Health.Inoculated)
        returnvalue++;
    }
    return returnvalue;
  }
  
  /** returns the number of entities in the network that are Infected
    * @return <code>returnvalue<code>
   */
  public int getNumInfected() {
    
    int returnvalue = 0;
   
    // runs through the network and adds 1 to returnvalue for each infected entity
    for(Entity e : network) {
      
      if (e.getHealthStatus() == Entity.Health.Infected)
        returnvalue++;
    }
    return returnvalue;
  }
  
  /** sets the health status of every entity in the network to Uninfected
   */
  public void resetNetwork() {
    
    for(Entity e : network) {
      
      e.setHealthStatus(Entity.Health.Uninfected); 
    }
  }
  
  /** prints the name and health status of each element in the network.
   */
  public void printNetwork() {
    
    for(Entity e : this.getNetwork() ) {
      
     System.out.println(e.getName() + e.getHealthStatus() ); 
    }
  }
  
  /** runs a propogation using infectionrate, healrate, and wearrate. Sets relations of the Infected to BeingInfected,
    * has probability healrate of switching infected entities to BeingInoculated, has probability wearrate of setting
    * Inoculated entities to Uninfected, sets BeingInoculated entities to Inoculated.
   */
  public void runPropogation() {
    
    for(Entity e : network) {
      
      if (e.getHealthStatus() == Entity.Health.Infected){
        
        for(Entity er : e.relations) {
          
          if (er.getHealthStatus() == Entity.Health.Uninfected) {
           
            if (Math.random() < infectionrate)
              er.setHealthStatus(Entity.Health.BeingInfected);
          }
        }
        
        if(Math.random() < healrate)
          e.setHealthStatus(Entity.Health.BeingInoculated);
      }
      
      if (e.getHealthStatus() == Entity.Health.Inoculated) {
        if(Math.random() < wearrate)
          e.setHealthStatus(Entity.Health.Uninfected);
      }
      
      if (e.getHealthStatus() == Entity.Health.BeingInoculated)
        e.setHealthStatus(Entity.Health.Inoculated);
    }
  }
  
  /** runs runPropogation() n number of times.
    * @param n the number of times to run runPropogation()
   */
  public void runPropogation(int n) {
    
    for(int i = 0; i == n; i++) {
      
      this.runPropogation(); 
    }
  }
}