package viewModel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Game;
import model.GameList;
import model.GameListModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class GameListViewModel implements PropertyChangeListener {
    private GameListModel model;
    private ObservableList<Game> availableGames;

    public GameListViewModel(GameListModel model) {
        this.model = model;
        this.availableGames = FXCollections.observableArrayList();
        model.addListener(this);
    }

    public ObservableList<Game> getAvailableGames() throws RemoteException, SQLException {
        GameList gameList = model.getAllGamesFromServer();
        availableGames.clear();
        for (int i = 0; i < gameList.size(); i++) {
            if(!(gameList.getGame(i).getId() == -1)){
                availableGames.add(gameList.getGame(i));
            }
        }
        GameList gameListUnavailable = model.getAllGamesFromServer();
        return availableGames;
    }
    public void addGameToGameList(Game game) throws RemoteException, SQLException {
        availableGames.add(game);
    }
    public void removeGameFromGameList(Game game) throws RemoteException, SQLException {
for(int i = 0; i<availableGames.size() ; i++){
    if (availableGames.get(i).getTitle().equals(game.getTitle()) && game.getType().equals(availableGames.get(i).getType()) && availableGames.get(i).getUserId() == game.getUserId()) {
        availableGames.remove(i);
    }
}
    }

    public void requestTrade(Game game) throws RemoteException, SQLException {
        model.clientRequestGame(model.login(model.getUsername(), model.getPassword()), game);
    }

    public void setGameBuffer(Game game) throws RemoteException {
        model.setGameBuffer(game);
    }
    public void updateGameAvailability(Game game){
        for(int i = 0; i<availableGames.size() ; i++){
            if (availableGames.get(i).getId() == game.getId()) {
                availableGames.remove(i);
                availableGames.add(game);
            }
        }
    }
    @Override public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            switch (evt.getPropertyName()) {
            case "gameAdded":
                try {
                    addGameToGameList((Game) evt.getNewValue());
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "gameRemoved":
                try {
                    removeGameFromGameList((Game) evt.getNewValue());
                    System.out.println("trying to remove");
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();

                }
                break;
                case "gameAvailabilityChange":
                    System.out.println("Updating availability");
                    updateGameAvailability((Game) evt.getNewValue());
        }});

    }
    }