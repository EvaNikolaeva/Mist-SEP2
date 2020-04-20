package view;

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
    name.set("");
    type.set("");
    releaseYear.set("");
    rentalPeriod.set("");
    availabilityPeriod.set("");
    deposit.setSelected(false);
  }

  @FXML public void onReset()
  {
    reset();
  }

  @FXML public void onSubmit()
  {
    //needs viewModel done
  }

}
