package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;
/**
 * Unimplemented, layout of how it would have been.
 * @author Jack Donoghue
 *
 */
public class Rating 
{  
  public long movieID;
  public long userID;
  public long rating;
  
 
  public Rating()
  {
  }
  /**
   * Rating constructor that defines the values and their types. 
   * @param movieID
   * @param userID
   * @param rating
   */
  public Rating(Long movieID, Long userID, Long rating)
  {
    this.movieID=movieID;
    this.userID=userID;
    this.rating=rating;
  }
  /**
   * toString method displays rating's values.
   */
  @Override
  public String toString()
  {
    return toStringHelper(this).addValue(userID)
                               .addValue(movieID)
                               .addValue(rating)
                               .toString();
  }
  
  @Override  
  public int hashCode()  
  {  
     return Objects.hashCode(this.movieID, this.userID, this.rating);  
  } 
  
  @Override
  public boolean equals(final Object obj)
  {
    if (obj instanceof Rating)
    {
      final Rating other = (Rating) obj;
      return Objects.equal(movieID,  other.movieID)
          && Objects.equal(userID,  other.userID)
          && Objects.equal(rating,     other.rating);    
    }
    else
    {
      return false;
    }
  }
}