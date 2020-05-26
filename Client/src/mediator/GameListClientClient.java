package mediator;

import model.*;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;


public interface GameListClientClient
{
    void connect() throws InterruptedException;

    void registerNewUser(String username, String password)
            throws RemoteException, SQLException;

    User login(String username, String password) throws
            RemoteException, SQLException;  //instead of the first 4 from below and last 2 from below

    GameList getGamesFromServer() throws RemoteException, SQLException;

    RentalList clientGetRentalList() throws RemoteException, SQLException;

    void clientRemoveGame(Game game) throws RemoteException, SQLException;

    void clientSetBio(User user, String bio) throws RemoteException, SQLException;

    void clientAddGame(Game game) throws RemoteException, SQLException;

    void clientRequestGame(User requester, Game game) throws RemoteException, SQLException;

    void clientAcceptIncomingGame(Rental rental) throws RemoteException, SQLException;

    void clientDeclineIncomingGame(Rental rental) throws RemoteException, SQLException;

    User getUserFromServer(Game game) throws RemoteException, SQLException; // instead of the 4 below

    void setGameAvailableTrue(Game game) throws RemoteException, SQLException;

    GameList getRentedGames(User user) throws RemoteException, SQLException;
    //return type is only for me


//  ArrayList<Integer> getAllAvailableGames() throws RemoteException;
//  ArrayList<Integer> getAllPendingGames() throws RemoteException;

//  ArrayList<Integer> getAllUserOwnedGames(int userID) throws RemoteException;
//  ArrayList<Integer> getAllUserPendingGames(int userID) throws RemoteException;
//  ArrayList<Integer> getAllUserRentedGames(int userID) throws RemoteException;
//  ArrayList<Integer> getAllUserIncomingGames(int userID) throws RemoteException;


//  ArrayList<Integer> getOtherAllUserOwnedGames(int userID)
//      throws RemoteException;
//  ArrayList<Integer> getOtherAllUserPendingGames()
//      throws RemoteException;
//  String getUsername(int gameID) throws RemoteException; //Same
//  String getBio(int gameID) throws RemoteException; //I send the game to retrieve the user
}
