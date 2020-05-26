package DBSConnection;

import model.Game;
import model.Rental;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RentedGameDAO
{
    ArrayList<Game> getRentedGames(User user) throws SQLException;
    Game getRentedGameById(int id) throws SQLException;
    void removeRentedGame(int id) throws SQLException;
}
