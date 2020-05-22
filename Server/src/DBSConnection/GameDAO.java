package DBSConnection;

import model.Game;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GameDAO
{
    void addGame(String title, String type, int releaseYear, boolean needsDeposit,
                 int availabilityPeriod, int userId) throws SQLException;
    void removeGame(int id) throws SQLException;
    void removeGame(Game game) throws SQLException;
    Game getGameById(int id) throws SQLException;
//    Game getGame(int index);
   int size() throws SQLException;
//    boolean equals(Object obj);
    ArrayList<Game> getAvailableGames() throws SQLException;
    ArrayList<Game> getUnavailableGames() throws SQLException;
    void setAvailable(Game game) throws SQLException;
}
