package model;

import java.io.Serializable;

public class Game implements Serializable
{
  private String title;
  private String type;
  private int releaseYear;
  private boolean needsDeposit;
  private DateInterval rentalPeriod;
  private int availabilityPeriod;
  private int id;
  private int userId;
  private boolean available;


  public Game(String title, String type, int releaseYear, boolean needsDeposit,
              DateInterval rentalPeriod, int availabilityPeriod, int userId)
  {
    this.title = title;
    this.type = type;
    this.releaseYear = releaseYear;
    this.needsDeposit = needsDeposit;
    this.rentalPeriod = rentalPeriod;
    this.availabilityPeriod = availabilityPeriod;
    this.id = (int) (Math.random() * 9999) + 1;
    this.userId = userId;
    this.available = true;
  }

  public Game(String title, String type, int releaseYear, boolean needsDeposit,
              DateInterval rentalPeriod, int availabilityPeriod, int userId, int gameID)
  {
    this.title = title;
    this.type = type;
    this.releaseYear = releaseYear;
    this.needsDeposit = needsDeposit;
    this.rentalPeriod = rentalPeriod;
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

  public int getUserId(){return userId;}

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

  public DateInterval getRentalPeriod()
  {
    return rentalPeriod;
  }

  public int getAvailabilityPeriod()
  {
    return availabilityPeriod;
  }

  public String toString()
  {
    return "Title: " + title + ", id: " + id + ", type: " + type
            + ", release year: " + releaseYear + ", deposit: " + needsDeposit
            + ", rental period: " + rentalPeriod + ", availability period: "
            + availabilityPeriod;
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Game))
      return false;
    Game other = (Game) obj;
    return title.equals(other.title) && type.equals(other.type)
            && id == other.id && releaseYear == other.releaseYear
            && needsDeposit == other.needsDeposit && rentalPeriod
            .equals(other.rentalPeriod)
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
