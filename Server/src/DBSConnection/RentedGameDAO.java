package DBSConnection;

import model.Game;
import model.Rental;

import java.util.ArrayList;

public interface RentedGameDAO
{

    ArrayList<Game> getRentedGames();
    Game getRentedGameById(int id);
    void removeRentedGame(int id);
}
