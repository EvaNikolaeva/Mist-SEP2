package viewModel;

import model.Model;

public class ViewModelFactory
{
  private GameListViewModel gameListViewModel;
  private GameMenuViewModel gameMenuViewModel;

  public ViewModelFactory(Model model)
  {
    this.gameListViewModel = new GameListViewModel(model);
    this.gameMenuViewModel = new GameMenuViewModel(model);
  }

  public GameMenuViewModel gameMenuViewModel()
  {
    return gameMenuViewModel;
  }

  public GameListViewModel gameListViewModel()
  {
    return gameListViewModel;
  }
}
