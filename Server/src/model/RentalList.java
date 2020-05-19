package model;

import java.util.ArrayList;

public class RentalList
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
    rentals.add(rental);
  }

  public Rental getRentalById(int id)
  {
    return rentals.get(id);
  }

  public void removeRental(Rental rental)
  {
    rentals.remove(rental);
  }

  public int size()
  {
    return rentals.size();
  }
}
