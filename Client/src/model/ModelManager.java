package model;

import mediator.GameListClient;

import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
    private GameListClient client;

    public ModelManager()
    {
        this.client = new GameListClient(this);
    }

    @Override
    public void AddGame(Game game)
    {
        client.addGame(game);
    }

    @Override
    public void RemoveGame(int id)
    {
        client.removeGame(id);
    }

    @Override
    public GameList GetGameList()
    {
        return client.getGameList();
    }
}
