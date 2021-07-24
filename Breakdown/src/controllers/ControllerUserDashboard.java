package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerUserDashboard {
    @FXML
    Image userDashImage;
    @FXML
    Button addVehicleButton;
    @FXML
    Button viewVehicleButton;
    @FXML
    Button searchMechanicButton;
    @FXML
    Button feedbackHistoryButton;
    @FXML
    Button userLogoutButton;

    public void setAddVehicleButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) addVehicleButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_addvehicle.fxml"));
        stage.setScene(new Scene(root, 420, 400));
        stage.show();
    }
    public void setviewVehicleButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage)viewVehicleButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_viewvehicles.fxml"));
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void setsearchMechanicButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage)searchMechanicButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_searchmechanic.fxml"));
        stage.setScene(new Scene(root, 600, 500));
        stage.show();
    }
    public void setfeedbackHistoryButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) feedbackHistoryButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_viewfeedback.fxml"));
        stage.setScene(new Scene(root, 444, 450));
        stage.show();
    }
    public void setuserLogoutButtonOnAction(ActionEvent event) throws IOException {
        Session.setUser(false);
        Session.setId(-1);
        Stage stage = (Stage) userLogoutButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ui_login.fxml"));
        stage.setScene(new Scene(root, 550, 400));
        stage.show();
    }

}
