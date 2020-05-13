package viewModel;

import model.Model;
import view.MyProfileController;

import java.rmi.RemoteException;

public class ViewModelFactory
{
  private GameListViewModel gameListViewModel;
  private GameMenuViewModel gameMenuViewModel;
  private MyProfileViewModel myProfileViewModel;
  private EditProfileViewModel editProfileViewModel;
  private LoadingScreenViewModel loadingScreenViewModel;
  private OtherProfileViewModel otherProfileViewModel;

  public ViewModelFactory(Model model) throws RemoteException
  {
    this.gameListViewModel = new GameListViewModel(model);
    this.gameMenuViewModel = new GameMenuViewModel(model);
    this.myProfileViewModel = new MyProfileViewModel(model);
    this.editProfileViewModel = new EditProfileViewModel(model);
    this.loadingScreenViewModel = new LoadingScreenViewModel(model);
    this.otherProfileViewModel = new OtherProfileViewModel(model);
  }

  public GameMenuViewModel gameMenuViewModel()
  {
    return gameMenuViewModel;
  }

  public GameListViewModel gameListViewModel()
  {
    return gameListViewModel;
  }

  public MyProfileViewModel myProfileViewModel()
  {
    return myProfileViewModel;
  }

  public EditProfileViewModel editProfileViewModel()
  {
    return editProfileViewModel;
  }

  public LoadingScreenViewModel loadingScreenViewModel()
  {
    return loadingScreenViewModel;
  }

  public OtherProfileViewModel otherProfileViewModel()
  {
    return otherProfileViewModel;
  }

}
