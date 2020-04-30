package view;

import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.DateInterval;
import model.Game;
import viewModel.GameMenuViewModel;

import java.rmi.RemoteException;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GameMenuController{
    @FXML
    TextField availabilityPeriod;
    @FXML
    DatePicker rentalFrom;
    @FXML
    DatePicker rentalTo;
    @FXML
    TextField name;
    @FXML
    TextField type;
    @FXML
    TextField releaseYear;
    @FXML
    CheckBox deposit;

    private Region root;
    private ViewHandler viewHandler;
    private GameMenuViewModel gameMenuViewModel;
    private StringProperty responseMessage;

    public void init(ViewHandler viewHandler, GameMenuViewModel gameMenuViewModel,
                     Region root) {
        this.viewHandler = viewHandler;
        this.root = root;
        this.gameMenuViewModel = gameMenuViewModel;
        this.name.textProperty().bindBidirectional(gameMenuViewModel.getName());
        this.type.textProperty().bindBidirectional(gameMenuViewModel.getType());
        this.rentalTo.valueProperty().bindBidirectional(gameMenuViewModel.getToDate());
        this.rentalFrom.valueProperty().bindBidirectional(gameMenuViewModel.getFromDate());
        this.releaseYear.textProperty().bindBidirectional(gameMenuViewModel.getReleaseYear());
        this.availabilityPeriod.textProperty().bindBidirectional(gameMenuViewModel.getAvailabilityPeriod());
        this.deposit.allowIndeterminateProperty().bindBidirectional(gameMenuViewModel.getCheckBox());
        this.responseMessage = gameMenuViewModel.getResponseMessage();
    }

    public Region getRoot() {
        return root;
    }

    public void reset() {
        name.clear();
        type.clear();
        releaseYear.clear();
        deposit.setSelected(false);
        availabilityPeriod.clear();
        rentalFrom.setValue(null);
        rentalTo.setValue(null);
    }

    @FXML
    public void onReset() {
        reset();
    }

    @FXML
    public void onSubmit() throws RemoteException {
        gameMenuViewModel.addCurrentGame();
        Platform.runLater(() -> {
            try {
                checkGame();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

    }
    public void checkGame() throws RemoteException {
        System.out.println(responseMessage);
        if(responseMessage.getValue().equals("Success")){
            viewHandler.openView("user");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR,responseMessage.getValue(),ButtonType.OK);
            alert.showAndWait();
            alert.close();
        }
    }

    public void onBack(ActionEvent actionEvent) throws RemoteException {
        viewHandler.openView("user");
    }
}
