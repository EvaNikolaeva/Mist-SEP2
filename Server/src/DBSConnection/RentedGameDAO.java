package DBSConnection;

import model.Game;
import model.Rental;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RentedGameDAO
{
    ArrayList<Game> getRentedGames() throws SQLException;
    Game getRentedGameById(int id) throws SQLException;
    void removeRentedGame(int id) throws SQLException;
}
