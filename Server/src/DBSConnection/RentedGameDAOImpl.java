package DBSConnection;

import model.Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RentedGameDAOImpl extends Database implements RentedGameDAO
{
  private static RentedGameDAOImpl instance;

  public RentedGameDAOImpl() throws SQLException
  {
    super();
  }

  public Connection getConnection() throws SQLException
  {
    return super.getConnection();
  }

  public static RentedGameDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new RentedGameDAOImpl();
    }
    return instance;
  }

  @Override public ArrayList<Game> getRentedGames() throws SQLException
  {
    ArrayList<Game> rentedGames = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement1 = connection.prepareStatement(
          "SELECT * FROM Game where game_id=(SELECT game_ID FROM Rented_game)");
      statement1.executeQuery();
      ResultSet resultSet1 = statement1.executeQuery();
      while (resultSet1.next())
      {
        Game game = new Game(resultSet1.getString("title"),
            resultSet1.getString("type"), resultSet1.getInt("release_year"),
            resultSet1.getBoolean("needs_deposit"),
            resultSet1.getInt("availability_period"),
            resultSet1.getInt("user_id"));
        rentedGames.add(game);
      }
      return rentedGames;
    }
  }

  @Override public Game getRentedGameById(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Rented_game WHERE game_id =?");
      statement.setInt(1, id);
      statement.executeQuery();
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        return new Game(resultSet.getString("title"),
            resultSet.getString("type"), resultSet.getInt("release_year"),
            resultSet.getBoolean("needs_deposit"),
            resultSet.getInt("availability_period"),
            resultSet.getInt("user_id"));
      }
      else
      {
        return null;
      }
    }

  }

  @Override public void removeRentedGame(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM GAME WHERE game_id=?");
      statement.setInt(1, id);
      statement.executeQuery();
    }
  }
}
