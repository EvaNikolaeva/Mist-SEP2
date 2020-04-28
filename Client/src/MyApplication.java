import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewModel.GameListViewModel;
import viewModel.GameMenuViewModel;
import viewModel.UserGameListViewModel;
import viewModel.ViewModelFactory;

public class MyApplication extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    Model model = new ModelManager();
    GameMenuViewModel gameMenuViewModel = new GameMenuViewModel(model);
    GameListViewModel gameListViewModel = new GameListViewModel(model);
    UserGameListViewModel userGameListViewModel = new UserGameListViewModel(model);
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
