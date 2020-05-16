package model;

import java.util.ArrayList;

public class UserList
{
  ArrayList<User> users;

  public UserList()
  {
    users = new ArrayList<>();
  }

  public User getUserByUserID(int id)
  {
    return (User) users.stream()
        .filter(user -> id == user.getUserID()); //the "better way"
  }

  public User getUserByCredentials(String username, String password)
  {
    return (User) users.stream().filter(
        user -> username.equals(user.getUsername()) && password
            .equals(user.getPassword()));
  }

  public void registerUser(String username, String password)
  {
    //maybe streams as well, but dunno how

    int userID = (int) ((Math.random() * 9999) + 1);
    User user = new User(username, password, userID);

    for (int i = 0; i < users.size(); i++)
    {
      if (!(userID == users.get(i).getUserID()))
      {
        users.add(user);
      }
      else
      {
        registerUser(username, password);
      }
    }
  }

  public void updateBio(int id, String bio)
  {
    User dummy = (User) users.stream().filter(user -> id == user.getUserID());
    dummy.setBio(bio);
  }

  public void addToOwned(int userID, int gameID)
  {
    User dummy = (User) users.stream()
        .filter(user -> userID == user.getUserID());
    dummy.getOwnedGames().add(gameID);
  }

  public void removeFromOwned(int userID, int gameID)
  {
    User dummy = (User) users.stream()
        .filter(user -> userID == user.getUserID());
    dummy.getOwnedGames().remove(gameID);
  }

  public void addToPending(int userID, int gameID)
  {
    User dummy = (User) users.stream()
        .filter(user -> userID == user.getUserID());
    dummy.getPendingGames().add(gameID);
  }

  public void removeFromPending(int userID, int gameID)
  {
    User dummy = (User) users.stream()
        .filter(user -> userID == user.getUserID());
    dummy.getPendingGames().remove(gameID);
  }

  public void addToRented(int userID, int gameID)
  {
    User dummy = (User) users.stream()
        .filter(user -> userID == user.getUserID());
    dummy.getRentedGames().add(gameID);
  }

  public void removeFromRented(int userID, int gameID)
  {
    User dummy = (User) users.stream()
        .filter(user -> userID == user.getUserID());
    dummy.getRentedGames().remove(gameID);
  }

  public void addToIncoming(int userID, int gameID)
  {
    User dummy = (User) users.stream()
        .filter(user -> userID == user.getUserID());
    dummy.getIncomingGames().add(gameID);
  }

  public void removeFromIncoming(int userID, int gameID)
  {
    User dummy = (User) users.stream()
        .filter(user -> userID == user.getUserID());
    dummy.getIncomingGames().remove(gameID);
  }

  public int getUserWhoHasGamePending(int gameID)
  {
    User dummy = (User) users.stream()
        .filter(user -> user.getPendingGames().contains(gameID));
    return dummy.getUserID();
  }
}
