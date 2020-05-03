package viewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Game;
import model.Model;
import model.ModelManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class UserGameListViewModelTest
{
  private Model model;
  private ObservableList<Game> gameObservableList;
  private UserGameListViewModel userGameListViewModel;

  @BeforeEach void setUp()
      throws RemoteException, MalformedURLException, InterruptedException,
      NotBoundException
  {
    this.model = new ModelManager();
    this.gameObservableList = FXCollections.observableArrayList();
    userGameListViewModel = new UserGameListViewModel(model);
  }

  @Test void UserGameListView()
  {
    assertNotNull(model);
    assertNotNull(gameObservableList);
  }

  //can not test because of the networking

  @Test void getList() throws RemoteException
  {
    assertEquals(gameObservableList, userGameListViewModel.getList());
  }

  @Test void removeGame()
  {
  }
}