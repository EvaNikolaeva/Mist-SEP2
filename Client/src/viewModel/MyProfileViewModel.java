package viewModel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Game;
import model.GameList;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

public class MyProfileViewModel implements PropertyChangeListener
{
    private Model model;
    private ObservableList<Game> incomingTradeList;
    private ObservableList<Game> owned;
    private ObservableList<Game> forTrade;
    private ObservableList<Game> rented;

    public MyProfileViewModel(Model model)
    {
        this.model = model;
        this.incomingTradeList = FXCollections.observableArrayList();
        this.owned = FXCollections.observableArrayList();
        this.forTrade = FXCollections.observableArrayList();            //these need implementation
        this.rented = FXCollections.observableArrayList();
        model.addListener(this);
    }

    public ObservableList<Game> getList() throws RemoteException
    {
        GameList games = model.GetGameList();
        incomingTradeList.clear();
        for (int i = 0; i < games.size(); i++)
        {
            incomingTradeList.add(games.getGame(i));
        }
        return incomingTradeList;
    }

    public void removeGame(Game game) throws RemoteException
    {
        model.RemoveGame(game.getId());
    }

    public void acceptGame(Game game, int userID) throws RemoteException
    {
        model.acceptTrade(game, userID);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        Platform.runLater(() -> incomingTradeList.add((Game) evt.getNewValue()));
    }

}
