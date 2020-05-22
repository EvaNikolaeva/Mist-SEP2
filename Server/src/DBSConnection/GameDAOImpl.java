package DBSConnection;

import model.Game;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameDAOImpl extends Database implements GameDAO
{
  private static GameDAOImpl instance;

  public GameDAOImpl() throws SQLException
  {
    super();
  }

  public Connection getConnection() throws SQLException
  {
    return super.getConnection();
  }

  public static GameDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new GameDAOImpl();
    }
    return instance;
  }

  @Override public void addGame(String title, String type, int releaseYear,
      boolean needsDeposit, int availabilityPeriod, int userId)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO Game(title, type,release_year,needs_deposit, availability_period, available, user_id) VALUES("
              + "?,?,?,?,?,?,?" + "); ");
      statement.setString(1, title);
      statement.setString(2, type);
      statement.setInt(3, releaseYear);
      statement.setBoolean(4, needsDeposit);
      statement.setInt(5, availabilityPeriod);
      statement.setBoolean(6, true);
      statement.setInt(7, userId);
      statement.executeQuery();

    }
  }

  @Override public void removeGame(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM GAME WHERE game_id=?");
      statement.setInt(1, id);
      statement.executeQuery();
    }
  }

  @Override public void removeGame(Game game) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM GAME WHERE game_id=?");
      statement.setInt(1, game.getId());
      statement.executeQuery();
    }
  }

  @Override public Game getGameById(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Game WHERE game_id =?");
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

  @Override public int size() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT COUNT(game_id) as size FROM Game");
      statement.executeQuery();
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        return resultSet.getInt("size");
      }
      else
      {
        return 0;
      }
    }

  }

  @Override public ArrayList<Game> getAvailableGames() throws SQLException
  {
    ArrayList<Game> availableGames = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Game WHERE available=True");
      statement.executeQuery();
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        Game game = new Game(resultSet.getString("title"),
            resultSet.getString("type"), resultSet.getInt("release_year"),
            resultSet.getBoolean("needs_deposit"),
            resultSet.getInt("availability_period"),
            resultSet.getInt("user_id"));
        availableGames.add(game);
      }
      return availableGames;
    }
  }

  @Override public ArrayList<Game> getUnavailableGames() throws SQLException
  {
    ArrayList<Game> UnavailableGames = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM Game WHERE available=False");
      statement.executeQuery();
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        Game game = new Game(resultSet.getString("title"),
            resultSet.getString("type"), resultSet.getInt("release_year"),
            resultSet.getBoolean("needs_deposit"),
            resultSet.getInt("availability_period"),
            resultSet.getInt("user_id"));
        UnavailableGames.add(game);
      }
      return UnavailableGames;
    }
  }
}
