package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;
/**
 * Movie class defines the requirements for a movie object.
 * @author Jack Donoghue
 *
 */
public class Movie
{
	  public static Long   counter = 0l;

	  public Long   id;
  public String title;
  public String year;
  public String url;
  
  public Movie()
  {
  }
  /**
   * Movie constructor defines the values and types for movies.
   * @param title
   * @param year
   * @param url
   */
  public Movie ( String title, String year, String url)
  {
	  this.id        = counter++;
    this.title  = title;
    this.year = year;
    this.url = url;
  }
  /**
   * Displays movies values in order.
   */
  @Override
  public String toString()
  {
    return toStringHelper(this).addValue(id)
                               .addValue(title)
                               .addValue(year)
                               .addValue(url)
                               .toString();
  }
  
  @Override  
  public int hashCode()  
  {  
     return Objects.hashCode(this.title, this.year, this.url);  
  } 
  
  @Override
  public boolean equals(final Object obj)
  {
    if (obj instanceof Movie)
    {
      final Movie other = (Movie) obj;
      return 
    	  Objects.equal(title, other.title) 
    	  &&  Objects.equal(year, other.year) 
          && Objects.equal(url, other.url);
    }
    else
    {
      return false;
    }
  }
}