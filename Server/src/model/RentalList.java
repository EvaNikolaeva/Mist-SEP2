package model;

import java.io.Serializable;
import java.util.ArrayList;

/**Class representing a list of Rental objects.
 * @author Group 1
 */
public class RentalList implements Serializable
{
  ArrayList<Rental> rentals;

  /**Constructor initialising the ArrayList of Rentals.
   */
  public RentalList()
  {
    this.rentals = new ArrayList<>();
  }

  /**
   * Returning the list of Rentals.
   * @return ArrayList of the Rental objects present int he list.
   */
  public ArrayList<Rental> getRentals()
  {
    return rentals;
  }

  /**
   * Adding a Rental to the list.
   * @param rental Rental object to be added to the list.
   */
  public void addRental(Rental rental)
  {
    if(rental != null)
      rentals.add(rental);
  }

  /**
   * Getting a Rental object by its ID.
   * @param id The ID of the object that should be returned from the list.
   * @return Rental object with the ID given.
   */
  public Rental getRentalById(int id)
  {
    return rentals.get(id);
  }

  /**
   * Removing an object from the list that is the same as given.
   * @param rental The object to be removed from the list.
   */
  public void removeRental(Rental rental)
  {
    for(int i = 0; i < rentals.size(); i++){
      if(rentals.get(i).getId() == rental.getId()){
        rentals.remove(i);
      }
    }
  }

  /**
   * Returning the size of the ArrayList.
   * @return An integer that represents the amount of Rentals present in the list.
   */
  public int size()
  {
    return rentals.size();
  }
}
