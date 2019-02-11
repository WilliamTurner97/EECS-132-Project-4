import junit.framework.TestCase;
import java.util.LinkedList;

public class Project4Tests extends TestCase {
  
  // Entity Tests
  public void testGetName() {
    
    Entity e1 = new Entity("Hospital");
    
    assertTrue("getName test failed", e1.getName() == "Hospital");
  }
  
  public void testSetName() {
    
    Entity e1 = new Entity("Hospital");
    e1.setName("Clinic"); 
    
    assertTrue("setName test failed", e1.getName() == "Clinic");
  }
  
  public void testGetHealthStatus() {
   
    Entity e1 = new Entity("Hospital");
    e1.setHealthStatus(Entity.Health.Uninfected);
    
    assertTrue("getHealthStatus test failed", e1.getHealthStatus() == Entity.Health.Uninfected); 
  }
  
  public void testSetHealthStatus() {   
  }
  
  public void testGetRelations() {
    
    Entity e1 = new Entity("Hospital");
    LinkedList<Entity> testlist = new LinkedList<Entity>();
    
    assertTrue("getRelations test failed", e1.getRelations().equals(testlist));
  }
  
  public void testEquals() {
    
    Entity e1 = new Entity("Hospital");
    Entity e2 = new Entity("Hospital");
    
    assertTrue("equals test failed", e1.equals(e2));
  }
  
  public void testToString() {
   
    Entity e1 = new Entity("Hospital");
    e1.setHealthStatus(Entity.Health.Uninfected);
    
    assertTrue("toString test failed", e1.toString().equals("Hospital: Uninfected")); 
  }
  
  // Network tests
  
  public void testAddRelation() {
    
    Network n1 = new Network();
    Entity e1 = new Entity("Hospital");
    Entity e2 = new Entity("Clinic");
    n1.addRelation(e1, e2);
    
    assertTrue("did not add e1", n1.entitieslist.contains(e1));
    assertTrue("did not add e2", n1.entitieslist.contains(e2));
    assertTrue("did not relate e1", e1.relations.contains(e2));
    assertTrue("did not relate e2", e2.relations.contains(e1));
  }
  
  public void testRemoveRelation() {
    
    Network n1 = new Network();
    Entity e1 = new Entity("Hospital");
    Entity e2 = new Entity("Clinic");
    n1.addRelation(e1, e2);
    n1.removeRelation(e1, e2);
   
    assertFalse("did not remove e1", n1.entitieslist.contains(e1));
    assertFalse("did not remove e2", n1.entitieslist.contains(e2));
    assertFalse("did not unrelate e1", e1.relations.contains(e2));
    assertFalse("did not unrelate e2", e1.relations.contains(e1));
  }
  
  public void testAddRelations() {
   
    Network n1 = new Network();
    n1.addRelations("example.txt");
    
    assertTrue("Did not add University Hospitals", n1.searchELByName("University Hospitals") ); 
    assertTrue("Did not add Cleveland Clinic", n1.searchELByName("Cleveland Clinic") );
    assertTrue("Did not add Louis Stokes Cleveland Va Medical Center", n1.searchELByName("Louis Stokes Cleveland VA Medical Center") );
    assertTrue("Did not add Mercy Regional Medical Center", n1.searchELByName("Mercy Regional Medical Center") ); 
  }
  
  // Propogation Study Tests
  
  public void testGetNetwork() {
    
    Network n1 = new Network();
    PropogationStudy ps = new PropogationStudy(n1,.5,.5,.5);
    
    assertTrue("getNetwork test failed", ps.getNetwork().equals(n1));
  }
  
  public void testSetNetwork() {
    
    Network n1 = new Network();
    Network n2 = new Network();
    PropogationStudy ps = new PropogationStudy(n1,.5,.5,.5); 
    ps.setNetwork(n2);
    
    assertTrue("setNetwork test failed", ps.getNetwork().equals(n2));
  }
  
  public void testGetInfectionRate() {
   
    Network n1 = new Network();
    PropogationStudy ps = new PropogationStudy(n1,.5,.5,.5);
    
    assertTrue("getInfectionRate test failed", ps.getInfectionRate() == .5);
  }
  
  public void testSetInfectionRate() {
    
    Network n1 = new Network();
    PropogationStudy ps = new PropogationStudy(n1,.5,.5,.5); 
    ps.setInfectionRate(.75);
    
    assertTrue("setInfectionRate test failed", ps.getInfectionRate() == .75);
  }
  
  public void testGetHealRate() {
   
    Network n1 = new Network();
    PropogationStudy ps = new PropogationStudy(n1,.5,.5,.5);
    
    assertTrue("getHealRate test failed", ps.getHealRate() == .5);
  }
  
  public void testSetHealRate() {
    
    Network n1 = new Network();
    PropogationStudy ps = new PropogationStudy(n1,.5,.5,.5); 
    ps.setHealRate(.75);
    
    assertTrue("setHealRate test failed", ps.getHealRate() == .75);
  }
  
  public void testGetWearRate() {
   
    Network n1 = new Network();
    PropogationStudy ps = new PropogationStudy(n1,.5,.5,.5);
    
    assertTrue("getWearRate test failed", ps.getHealRate() == .5);
  }
  
  public void testSetWearRate() {
    
    Network n1 = new Network();
    PropogationStudy ps = new PropogationStudy(n1,.5,.5,.5); 
    ps.setWearRate(.75);
    
    assertTrue("setWearRate test failed", ps.getWearRate() == .75);
  }
  
  public void TestInfect() {
    
    Network n1 = new Network();
    Entity e1 = new Entity("Derp");
    PropogationStudy ps = new PropogationStudy(n1,.5,.5,.5);
    ps.infect(e1);
    
    assertTrue("infect test failed", e1.getHealthStatus() == Entity.Health.Infected);
  }
  
  public void TestInoculate() {
    
    Network n1 = new Network();
    Entity e1 = new Entity("Derp");
    PropogationStudy ps = new PropogationStudy(n1,.5,.5,.5);
    ps.inoculate(e1);
    
    assertTrue("inoculate test failed", e1.getHealthStatus() == Entity.Health.Inoculated);
  }
  
  public void testGetNumUninfected() {
    
    Network n1 = new Network();
    Entity e1 = new Entity("Derp");
    e1.setHealthStatus(Entity.Health.Uninfected);
    Entity e2 = new Entity("Herp");
    e2.setHealthStatus(Entity.Health.Uninfected);
    n1.addRelation(e1,e2);
    PropogationStudy ps = new PropogationStudy(n1,.5,.5,.5);
    
    assertTrue("getNumUninfected test failed", ps.getNumUninfected() == 2);
  }
  
  public void testGetNumInoculated() {
    
    Network n1 = new Network();
    Entity e1 = new Entity("Derp");
    e1.setHealthStatus(Entity.Health.Inoculated);
    Entity e2 = new Entity("Herp");
    e2.setHealthStatus(Entity.Health.Inoculated);
    n1.addRelation(e1,e2);
    PropogationStudy ps = new PropogationStudy(n1,.5,.5,.5);
    
    assertTrue("getInoculated test failed", ps.getNumInoculated() == 2);
  }
  
  public void testGetNumInfected() {
    
    Network n1 = new Network();
    Entity e1 = new Entity("Derp");
    e1.setHealthStatus(Entity.Health.Infected);
    Entity e2 = new Entity("Herp");
    e2.setHealthStatus(Entity.Health.Infected);
    n1.addRelation(e1,e2);
    PropogationStudy ps = new PropogationStudy(n1,.5,.5,.5);
    
    assertTrue("getNumInfected test failed", ps.getNumInfected() == 2);
  }
  
  public void testResetNetwork() {
    
    Network n1 = new Network();
    Entity e1 = new Entity("Derp");
    e1.setHealthStatus(Entity.Health.Infected);
    Entity e2 = new Entity("Herp");
    e2.setHealthStatus(Entity.Health.Infected);
    n1.addRelation(e1,e2);
    PropogationStudy ps = new PropogationStudy(n1,.5,.5,.5);
    ps.resetNetwork();
    
    assertTrue("resetNetwork test failed", ps.getNumUninfected() == 2);
  }
}
