package model;

import java.io.Serializable;

public class Game implements Serializable
{
  private String title;
  private String type;
  private int releaseYear;
  private boolean needsDeposit;
  private DateInterval rentalPeriod;
  private DateInterval availabilityPeriod;
  private int id;
  private int userID;

  public Game(String title, String type, int releaseYear, boolean needsDeposit,
      DateInterval rentalPeriod, DateInterval availabilityPeriod, int userID)
  {
    this.title = title;
    this.type = type;
    this.releaseYear = releaseYear;
    this.needsDeposit = needsDeposit;
    this.rentalPeriod = rentalPeriod;
    this.availabilityPeriod = availabilityPeriod;
    this.id = (int) (Math.random() * 9999) + 1;
    this.userID = userID;
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

  public boolean needsDeposit()
  {
    return needsDeposit;
  }

  public DateInterval getRentalPeriod()
  {
    return rentalPeriod;
  }

  public DateInterval getAvailabilityPeriod()
  {
    return availabilityPeriod;
  }

  public int getId()
  {
    return id;
  }

  public int getUserID()
  {
    return userID;
  }

  public String toString()
  {
    return "Title: " + title + ", id: " + id + ", type: " + type
        + ", release year: " + releaseYear + ", deposit: " + needsDeposit
        + ", rental period: " + rentalPeriod + ", availability period: "
        + availabilityPeriod;
  }
}
