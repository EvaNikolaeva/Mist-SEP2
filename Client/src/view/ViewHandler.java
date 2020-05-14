package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.ViewModelFactory;

import java.io.IOException;
import java.rmi.RemoteException;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;

  private ViewModelFactory viewModelFactory;
  private GameListController gameListController;
  private GameMenuController gameMenuController;
  private MyProfileController myProfileController;
  private EditProfileController editProfileController;
  private LoadingScreenController loadingScreenController;
  private OtherProfileController otherProfileController;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
  }

  public void start(Stage primaryStage) throws RemoteException
  {
    this.primaryStage = primaryStage;
    this.currentScene = new Scene(new Region());
    openView("loading");
  }

  public void openView(String id) throws RemoteException
  {
    Region root = null;
    switch (id)
    {
      case "list":
        root = loadGameListView("GameList.fxml");
        break;
      case "menu":
        root = loadGameMenuView("GameMenu.fxml");
        break;
      case "profile":
        root = loadMyProfileMenuView("MyProfile.fxml");
        break;
      case "editBio":
        root = loadEditProfileController("EditProfile.fxml");
        break;
      case "loading":
        root = loadLoadingController("LoadingScreen.fxml");
        break;
      case "other":
        root = loadOtherProfileController("OtherProfile.fxml");
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

  private Region loadGameListView(String fxmlFile) throws RemoteException
  {
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

  private Region loadMyProfileMenuView(String fxmlFile) throws RemoteException
  {
    if (myProfileController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        myProfileController = loader.getController();
        myProfileController
            .init(this, viewModelFactory.myProfileViewModel(), root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      myProfileController.reset();
    }
    return myProfileController.getRoot();
  }

  private Region loadEditProfileController(String fxmlFile)
      throws RemoteException
  {
    if (editProfileController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        editProfileController = loader.getController();
        editProfileController
            .init(this, viewModelFactory.editProfileViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
    }
    return editProfileController.getRoot();
  }

  private Region loadLoadingController(String fxmlFile) throws RemoteException
  {
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
      loadingScreenController.reset();
    }
    return loadingScreenController.getRoot();
  }

  private Region loadOtherProfileController(String fxmlFile) throws RemoteException
  {
    if (otherProfileController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        otherProfileController = loader.getController();
        otherProfileController
            .init(this, viewModelFactory.otherProfileViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      otherProfileController.reset();
    }
    return otherProfileController.getRoot();
  }

}
