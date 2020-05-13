import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewModel.*;

public class MyApplication extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    Model model = new ModelManager();
    GameMenuViewModel gameMenuViewModel = new GameMenuViewModel(model);
    GameListViewModel gameListViewModel = new GameListViewModel(model);
    LoadingScreenViewModel loadingScreenViewModel = new LoadingScreenViewModel(model);
    EditProfileViewModel editProfileViewModel = new EditProfileViewModel(model);
    MyProfileViewModel myProfileViewModel = new MyProfileViewModel(model);
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);

    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(stage);
//    stage.setOnCloseRequest((WindowEvent event1) -> {
//      model.removeUser();
//    });
  }
//  public void stop(){
//    Platform.exit();
//    System.exit(0);
//  }
}
