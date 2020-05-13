package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import viewModel.EditProfileViewModel;
import viewModel.GameListViewModel;

import java.rmi.RemoteException;

public class EditProfileController
{
  @FXML TextArea textArea;

  private Region root;

  private ViewHandler viewHandler;
  private EditProfileViewModel editProfileViewModel;

  public void init(ViewHandler viewHandler,
      EditProfileViewModel editProfileViewModel, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.editProfileViewModel = editProfileViewModel;
    this.textArea.setText("");
  }

  public Region getRoot()
  {
    return root;
  }

  public void reset()
  {
    this.textArea.setText("");
  }

  @FXML public void onCancel() throws RemoteException
  {
    viewHandler.openView("profile");
  }

  @FXML public void onSave() throws RemoteException
  {
    editProfileViewModel.editMessage(textArea.getText());
    viewHandler.openView("profile");
  }
}
