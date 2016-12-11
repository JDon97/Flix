package controllers;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import utils.Serializer;
import utils.XMLSerializer;
import controllers.FlixAPI;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import models.Movie;
import models.User;

/**
 * Main Class used for initially reading in data
 * and presenting the menu system that is used 
 * to access the Movie/User database
 * @author jack
 *
 */
public class Main
{
    File  moviedatabase = new File("moviedatabase.xml");
    Serializer serializer = new XMLSerializer(moviedatabase);
    
  //  FlixAPI pacemakerAPI = new FlixAPI(serializer);

	public Scanner scan = new Scanner(System.in);
	public String[] splitString;
	public FlixAPI flixAPI = new FlixAPI(serializer);
	
	
	
	/**
	 * Here the .dat file are split and serialized
	 * first the movies are split into (title, year url)
	 * then users are split into (firstName, lastName, age, gender, occupation)
	 * @throws Exception
	 */
	public void readIn()throws Exception
	{
		BufferedReader read = new BufferedReader(new FileReader(new File("/Users/jack/Desktop/items5.dat")));
		try
		{
			String inputLine;
			while ((inputLine = read.readLine()) !=null)
		{
				//defining the csv on which to split the input
			splitString = inputLine.split("[|]");
			Long movieID=Long.parseLong(splitString[0]);
			String title=splitString[1];
			String year=splitString[2];
			String url=splitString[3];
			// take in objects of user
			Movie movie = new Movie(title, year,url);
			flixAPI.movieIndex.put(movieID, movie);
			//store the current Hashmap inputs
			flixAPI.store();
		}
		}
		finally
		{
		if(read !=null)
		{
			read.close();
		}
				BufferedReader read1 = new BufferedReader(new FileReader(new File("/Users/jack/Desktop/users5.dat")));
				try{
					String input;
					while ((input = read1.readLine())!=null)
					{
					
			splitString = input.split("[|]");
			
			Long id=Long.parseLong(splitString[0]);
			String firstName=splitString[1];
			String lastName=splitString[2];
			String age=splitString[3];
			String gender=splitString[4];
			String occupation=splitString[5];
			
			User user = new User(firstName, lastName, age, gender, occupation);
			flixAPI.userIndex.put(id, user);
	flixAPI.store();
			
			
			
		}
		}
		finally
		{
		if(read1 !=null)
		{
			read1.close();
		}
		}
		}
	}
	/**
	 * Main that will load from xml and run main menu
	 * @param args
	 * @throws Exception
	 */
  public static void main(String[] args) throws Exception
  {    
	  Main run = new Main();
	  //load from the xml file "moviedatabase.xml"
    run.flixAPI.load();
    //welcome user
    run.printWelcome();
    //run the menu system
	run.runMainMenu();
  }

  /**
   * Main menu for the movie/user database
   * @return
   */
  public int mainMenu()
  {
  
  	scan.nextLine();
	    System.out.println("~~~~~~~~~~~~~~~~~~~~");
      System.out.println("1) Add User");  
      System.out.println("2) Save User Data");     
      System.out.println("3) Delete User");
      System.out.println("4) List Users");
      System.out.println("~~~~~~~~~~~~~~~~~~~~");
      System.out.println("5) Add Movie");
      System.out.println("6) Save Movie Data"); 
      System.out.println("7) Delete Movie");
      System.out.println("8) List Movies");
      System.out.println("~~~~~~~~~~~~~~~~~~~~");
      System.out.println("0) Exit");
      System.out.println("~~~~~~~~~~~~~~~~~~~~");
      System.out.print("==>>");
      boolean goodInput = false;
		int option = 0;
		do{
			try {
      option = scan.nextInt();
      goodInput=true;
	}
	catch (Exception e){
		String throwOut = scan.nextLine();
		System.out.println("Number required - you entered text");
		System.out.println("Press any key to continue");
		//Exception handling
		scan.nextLine();
		runMainMenu();
	}
	}while(!goodInput);
      return option;
	   }
/**
 *  main menu switch statement
 * calls methods used in the above menu
 * also contains load and save features
 */
  public void runMainMenu()
  {
      int option = mainMenu();
      while (option != 0)
      {
       
        switch (option)
        {
          case 1:               
          createUser(); 
            break;
          case 2:
			try {
				flixAPI.store();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
            break;
          case 3:
          deleteUser();     
            break;  
          case 4: 
       	   listUsers();
       	   break;
          case 5:
        	  createMovie();
        	  break;
          case 6:
        	  try {
				flixAPI.store();
			} catch (Exception e) {
				e.printStackTrace();
			}
        	  break;
          case 7:
        	  deleteMovie();
        	  break;
          case 8:
        	  listMovies();
        	  break;
        }
        System.out.println("\nPress any key to continue...");
        scan.nextLine();
        scan.nextLine();
       
        option = mainMenu();
     
      }
      System.out.println("Exiting... bye");
      System.exit(0);
   }
  
  /**
   * Method uses addMovie method from FlixAPI
   * to create a new user 
   */
  public void createMovie() 
  {
	  flixAPI.addMovie(title(), year(), url());
	  }
  /**
   * Method uses addUser from FlixAPI
   * to create a new movie
   */
  public void createUser() 
  { 
	 flixAPI.addUser(firstName(), lastName(), age(), gender(), occupation());
	
  }
  /**
   * Method uses deleteUser from FlixAPI
   * to delete a user from the map
   */
  public void deleteUser() 
  {
	 flixAPI.deleteUser(userIDGet());
  }
 /**
  * Method used to take the input for the id to be deleted
  * @return
  */
  public String userIDGet()
  {
	  System.out.println("Enter the id of the user to be deleted: ");
	  scan.nextLine();
	  String id = scan.nextLine();
	  return id;
  }
  /**
   * Method used deleteMovie() from flipAPI to delete a key from the map	
   */
  public void deleteMovie() 
  {
	 flixAPI.deleteMovie(movieIDGet());
  }
  /**
   * Method that allows user to input an id to delete
   * @return
   */
  public String movieIDGet()
  {
	  System.out.println("Enter the id of the movie to be deleted: ");
	  String id = scan.nextLine();
	  return id;
  }
  

  /**
   * Takes in an input for the String title
   * @return
   */
  public String title()
  {
	  System.out.println("Enter a movie title");
	  scan.nextLine();
	  String title = scan.nextLine();
	return title;
	  
  }
  /**
   * Takes in an input for the String year which is a mix of String and other types
   * @return
   */
  public String year()
  {
	  System.out.println("Enter the movies release date: ");
	  String year = scan.nextLine();
	  return year;
  }
  /**
   * Method to take in user input for a movies IMBD url
   * @return
   */
  public String url()
  {
	  System.out.println("Enter the movies IMDB address: ");
	  String url = scan.nextLine();
	  return url;
  }

  /**
   * System.out will output the contents of movieIndex to show all movies
   */
  public void listMovies() 
  {
	  System.out.println(flixAPI.movieIndex.values());
  }
  /**
   * System.out outputs the contents of userIndex to show the users 
   */
  public void listUsers() 
  {
	 // flixAPI.load();
	  System.out.println(flixAPI.userIndex.values());
  }
  /**
   * Takes in a String inputed by the user for lastName
   * @return
   */
  public String lastName()
  {
	  System.out.println("Enter your last name: ");
	  String title = scan.nextLine();
	  return title;
  }
  /**
   * Takes an input for the users age
   * @return
   */
  public String age()
  {
	  System.out.println("Enter your age: ");
	  String age = scan.nextLine();
	  return age;
  }
  /**
   * Takes an input for the users occupation
   * @return
   */
  public String occupation()
  {
	  System.out.println("Enter your occupation: ");
	  String occupation = scan.nextLine();
	  return occupation;
  }
  /**
   * Takes an input for the users gender
   * @return
   */
  public String gender()
  {
	  System.out.println("Enter your gender: ");
	  String gender = scan.nextLine();
	  return gender;
  }
  /**
   * Takes an input for the users first name
   * @return
   */
  public String firstName()
  {
	  System.out.println("Enter your first name: ");
	  scan.nextLine();
	  String title = scan.nextLine();
	  return title;
  }
  
  /**
   * Prints a welcome message and tells user how to enter
   */
  public void printWelcome()
	 {
		 System.out.println("Welcome to Flix \n");
		 System.out.println("Press Enter to continue\n");	
	 }
  
}