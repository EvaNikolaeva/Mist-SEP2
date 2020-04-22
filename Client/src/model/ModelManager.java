package model;

import mediator.GameListClient;

import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
    private GameListClient client;
private User user;
    public ModelManager()
    {
        this.client = new GameListClient(this);
        this.user = new User("Testy", 123456);
    }
    @Override
    public void AddGame(Game game)
    {
        client.addGame(game);
        user.getGames().addGame(game);
    }

    @Override
    public void RemoveGame(int id)
    {
        client.removeGame(id);
        user.getGames().removeGame(id);
    }

    @Override
    public GameList GetGameList()
    {
        return client.getGameList();
    }
    @Override
    public GameList getUserGamesList(){
        return user.getGames();
    }
}
