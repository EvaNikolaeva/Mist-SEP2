package model;

import Utility.UnnamedPropertyChangeSubject;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;

public interface Model extends UnnamedPropertyChangeSubject {
    void AddGame(Game game) throws RemoteException;

    void RemoveGame(int id) throws RemoteException;

    GameList GetGameList() throws RemoteException;

    GameList getUserGamesList() throws RemoteException;

    void updateUserGames() throws RemoteException;

    int getUserId();

    void validateGame(String name, String type, String releaseYear,
                      LocalDate rentalFrom, LocalDate rentalTo, String availablePeriod,
                      boolean needsDeposit) throws RemoteException;

    void connectToServer() throws RemoteException, MalformedURLException, InterruptedException, NotBoundException;

    User getUser(String username) throws RemoteException;

    void setUserCurrent(String username) throws RemoteException;

    void acceptTrade(Game game, int userID) throws RemoteException;
}
