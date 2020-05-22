import javafx.application.Application;
import javafx.stage.Stage;
import mediator.GameListClient;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewModel.*;

public class MyApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }
        GameListClient gameListClient = new GameListClient();
        Model model = new ModelManager(gameListClient);
        GameMenuViewModel gameMenuViewModel = new GameMenuViewModel(model);
        GameListViewModel gameListViewModel = new GameListViewModel(model);
        LoginViewModel loginViewModel = new LoginViewModel(model);

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
