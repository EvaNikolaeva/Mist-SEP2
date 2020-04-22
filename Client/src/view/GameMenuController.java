package view;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
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

  public void init(ViewHandler viewHandler,
      GameMenuViewModel gameMenuViewModel, Region root)
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
    viewHandler.openView("user");
  }

}
