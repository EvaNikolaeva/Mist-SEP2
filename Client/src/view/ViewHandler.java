package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import viewModel.UserGameListViewModel;
import viewModel.ViewModelFactory;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;

  private ViewModelFactory viewModelFactory;
  private GameListController gameListController;
  private GameMenuController gameMenuController;
  private UserGameListController userGameListController;
  private LoadingScreenController loadingScreenController;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
  }

  public void start(Stage primaryStage) throws RemoteException, InterruptedException, NotBoundException, MalformedURLException {
    this.primaryStage = primaryStage;
    this.currentScene = new Scene(new Region());
    openView("loading");
  }

  public void openView(String id) throws RemoteException, InterruptedException, NotBoundException, MalformedURLException {
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
      case "loading":
        root = loadLoadingScreen("LoadingScreen.fxml");
        break;
    }
    currentScene.setRoot(root);

    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.initStyle(StageStyle.UNDECORATED);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  private Region loadLoadingScreen(String fxmlFile){
    if (loadingScreenController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        loadingScreenController = loader.getController();
        loadingScreenController
                .init(this, viewModelFactory.loadingScreenViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
    }
    return loadingScreenController.getRoot();
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
