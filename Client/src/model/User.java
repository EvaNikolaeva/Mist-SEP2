package model;

public class User {
    private String username;
    private int userID;
private GameList gameList;
    public User(String username, int userID){
        this.userID = userID;
        this.username = username;
        this.gameList = new GameList();
    }
    public int getUserID(){
        return userID;
    }
    public String getUsername() {
        return username;
    }
    public GameList getGames(){
        return gameList;
    }
}
