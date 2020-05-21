package DBSConnection;

import model.Game;

import java.util.ArrayList;

public interface GameDAO
{
    void addGame(String title, String type, int releaseYear, boolean needsDeposit,
                 int availabilityPeriod, int userId, int gameID);
    void removeGame(int id);
    void removeGame(Game game);
    Game getGameById(int id);
//    Game getGame(int index);
//    int size();
//    boolean equals(Object obj);
    ArrayList<Game> getAvailableGames();
    ArrayList<Game> getUnavailableGames();
}
