package DBSConnection;

import model.Game;
import model.Rental;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RentalDAO
{
    ArrayList<Rental> getRentals() throws SQLException;
    void addRental(User owner, User requester, Game game) throws SQLException;
    Rental getRentalById(int id) throws SQLException;
    void acceptRental(Rental rental) throws SQLException;
    void declineRental(Rental rental) throws SQLException;
    int size() throws SQLException;
}
