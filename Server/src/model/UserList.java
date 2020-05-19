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
    return users.stream()
        .filter(user -> user.getUserID() == id)
            .findAny()
            .orElse(null);
     //the "better way"
  }

  public User getUser(User user)
  {
    return users.stream().filter(user1 -> user1.equals(user)).findAny().orElse(null);
  }

  public User getUserByCredentials(String username, String password)
  {
    return users.stream().filter(
        user -> username.equals(user.getUsername()) && password
            .equals(user.getPassword())).findFirst().orElse(null);
  }

  public void registerUser(String username, String password)
  {
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

  public User getUserByIndex(int index)
  {
    return users.get(index);
  }


  public int size()
  {
    return users.size();
  }
}
