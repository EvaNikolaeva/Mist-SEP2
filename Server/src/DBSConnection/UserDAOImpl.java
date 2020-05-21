package DBSConnection;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl extends Database implements UserDAO
{
    private static UserDAOImpl instance;

    public UserDAOImpl() throws SQLException
    {
        super();
    }

    public static UserDAOImpl getInstance() throws SQLException
    {
        if (instance == null)
        {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public User getUserByUserID(int id) throws SQLException
    {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE userid = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                return new User(username, password,id);
            }
            else {
                return null;
            }
        }
    }

    @Override
    public User getUserByCredentials(String username, String password) throws SQLException
    {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE username LIKE ? and password LIKE ?");
            statement.setString(1, "%" + username +"%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("userid");
                return new User(username, password, id);
            }
            else {
                return null;
            }
        }
    }

    @Override
    public void registerUser(String username, String password)
    {
        
    }
}
