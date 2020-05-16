package model;

public interface LoginModel extends Model
{
  void registerNewUser(String username, String password);
  User login(String username, String password);
}
