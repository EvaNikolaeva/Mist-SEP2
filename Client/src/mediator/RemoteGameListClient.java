package mediator;

import model.Game;
import model.Rental;
import model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface RemoteGameListClient extends Remote
{
  void serverRemoveGame(Game game) throws RemoteException;
  void serverAddGame(Game game) throws RemoteException;

  void serverRequestGame(Rental rental) throws RemoteException;
  void serverAcceptIncomingGame(Rental rental) throws RemoteException;
  void serverDeclineIncomingGame(Rental rental) throws RemoteException;


//  User getUserByID(int id) throws RemoteException;
//  User getUserByCredentials(String username, String password)
//      throws RemoteException;
//  void setBio(String bio) throws RemoteException;
//
//  void requestGame(int gameID) throws RemoteException;
//  void acceptGame(int gameID) throws RemoteException;
//  void declineGame(int gameID) throws RemoteException;
//
//  void addGame(int gameID) throws RemoteException;
//  void removeGame(int gameID) throws RemoteException;
//
//  Game getGameByIndex(int index) throws RemoteException;
//  Game getGameByID(int gameID) throws RemoteException;
//  int getSizeOfGameList() throws RemoteException;
//  void registerUser(String username, String password) throws RemoteException;
}
