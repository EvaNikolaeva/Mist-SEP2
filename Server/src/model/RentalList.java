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
  }

  public void removeRental(Rental rental)
  {
    for(int i = 0; i < rentals.size(); i++){
      if(rentals.get(i).getId() == rental.getId()){
        rentals.remove(i);
      }
    }
  }

  public int size()
  {
    return rentals.size();
  }
}
