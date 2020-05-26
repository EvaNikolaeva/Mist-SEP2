package model;

import java.io.Serializable;
import java.util.ArrayList;

public class RentalList implements Serializable
{
  ArrayList<Rental> rentals;

  public RentalList()
  {
    this.rentals = new ArrayList<>();
  }

  public ArrayList<Rental> getRentals()
  {
    return rentals;
  }

  public void addRental(Rental rental)
  {
    if(rental != null)
    rentals.add(rental);
  }

  public Rental getRentalById(int id)
  {
    return rentals.get(id);
  } //cant use

  public void removeRental(Rental rental)
  {
    rentals.remove(rental);
  }

  public int size()
  {
    return rentals.size();
  }
}
