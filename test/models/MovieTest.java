package models;

import static org.junit.Assert.*;

import org.junit.Test;

public class MovieTest 
{
	  Movie titanic = new Movie ("titanic", "01-December-1997", "http://m.imdb.com/title/tt0120338/");

	  @Test
	  public void testCreate()
	  {
	    assertEquals ("titanic",                titanic.title);
	    assertEquals ("01-December-1997",                 titanic.year);
	    assertEquals ("http://m.imdb.com/title/tt0120338/" , titanic.url);   
	  }



	  @Test
	  public void testToString()
	  {
	    assertEquals ("Movie{" + titanic.id + ", titanic, 01-December-1997, http://m.imdb.com/title/tt0120338/}", titanic.toString());
	  }
	  
	  @Test
	  public void testEquals()
	  {
	    Movie interstellar = new Movie ("interstellar", "17-January-2015", "http://m.imdb.com/title/tt0120321/"); 
	    Movie inception   = new Movie ("inception", "15-May-2011", "http://m.imdb.com/title/tt0120328/"); 

	    assertEquals(interstellar, interstellar);
	    assertEquals(inception, inception);
	    assertNotEquals(interstellar, inception);
	  } 
}
	  