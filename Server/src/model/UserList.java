package model;

import java.util.ArrayList;

public class UserList
{
  ArrayList<User> users;

  public UserList()
  {
    users = new ArrayList<>();
  }

  public User getUser(String username)
  {
    for (int i = 0; i < users.size(); i++)
    {
      if (users.get(i).getUsername().equals(username))
      {
        return users.get(i);
      }
    }
    return null;
  }

  public User getUserByID(int id)
  {
    for (int i = 0; i < users.size(); i++)
    public void addUser(User user){
        users.add(user);
    }
    public User getUser(String username)
    {
      if (users.get(i).getUserID() == id)
      {
        return users.get(i);
      }
    }
    return null;
  }

  public int size()
  {
    return users.size();
  }
}
