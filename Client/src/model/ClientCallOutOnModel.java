package model;

import mediator.GameListClientClient;

import java.rmi.RemoteException;

public interface ClientCallOutOnModel
{
  void setClient(GameListClientClient gameListClientModel);

  void requestGameFromServer(Rental rental) throws RemoteException;
  void acceptIncomingGameFromServer(Rental rental) throws RemoteException;
  void declineIncomingGameFromServer(Rental rental) throws RemoteException;

  void addGameFromServer(Game game) throws RemoteException;
  void removeGameFromServer(Game game) throws RemoteException;
}
