import mediator.GameListServer;
import mediator.ThreadSafeServer;
import model.*;

import java.rmi.RemoteException;

public class Server
{
  public static void main(String[] args) throws RemoteException
  {
    Model model = new ModelManager();
    ThreadSafeServer server = new ThreadSafeServer();
    GameListServer gameListServer = new GameListServer(model, server);


    model.registerUser("admin", "1111");
    model.registerUser("user1", "1222");
    model.registerUser("user2", "1333");
    model.registerUser("user3", "1444");

    Game game1 = new Game("Doom Eternal", "PC", 2020, false, 10,
            model.getUserByCredentials("admin", "1111").getUserID());
    Game game2 = new Game("Doom  GOT", "PC", 2020, false, 10,
            model.getUserByCredentials("user1", "1222").getUserID());

    model.addGame(game1);
    model.addGame(game2);
  }
}
