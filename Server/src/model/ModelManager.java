package model;

import utility.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
    private PropertyChangeSupport property;
    private GameList list;

    public ModelManager()
    {
        this.list = new GameList();
        this.property = new PropertyChangeSupport(this);
    }

    @Override
    public void AddGame(Game game)
    {
        list.addGame(game);
    }

    @Override
    public void RemoveGame(int id)
    {
        list.removeGame(id);
    }

    @Override
    public GameList GetGameList()
    {
        return list;
    }

    @Override
    public void addListener(PropertyChangeListener listener)
    {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener)
    {
        property.removePropertyChangeListener(listener);
    }
}
