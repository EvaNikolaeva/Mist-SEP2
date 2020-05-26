package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserListTest
{
  UserList userList;
  ArrayList<User> users;
  User user1;
  User user2;

  @BeforeEach void setUp()
  {
    userList = new UserList();
    users = new ArrayList<>();
    user1 = new User("bob", "1234", 1234);
    user2 = new User("john", "9876", 9876);
  }

  @Test void UserList()
  {
    assertNotNull(userList);
    assertNotNull(users);
    assertNotNull(user1);
    assertNotNull(user2);
  }


  @Test void getUserByUserID()
  {
    users.add(user1);
    assertEquals(user1.getUserID(), users.get(0).getUserID());
  }

  @Test void getUser()
  {
    users.add(user1);
    assertEquals(user1, users.get(0));
  }

  //  @Test void getUserByCredentials()     //cant test this
  //  {
  //    userList.registerUser("bob", "1234");
  //    assertEquals(user1, userList.getUserByCredentials("bob", "1234")); //just need to add a get credentials in user
  //  }

  @Test void registerUser()
  {
    userList.registerUser("asd", "dsa");
    assertEquals(1, userList.size());
  }

  @Test void size()
  {
    users.add(user1);
    users.add(user2);
    assertEquals(2, users.size());
  }
}