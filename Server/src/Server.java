import mediator.GameListServer;
import model.*;

import java.rmi.RemoteException;

public class Server
{
  public static void main(String[] args) throws RemoteException
  {
    Model model = new ModelManager();
    GameListServer chatServer = new GameListServer(model);
    DateInterval dateInterval = new DateInterval(12, 12, 12, 12);
    Game game1 = new Game("Doom Eternal", "PC", 2020, false, dateInterval, 10,
        421421);
    Game game2 = new Game("Doom  GOT", "PC", 2020, false, dateInterval, 10,
        4214231);

    model.registerUser("admin", "1111");
    model.registerUser("user1", "1222");
    model.registerUser("user2", "1333");
    model.registerUser("user3", "1444");

    model.addGame(game1.getUserID(), game1);
    model.addGame(game2.getUserID(), game2);

  }
}
