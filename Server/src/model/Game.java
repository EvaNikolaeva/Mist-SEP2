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
    private int userID;

    public Game(String title, String type, int releaseYear, boolean needsDeposit,
        DateInterval rentalPeriod, int availabilityPeriod, int userID)
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

    public void reRollID()
    {
        this.id = (int) (Math.random() * 9999) + 1;
    }

    public int getId()
    {
        return id;
    }

    public int getUserID()
    {
        return userID;
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
}
