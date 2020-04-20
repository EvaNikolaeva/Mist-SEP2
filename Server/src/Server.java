import mediator.GameListServer;
import model.Model;
import model.ModelManager;

public class Server
{
    public static void main(String[] args)  {
        Model model = new ModelManager();
        GameListServer chatServer = new GameListServer(model);
    }
}
