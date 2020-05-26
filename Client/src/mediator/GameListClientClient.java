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
            RemoteException, SQLException;

    GameList getGamesFromServer() throws RemoteException, SQLException;

    RentalList clientGetRentalList() throws RemoteException, SQLException;

    void clientRemoveGame(Game game) throws RemoteException, SQLException;

    void clientSetBio(User user, String bio) throws RemoteException, SQLException;

    void clientAddGame(Game game) throws RemoteException, SQLException;

    void clientRequestGame(User requester, Game game) throws RemoteException, SQLException;

    void clientAcceptIncomingGame(Rental rental) throws RemoteException, SQLException;

    void clientDeclineIncomingGame(Rental rental) throws RemoteException, SQLException;

    User getUserFromServer(Game game) throws RemoteException, SQLException;

    void setGameAvailableTrue(Game game) throws RemoteException, SQLException;

    GameList getRentedGames(User user) throws RemoteException, SQLException;

    void clientRemoveUser(User user) throws RemoteException, SQLException;
}
