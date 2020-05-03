package model;

import Utility.UnnamedPropertyChangeSubject;

import java.rmi.RemoteException;
import java.time.LocalDate;

public interface Model extends UnnamedPropertyChangeSubject
{
  void AddGame(Game game) throws RemoteException;
  void RemoveGame(int id) throws RemoteException;
  GameList GetGameList() throws RemoteException;
  public GameList getUserGamesList() throws RemoteException;
  public void updateUserGames() throws RemoteException;
  public int getUserId();
  void validateGame(String name, String type, String releaseYear,
      LocalDate rentalFrom, LocalDate rentalTo, String availablePeriod,
      boolean needsDeposit) throws RemoteException;

}
