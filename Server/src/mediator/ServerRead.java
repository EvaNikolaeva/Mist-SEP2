package mediator;

import model.Game;
import model.GameList;
import model.RentalList;
import model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerRead extends Remote
{
  GameList getAllGames() throws RemoteException;
  User getUserByGame(Game game) throws RemoteException;
  User getUserByCredentials(String username,String password) throws RemoteException;
  RentalList getRentalList() throws RemoteException;


//  User getUserByID(int id) throws RemoteException;
//  User getUserByCredentials(String username, String password) throws RemoteException;
//  Game getGameByIndex(int index) throws RemoteException;
//  Game getGameByID(int gameID) throws RemoteException;
//  int getSizeOfGameList() throws RemoteException;

}
