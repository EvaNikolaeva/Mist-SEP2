package model;

import Utility.UnnamedPropertyChangeSubject;
import mediator.GameListClientClient;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Model
        extends UnnamedPropertyChangeSubject, OtherProfileModel, MyProfileModel,
        LoginModel, GameListModel, EditProfileModel, AddGameModel{
    void registerNewUser(String username, String password) throws RemoteException, SQLException;

    User login(String username, String password) throws RemoteException, SQLException;

    GameList getAllGamesFromServer() throws RemoteException, SQLException;

    User getUser(Game game) throws RemoteException, SQLException;

    void setGameBuffer(Game game);

    Game getGameBuffer();

    String getUsername();

    String getPassword();

    void setBio(User user, String bioText) throws RemoteException, SQLException;

    void validateGame(String name, String type, String releaseYear,
                      String availablePeriod, boolean needsDeposit) throws RemoteException, SQLException;

    RentalList getRentalList() throws RemoteException, SQLException;

    void setGameAvailabilityTrue(Game game) throws RemoteException, SQLException;

    GameList getAllRentedGames(User user) throws RemoteException, SQLException;

    void GameAddedOnServer(Game game) throws RemoteException;
//  void setLocalUser(User user);

//  Game getGameByID(int userID) throws RemoteException;

    //  ArrayList<Integer> getAllAvailableGames() throws RemoteException;
    //  ArrayList<Integer> getAllPendingGames() throws RemoteException;

    //  ArrayList<Integer> getAllUserOwnedGames() throws RemoteException;
    //  ArrayList<Integer> getAllUserPendingGames() throws RemoteException;
    //  ArrayList<Integer> getAllUserRentedGames() throws RemoteException;
    //  ArrayList<Integer> getAllUserIncomingGames() throws RemoteException;

    //  void requestGame(int userID, int gameID) throws RemoteException;
    //  String getUsername(int userID) throws RemoteException;
    //  String getBio(int userID) throws RemoteException;

    //  void removeGame(int userID, int gameID) throws RemoteException;
    //  void setBio(int userID, String bio) throws RemoteException;
    //  public int getLocalUserId();
    //  void acceptIncomingGame(int userID, int gameID) throws RemoteException;
    //  void declineIncomingGame(int userID, int gameID) throws RemoteException;

    //  void addGame(Game game) throws RemoteException;

//  User getOtherUserByID(int userID) throws RemoteException;
//  ArrayList<Integer> getOtherAllUserOwnedGames(int userID)
//      throws RemoteException;
//  ArrayList<Integer> getOtherAllUserPendingGames(int userID)
//      throws RemoteException;


}
