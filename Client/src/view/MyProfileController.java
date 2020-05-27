package view;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import model.Game;
import model.Rental;
import model.User;
import viewModel.MyProfileViewModel;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class MyProfileController {
    @FXML
    ListView<Game> ownedGames;
    @FXML
    ListView<Rental> rentals;
    @FXML
    ListView<Game> rentedGames;
    @FXML
    ListView<Rental> pendingRentals;
    @FXML
    Label bio;
    @FXML
    Label username;
    private MyProfileViewModel myProfileViewModel;
    private Region root;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler,
                     MyProfileViewModel myProfileViewModel, Region root)
            throws RemoteException, InterruptedException, NotBoundException,
            MalformedURLException, SQLException {
        this.viewHandler = viewHandler;
        this.root = root;

        this.myProfileViewModel = myProfileViewModel;

        Platform.runLater(() -> {
            this.username.textProperty().bind(myProfileViewModel.getUsername());
          try {
            this.rentedGames.setItems(myProfileViewModel.getRentedGames());
          } catch (RemoteException e) {
            e.printStackTrace();
          } catch (SQLException e) {
            e.printStackTrace();
          }
          try {
            this.pendingRentals.setItems(myProfileViewModel.getPendingRentals());
          } catch (RemoteException e) {
            e.printStackTrace();
          } catch (SQLException e) {
            e.printStackTrace();
          }
          try {
            this.ownedGames.setItems(myProfileViewModel.getOwnedGames());
          } catch (RemoteException e) {
            e.printStackTrace();
          } catch (SQLException e) {
            e.printStackTrace();
          }
          try {
            this.rentals.setItems(myProfileViewModel.getRentals());
          } catch (RemoteException e) {
            e.printStackTrace();
          } catch (SQLException e) {
            e.printStackTrace();
          }
          try {
            this.bio.textProperty().bind(myProfileViewModel.getBio());
          } catch (RemoteException e) {
            e.printStackTrace();
          } catch (SQLException e) {
            e.printStackTrace();
          }
        });
    }

    public Region getRoot() {
        return root;
    }

    public void reset()
            throws RemoteException, InterruptedException, NotBoundException,
            MalformedURLException, SQLException {
        this.ownedGames.setItems(myProfileViewModel.getOwnedGames());
        this.rentals.setItems(myProfileViewModel.getRentals());
        this.rentedGames.setItems(myProfileViewModel.getRentedGames());
        this.bio.textProperty().bind(myProfileViewModel.getBio());
        this.pendingRentals.setItems(myProfileViewModel.getPendingRentals());
    }

    @FXML
    public void onAddGame()
            throws RemoteException, InterruptedException, NotBoundException,
            MalformedURLException, SQLException {
        viewHandler.openView("menu");
    }

    @FXML
    public void onBrowseGames()
            throws RemoteException, InterruptedException, NotBoundException,
            MalformedURLException, SQLException {
        viewHandler.openView("list");
    }

    @FXML
    public void onDelete() throws RemoteException, SQLException {
        if (ownedGames.getSelectionModel().getSelectedIndex() < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "You have to select a game.", ButtonType.OK);
            alert.showAndWait();
            alert.close();
        } else {
            Game selectedGame = ownedGames.getSelectionModel().getSelectedItem();
            myProfileViewModel.removeGame(selectedGame);
            int index = ownedGames.getSelectionModel().getSelectedIndex();
            if (ownedGames.getSelectionModel().getSelectedItem() == null) {
                this.myProfileViewModel.getOwnedGames()
                        .remove(index); //ok java you kinky
            }
            this.myProfileViewModel.getOwnedGames().clear();
            this.ownedGames.setItems(myProfileViewModel.getOwnedGames());
        }
    }

    @FXML
    public void onAccept()
            throws RemoteException, InterruptedException, NotBoundException,
            MalformedURLException, SQLException {
//        Rental selectedRental = rentals.getSelectionModel().getSelectedItem();
//        myProfileViewModel.acceptGame(selectedRental);
//        Platform.runLater(() -> {
//            try {
//                reset();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        });
        if (rentals.getSelectionModel().getSelectedIndex() < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "You have to select a incoming trade.", ButtonType.OK);
            alert.showAndWait();
            alert.close();
        } else {
            Rental selectedRental = rentals.getSelectionModel().getSelectedItem();
            myProfileViewModel.acceptGame(selectedRental);
            int index = rentals.getSelectionModel().getSelectedIndex();
            rentals.setItems(myProfileViewModel.getPendingRentals());
        }
    }

    @FXML
    public void onDecline() throws RemoteException, SQLException, InterruptedException, NotBoundException, MalformedURLException {
        if (rentals.getSelectionModel().getSelectedIndex() < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "You have to select a game.", ButtonType.OK);
            alert.showAndWait();
            alert.close();
        } else {
            //CATA: i think this needs to happen in two sides
            Rental selectedRental = rentals.getSelectionModel().getSelectedItem();
            int index = rentals.getSelectionModel().getSelectedIndex();
            if (index == 0) {
                myProfileViewModel.declineGame(selectedRental);
            }
            this.myProfileViewModel.getRentals().clear();
            this.rentals.setItems(myProfileViewModel.getRentals());
        }
    }

    @FXML
    public void onEdit()
            throws RemoteException, InterruptedException, NotBoundException,
            MalformedURLException, SQLException {
        viewHandler.openView("editBio");
    }

    @FXML
    public void onSetAvailable() throws RemoteException, InterruptedException, NotBoundException, MalformedURLException, SQLException {
        if (ownedGames.getSelectionModel().getSelectedIndex() < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "You have to select a game.", ButtonType.OK);
            alert.showAndWait();
            alert.close();
        } else if (ownedGames.getSelectionModel().getSelectedItem().getAvailable()) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "You have to select a game that is not available", ButtonType.OK);
            alert.showAndWait();
            alert.close();
        } else {
            myProfileViewModel.setGameAvailable(ownedGames.getSelectionModel().getSelectedItem());
            reset();
        }
    }


    @FXML
    public void onDeleteUser() throws RemoteException, SQLException, InterruptedException, NotBoundException, MalformedURLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to proceed?", ButtonType.YES);
        alert.showAndWait();
        alert.close();
        if (alert.getResult() == ButtonType.YES)
        {
        User selectedUser = myProfileViewModel.getUser();
        myProfileViewModel.removeUser(selectedUser);
        viewHandler.openView("loading");
        }
    }
}
