package models;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import javax.xml.stream.Location;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import models.User;
import controllers.FlixAPI;
import models.Movie;


public class FlixAPITest
{
	public FlixAPI flix;

	@Before
	  public void setup()
	  {
	    flix = new FlixAPI();
	    for (User user : flix.users)
	    {
	      flix.addUser(user.firstName, user.lastName, user.age, user.gender, user.occupation);
	    }
	  }

	  @After
	  public void tearDown()
	  {
	    flix = null;
	  }

	  @Test
	  public void testUser()
	  {
	    assertEquals (flix.users.size(), flix.getUsers().size());
	    flix.addUser("john", "doe", "19", "male", "farmer");
	    assertEquals (flix.users.size(), flix.getUsers().size());
	  }  

	  @Test
	  public void testUsers()
	  {
	    assertEquals (flix.users.size(), flix.getUsers().size());
	 
	  }
       
	  @Test
	  public void testDeleteUsers()
	  {
	    assertEquals (flix.users.size(), flix.getUsers().size());
	 //   User jimmy = flix.getUser((long) 1);
	  //  flix.deleteUser(jimmy.id);
	    assertEquals (flix.users.size()-1, flix.getUsers().size());    
	  }  
	  
	  @Test
	  public void testMovie()
	  {
	    Movie movie = flix.addMovie("titanic", "2000", "www.titanic.com");
	    Movie id = flix.getMovie((long) 0);
	    assertEquals(movie,  movie);
	    assertEquals(id, movie);
	  }
	  
	}

