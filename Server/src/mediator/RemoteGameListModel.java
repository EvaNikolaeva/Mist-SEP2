package mediator;

import model.Game;
import model.GameList;
import model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteGameListModel extends Remote
{
 GameList getGameList() throws RemoteException;
 void addGame(Game game) throws RemoteException;
 void removeGame(int id) throws RemoteException;
 User getUserData(String username) throws RemoteException;
 User getUserDataById(int id) throws RemoteException;
 void acceptTrade(Game game,int userID) throws RemoteException;
 void declineTrade(Game game,int userID) throws RemoteException;
 void requestTrade(Game game, int userID) throws RemoteException;
 void setUserBio(User user, String bioText) throws RemoteException;
}
