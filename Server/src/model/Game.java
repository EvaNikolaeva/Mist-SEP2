package model;

import java.io.Serializable;

public class Game implements Serializable
{
  private String title;
  private String type;
  private int releaseYear;
  private boolean needsDeposit;
  private int availabilityPeriod; //after the point of acquire
  private int id;
  private int userId;
  private boolean available;

  public Game(String title, String type, int releaseYear, boolean needsDeposit,
              int availabilityPeriod, int userId)
  {
    this.title = title;
    this.type = type;
    this.releaseYear = releaseYear;
    this.needsDeposit = needsDeposit;
    this.availabilityPeriod = availabilityPeriod;
    this.id = (int) (Math.random() * 9999) + 1;
    this.userId = userId;
    this.available = true;
  }

  public Game(String title, String type, int releaseYear, boolean needsDeposit,
              int availabilityPeriod, int userId, int gameID)
  {
    this.title = title;
    this.type = type;
    this.releaseYear = releaseYear;
    this.needsDeposit = needsDeposit;
    this.availabilityPeriod = availabilityPeriod;
    this.id = gameID;
    this.userId = userId;
    this.available = true;
  }

  public void reRollID()
  {
    this.id = (int) (Math.random() * 9999) + 1;
  }

  public int getId()
  {
    return id;
  }

  public int getUserId()
  {
    return userId;
  }

  public String getTitle()
  {
    return title;
  }

  public String getType()
  {
    return type;
  }

  public int getReleaseYear()
  {
    return releaseYear;
  }

  public boolean deposit()
  {
    return needsDeposit;
  }

  public int getAvailabilityPeriod()
  {
    return availabilityPeriod;
  }

  public String toString()
  {
    if(deposit()){
      if(available){
        return  title + ", " + type
                + ", " + releaseYear + ", Deposit required" + ", Game Available for " + availabilityPeriod + " days, Available";
      }
      else {
        return  title + ", " + type
                + ", " + releaseYear + ", Deposit required" + ", Game Available for " + availabilityPeriod + " days, Unavailable";
      }
    }
    else{
      if(available){
        return  title + ", " + type
                + ", " + releaseYear + ", Game Available for " + availabilityPeriod + " days, Available";
      }
      else{
        return  title + ", " + type
                + ", " + releaseYear + ", Game Available for " + availabilityPeriod + " days, Unavailable";
      }
    }

  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Game))
      return false;
    Game other = (Game) obj;
    return title.equals(other.title) && type.equals(other.type)
            && id == other.id && releaseYear == other.releaseYear
            && needsDeposit == other.needsDeposit
            && availabilityPeriod == other.availabilityPeriod;
  }

  public void setAvailable(boolean available)
  {
    this.available = available;
  }

  public boolean getAvailable()
  {
    return available;
  }
}
