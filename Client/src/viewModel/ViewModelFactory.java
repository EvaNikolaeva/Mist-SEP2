package viewModel;

import model.Model;

public class ViewModelFactory
{
  private GameListViewModel gameListViewModel;
  private GameMenuViewModel gameMenuViewModel;
  private UserGameListViewModel userGameListViewModel;
  private LoadingScreenViewModel loadingScreenViewModel;

  public ViewModelFactory(Model model)
  {
    this.gameListViewModel = new GameListViewModel(model);
    this.gameMenuViewModel = new GameMenuViewModel(model);
    this.userGameListViewModel = new UserGameListViewModel(model);
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
public LoadingScreenViewModel loadingScreenViewModel(){
    return loadingScreenViewModel;
}
}
