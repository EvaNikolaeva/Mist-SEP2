package DBSConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Database
{
    public Database() throws SQLException
    {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:postgresql://35.204.219.115:5432/sep2", "JavaApp", "password123");
    }
}
