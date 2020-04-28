package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import viewModel.LoadingScreenViewModel;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class LoadingScreenController {
    private ViewHandler viewHandler;
    private LoadingScreenViewModel loadingScreenViewModel;
    private Region root;

    @FXML Label loadingLabel;

    public void init(ViewHandler viewHandler, LoadingScreenViewModel loadingScreenViewModel,
                     Region root) throws RemoteException, MalformedURLException, InterruptedException, NotBoundException {
        this.root = root;
        this.viewHandler = viewHandler;
        this.loadingScreenViewModel = loadingScreenViewModel;
    }

    public Region getRoot()
    {
        return root;
    }

    public void onConnectButton(ActionEvent actionEvent) throws RemoteException, MalformedURLException, InterruptedException, NotBoundException {
        loadingScreenViewModel.startConnection();
    }
}
