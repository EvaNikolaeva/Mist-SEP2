package DBSConnection;

import model.Game;
import model.Rental;
import model.User;

import java.util.ArrayList;

public interface RentalDAO
{
    ArrayList<Rental> getRentals();
    void addRental(User owner, User requester, Game game);
    Rental getRentalById(int id);
    void removeRental(Rental rental);
    //int size();
}
