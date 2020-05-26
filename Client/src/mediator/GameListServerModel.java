package mediator;


import model.*;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

//RemoteSubject<GameList, RentalList>
public interface GameListServerModel extends RemoteSubject<Game, User>{

  GameList getAllGames() throws RemoteException, SQLException;

  User getUserByGame(Game game) throws RemoteException, SQLException;

  User getUserByCredentials(String username, String password) throws RemoteException, SQLException;

  RentalList getRentalList() throws RemoteException, SQLException;

  void registerClient(String username, String password) throws RemoteException, SQLException;

  void removeGame(Game game) throws RemoteException, SQLException;

  void setBio(User user, String bio) throws RemoteException, SQLException;

  void addGame(Game game) throws RemoteException, SQLException;

  void requestGame(User requester, Game game) throws RemoteException, SQLException;

  void acceptIncomingGame(Rental rental) throws RemoteException, SQLException;

  void declineIncomingGame(Rental rental) throws RemoteException, SQLException;

  void setGameAvailableTrue(Game game) throws RemoteException, SQLException;

  GameList getRentedGames(User user) throws RemoteException, SQLException;

  void removeUser(User user) throws RemoteException, SQLException;

  public User getUserById(int userId) throws RemoteException, SQLException;
}
