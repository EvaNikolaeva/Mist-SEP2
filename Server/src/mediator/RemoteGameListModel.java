package mediator;

import model.Game;
import model.GameList;
import model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteGameListModel extends Remote
{
 User getUserByID(int id);
 User getUserByCredentials(String username, String password);
 void setBio(int userID, String bio);

 void requestGame(int userID, int gameID);
 void acceptGame(int userID, int gameID);
 void declineGame(int userID, int gameID);

 void addGame(int userID, int gameID);
 void removeGame(int userID, int gameID);
 Game getGameByIndex(int index);
 Game getGameByID(int gameID);
 int getSizeOfGameList();

 void registerUser(String username, String password);
}
