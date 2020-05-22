package viewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Game;
import model.GameList;
import model.GameListModel;
import model.Model;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class GameListViewModel {
    private GameListModel model;
    private ObservableList<Game> availableGames;

    public GameListViewModel(GameListModel model) {
        this.model = model;
        this.availableGames = FXCollections.observableArrayList();
    }

    public ObservableList<Game> getAvailableGames() throws RemoteException, SQLException {
        GameList gameList = model.getAllGamesFromServer();
        availableGames.clear();
        for (int i = 0; i < gameList.size(); i++) {
            availableGames.add(gameList.getGame(i));
        }
        return availableGames;
    }

    public void requestTrade(Game game) throws RemoteException, SQLException {
        model.clientRequestGame(model.login(model.getUsername(), model.getPassword()), game);
    }

    public void setGameBuffer(Game game) throws RemoteException {
        model.setGameBuffer(game);
    }

}
