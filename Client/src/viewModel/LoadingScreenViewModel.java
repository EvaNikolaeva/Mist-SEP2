package viewModel;

import model.Model;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LoadingScreenViewModel {
    private Model model;
    public LoadingScreenViewModel(Model model){
        this.model = model;
    }
    public void startConnection() throws RemoteException, MalformedURLException, InterruptedException, NotBoundException {
        model.connect();
    }
}
