package controllers;

import java.util.Collection;
import java.util.Collections;

import com.google.common.base.Optional;

import java.util.HashMap;
import java.util.Map;

import utils.Serializer;
import models.Rating;
import models.Movie;
import models.User;

public class FlixAPI
{
  public Serializer serializer;
  
  public Map<Long,   User>   userIndex       = new HashMap<>();
  public Map<Long, Movie>   movieIndex      = new HashMap<>();
  public Collection<User> users = getUsers();
 
      
  public FlixAPI()
  {}
  
  public FlixAPI(Serializer serializer)
  {
    this.serializer = serializer;
  }
  /**
   * load method populates the HashMaps with the saved data
   * counter is also saved here to prevent bug where
   * id is reset to 0 when loaded in.
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  public void load() throws Exception
  {
    serializer.read();
    userIndex       = (Map<Long, User>)     serializer.pop();
    movieIndex     = (Map<Long, Movie>)   serializer.pop();
    User.counter = (Long) serializer.pop();
    Movie.counter= (Long) serializer.pop();

  }
  /**
   * takes the contents of the HashMaps and stores the to xml
   * counter is also saved from here.
   * @throws Exception
   */
  void store() throws Exception
  {
	  serializer.push(Movie.counter);
	  serializer.push(User.counter);
    serializer.push(movieIndex);
    serializer.push(userIndex);
    serializer.write(); 
  }
  

  /**
   * Users are saved to Collection
   * @return
   */
  public Collection<User> getUsers ()
  {
    return userIndex.values();
  }
  /**
   * This is where new users are created and added to the userIndex HashMap.
   * @param firstName
   * @param lastName
   * @param age
   * @param occupation
   * @param gender
   * @return
   */
  public User addUser(String firstName, String lastName, String age, String occupation, String gender)
  {
    User user = new User (firstName, lastName, age, occupation, gender);
    userIndex.put(user.id, user);
    return user;
  }
  

/**
 * Used for finding a user using their id.
 * @param id
 * @return
 */
  public User getUser(Long id) 
  {
    return userIndex.get(id);
  }
  /**
   * Find a movie by referencing its id.
   * @param id
   * @return
   */
  public Movie getMovie(Long id) 
  {
    return movieIndex.get(id);
  }
/**
 * deletes a user from the userIndex using id.
 * @param id
 */
  public void deleteUser(String id) 
  {
    userIndex.remove(id);
  }
  /**
   * deletes a movie from movieIndex using id.
   * @param id
   */
  public void deleteMovie(String id) 
  {
	 movieIndex.remove(id);
  }

  /**
   * Unimplemented
   * Would get a users rating
   * @param id
   * @return
   */
  public Movie getMovieRating (Long id)
  {
    return movieIndex.get(id);
    
  }
  
  /**
   * Creates a new Movie and stores it in movieIndex.
   * @param title
   * @param year
   * @param url
   * @return
   */
  public Movie addMovie(String title, String year, String url) 
  {
	   Movie movie = new Movie (title, year, url);
	    movieIndex.put(movie.id, movie);
	    return movie;
    }
  }

