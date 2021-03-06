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
    private ObservableList<Game> list;

    public MyProfileViewModel(Model model)
    {
        this.model = model;
        this.list = FXCollections.observableArrayList();
        model.addListener(this);
    }

    public ObservableList<Game> getList() throws RemoteException
    {
        GameList games = model.GetGameList();
        list.clear();
        for (int i = 0; i < games.size(); i++)
        {
            list.add(games.getGame(i));
        }
        return list;
    }

    public void removeGame(Game game) throws RemoteException
    {
        model.RemoveGame(game.getId());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        Platform.runLater(() -> list.add((Game) evt.getNewValue()));
    }

    //needs to be finished
}
