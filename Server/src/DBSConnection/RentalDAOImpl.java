package DBSConnection;

import model.Game;
import model.Rental;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RentalDAOImpl extends Database implements RentalDAO
{
    private static RentalDAOImpl instance;

    public RentalDAOImpl() throws SQLException
    {
        super();
    }

    public Connection getConnection() throws SQLException
    {
        return super.getConnection();
    }

    public static synchronized RentalDAOImpl getInstance() throws SQLException
    {
        if (instance == null)
        {
            instance = new RentalDAOImpl();
        }
        return instance;
    }

    @Override
    public ArrayList<Rental> getRentals() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Rental");
            //statement.setString(1, "%" + searchString + "%");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Rental> result = new ArrayList<>();
            while (resultSet.next())
            {
                User owner = (User) resultSet.getObject("gameownerid");
                User requester = (User) resultSet.getObject("gamerequesterid");
                Game game = (Game) resultSet.getObject("rentedgameid");
                Rental rental = new Rental(owner, requester, game);
                result.add(rental);
            }
            return result;
        }
    }

    @Override
    public void addRental(User owner, User requester, Game game) throws SQLException
    {
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Rental (GameOwnerID, GameRequesterID, RentedGameID) VALUES (?,?,?) ", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, owner.getUserID());
            statement.setInt(2, requester.getUserID());
            statement.setInt(3, game.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public Rental getRentalById(int id) throws SQLException
    {
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Rental WHERE rentalid = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User owner = (User) resultSet.getObject("gameownerid");
                User requester = (User) resultSet.getObject("gamerequesterid");
                Game game = (Game) resultSet.getObject("rentedgameid");
                return new Rental(owner, requester, game);
            }
            else {
                return null;
            }
        }
    }

    @Override
    public void acceptRental(Rental rental) throws SQLException
    {
        try (Connection connection = getConnection())
        {
            int ownerId = rental.getOwner().getUserID();
            int gameId = rental.getGame().getId();
            int userId = rental.getRequester().getUserID();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Rental WHERE rentalid = ?");
            statement.setInt(1, rental.getId());
            statement.executeUpdate();
            PreparedStatement statement1 = connection.prepareStatement("INSERT INTO Rented_game VALUES (?,?,?)");
            statement1.setInt(1, gameId);
            statement1.setInt(2,ownerId);
            statement1.setInt(3,userId);
            statement1.executeUpdate();
        }
    }

    //might have to use joins
    @Override
    public void declineRental(Rental rental) throws SQLException
    {
        try (Connection connection = getConnection())
        {
            int id = rental.getId();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Rental WHERE rentalid = ?");
            statement.setInt(1, rental.getId());
            statement.executeUpdate();
            PreparedStatement statement1 = connection.prepareStatement("UPDATE Game SET available = true WHERE gameid = ?");
            statement1.setInt(1, id);
        }
    }

    @Override
    public int size() throws SQLException
    {
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT COUNT(rentalid) as size FROM Rental");
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
}
