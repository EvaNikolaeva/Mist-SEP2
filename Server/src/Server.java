import mediator.GameListServer;
import model.DateInterval;
import model.Game;
import model.Model;
import model.ModelManager;

public class Server
{
    public static void main(String[] args)  {
        Model model = new ModelManager();
        GameListServer chatServer = new GameListServer(model);
        DateInterval dateInterval = new DateInterval(12, 12,12, 12);
        Game game1 = new Game("Doom Eternal", "PC", 2020, false, dateInterval, dateInterval, 123456);
        model.AddGame(game1);
    }
}
