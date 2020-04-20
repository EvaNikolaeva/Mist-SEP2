package model;

public interface Model
{
    void AddGame(Game game);
    void RemoveGame(int id);
    GameList GetGameList();
}
