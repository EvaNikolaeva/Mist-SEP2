package model;

public interface LoginModel
{
  void registerNewUser(String username, String password);
  User login(String username, String password);
}
