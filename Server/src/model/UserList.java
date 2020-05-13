package model;

import java.util.ArrayList;

public class UserList {
    ArrayList<User> users;

    public UserList(){
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
}
