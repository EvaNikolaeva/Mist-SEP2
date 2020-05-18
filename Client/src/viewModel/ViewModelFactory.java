package viewModel;

import model.Model;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ViewModelFactory
{
  private GameListViewModel gameListViewModel;
  private GameMenuViewModel gameMenuViewModel;
  private MyProfileViewModel myProfileViewModel;
  private EditProfileViewModel editProfileViewModel;
  private LoginViewModel loginViewModel;
  private OtherProfileViewModel otherProfileViewModel;

  public ViewModelFactory(Model model)
      throws RemoteException, InterruptedException, NotBoundException,
      MalformedURLException
  {
    this.gameListViewModel = new GameListViewModel(model);
    this.gameMenuViewModel = new GameMenuViewModel(model);
    this.myProfileViewModel = new MyProfileViewModel(model);
    this.editProfileViewModel = new EditProfileViewModel(model);
    this.loginViewModel = new LoginViewModel(model);
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

  public LoginViewModel loadingScreenViewModel()
  {
    return loginViewModel;
  }

  public OtherProfileViewModel otherProfileViewModel()
  {
    return otherProfileViewModel;
  }

}
