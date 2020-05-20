package mediator;

import model.*;
import utility.observer.listener.GeneralListener;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class GameListServer implements GameListServerModel
{
    private Model model;
private ThreadSafeServer threadSafeServer;
    //  private PropertyChangeAction<GameList, RentalList> property;
    public GameListServer(Model model, ThreadSafeServer threadSafeServer) {
        this.model = model;
//    this.property= new PropertyChangeProxy<>(this, true);
        this.threadSafeServer = threadSafeServer;
        startServer();
    }

    private void startServer() {
        try {
            startRegistry();
            UnicastRemoteObject.exportObject(this, 1099);
            Naming.rebind("games", this);
            System.out.println("Starting server...");
            Registry registry = LocateRegistry.getRegistry();
            System.out.println(registry.lookup("games"));
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    private void startRegistry() {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);

            System.out.println("Registry started...");
        } catch (java.rmi.server.ExportException ex) {
            System.out.println("Registry already started? " + ex.getMessage());
        } catch (RemoteException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public GameList getAllGames() throws RemoteException {
        try{
            threadSafeServer.acquireRead();
            return model.getFullListOfGames();
        }
        finally {
            threadSafeServer.releaseRead();
        }
    }

    @Override
    public User getUserByGame(Game game) throws RemoteException {
        try{
            threadSafeServer.acquireRead();
            return model.getUserByGame(game);
        }
        finally {
            threadSafeServer.releaseRead();
        }
    }

    @Override
    public User getUserByCredentials(String username, String password) throws RemoteException {
       try{
           threadSafeServer.acquireRead();
           return model.getUserByCredentials(username, password);
       }
       finally {
           threadSafeServer.releaseRead();
       }
    }

    @Override
    public RentalList getRentalList() throws RemoteException {
        try{
            threadSafeServer.acquireRead();
            return model.getRentalList();
        }
        finally {
            threadSafeServer.releaseRead();
        }
    }

    @Override
    public void registerClient(String username, String password) throws RemoteException {
        try{
            threadSafeServer.acquireWrite();
            model.registerUser(username, password);
        }
        finally {
            threadSafeServer.releaseWrite();
        }
    }

    @Override
    public void removeGame(Game game) throws RemoteException {
        try{
            threadSafeServer.acquireWrite();
            model.removeGame(game);
        }
        finally {
            threadSafeServer.releaseWrite();
        }
    }

    @Override
    public void setBio(User user, String bio) throws RemoteException {
        try{
            threadSafeServer.acquireWrite();
            model.setUserBio(user, bio);
        }
        finally {
            threadSafeServer.releaseWrite();
        }
    }

    @Override
    public void addGame(Game game) throws RemoteException {
        try{
            threadSafeServer.acquireWrite();
            System.out.println("got write");
            model.addGame(game);
        }
        finally {
            threadSafeServer.releaseWrite();
            System.out.println("Released write");
        }
    }

    @Override
    public void requestGame(User requester, Game game) throws RemoteException {
        try{
            threadSafeServer.acquireWrite();
            model.requestGame(requester, game);
            System.out.println("game requested");
        }
        finally {
            threadSafeServer.releaseWrite();
        }
    }

    @Override
    public void acceptIncomingGame(Rental rental) throws RemoteException {
        try{
            threadSafeServer.acquireWrite();
            model.acceptGame(rental);
        }
        finally {
            threadSafeServer.releaseWrite();
        }
    }

    @Override
    public void declineIncomingGame(Rental rental) throws RemoteException {
        try{
            threadSafeServer.acquireWrite();
            model.declineGame(rental);
        }
        finally {
            threadSafeServer.releaseWrite();
        }
    }

    @Override
    public void setGameAvailableTrue(Game game) throws RemoteException {
        try{
            threadSafeServer.acquireWrite();
            model.setGameAvailableTrue(game);
        }
        finally {
            threadSafeServer.releaseWrite();
        }
    }

    @Override
    public boolean addListener(GeneralListener<GameList, RentalList> listener, String... propertyNames) throws RemoteException {
        return false;
    }

    @Override
    public boolean removeListener(GeneralListener<GameList, RentalList> listener, String... propertyNames) throws RemoteException {
        return false;
    }

//    @Override
//    public boolean addListener(GeneralListener<GameList, RentalList> listener, String... propertyNames) throws RemoteException {
//        return false;
//    }
//
//    @Override
//    public boolean removeListener(GeneralListener<GameList, RentalList> listener, String... propertyNames) throws RemoteException {
//        return false;
//    }


//  @Override public void registerClient(String username, String password)
//      throws RemoteException
//  {
//    this.model.registerUser(username,password);
//  }
//
//  @Override public User loginClient(String username, String password)
//      throws RemoteException
//  {
// try{
//   threadSafeServer.acquireRead();
//  return model.getUserByCredentials(username, password);
// }
// finally {
//   threadSafeServer.releaseRead();
// }
//  }
//
//  @Override public void removeGame(Game game) throws RemoteException
//  {
//    this.model.removeGame(game);
//    property.firePropertyChange("gameListUpdate", model.getFullListOfGames(), null);
//  }
//
//  @Override public void setBio(User user, String bio) throws RemoteException
//  {
//    this.model.setUserBio(user,bio);
//  }
//
//  @Override public void addGame(Game game) throws RemoteException
//  {
//    this.model.addGame(game);
//    property.firePropertyChange("gameListUpdate", model.getFullListOfGames(), null);
//  }
//
//  @Override public void requestGame(User requester,Game game) throws RemoteException
//  {
//    this.model.requestGame(requester, game);
//    property.firePropertyChange("rentalListUpdate", null, model.getRentalList());
//  }
//
//  @Override public void acceptIncomingGame(Rental rental) throws RemoteException
//  {
//    this.model.acceptGame(rental);
//    property.firePropertyChange("rentalListUpdate", null, model.getRentalList());
//  }
//
//  @Override public void declineIncomingGame(Rental rental)
//      throws RemoteException
//  {
//    this.model.declineGame(rental);
//    property.firePropertyChange("rentalListUpdate", null, model.getRentalList());
//  }
//
//  @Override public GameList getAllGames() throws RemoteException
//  {
//    return this.model.getFullListOfGames();
//  }
//
//  @Override public User getUserByGame(Game game) throws RemoteException
//  {
//    return this.model.getUserByGame(game);
//  }
//
//  @Override public User getUserByCredentials(String username, String password)
//      throws RemoteException
//  {
//    return this.model.getUserByCredentials(username, password);
//  }
//
//  @Override
//  public RentalList getRentalList() throws RemoteException {
//    return model.getRentalList();
//  }
//
//  @Override
//  public boolean addListener(GeneralListener<GameList, RentalList> listener, String... propertyNames) throws RemoteException {
//    return property.addListener(listener, propertyNames);
//  }
//
//  @Override
//  public boolean removeListener(GeneralListener<GameList, RentalList> listener, String... propertyNames) throws RemoteException {
//    return property.removeListener(listener, propertyNames);
//  }
}
