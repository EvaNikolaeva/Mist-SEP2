package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.UserGameListViewModel;
import viewModel.ViewModelFactory;

import java.rmi.RemoteException;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;

  private ViewModelFactory viewModelFactory;
  private GameListController gameListController;
  private GameMenuController gameMenuController;
  private UserGameListController userGameListController;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
  }

  public void start(Stage primaryStage) throws RemoteException {
    this.primaryStage = primaryStage;
    this.currentScene = new Scene(new Region());
    openView("list");
  }

  public void openView(String id) throws RemoteException {
    Region root = null;
    switch (id)
    {
      case "list":
        root = loadGameListView("GameList.fxml");
        break;
      case "menu":
        root = loadGameMenuView("GameMenu.fxml");
        break;
      case "user":
        root = loadUserGameListView("UserGameList.fxml");
        break;
    }
    currentScene.setRoot(root);

    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  private Region loadGameListView(String fxmlFile) throws RemoteException {
    if (gameListController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        gameListController = loader.getController();
        gameListController
            .init(this, viewModelFactory.gameListViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      gameListController.reset();
    }
    return gameListController.getRoot();
  }

  private Region loadGameMenuView(String fxmlFile)
  {
    if (gameMenuController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        gameMenuController = loader.getController();
        gameMenuController
            .init(this, viewModelFactory.gameMenuViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      gameMenuController.reset();
    }
    return gameMenuController.getRoot();
  }

  private Region loadUserGameListView(String fxmlFile) throws RemoteException {
    if (userGameListController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        userGameListController = loader.getController();
        userGameListController
            .init(this, viewModelFactory.userGameListViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      userGameListController.reset();
    }
    return userGameListController.getRoot();
  }
}
