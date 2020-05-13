import mediator.GameListServer;
import model.*;

public class Server
{
  public static void main(String[] args)
  {
    Model model = new ModelManager();
    GameListServer chatServer = new GameListServer(model);
    DateInterval dateInterval = new DateInterval(12, 12, 12, 12);
    Game game1 = new Game("Doom Eternal", "PC", 2020, false, dateInterval, 10,
        123456);
    User user1 = new User("admin", 312421);
    User user2 = new User("test1", 12443123);
    User user3 = new User("test2", 421421);
    User user4 = new User("test3", 421421);

    model.addUser(user1);
    model.addUser(user2);
    model.addUser(user3);
    model.addUser(user4);

    model.AddGame(game1);
  }
}
