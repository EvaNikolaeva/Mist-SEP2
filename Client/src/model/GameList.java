package model;

import java.util.ArrayList;

public class GameList
{
    private ArrayList<Game> games;

    public GameList()
    {
        this.games = new ArrayList<>();
    }

    public void addGame(Game game)
    {
        games.add(game);
    }

    public void removeGame(int id)
    {
        for (int i = 0; i < games.size(); i++)
        {
            if (games.get(i).getId() == id)
                games.remove(games.get(i));
        }
    }

    public Game getGameById(int id)
    {
        for (int i = 0; i < games.size(); i++)
        {
            if (games.get(i).getId() == id)
                return games.get(i);
        }
        return null;
    }

    public Game getGame(int index)
    {
        return games.get(index);
    }

    public int size()
    {
        return games.size();
    }



}
