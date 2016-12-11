package models;


import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import controllers.FlixAPI;


public class UserTest
{
	
  User john = new User ("john", "doe", "60",  "male", "farmer");
  
  @Test
  public void testCreate()
  {
	
    assertEquals ("john",                john.firstName);
    assertEquals ("doe",                 john.lastName);
    assertEquals ("60" ,                   john.age);   
    assertEquals ("male",              john.gender);   
    assertEquals ("farmer" ,               john.occupation);
  }

  @Test
  public void testToString()
  {
    assertEquals ("User{" + john.id + ", john, doe, 60, male, farmer}", john.toString());
  }
  
  @Test
  public void testEquals()
  {
    User jimmy = new User ("jimmy", "nolan", "45",  "male", "student"); 
    User jeb   = new User ("jeb", "bush", "40",  "male", "teacher"); 

    assertEquals(jeb, jeb);
    assertEquals(jimmy, jimmy);
    assertNotEquals(jimmy, jeb);
  } 
  
}