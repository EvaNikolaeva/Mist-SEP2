package mediator;


import model.*;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;
//RemoteSubject<GameList, RentalList>
public interface GameListServerModel extends Remote, RemoteSubject<GameList, RentalList>{

    GameList getAllGames() throws RemoteException;

    User getUserByGame(Game game) throws RemoteException;

    User getUserByCredentials(String username, String password) throws RemoteException;

    RentalList getRentalList() throws RemoteException;

    void registerClient(String username, String password) throws RemoteException;

    void removeGame(Game game) throws RemoteException;

    void setBio(User user, String bio) throws RemoteException;

    void addGame(Game game) throws RemoteException;

    void requestGame(User requester, Game game) throws RemoteException;

    void acceptIncomingGame(Rental rental) throws RemoteException;

    void declineIncomingGame(Rental rental) throws RemoteException;

}
