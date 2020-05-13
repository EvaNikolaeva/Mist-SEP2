package viewModel;

import model.Model;
import view.MyProfileController;

public class ViewModelFactory
{
  private GameListViewModel gameListViewModel;
  private GameMenuViewModel gameMenuViewModel;
  private UserGameListViewModel userGameListViewModel;
  private MyProfileViewModel myProfileViewModel;
  private EditProfileViewModel editProfileViewModel;
  private LoadingScreenViewModel loadingScreenViewModel;

  public ViewModelFactory(Model model)
  {
    this.gameListViewModel = new GameListViewModel(model);
    this.gameMenuViewModel = new GameMenuViewModel(model);
    this.userGameListViewModel = new UserGameListViewModel(model);
    this.myProfileViewModel = new MyProfileViewModel(model);
    this.editProfileViewModel = new EditProfileViewModel(model);
    this.loadingScreenViewModel = new LoadingScreenViewModel(model);
  }

  public GameMenuViewModel gameMenuViewModel()
  {
    return gameMenuViewModel;
  }

  public GameListViewModel gameListViewModel()
  {
    return gameListViewModel;
  }

  public UserGameListViewModel userGameListViewModel()
  {
    return userGameListViewModel;
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

}
