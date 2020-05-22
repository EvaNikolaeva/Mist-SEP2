package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.Game;
import viewModel.GameListViewModel;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class GameListController {

    @FXML
    ListView<Game> availableGames;
    private Region root;
    private ViewHandler viewHandler;
    private GameListViewModel gameListViewModel;

    public void init(ViewHandler viewHandler, GameListViewModel gameListViewModel,
                     Region root) throws RemoteException, SQLException {
        this.root = root;
        this.viewHandler = viewHandler;
        this.gameListViewModel = gameListViewModel;
        this.availableGames.setItems(gameListViewModel.getAvailableGames());
    }

    public Region getRoot() {
        return root;
    }

    public void reset() throws RemoteException, SQLException {
        this.availableGames.setItems(gameListViewModel.getAvailableGames());
    }

    @FXML
    public void onMyProfile()
            throws RemoteException, InterruptedException, NotBoundException,
            MalformedURLException, SQLException {
        viewHandler.openView("profile");
    }

    @FXML
    public void onAddGame()
            throws RemoteException, InterruptedException, NotBoundException,
            MalformedURLException, SQLException {
        viewHandler.openView("menu");
    }

    @FXML
    public void requestGame() throws RemoteException, SQLException {
        if (availableGames.getSelectionModel().getSelectedIndex() < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "You have to select a game.", ButtonType.OK);
            alert.showAndWait();
            alert.close();
        }
        else if(!availableGames.getSelectionModel().getSelectedItem().getAvailable()){
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "You have to select a available game", ButtonType.OK);
            alert.showAndWait();
            alert.close();
        }
        else {
          gameListViewModel.setGameBuffer(availableGames.getSelectionModel().getSelectedItem());
            gameListViewModel.requestTrade(availableGames.getSelectionModel().getSelectedItem());
            reset();
        }
    }

    @FXML
    public void onOtherProfile()
            throws RemoteException, InterruptedException, NotBoundException,
            MalformedURLException, SQLException {
        if (availableGames.getSelectionModel().getSelectedIndex() < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "You have to select a game.", ButtonType.OK);
            alert.showAndWait();
            alert.close();
        } else {
          gameListViewModel.setGameBuffer(availableGames.getSelectionModel().getSelectedItem());
            viewHandler.openView("other");
        }
    }
}
