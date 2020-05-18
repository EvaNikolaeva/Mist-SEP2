package mediator;

import model.Game;
import model.GameList;
import model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteGameListModel extends Remote
{
 User getUserByID(int id) throws RemoteException;
 User getUserByCredentials(String username, String password) throws RemoteException;
 void setBio(int userID, String bio) throws RemoteException;

 void requestGame(int userID, int gameID) throws RemoteException;
 void acceptGame(int userID, int gameID) throws RemoteException;
 void declineGame(int userID, int gameID) throws RemoteException;

 void addGame(int userID, Game game) throws RemoteException;
 void removeGame(int userID, int gameID) throws RemoteException;
 Game getGameByIndex(int index) throws RemoteException;
 Game getGameByID(int gameID) throws RemoteException;
 int getSizeOfGameList() throws RemoteException;

 void registerUser(String username, String password) throws RemoteException;
}
