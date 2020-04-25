package view;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.DateInterval;
import model.Game;
import viewModel.GameMenuViewModel;

public class GameMenuController
{
  @FXML TextField name;
  @FXML TextField type;
  @FXML TextField releaseYear;
  @FXML TextField rentalPeriod;
  @FXML TextField availabilityPeriod;
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
    rentalPeriod.clear();
    availabilityPeriod.clear();
    deposit.setSelected(false);
  }

  @FXML public void onReset()
  {
    reset();
  }

  @FXML public void onSubmit()
  {
    this.name.setText(gameMenuViewModel.getName().get());
    this.type.setText(gameMenuViewModel.getType().get());
    this.releaseYear.setText(gameMenuViewModel.getReleaseYear().get());
    this.rentalPeriod.setText(gameMenuViewModel.getRentalPeriod().get());
    this.availabilityPeriod.setText(gameMenuViewModel.getAvailabilityPeriod().get());
    this.deposit.setSelected(gameMenuViewModel.getCheckBox().get());
    viewHandler.openView("user");
  }

}
