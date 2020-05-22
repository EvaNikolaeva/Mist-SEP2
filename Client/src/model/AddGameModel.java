package model;

import Utility.UnnamedPropertyChangeSubject;
import viewModel.GameMenuViewModel;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;

public interface AddGameModel extends UnnamedPropertyChangeSubject {
  void clientAddGame(Game game) throws RemoteException, SQLException;
  void validateGame(String name, String type, String releaseYear,
      String availablePeriod, boolean needsDeposit) throws RemoteException, SQLException;

//  void addGame(Game game) throws RemoteException;
//  void validateGame(String name, String type, String releaseYear,
//      LocalDate rentalFrom, LocalDate rentalTo, String availablePeriod,
//      boolean needsDeposit) throws RemoteException;
}
