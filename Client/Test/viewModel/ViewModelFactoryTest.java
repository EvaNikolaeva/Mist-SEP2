package viewModel;

import model.Model;
import model.ModelManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class ViewModelFactoryTest
{
  private GameListViewModel gameListViewModel;
  private GameMenuViewModel gameMenuViewModel;
  private UserGameListViewModel userGameListViewModel;
  private Model model;

  @BeforeEach void setUp()
      throws RemoteException, MalformedURLException, InterruptedException,
      NotBoundException
  {
//    model = new ModelManager();
    gameListViewModel = new GameListViewModel(model);
    gameMenuViewModel = new GameMenuViewModel(model);
    userGameListViewModel = new UserGameListViewModel(model);
  }

    //you can't really test this because of networking

  @Test void ViewModelFactory()
  {
    assertNotNull(gameListViewModel);
    assertNotNull(gameMenuViewModel);
    assertNotNull(userGameListViewModel);
  }

  @Test void gameMenuViewModel()
  {
  }

  @Test void gameListViewModel()
  {
  }

  @Test void userGameListViewModel()
  {
  }
}