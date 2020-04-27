package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewModel.GameMenuViewModel;

import java.rmi.RemoteException;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GameMenuController
{
  @FXML DatePicker availableTo;
  @FXML DatePicker availableFrom;
  @FXML DatePicker rentalFrom;
  @FXML DatePicker rentalTo;
  @FXML TextField name;
  @FXML TextField type;
  @FXML TextField releaseYear;
  @FXML CheckBox deposit;

  private Region root;
  private ViewHandler viewHandler;
  private GameMenuViewModel gameMenuViewModel;

  public void init(ViewHandler viewHandler, GameMenuViewModel gameMenuViewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.root = root;
    this.gameMenuViewModel = gameMenuViewModel;
    this.name.setText(gameMenuViewModel.getName().get());
    this.type.setText(gameMenuViewModel.getType().get());
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset()
  {
    name.clear();
    type.clear();
    releaseYear.clear();
    deposit.setSelected(false);
    availableTo.setValue(null);
    availableFrom.setValue(null);
    rentalFrom.setValue(null);
    rentalTo.setValue(null);
  }

  @FXML public void onReset()
  {
    reset();
  }

  @FXML public void onSubmit() throws RemoteException {
    //public void addGame(String name, String type, String releaseYear, Calendar rentalFrom, Calendar rentalTo, Calendar availableFrom, Calendar availableTo, boolean needsDeposit)
  gameMenuViewModel.addGame(name.getText(), type.getText(), releaseYear.getText(), rentalFrom.getValue(), rentalTo.getValue(), availableFrom.getValue(), availableTo.getValue(), deposit.isSelected());
    viewHandler.openView("user");
  }

  public void onBack(ActionEvent actionEvent) throws RemoteException {
    viewHandler.openView("user");
  }
}
