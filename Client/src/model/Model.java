package model;

import Utility.UnnamedPropertyChangeSubject;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;

public interface Model extends UnnamedPropertyChangeSubject
{
  void AddGame(Game game) throws RemoteException;

  void RemoveGame(int id) throws RemoteException;

  GameList GetGameList() throws RemoteException;

  GameList getUserGamesList() throws RemoteException;

  void updateUserGames() throws RemoteException;

  int getUserId();

  void validateGame(String name, String type, String releaseYear,
      LocalDate rentalFrom, LocalDate rentalTo, String availablePeriod,
      boolean needsDeposit) throws RemoteException;

  void updateUser()
      throws RemoteException, MalformedURLException, InterruptedException,
      NotBoundException;

  User getOtherUser(String username) throws RemoteException;

  User getLocalUser() throws RemoteException;

  void setUserBio(User user, String bioText) throws RemoteException;

  void setLocalUser(String username) throws RemoteException;

  void acceptTrade(Game game, int userID) throws RemoteException;

  void declineTrade(Game game, int userID) throws RemoteException;

  void requestTrade(Game game, int targetID) throws RemoteException;

  public void setSelectedOtherUserIdBuffer(int id);

  int getSelectedOtherUserIdBuffer();

  User getUserDataById(int id) throws RemoteException;

}
