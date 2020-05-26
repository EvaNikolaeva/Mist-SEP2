package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A list containing the Users present in the system.
 *
 * @author Group 1
 */
public class UserList
{
  ArrayList<User> users;

  /**
   * Constructor initialising the ArrayList.
   */
  public UserList()
  {
    users = new ArrayList<>();
  }

  /**
   * Getting a User from the list who has the given ID.
   *
   * @param id UserID representing a User.
   * @return A User object that has the same ID as given.
   */
  public User getUserByUserID(int id)
  {
    //    return users.stream().filter(user -> user.getUserID() == id).findAny().orElse(null);
    for (int i = 0; i < users.size(); i++)
    {
      if (users.get(i).getUserID() == id)
      {
        return users.get(i);
      }
    }
    return null;
  }

  /**
   * Getting a User from the list.
   *
   * @param user The User to be returned.
   * @return User that is the same as the one given.
   */
  public User getUser(User user)
  {
    return users.stream().filter(user1 -> user1.equals(user)).findAny()
        .orElse(null);
  }

  /**
   * Getting a User by its credentials.
   *
   * @param username Username of the User to be gotten.
   * @param password Password of the User to be gotten.
   * @return User that has the given credentials.
   */
  public User getUserByCredentials(String username, String password)
  {
    //    return users.stream().filter(user -> username.equals(user.getUsername()) && password.equals(user.getPassword())).findAny().orElse(null);
    for (int i = 0; i < users.size(); i++)
    {
      if (users.get(i).getUsername().equals(username) && users.get(i)
          .getPassword().equals(password))
      {
        return users.get(i);
      }
    }
    return null;
  }

  /**
   * Registering a new User to the system.
   *
   * @param username Username of the User.
   * @param password Password of the User.
   */
  public void registerUser(String username, String password)
  {
    //    int userID = (int) ((Math.random() * 9999) + 1);
    //        User user = new User(username, password, userID);
    //
    //        for (int i = 0; i < users.size(); i++)
    //        {
    //            if (!(userID == users.get(i).getUserID()) && !(users.get(i).getUsername().equals(username)))
    //            {
    //                users.add(user);
    //                System.out.println("added user" + user);
    //            }
    //            else
    //            {
    //                registerUser(username, password);
    //            }
    //        }
    int userID = (int) ((Math.random() * 9999) + 1);
    User user = new User(username, password, userID);
    users.add(user);
  }

  /**
   * Returning a User by the index they have in the list of Users.
   *
   * @param index
   * @return
   */
  public User getUserByIndex(int index)
  {
    return users.get(index);
  }

  /**
   * Returning the number of users in the list.
   *
   * @return An integer representing the number users in the list stored.
   */
  public int size()
  {
    return users.size();
  }
}
