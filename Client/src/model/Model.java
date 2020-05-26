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

    void gameAddedOnServer(Game game) throws RemoteException, SQLException;

    void gameRemovedOnServer(Game game) throws RemoteException, SQLException;

    void profileUpdate(User user)  throws RemoteException, SQLException;

    public void gameAvailabilityUpdate(Game game) throws RemoteException, SQLException;


}
